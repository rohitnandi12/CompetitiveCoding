package com.java.thread;

class PrintNumberTask implements Runnable {
    int n;

    PrintNumberTask(int n) {
        this.n = n;
    }

    @Override
    public synchronized void run() { // remove synchronized gives unpredictable values
        System.out.println("The task done (n, thread-name): (" + n + ", "
                + Thread.currentThread().getName() + ")");
        this.n++;
    }
}
