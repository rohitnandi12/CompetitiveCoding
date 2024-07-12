package com.java;

import java.util.concurrent.*;

public class ExecutorServiceExample {

    public static void main(String[] args) {

        ExecutorService ex = Executors.newFixedThreadPool(10);

        Runnable runnableTask = () -> {
            try {
                // TimeUnit enum, ultimately calls Thread.sleep but allows you to specify the time unit more explicitly.
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Callable<String> callableTask = () -> {
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Tasks executed";
        };

        ex.submit(runnableTask);
        Future<String> future = ex.submit(callableTask);

        if (!future.isDone()) {
            future.cancel(true);
        }


        try {
            if (!future.isCancelled())
                future.get(200, TimeUnit.MILLISECONDS); // get with Timeout
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException(e);
        }

        // In general, the ExecutorService will not be automatically destroy when there is no task to process.
        // It will stay alive and wait for new work to do.
        // The shutdown() method does not cause immediate destruction of the ExecutorService. It will make the
        // ExecutorService stop accepting new tasks and shut down after all running threads finish their current work:
        ex.shutdown(); // stop accepting new tasks
        try {
            //Returns: true if this executor terminated and false if the timeout elapsed before termination
            if (!ex.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                ex.shutdownNow();
            }
        } catch (InterruptedException e) {
            ex.shutdownNow();
        }


        // Scheduled Executor Service
        ScheduledExecutorService executorService = Executors
                .newSingleThreadScheduledExecutor();
        Future<String> resultFuture = executorService.schedule(callableTask, 1, TimeUnit.SECONDS);
        System.out.println(resultFuture);
        executorService.scheduleAtFixedRate(runnableTask, 100, 450, TimeUnit.MILLISECONDS);
//        If the processor needs more time to run an assigned task than the period parameter of the scheduleAtFixedRate() method, the ScheduledExecutorService will wait until the current task is completed before starting the next.


        // If it is necessary to have a fixed length delay between iterations of the task, scheduleWithFixedDelay() should be used.
        // the following code will guarantee a 150-millisecond pause between the end of the current execution and the start of another one:
        executorService.scheduleWithFixedDelay(runnableTask, 100, 150, TimeUnit.MILLISECONDS);

        // ExecutorService is the processing of independent tasks, such as transactions or requests according to the scheme “one thread for one task.”
        // fork/join was designed to speed up work that can be broken into smaller pieces recursively.
    }
}
