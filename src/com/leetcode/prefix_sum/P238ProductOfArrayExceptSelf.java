package com.leetcode.prefix_sum;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/product-of-array-except-self/description/">238. Product of Array Except Self</a>
 * Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements
 * of nums except nums[i].
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * <p>
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 * Example 2:
 * <p>
 * Input: nums = [-1,1,0,-3,3]
 * Output: [0,0,9,0,0]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= nums.length <= 105
 * -30 <= nums[i] <= 30
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * <p>
 * <p>
 * Follow up: Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space
 * for space complexity analysis.)
 * Level:Medium
 */
public class P238ProductOfArrayExceptSelf {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(productExceptSelf(new int[]{1, 2, 3, 4})));
        System.out.println(Arrays.toString(productExceptSelf(new int[]{-1, 1, 0, -3, 3})));
        System.out.println(Arrays.toString(productExceptSelf(new int[]{-1, 1, 0, -3, 0})));
    }

    public static int[] productExceptSelf(int[] nums) {
        int[] op = new int[nums.length];
        int completeProduct = 1;
        int countZero = 0;
        int zeroIndex = -1;

        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            if (n == 0) {
                countZero += 1;
                zeroIndex = i;
            } else {
                completeProduct *= n;
            }
        }

        if (countZero > 1) {
            return op;
        } else if (countZero == 1) {
            op[zeroIndex] = completeProduct;
            return op;
        } else {
            int i = 0;
            for (int n : nums) {
                op[i++] = completeProduct / n;
            }

            return op;
        }
    }
}