package com.java.thread.virtualThread;

import java.util.stream.IntStream;

public class VirtualThreadExamples {
    public static void main(String[] args) {
        VirtualThreadExamples obj = new VirtualThreadExamples();
//        obj.example1();
//        obj.example2();
        obj.example3();
    }

    private void example1() {
        // Assigned to platform thread
        Thread.ofPlatform().start(() -> System.out.println("Platform Thread : " + Thread.currentThread()));
        // Assigned to FokJoinPool Worker
        Thread.ofVirtual().start(() -> System.out.println("Platform Thread : " + Thread.currentThread()));
    }

    private void example2() {
        int totalThreads = 10;
        var threads = IntStream.range(0, totalThreads)
                .mapToObj(index -> Thread.ofVirtual().unstarted(
                        () -> {
                            // Before Sleep, it will be assigned to a Worker
                            if (index == 0) {
                                System.out.println("Before Sleep : " + Thread.currentThread());
                            }

                            // the platform worker is detached
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException ignore) {
                            }

                            // Another Worker is attached
                            if (index == 0) {
                                System.out.println("After Sleep : " + Thread.currentThread());
                            }
                        }
                )).toList();
        threads.forEach(Thread::start);
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException ignore) {
            }
        });
    }


    private void example3() {
        int totalThreads = 100000;

        var start = System.currentTimeMillis();
        var threadsVirtual = IntStream.range(0, totalThreads)
                .mapToObj(index -> Thread.ofVirtual().unstarted(() -> { /* do nothing */})).toList();
        threadsVirtual.forEach(Thread::start);
        threadsVirtual.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException ignore) {
            }
        });
        var end = System.currentTimeMillis();
        System.out.println("Total time taken for Virtual Threads " + (end - start) + "ms.");

        // platform threads

        start = System.currentTimeMillis();
        threadsVirtual = IntStream.range(0, totalThreads)
                .mapToObj(index -> Thread.ofPlatform().unstarted(() -> { /* do nothing */})).toList();
        threadsVirtual.forEach(Thread::start);
        threadsVirtual.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException ignore) {
            }
        });
        end = System.currentTimeMillis();
        System.out.println("Total time taken for Platform Threads " + (end - start) + "ms.");
    }
}
