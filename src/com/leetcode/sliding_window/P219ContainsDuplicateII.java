package com.leetcode.sliding_window;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <a href="https://leetcode.com/problems/contains-duplicate-ii/description/">219. Contains Duplicate II</a>
 * Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.
 * Example 1:
 * Input: nums = [1,2,3,1], k = 3
 * Output: true
 * Example 2:
 * Input: nums = [1,0,1,1], k = 1
 * Output: true
 * Example 3:
 * Input: nums = [1,2,3,1,2,3], k = 2
 * Output: false
 */
public class P219ContainsDuplicateII {

    public static boolean containsDuplicate(List<Integer> input, int k) {

        Map<Integer, Integer> lu = new HashMap<>();

        for(int idx = 0; idx < input.size(); idx++) {
            int currentNumber = input.get(idx);

            if(lu.containsKey(currentNumber) && (idx - lu.get(currentNumber)) <= k) {
                return true;
            } else {
                lu.put(currentNumber, idx);
            }
        }
        return false;
    }
    public static void main(String[] args) {
        System.out.println(containsDuplicate(List.of(1,2,3,1), 3));
        System.out.println(containsDuplicate(List.of(1,0,1,1), 1));
        System.out.println(containsDuplicate(List.of(1,2,3,1,2,3), 2));
    }
}
