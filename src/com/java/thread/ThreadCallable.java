package com.java.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadCallable {

    public static void main(String[] args) {

        Callable<String> callable = () -> {
            System.out.println("Inside callable");
            Thread.sleep(1000);
            System.out.println("Inside callable: After sleep");
            return "Returned by callable";
        };

        // Thread t = new Thread(callable); // compilation error
        FutureTask<String> futureTask = new FutureTask<>(callable);
        Thread t = new Thread(futureTask);
        t.start(); // comment this and test, FutureTask never completes
        try {
            System.out.println("before get");
            String str = futureTask.get(); // blocks main after this line
            // if t.start() is not called futureTask.get() never completes
            System.out.println("after get");
            System.out.println(str);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
