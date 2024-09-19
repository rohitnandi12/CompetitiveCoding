package com.leetcode.contribution;

import java.util.Deque;
import java.util.ArrayDeque;

/**
 * <a href="https://leetcode.com/problems/sum-of-subarray-minimums/description/">907. Sum of Subarray Minimums</a>
 * Given an array of integers arr, find the sum of min(b), where b ranges over every (contiguous) subarray of arr. Since the answer may be large, return the answer modulo 109 + 7.
 *
 * Example 1:
 *
 * Input: arr = [3,1,2,4]
 * Output: 17
 * Explanation:
 * SubArrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
 * Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
 * Sum is 17.
 * Example 2:
 *
 * Input: arr = [11,81,94,43,3]
 * Output: 444
 *
 * Constraints:
 *
 * 1 <= arr.length <= 3 * 104
 * 1 <= arr[i] <= 3 * 104
 * Level:Medium
 */
class P907SumOfSubarrayMinimum {
    public static void main(String[] args) {
        sumSubarrayMins(new int[]{3, 1, 2, 4});
        System.out.println(sumSubarrayMins(new int[]{71, 55, 82, 55}));
    }

    public static int sumSubarrayMins(int[] arr) {
        final int N = arr.length;
        final int mod = (int) 1e9 + 7;

        // next minimum towards right
        int[] minTowardsRight = new int[N];
        Deque<Integer> stack = new ArrayDeque<>();

        // Find the next minimum towards the right
        for (int i = N - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }
            minTowardsRight[i] = stack.isEmpty() ? N : stack.peek();
            stack.push(i);
        }

        stack.clear();
        long sumOfMins = 0;

        // Calculate sum of subarray mins
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }

            int leftLimit = stack.isEmpty() ? -1 : stack.peek();
            int rightLimit = minTowardsRight[i];

            long contribution = (arr[i] * (long) (i - leftLimit)) % mod;
            contribution = (contribution * (rightLimit - i)) % mod;
            sumOfMins = (sumOfMins + contribution) % mod;

            stack.push(i);
        }
        return (int) sumOfMins;
    }

    public int bestSolution(int[] arr) {
        int[] dp = new int[arr.length];
        int[] stack = new int[arr.length];
        int si = -1;
        for (int i=0; i<arr.length; i++) {
            int num = arr[i];
            while (si >= 0 && arr[stack[si]] >= num) {
                si--;
            }
            if (si < 0) {
                dp[i] = (i + 1) * num;
            } else {
                int prev = stack[si];
                dp[i] = dp[prev] + (i - prev) * num;
            }
            stack[++si] = i;
        }
        long result = 0;
        for (int val : dp) {
            result += val;
        }
        return (int) (result % 1_000_000_007);
    }
}
