package com.leetcode.bit_manipulation;

/**
 * 191. Number of 1 Bits
 * Write a function that takes the binary representation of a positive integer and returns the number of
 * set bits
 * it has (also known as the Hamming weight).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 11
 * <p>
 * Output: 3
 * <p>
 * Explanation:
 * <p>
 * The input binary string 1011 has a total of three set bits.
 * <p>
 * Example 2:
 * <p>
 * Input: n = 128
 * <p>
 * Output: 1
 * <p>
 * Explanation:
 * <p>
 * The input binary string 10000000 has a total of one set bit.
 * <p>
 * Example 3:
 * <p>
 * Input: n = 2147483645
 * <p>
 * Output: 30
 * <p>
 * Explanation:
 * <p>
 * The input binary string 1111111111111111111111111111101 has a total of thirty set bits.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 231 - 1
 * <p>
 * <p>
 * Follow up: If this function is called many times, how would you optimize it?
 * Level:Easy
 */
public class P191NumberOf1Bits {
    public static int hammingWeight(int n) {
        int count = 0;
        while(n > 0) {
            count += (n&1);
            n = n >> 1;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(hammingWeight(11)); // equals 3
    }
}
