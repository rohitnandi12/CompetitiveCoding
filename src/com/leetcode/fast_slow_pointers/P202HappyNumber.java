package com.leetcode.fast_slow_pointers;

/**
 * <a href="https://leetcode.com/problems/happy-number/">202. Happy Number</a>
 * <p>
 * Write an algorithm to determine if a number n is happy.
 * <p>
 * A happy number is a number defined by the following process:
 * <p>
 * Starting with any positive integer, replace the number by the sum of the squares of its digits.
 * Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
 * Those numbers for which this process ends in 1 are happy.
 * Return true if n is a happy number, and false if not.
 * <p>
 * Level:Easy
 */
public class P202HappyNumber {

    public static void main(String[] args) {
        P202HappyNumber obj = new P202HappyNumber();
        System.out.println(obj.isHappy(19)); // true
        System.out.println(obj.isHappy(2)); // false
    }
    public boolean isHappy(int n) {
        if (n == 1 || getSumOfDigits(n) == 1)
            return true;


        int slow = n;
        int fast = getSumOfDigits(n);

        while (fast != slow) {

            slow = getSumOfDigits(slow);
            fast = getSumOfDigits(getSumOfDigits(fast));
        }

        return fast == 1;

    }

    private int getSumOfDigits(int n) {
        int sum = 0;
        while (n > 0) {
            int d = n % 10;
            sum += (d * d);
            n /= 10;
        }
        return sum;
    }

}
