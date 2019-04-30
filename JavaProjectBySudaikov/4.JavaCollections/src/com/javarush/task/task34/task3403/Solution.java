package com.javarush.task.task34.task3403;

/* 
Разложение на множители с помощью рекурсии
*/
public class Solution {
    public void recursion(int n) {
        int i = 2;
        while (i<=n){
            if (n%i==0){
                System.out.println(i+" ");
                if (i==n)
                    return;
                recursion(n/i);
                break;
            }i++;
        }
    }
}
