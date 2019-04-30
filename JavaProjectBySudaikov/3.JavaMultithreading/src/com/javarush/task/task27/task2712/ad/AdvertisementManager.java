package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() throws NoVideoAvailableException {
        List<Advertisement> playlist = storage.list();
        if (playlist.isEmpty()) throw new NoVideoAvailableException();
        List<Advertisement> needShowVideo = new ArrayList<>();
        for (Advertisement advertisement : playlist) {
            if (advertisement.getDuration() <= timeSeconds && advertisement.getHits() > 0)
                needShowVideo.add(advertisement);
        }
        needShowVideo = getVideos(needShowVideo, 0);
        if (needShowVideo.isEmpty()) throw new NoVideoAvailableException();

        Collections.sort(needShowVideo, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                int result = Long.compare(o1.getAmountPerOneDisplaying(), o2.getAmountPerOneDisplaying());
                if (result == 0)
                    result = Long.compare(o1.getAmountPerOneDisplaying() / o1.getDuration(), o2.getAmountPerOneDisplaying() / o2.getDuration());
                return result;
            }
        });
        Collections.reverse(needShowVideo);
        for (Advertisement advertisement : needShowVideo) {
            ConsoleHelper.writeMessage(String.format("%s  is displaying... %d, %d", advertisement.getName(), advertisement.getAmountPerOneDisplaying(), advertisement.getAmountPerOneDisplaying() * 1000 / advertisement.getDuration()));
            advertisement.revalidate();
        }
    }

    public int summOfListMoney(List<Advertisement> list) {
        int summ = 0;
        for (Advertisement ad : list) {
            summ += ad.getAmountPerOneDisplaying();
        }
        return summ;
    }

    public int summOfTime(List<Advertisement> list) {
        int summ = 0;
        for (Advertisement advertisement : list) {
            summ += advertisement.getDuration();
        }
        return summ;
    }

    public List<Advertisement> getVideos(List<Advertisement> inList, int start) {
        List<Advertisement> storageList = storage.list();
        List<Advertisement> tmp;
        List<Advertisement> result = new ArrayList<>();

        int timeLeft = timeSeconds - summOfTime(inList);
        Advertisement advertisement;
        for (int i = start; i < storageList.size(); i++) {
            advertisement = storageList.get(i);
            if (!inList.contains(advertisement) && advertisement.getHits() > 0 && (advertisement.getDuration() <= timeLeft)) {
                inList.add(advertisement);
                result = checkWhoBetter(inList, getVideos(inList, i));
            }
        }

        return result;
    }


    public List<Advertisement> checkWhoBetter(List<Advertisement> check, List<Advertisement> tmp) {
        List<Advertisement> result = new ArrayList<>();
        if (summOfListMoney(check) > summOfListMoney(tmp)) result = check;
        if (summOfListMoney(check) < summOfListMoney(tmp)) result = tmp;
        if (summOfListMoney(check) == summOfListMoney(tmp)) {
            if (summOfTime(check) > summOfTime(tmp)) result = check;
            if (summOfTime(check) < summOfTime(tmp)) result = tmp;
            if (summOfTime(check) == summOfTime(tmp)) {
                if (check.size() > tmp.size()) result = check;
                else result = tmp;
            }
        }
        return result;
    }
}

