package com.leetcode.dp;

/**
 * <a href="https://leetcode.com/problems/decode-ways/description/">91. Decode Ways</a>
 * A message containing letters from A-Z can be encoded into numbers using the following mapping:
 * <p>
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:
 * <p>
 * "AAJF" with the grouping (1 1 10 6)
 * "KJF" with the grouping (11 10 6)
 * Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".
 * <p>
 * Given a string s containing only digits, return the number of ways to decode it.
 * <p>
 * The test cases are generated so that the answer fits in a 32-bit integer.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "12"
 * Output: 2
 * Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
 * Example 2:
 * <p>
 * Input: s = "226"
 * Output: 3
 * Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 * Example 3:
 * <p>
 * Input: s = "06"
 * Output: 0
 * Explanation: "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06").
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 100
 * s contains only digits and may contain leading zero(s).
 */
public class P91DecodeWays {
    public static void main(String[] args) {
        System.out.println(numbOfPossibleDecoding("12"));
        System.out.println(numbOfPossibleDecoding("226"));
        System.out.println(numbOfPossibleDecoding("06"));
        System.out.println(numbOfPossibleDecoding("111111111111111111111111111111111111111111111"));
        System.out.println(numbOfPossibleDecoding("27"));
    }
/*
2  2 6
     one = 6
     curr =  2
two = 2
   curr = 3

prevprev = 2
prev = 3
 */

    public static int numbOfPossibleDecoding(String s) {
        int n = s.length();
        if (s.charAt(0) == '0') return 0;
        int prev = 1, prevprev = 1;

        for (int i = 1; i < n; i++) {

            int one = s.charAt(i) - '0';
            int curr = 0;

            //take one char
            if (one != 0)
                curr = prev;

            //take two char
            int two = s.charAt(i - 1) - '0';
            if (two == 1 || (two == 2 && one <= 6))
                curr += prevprev;


            prevprev = prev;
            prev = curr;
        }

        return prev;
    }

    public static int numDecoding(String s) {
        int[] cache = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            cache[i] = -1;
        }
        return numbDecoding(0, s, cache);
    }

    public static int numbDecoding(int idx, String s, int[] cache) {
        if (idx >= s.length())
            return 1;

        if (cache[idx] == -1) {

            char firstChar = s.charAt(idx);

            if (firstChar == '0')
                return 0;

            if (idx == s.length() - 1)
                return 1;

            if (idx == s.length() - 2) {
                if (s.charAt(idx + 1) == '0' && Integer.parseInt(s.substring(idx)) < 27)
                    return 1;
            }

            int pos2 = 0;
            if (firstChar == '1' || firstChar == '2') {
                if (Integer.parseInt(s.substring(idx, idx + 2)) < 27)
                    pos2 = numbDecoding(idx + 2, s, cache);
            }
            int pos1 = numbDecoding(idx + 1, s, cache);
            cache[idx] = pos1 + pos2;
        }

        return cache[idx];
    }
}
