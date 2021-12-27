package com.urise.webapp;

public class DeadLock {
    public static String lock1 = "lock1";
    public static String lock2 = "lock2";

    public static void main(String[] args) {
        deadLock(lock1, lock2);
        deadLock(lock2, lock1);
    }

    public static void deadLock(String lock1, String lock2) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ": попытка захватить монитор " + lock1);
            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName() + ": монитор " + lock1 + " захвачен");
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ": попытка захватить монитор " + lock2);
                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + ": мониторы " + lock1 + " и " + lock2 + " захвачены");
                }
            }
        }).start();
    }
}