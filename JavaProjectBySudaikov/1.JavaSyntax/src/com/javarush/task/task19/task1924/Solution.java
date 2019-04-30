package com.javarush.task.task19.task1924;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/*
Замена чисел
*/

public class Solution {
    public static Map<Integer, String> map = new HashMap<Integer, String>();
    static{
        map.put(0, "ноль");
        map.put(1, "один");
        map.put(2, "два");
        map.put(3, "три");
        map.put(4, "четыре");
        map.put(5, "пять");
        map.put(6, "шесть");
        map.put(7, "семь");
        map.put(8, "восемь");
        map.put(9, "девять");
        map.put(10, "десять");
        map.put(11, "одиннадцать");
        map.put(12, "двенадцать");
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader2 = new BufferedReader(new InputStreamReader(System.in));
        String name = reader2.readLine();
        reader2.close();
        FileReader reader = new FileReader(name);
        String result2 = "";
        while(reader.ready()){
            result2+=((char)reader.read()+"");
        }
        String[] result = result2.split(" ");
        int i=0;
        for(String a: result) {
            try {
                System.out.print(map.get(Integer.parseInt(a)));
                i++;
                if(i<result.length) System.out.print(" ");
            } catch (NumberFormatException j){
                System.out.print(a);
                i++;
                if(i<result.length) System.out.print(" ");
            }
        } reader.close();
    }
}
