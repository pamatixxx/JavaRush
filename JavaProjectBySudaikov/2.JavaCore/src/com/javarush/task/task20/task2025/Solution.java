package com.javarush.task.task20.task2025;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
Алгоритмы-числа
*/
public class Solution {
    private static  long N  ;
    private static List<Long> longlist ;
    private static long[][]power;
    private static int number;
    private static int[] digitmult = new int[10];


    public static long[] getNumbers(long N) {
        Solution.N = N;
        long[] result = null;
        longlist = new ArrayList<>();


        int length = 0;
        long tmp = N;
        while (tmp>0){
            tmp/=10;
            length++;
        }
        power = new long[10][length+1];
                for(int i = 0; i <power.length;i++){
              long p =1;
              for (int j=0;j<power[0].length;j++){
                  power[i][j] = p;
                  p*=i;
              }
                }
for (number = 1; number<=length;number++){
            search(0,number,0);
}

        Collections.sort(longlist);
        result = new long[longlist.size()];
        for (int i = 0;i<result.length;i++){
            result[i]= longlist.get(i);
        }
        return result;
    }

    private static void search(int digits, int usued, long pow){
        if (digits ==10){
            if (check(pow)){
                longlist.add(pow);
            return;
            }
        }
        if (digits == 9) {
            digitmult[digits] = usued;
            search(digits + 1, 0, pow + usued * power[digits][number]);
        }else {
            for (int i = 0 ; i<=usued;i++){
                digitmult[digits] = i;
                search(digits+i,usued-i,pow+i*power[digits][number]);
            }
        }
    }
    private static boolean check(long pow) {
        if (Solution.N <= pow)
            return false;
        int[] test = new int[10];
        while (pow > 0) {
            int i = (int) (pow % 10);
            test[i]++;
            pow = pow / 10;
        }
        for (int i = 0; i < 10; i++) {
            if (test[i] != digitmult[i])
                return false;

        } return true;
    }
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        System.out.println(Arrays.toString(getNumbers(Integer.MAX_VALUE)));
        long end = System.currentTimeMillis();
        System.out.println(end - start + " millisecond");
        System.out.println("memory: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024) + " mb");

    }
}
