package com.company.salesforce;

import java.util.ArrayList;
import java.util.List;

public class ReplaceConsecutiveRepeating {

    public static void main(String[] args) {
        System.out.println(
                findChangeRequired("abaaaba")
        );
        /*
        Example 1:
        Input = abaaaba
        "aba" first three characters no change required
        4th character "a" == "a" 3rd chang required = 1
        5th character "a" == "a" 4th, but since it was changed in the
        previous step. There is no change required.

        Output: total changes = 1

        Example 2:
        Input = abaaaabba
        Output = 3
         */
    }
    public static List<Integer> minimalOperations(List<String> words) {
        // Write your code here
        List<Integer> changesRequired = new ArrayList<>();

        for(String word: words) {
            int noOfChanges = findChangeRequired(word);
            changesRequired.add(noOfChanges);
        }

        return changesRequired;
    }

    public static Integer findChangeRequired(String word) {
        int noOfChanges = 0;
        // abaaaba
        boolean isRepeating = true;
        char lastChar = word.charAt(0);

        for(int i=1; i<word.length(); i++) {
            char currentChar = word.charAt(i);

            if(lastChar == currentChar && isRepeating) {
                noOfChanges += 1;
                isRepeating = false;
            } else if(lastChar == currentChar && !isRepeating) {
                isRepeating = true;
            } else {
                lastChar = currentChar;
                isRepeating = true;
            }
        }

        return noOfChanges;
    }
}
