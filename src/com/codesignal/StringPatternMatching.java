package com.codesignal;

import java.util.List;

/**
 ou are given two strings: pattern and source. The first string pattern contains only the symbols 0 and 1, and the second string source contains only lowercase English letters.

 Your task is to calculate the number of substrings of source that match pattern.

 We’ll say that a substring source[l..r] matches pattern if the following three conditions are met:
 – The pattern and substring are equal in length.
 – Where there is a 0 in the pattern, there is a vowel in the substring.
 – Where there is a 1 in the pattern, there is a consonant in the substring.

 Vowels are ‘a‘, ‘e‘, ‘i‘, ‘o‘, ‘u‘, and ‘y‘. All other letters are consonants.

 Example

 For pattern = "010" and source = "amazing", the output should be solution(pattern, source) = 2.
 – “010” matches source[0..2] = "ama". The pattern specifies “vowel, consonant, vowel”. “ama” matches this pattern: 0 matches a, 1 matches m, and 0 matches a.
 – “010” doesn’t match source[1..3] = "maz"
 – “010” matches source[2..4] = "azi"
 – “010” doesn’t match source[3..5] = "zin"
 – “010” doesn’t match source[4..6] = "ing"

 So, there are 2 matches. For a visual demonstration, see the example video.

 For pattern = "100" and source = "codesignal", the output should be solution(pattern, source) = 0.
 – There are no double vowels in the string "codesignal", so it’s not possible for any of its substrings to match this pattern.

 Guaranteed constraints:
 1 ≤ source.length ≤ 103
 1 ≤ pattern.length ≤ 103
 **/

public class StringPatternMatching {

    public static void matchPattern(String pattern, String source) {
        int idx = 0;
        int count = 0;
        while(idx < source.length() - pattern.length()) {
            count += match(idx, pattern, source) ? 1 : 0;
            idx += 1;
        }
        System.out.println("Total matches: " + count);
    }

    public static boolean match(int idx, String pattern, String source) {
        List<Character> vowels = List.of('a', 'e', 'i', 'o', 'u', 'y');
        for(Character p: pattern.toCharArray()) {
            if(p == '0') {
                if(!vowels.contains(source.charAt(idx)))
                    return false;
            } else {
                if(vowels.contains(source.charAt(idx)))
                    return false;
            }
            idx+=1;
        }
        return true;
    }

    public static void main(String[] args) {
        matchPattern("010", "amazing");
        matchPattern("100", "codesignal");
    }
}
