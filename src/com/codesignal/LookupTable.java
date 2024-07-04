package com.codesignal;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://codesignal.com/blog/interview-prep/example-codesignal-questions/">Lookup Table </a>
 */
//[1, -1, 2, 3]
//
//x + y = 2^n
//x = 2^n - y
//
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
            for (int x=0; x<21; x++) {
                int nj = (1 << x) - ni;
                if(lu.containsKey(nj))
                    count += lu.get(nj);
            }
        }
        return count;
    }
}
