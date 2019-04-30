package com.javarush.task.task18.task1828;

/*
Прайсы 2
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String FileName = reader.readLine();
        reader.close();
        String s;
        ArrayList<String> list = new ArrayList();
        BufferedReader rd = new BufferedReader(new FileReader(FileName));
        while ((s = rd.readLine()) != null) {
            list.add(s);
        }
        rd.close();
        if (args[0].equals("-u")){
            ArrayList<String>arr2 = new ArrayList<>();

            for (int i = 0;i<list.size();i++){
          //  arr2.get(list.get(i))}
        }


    }
}}

