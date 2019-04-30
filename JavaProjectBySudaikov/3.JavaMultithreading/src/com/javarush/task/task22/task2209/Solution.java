package com.javarush.task.task22.task2209;

import java.io.*;
import java.util.ArrayList;

/*
Составить цепочку слов
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader readFile = new BufferedReader(new FileReader(reader.readLine()));
        String[]file;
        StringBuffer buffer = new StringBuffer();
        String s = "";
        while ((s=readFile.readLine())!=null){
            buffer.append(s + " ");
        }
        file = buffer.toString().split(" ");
        StringBuilder result = getLine(file);
        System.out.println(result);
    }

    public static StringBuilder getLine(String... words) {
        StringBuilder sb = new StringBuilder();
        ArrayList<StringBuilder> list = new ArrayList<>();
        if (words.length==0||words==null)return sb;
        for (int i = 0; i<words.length;i++){
            if (!words[i].isEmpty()) list.add(new StringBuilder(words[i]));
        }
          return null;
    }
}
