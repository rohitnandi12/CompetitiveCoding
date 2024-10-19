package com.java.thread;

public class JoinExample {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Line 1");
        System.out.println("Line 2");

        Thread t = new Thread(() -> {
            System.out.println("Thread Line 1");
            System.out.println("Thread Line 2");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread Line 3");
        });

        System.out.println("Line 3");
        System.out.println("Line 4");

        t.start(); // main is not blocked on start
        Thread.sleep(500);
        System.out.println("Line 5");
        System.out.println("Line 6");

        t.join(); // main is blocked and does nothing, even if it gets CPU time.

        System.out.println("Line 7");
        System.out.println("Line 8");
    }
}
