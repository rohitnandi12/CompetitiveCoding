package com.leetcode.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/group-anagrams/">49. Group Anagrams</a>
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 * <p>
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 * <p>
 * Example 1:
 * <p>
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * Example 2:
 * <p>
 * Input: strs = [""]
 * Output: [[""]]
 * Example 3:
 * <p>
 * Input: strs = ["a"]
 * Output: [["a"]]
 * <p>
 * Constraints:
 * <p>
 * 1 <= strs.length <= 104
 * 0 <= strs[i].length <= 100
 * strs[i] consists of lowercase English letters.
 * <p>
 * Level:Medium
 */
class P49GroupAnagrams {

    public static void main(String[] args) {
        P49GroupAnagrams obj = new P49GroupAnagrams();
        List<List<String>> result = obj.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
        System.out.println(result);
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> hm = new HashMap<>();

        for (String s : strs) {
            String hash = getHashArrayCount(s);

            hm.computeIfAbsent(hash, k -> new ArrayList<>());

            hm.get(hash).add(s);
        }

        return hm.values().stream().toList();
    }

    public String getHashArrayCount(String str) {
        char[] charArray = new char[26];

        for (char ch : str.toCharArray()) {
            charArray[ch - 'a']++;
        }
        return new String(charArray);
    }

    public String getHashSortString(String str) {
        char[] charArray = str.toCharArray();

        Arrays.sort(charArray);

        return new String(charArray);
    }
}
