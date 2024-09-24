package com.leetcode.monotonic_stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * <a href="https://leetcode.com/problems/daily-temperatures/description/">739. Daily Temperatures</a>
 * Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.
 * <p>
 * Example 1:
 * <p>
 * Input: temperatures = [73,74,75,71,69,72,76,73]
 * Output: [1,1,4,2,1,1,0,0]
 * Example 2:
 * <p>
 * Input: temperatures = [30,40,50,60]
 * Output: [1,1,1,0]
 * Example 3:
 * <p>
 * Input: temperatures = [30,60,90]
 * Output: [1,1,0]
 * <p>
 * Constraints:
 * <p>
 * 1 <= temperatures.length <= 105
 * 30 <= temperatures[i] <= 100
 * Level:Medium
 */
public class P739DailyTemperatures {

    public static void main(String[] args) {
        System.out.println(
                Arrays.toString(
                        dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73})
                )
        );
        // Output: [1,1,4,2,1,1,0,0]
    }

    public static int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] waitingTimes = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();  // Use ArrayDeque instead of Stack

        for (int i = n - 1; i >= 0; i--) {
            // Pop stack until we find a warmer day or stack is empty
            while (!stack.isEmpty() && temperatures[stack.peek()] <= temperatures[i]) {
                stack.pop();
            }

            // If stack is not empty, the index at the top of the stack is the next warmer day
            waitingTimes[i] = stack.isEmpty() ? 0 : stack.peek() - i;

            // Push the current day index onto the stack
            stack.push(i);
        }

        return waitingTimes;
    }
}
