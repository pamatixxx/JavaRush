package com.javarush.task.task27.task2707;

/*
Определяем порядок захвата монитора
*/
public class Solution {
    public void someMethodWithSynchronizedBlocks(Object obj1, Object obj2) {
        int firstHash=obj1.hashCode();
        int secondHash = obj2.hashCode();
        Object firstLock = firstHash < secondHash?obj1:obj2;
        Object secondLock = secondHash<firstHash?obj2:obj1;
        synchronized (obj1) {
            synchronized (obj2) {
                System.out.println(obj1 + " " + obj2);
            }
        }
    }

    public static boolean isNormalLockOrder(final Solution solution, final Object o1, final Object o2) throws Exception {
           Thread thread = new Thread(()->solution.someMethodWithSynchronizedBlocks(o1,o2));
           thread.start();
           Thread.State st1,st2;
           synchronized (o1){
               st1 = waitAndGetState(thread);
           }
           synchronized (o2){
               st2 = waitAndGetState(thread);
           }

        return st1==st2;
    }
    public static Thread.State waitAndGetState(Thread thread)throws InterruptedException{
        Thread.sleep(300);
        return thread.getState();
    }

    public static void main(String[] args) throws Exception {
        final Solution solution = new Solution();
        final Object o1 = new Object();
        final Object o2 = new Object();

        System.out.println(isNormalLockOrder(solution, o1, o2));
    }
}
