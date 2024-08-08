package com.leetcode.dp;

/**
 * <a href="https://leetcode.com/problems/climbing-stairs/description/">70. Climbing Stairs</a>
 * You are climbing a staircase. It takes n steps to reach the top.
 *
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * Example 2:
 *
 * Input: n = 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 *
 *
 * Constraints:
 *
 * 1 <= n <= 45
 * Level:Easy
 */
public class P70ClimbingStairs {

    public static void main(String[] args) {
        System.out.println(climbStairs(3)); // 3
        System.out.println(climbStairs(45)); // 1836311903
    }
    public static int climbStairs(int n) {

        int lastToLastStep = 1;
        int lastStep = 1;

        for(int step=2; step<=n; step++) {
            int currStep = lastStep + lastToLastStep;
            lastToLastStep = lastStep;
            lastStep = currStep;
        }

        return lastStep;
    }
}
