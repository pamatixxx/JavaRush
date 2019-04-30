package com.javarush.task.task18.task1825;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Собираем файл
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> list = new ArrayList<>();

        while (true){
            String nameFile = reader.readLine();
            if (nameFile.equals("end"))
                break;
            list.add(nameFile);

        }
        reader.close();
        String name ="";
        name.substring(0,list.lastIndexOf("."));

        FileOutputStream out = new FileOutputStream(name);
    }
}
