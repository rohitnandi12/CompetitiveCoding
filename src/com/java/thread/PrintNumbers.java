package com.java.thread;

public class PrintNumbers {
    public static void main(String[] args) {
        PrintNumberTask t = new PrintNumberTask(1);

        for(int i=0;i<100; i++) {
            Thread thread = new Thread(t);
            thread.start();
        }
    }
}

