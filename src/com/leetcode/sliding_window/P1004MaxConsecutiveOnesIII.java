package com.leetcode.sliding_window;

/**
 * <a href="https://leetcode.com/problems/max-consecutive-ones-iii/description/">1004. Max Consecutive Ones III</a>
 * Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
 *
 *  Example 1:
 * Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
 * Output: 6
 * Explanation: [1,1,1,0,0,1,1,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 *
 * Example 2:
 * Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
 * Output: 10
 * Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * nums[i] is either 0 or 1.
 * 0 <= k <= nums.length
 *
 *  Level:Medium
 */
public class P1004MaxConsecutiveOnesIII {
    public static void main(String[] args) {
        System.out.println(longestOnes(new int[]{1,1,1,0,0,0,1,1,1,1,0}, 2));
        System.out.println(longestOnes(new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1}, 3));
    }
    public static int longestOnes(int[] nums, int k) {
        int start = 0;
        int end = 0;
        int maxLength = -1;

        while(end < nums.length) {
            if(nums[end] == 0) {
                if(k == 0) {
                    while(nums[start] != 0) {
                        start += 1;
                    }
                    start += 1;
                } else {
                    k -= 1;
                }
            }
            maxLength = Math.max(maxLength, end - start + 1);
            end += 1;
        }

        maxLength = Math.max(maxLength, end - start);

        return maxLength;
    }

}