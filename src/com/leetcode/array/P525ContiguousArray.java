package com.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.com/problems/contiguous-array/">525. Contiguous Array</a>
 * Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [0,1]
 * Output: 2
 * Explanation: [0, 1] is the longest contiguous subarray with an equal number of 0 and 1.
 * Example 2:
 * <p>
 * Input: nums = [0,1,0]
 * Output: 2
 * Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * nums[i] is either 0 or 1.
 */
public class P525ContiguousArray {
    public int findMaxLength(int[] nums) {
        int longestSubarrayLength = 0;
        int prefixSum = 0;
        Map<Integer, Integer> lastSumIndex = new HashMap<>();

        // Initialize to handle cases where the subarray starts from index 0
        lastSumIndex.put(0, -1);

        for (int i = 0; i < nums.length; i++) {
            // Translate 0 to -1 and 1 remains as is for the prefix sum calculation
            prefixSum += nums[i] == 0 ? -1 : 1;

            // If the current prefix sum has been seen before, calculate subarray length
            if (lastSumIndex.containsKey(prefixSum)) {
                longestSubarrayLength = Math.max(longestSubarrayLength, i - lastSumIndex.get(prefixSum));
            } else {
                // Store the first occurrence of the prefix sum
                lastSumIndex.put(prefixSum, i);
            }
        }

        return longestSubarrayLength;
    }
}
