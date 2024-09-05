package com.leetcode.sliding_window;

/**
 * <a href="https://leetcode.com/problems/maximum-average-subarray-i/description/">643. Maximum Average Subarray I</a>
 * You are given an integer array nums consisting of n elements, and an integer k.
 * <p>
 * Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value. Any answer with a calculation error less than 10-5 will be accepted.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,12,-5,-6,50,3], k = 4
 * Output: 12.75000
 * Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
 * Example 2:
 * <p>
 * Input: nums = [5], k = 1
 * Output: 5.00000
 * <p>
 * Constraints:
 * <p>
 * n == nums.length
 * 1 <= k <= n <= 105
 * -104 <= nums[i] <= 104
 *
 * Level:Easy
 */
public class P643MaximumAverageSubarrayI {

    public static void main(String[] args) {
        System.out.println(findMaxAverage(new int[]{1, 12, -5, -6, 50, 3}, 4)); // 12.75000
    }

    public static double findMaxAverage(int[] nums, int k) {

        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += nums[i];
        }

        // Initialize maxSum with the sum of the first window
        int maxSum = windowSum;

        // Slide the window across the array
        for (int i = k; i < nums.length; i++) {
            // Update the window sum by removing the element going out of the window
            // and adding the element coming into the window
            windowSum += nums[i] - nums[i - k];

            // Track the maximum sum encountered
            maxSum = Math.max(maxSum, windowSum);
        }

        // Calculate and return the maximum average
        return (double) maxSum / k;
    }
}
