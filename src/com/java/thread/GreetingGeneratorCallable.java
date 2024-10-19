package com.java.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class GreetingGeneratorCallable implements Callable<String> {

    public static void main(String[] args) throws Exception {
        GreetingGeneratorCallable greetingGeneratorCallable = new GreetingGeneratorCallable(new Random());

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        ArrayList<Future<String>> promises = new ArrayList<>();
        for(int i=0; i<20; i++) {
            Future<String> promiseOfGreeting = executorService.submit(greetingGeneratorCallable);
            promises.add(promiseOfGreeting);
        }

        promises.forEach(p -> {
            try {
                System.out.println(p.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });

        executorService.shutdown(); // if not shutdown the application doesn't stop
    }

    Random random;
    public GreetingGeneratorCallable(Random random) {
        this.random = random;
    }

    @Override
    public String call() throws Exception {
        int n = this.random.nextInt(6);
        return List.of(
                "Hello " + Thread.currentThread().getName(),
                "Hola " + Thread.currentThread().getName(),
                "Bonjour " + Thread.currentThread().getName(),
                "Namaste " + Thread.currentThread().getName(),
                "Jule " + Thread.currentThread().getName(),
                "Bye " + Thread.currentThread().getName()
        ).get(n);
    }
}