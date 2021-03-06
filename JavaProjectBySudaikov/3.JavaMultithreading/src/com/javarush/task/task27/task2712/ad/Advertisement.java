package com.javarush.task.task27.task2712.ad;

public class Advertisement {
    private Object content; //  - видео
    private String name;   //- имя/название
    private long initialAmount; // - начальная сумма, стоимость рекламы в копейках. Используем long, чтобы избежать проблем с округлением
    private int hits;// - количество оплаченных показов
    private int duration;//- продолжительность в секундах
    private long amountPerOneDisplaying;

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public long getAmountPerOneDisplaying() {
        return amountPerOneDisplaying;
    }

    public void revalidate() {
        if (hits <= 0) throw new UnsupportedOperationException();
        else hits--;
    }



    public Advertisement(Object content, String name, long initialAmount, int hits, int duration) {
        this.content = content;
        this.name = name;
        this.initialAmount = initialAmount;
        this.hits = hits;
        this.duration = duration;
        this.amountPerOneDisplaying = initialAmount / hits;
    }
    @Override
    public String toString() {
        return name + " is displaying... " + getAmountPerOneDisplaying() + ", " + getAmountPerOneDisplaying()/duration*1000;
    }
    public int getHits() {
        return hits;
    }
}
