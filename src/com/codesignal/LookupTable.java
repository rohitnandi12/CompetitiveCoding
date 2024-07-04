package com.codesignal;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://codesignal.com/blog/interview-prep/example-codesignal-questions/">Lookup Table </a>
 * Given an array of unique integers numbers, your task is to find the number of pairs of indices (i, j) such that i ≤ j and the sum numbers[i] + numbers[j] is equal to some power of 2.
 * <p>
 * Note: The numbers 20  = 1, 21 = 2, 22 = 4, 23 = 8, etc. are considered to be powers of 2.
 * <p>
 * Example
 * <p>
 * For numbers = [1, -1, 2, 3], the output should be solution(numbers) = 5.
 * – There is one pair of indices where the sum of the elements is 20 = 1:(1, 2): numbers[1] + numbers[2] = -1 + 2 = 1
 * – There are two pairs of indices where the sum of the elements is 21 = 2: (0, 0) and (1, 3)
 * – There are two pairs of indices where the sum of the elements is 22 = 4: (0, 3) and (2, 2)
 * – In total, there are 1 + 2 + 2 = 5 pairs summing to powers of 2.
 * <p>
 * For numbers = [2], the output should be solution(numbers) = 1.
 * – The only pair of indices is (0, 0) and the sum is equal to 22 = 4. So, the answer is 1.
 * <p>
 * For numbers = [-2, -1, 0, 1, 2], the output should be solution(numbers) = 5.
 * – There are two pairs of indices where the sum of the elements is 20 = 1: (2, 3) and (1, 4)
 * – There are two pairs of indices where the sum of the elements is 21 = 2: (2, 4) and (3, 3)
 * – There is one pair of indices where the sum of the elements is 22 = 4: (4, 4)
 * – In total, there are 2 + 2 + 1 = 5 pairs summing to powers of 2.
 * <p>
 * Guaranteed constraints:
 * <p>
 * 1 ≤ numbers.length ≤ 105
 * -106 ≤ numbers[i] ≤ 106
 */
public class LookupTable {

    public static void main(String[] args) {
        assert countPairs(new int[]{1, -1, 2, 3}) == 5;
        assert countPairs(new int[]{2}) == 1;
        assert countPairs(new int[]{-2, -1, 0, 1, 2}) == 5;
        System.out.println("All tests passed");
    }

    public static int countPairs(int[] nums) {
        Map<Integer, Integer> lu = new HashMap<>();
        int count = 0;
        for (int ni : nums) {
            lu.put(ni, lu.getOrDefault(ni, 0) + 1);
            for (int x = 0; x < 21; x++) {
                int nj = (1 << x) - ni;
                if (lu.containsKey(nj))
                    count += lu.get(nj);
            }
        }
        return count;
    }
}
