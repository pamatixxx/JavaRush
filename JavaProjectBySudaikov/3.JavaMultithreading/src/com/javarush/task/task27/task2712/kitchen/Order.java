package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.util.List;

public class Order {
    protected List<Dish> dishes;
    private final Tablet tablet;

    public Order(Tablet tablet) throws Exception {
        this.tablet = tablet;
        this.dishes = ConsoleHelper.getAllDishesForOrder();
    }

  public int getTotalCookingTime() {
        int fullTimeCooking = 0;
        for (Dish dish : dishes) {
            fullTimeCooking += dish.getDuration();
        }
        return fullTimeCooking;
    }

  /*  public int getTotalCookingTime(){
        return dishes.stream().mapToInt((s)->s.getDuration()).sum();
    }*/

    public boolean isEmpty() {
        return dishes.isEmpty();
    }

    @Override
    public String toString() {
        if (dishes.isEmpty()) {
            return "";
        } else {
            return "Your order: " + dishes + " of " + tablet  ;
        }
    }
}
