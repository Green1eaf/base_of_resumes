package com.urise.webapp;

public class DeadLock {
    public static Object lock1 = new Object();
    public static Object lock2 = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread1();
        Thread thread2 = new Thread2();
        thread1.start();
        thread2.start();

    }
}

class Thread1 extends Thread {
    @Override
    public void run() {
        System.out.println("Thread1: попытка захватить монитор lock1");
        synchronized (DeadLock.lock1) {
            System.out.println("Thread1: монитор lock1 захвачен");
            System.out.println("Thread1: попытка звхватить монитор lock2");
            synchronized (DeadLock.lock2) {
                System.out.println("Thread1: мониторы lock1 и lock2 захвачены");
            }
        }
    }
}

class Thread2 extends Thread {
    @Override
    public void run() {
        System.out.println("Thread2: попытка захватить монитор lock2");
        synchronized (DeadLock.lock2) {
            System.out.println("Thread2: монитор lock2 захвачен");
            System.out.println("Thread2: попытка звхватить монитор lock1");
            synchronized (DeadLock.lock1) {
                System.out.println("Thread2: мониторы lock1 и lock2 захвачены");
            }
        }
    }
}