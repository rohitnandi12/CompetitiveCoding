package com.scaler.array;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Problem Description
 * Given an array A of size N, Groot wants you to pick 2 indices i and j such that
 *
 * 1 <= i, j <= N
 * A[i] % A[j] is maximum among all possible pairs of (i, j).
 * Help Groot in finding the maximum value of A[i] % A[j] for some i, j.
 *
 *
 *
 * Problem Constraints
 * 1 <= N <= 100000
 * 0 <= A[i] <= 100000
 *
 *
 *
 * Input Format
 * First and only argument in an integer array A.
 *
 *
 *
 * Output Format
 * Return an integer denoting the maximum value of A[i] % A[j] for any valid i, j.
 *
 *
 *
 * Example Input
 * Input 1:
 *
 *  A = [1, 2, 44, 3]
 * Input 2:
 *
 *  A = [2, 6, 4]
 *
 *
 * Example Output
 * Output 1:
 *
 *  3
 * Output 2:
 *
 *  4
 *
 *
 * Example Explanation
 * Explanation 1:
 *
 *  For i = 4, j = 3  A[i] % A[j] = 3 % 44 = 3.
 *  No pair exists which has more value than 3.
 * Explanation 2:
 *
 *  For i = 3, j = 2  A[i] % A[j] = 4 % 6 = 4.
 */
public class MaxMod {

    public static void main(String[] args) {
        System.out.println(solve(new ArrayList<>(Arrays.asList(1, 2, 44, 3)))); // 3
        System.out.println(solve(new ArrayList<>(Arrays.asList(2, 6, 4)))); // 4
    }
    public static int solve(ArrayList<Integer> A) {
        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        for(int a : A) {
            if(largest < a) {
                secondLargest = largest;
                largest = a;
            } else if(a!=largest && secondLargest < a) {
                secondLargest = a;
            }
        }
        if(secondLargest == Integer.MIN_VALUE)
            return 0;
        return secondLargest;
    }
}
