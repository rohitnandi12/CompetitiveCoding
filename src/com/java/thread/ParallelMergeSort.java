package com.java.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ParallelMergeSort {

    static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        List<Integer> arr = new ArrayList<>(List.of(5, 4, 3, 2, 1, 6, 5, 4, 3, 2, 1, 6));

        System.out.println(parallelMergeSort(arr, 0, arr.size() - 1));

        executorService.shutdown();
    }

    private static List<Integer> parallelMergeSort(List<Integer> arr, int start, int end) throws ExecutionException, InterruptedException {
        System.out.println("Thread " + Thread.currentThread().getName() + "executing (" + start + ", " + end + ")");
        if (start >= end) {
            return arr;
        }

        int mid = start + (end - start) / 2;

        Future<List<Integer>> fh = executorService.submit(() -> parallelMergeSort(arr, start, mid));
        Future<List<Integer>> sh = executorService.submit(() -> parallelMergeSort(arr, mid + 1, end));

        List<Integer> fhl = fh.get();
        List<Integer> shl = sh.get();

        List<Integer> merged = new ArrayList<>();
        int i = start;
        int j = mid + 1;

        while (i <= mid && j <= end) {
            if (fhl.get(i) < shl.get(j)) {
                merged.add(fhl.get(i++));
            } else {
                merged.add(shl.get(j++));
            }
        }

        while (i <= mid) {
            merged.add(fhl.get(i++));
        }

        while (j <= end) {
            merged.add(shl.get(j++));
        }

        int l = 0;
        for (int k = start; k <= end; k++) {
            arr.set(k, merged.get(l++));
        }

        return arr;
    }
}
