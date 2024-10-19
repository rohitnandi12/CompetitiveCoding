package com.leetcode.array;

/**
 * <a href="https://leetcode.com/problems/maximum-product-subarray/description/">152. Maximum Product Subarray</a>
 * <p>
 * Given an integer array nums, find a subarray that has the largest product, and return the product.
 * <p>
 * The test cases are generated so that the answer will fit in a 32-bit integer.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * Example 2:
 * <p>
 * Input: nums = [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 2 * 104
 * -10 <= nums[i] <= 10
 * The product of any subarray of nums is guaranteed to fit in a 32-bit integer.
 * Level:Medium
 */
public class P152MaximumProductSubarray {

    public int maxProduct(int[] nums) {
        // all positives = complete array
        // even negatives = complete array
        // odd negatives = Max(left, right) from any negative element
        // all zeros = 0
        // some zeros = Max(segments) partition by 0.
        // reset prefix when 0
        // [1,3,0,1,5,0,6,7]

        int prefix = 1, suffix = 1;
        int maxTillNow = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {

            if (prefix == 0) prefix = 1;
            if (suffix == 0) suffix = 1;

            prefix = prefix * nums[i];
            suffix = suffix * nums[nums.length - i - 1];
            maxTillNow = Math.max(maxTillNow, Math.max(suffix, prefix));
        }

        return maxTillNow;
    }


    /**
     * To solve the Maximum Product Subarray problem using a dynamic programming approach,
     * the key idea is to track both the maximum and minimum products ending at each position
     * in the array, as a negative number can turn a large negative product into a positive
     * one, and vice versa. Therefore, at each step, you need to maintain both the maximum
     * and the minimum product up to that point.
     */
    public int maxProductDynamicProgramming(int[] nums) {

        if (nums.length == 0) {
            return 0;
        }

        // Initialize maxProduct, minProduct, and result to the first element
        int maxProduct = nums[0];
        int minProduct = nums[0];
        int result = nums[0];

        // Iterate through the array starting from the second element
        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];

            // Temporarily store maxProduct to use it for minProduct calculation
            int tempMax = Math.max(current * maxProduct, current * minProduct);
            int tempMin = Math.min(current * maxProduct, current * minProduct);

            // Update maxProduct and minProduct
            maxProduct = Math.max(current, tempMax);
            minProduct = Math.min(current, tempMin);

            // Update the result with the largest maxProduct found so far
            result = Math.max(result, maxProduct);
        }

        return result;
    }
}
