package com.leetcode.prefix_sum;

/**
 * <a href="https://leetcode.com/problems/range-sum-query-immutable/description/">303. Range Sum Query - Immutable</a>
 * Given an integer array nums, handle multiple queries of the following type:
 * <p>
 * Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
 * Implement the NumArray class:
 * <p>
 * NumArray(int[] nums) Initializes the object with the integer array nums.
 * int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["NumArray", "sumRange", "sumRange", "sumRange"]
 * [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
 * Output
 * [null, 1, -1, -3]
 * <p>
 * Explanation
 * NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
 * numArray.sumRange(0, 2); // return (-2) + 0 + 3 = 1
 * numArray.sumRange(2, 5); // return 3 + (-5) + 2 + (-1) = -1
 * numArray.sumRange(0, 5); // return (-2) + 0 + 3 + (-5) + 2 + (-1) = -3
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 104
 * -105 <= nums[i] <= 105
 * 0 <= left <= right < nums.length
 * At most 104 calls will be made to sumRange.
 * Level:Easy
 */
public class P303RangeSumQueryImmutable {

    public static void main(String[] args) {
        P303RangeSumQueryImmutable obj = new P303RangeSumQueryImmutable(
                new int[]{-2, 0, 3, -5, 2, -1}
        );
        System.out.println(obj.sumRange(0, 2)); // 1
        System.out.println(obj.sumRange(2, 5)); // -1
        System.out.println(obj.sumRange(0, 5)); // -3
    }

    int[] prefixSumArray;

    public P303RangeSumQueryImmutable(int[] nums) {

        int numsLength = nums.length;

        this.prefixSumArray = new int[numsLength];
        this.prefixSumArray[0] = nums[0];

        for (int i = 1; i < numsLength; i++) {
            this.prefixSumArray[i] = nums[i] + this.prefixSumArray[i - 1];
        }

    }

    public int sumRange(int left, int right) {
        int leftSum = left == 0 ? 0 : this.prefixSumArray[left - 1];
        return this.prefixSumArray[right] - leftSum;
    }
}
