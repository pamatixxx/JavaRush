package com.javarush.task.task27.task2712.kitchen;

import java.lang.reflect.Array;
import java.util.Arrays;

public enum Dish {
    Fish(25), Steak(30), Soup(15), Juice(5), Water(3);
    private int duration;

    public int getDuration() {
        return duration;
    }

    Dish(int duration) {
        this.duration = duration;
    }

    public static String allDishesToString() {
        return Arrays.toString(Dish.values()).substring(1, Arrays.toString(Dish.values()).length() - 1);
    }
}

