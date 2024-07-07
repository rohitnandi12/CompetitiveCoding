package com.leetcode.prefix_sum;

import java.util.Arrays;

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

        for(int i=0; i<nums.length; i++) {
            int n = nums[i];
            if(n == 0) {
                countZero += 1;
                zeroIndex = i;
            } else {
                completeProduct *= n;
            }
        }

        if(countZero > 1) {
            return op;
        } else if(countZero == 1) {
            op[zeroIndex] = completeProduct;
            return op;
        } else {
            int i=0;
            for(int n : nums) {
                op[i++] = completeProduct / n;
            }

            return op;
        }
    }
}