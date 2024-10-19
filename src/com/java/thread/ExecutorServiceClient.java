package com.java.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceClient {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        PrintNumberTask task = new PrintNumberTask(1);
        for (int i = 0; i < 100; i++) {
            executorService.submit(task);
        }

        executorService.shutdown(); // if not shutdown the application doesn't stop
    }
}

