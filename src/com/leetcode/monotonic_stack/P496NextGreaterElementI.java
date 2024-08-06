package com.leetcode.monotonic_stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * <a href="https://leetcode.com/problems/next-greater-element-i/submissions/1346245590/">496. Next Greater Element I</a>
 * The next greater element of some element x in an array is the first greater element that is to the right of x in the same array.
 * <p>
 * You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.
 * <p>
 * For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and determine the next greater element of nums2[j] in nums2. If there is no next greater element, then the answer for this query is -1.
 * <p>
 * Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
 * Output: [-1,3,-1]
 * Explanation: The next greater element for each value of nums1 is as follows:
 * - 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
 * - 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
 * - 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
 * Example 2:
 * <p>
 * Input: nums1 = [2,4], nums2 = [1,2,3,4]
 * Output: [3,-1]
 * Explanation: The next greater element for each value of nums1 is as follows:
 * - 2 is underlined in nums2 = [1,2,3,4]. The next greater element is 3.
 * - 4 is underlined in nums2 = [1,2,3,4]. There is no next greater element, so the answer is -1.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums1.length <= nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 104
 * All integers in nums1 and nums2 are unique.
 * All the integers of nums1 also appear in nums2.
 * <p>
 * <p>
 * Follow up: Could you find an O(nums1.length + nums2.length) solution?
 */
public class P496NextGreaterElementI {

    public static void main(String[] args) {
        System.out.println(
                Arrays.toString(
                        nextGreaterElement(
                                new int[]{4, 1, 2},
                                new int[]{1, 3, 4, 2}
                        )
                )
        ); // Output: [-1,3,-1]
    }

    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> st = new Stack<>();
        Map<Integer, Integer> eleIndex = new HashMap<>();
        int[] nextGreater = new int[nums2.length];

        for (int i = nums2.length - 1; i >= 0; i--) {
            int currEle = nums2[i];
            eleIndex.put(currEle, i);
            while (!st.isEmpty() && currEle >= st.peek()) {
                st.pop();
            }

            if (st.isEmpty()) {
                nextGreater[i] = -1;
            } else {
                nextGreater[i] = st.peek();
            }
            st.add(currEle);
        }

        int ans[] = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            int index = eleIndex.get(nums1[i]);
            ans[i] = nextGreater[index];
        }

        return ans;
    }
}
