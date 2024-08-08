package com.leetcode.dp;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/house-robber/description/">198. House Robber</a>
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * <p>
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * Example 2:
 * <p>
 * Input: nums = [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 * Total amount you can rob = 2 + 9 + 1 = 12.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 * Level:Medium
 */
public class P198HouseRobber {
    public static void main(String[] args) {
        System.out.println(rob(new int[]{2, 7, 9, 3, 1})); // 12
    }

    public static int rob(int[] nums) {
        int[] buff = new int[nums.length];
        Arrays.fill(buff, -1);
        return compute(nums, 0, buff);
    }

    public static int compute(int[] nums, int idx, int[] buff) {

        if (idx >= nums.length) {
            return 0;
        }

        if (buff[idx] == -1) {
            int maxMoney = 0;
            if (idx == nums.length - 1) {
                maxMoney = nums[idx];
            } else if (idx == nums.length - 2) {
                maxMoney = Math.max(nums[idx], nums[idx + 1]);
            } else {
                int noRob = compute(nums, idx + 1, buff);
                int rob = nums[idx] + compute(nums, idx + 2, buff);

                maxMoney = Math.max(noRob, rob);
            }

            buff[idx] = maxMoney;
        }

        return buff[idx];
    }
}
