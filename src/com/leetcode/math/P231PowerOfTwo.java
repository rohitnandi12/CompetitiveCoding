package com.leetcode.math;

/**
 * <a href="https://leetcode.com/problems/power-of-two/description/">231. Power of Two</a>
 * Given an integer n, return true if it is a power of two. Otherwise, return false.
 * An integer n is a power of two, if there exists an integer x such that n == 2x.
 * Learnings
 * 1. negative numbers are not power of 2 i.e. they cannot be represented as 2^x
 * 2. (n & n - 1) is an effective way to remove trailing 1s
 * 3. 8-bit unsigned integer 0 to 255
 * Level:Easy
 */
public class P231PowerOfTwo {
    public static boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        return (n & n - 1) == 0;
    }

    public static void main(String[] args) {
        System.out.println(isPowerOfTwo(-16));
        System.out.println(isPowerOfTwo(0));
        System.out.println(isPowerOfTwo(1));
        System.out.println(isPowerOfTwo(16));
        System.out.println(isPowerOfTwo(10));
    }
}
