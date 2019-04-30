package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {
    private static BufferedReader reader  = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }
    public static String readString() throws IOException {
       return reader.readLine();
    }
   public static List<Dish> getAllDishesForOrder() throws Exception{
        List<Dish> dishes = new ArrayList<>();
        ConsoleHelper.writeMessage("Выберите блюда. Для завершения введите 'exit'");
        ConsoleHelper.writeMessage(Dish.allDishesToString());
        while (true){
            String dishSelect =readString();
            if (dishSelect.equals("exit")){
                break;
            }
            if (dishSelect.isEmpty()){
                ConsoleHelper.writeMessage("Блюдо не выбрано");
                continue;
            }
            boolean Selected = false;
            for (Dish d : Dish.values())
            if (d.name().equalsIgnoreCase(dishSelect)) {
                dishes.add(d);
                Selected = true;
            }
            if (!Selected){
                ConsoleHelper.writeMessage("Нет такого блюда");
            }

        }return dishes;
   }

}
