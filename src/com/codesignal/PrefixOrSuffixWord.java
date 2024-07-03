package com.codesignal;

import java.util.Arrays;
import java.util.List;

/**
 * https://garmadon.notion.site/Uber-scaled-solutions-Generative-AI-ddc40726ddaa4a578e20db7e16285ffc
 * https://garmadon.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F0be809f4-04c2-4ed1-a319-440600827738%2F58c84c13-f304-467e-ac65-28c2dab707be%2FScreenshot_from_2024-06-19_15-55-10.png?table=block&id=3fa30920-e317-45f4-99ca-9ac2449a681e&spaceId=0be809f4-04c2-4ed1-a319-440600827738&width=1360&userId=&cache=v2
 * https://garmadon.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F0be809f4-04c2-4ed1-a319-440600827738%2F390be653-2e8d-4fe2-bc43-48a5ca05ef15%2FScreenshot_from_2024-06-19_15-55-13.png?table=block&id=163f0221-79d0-49bb-b6eb-f84decdc37ef&spaceId=0be809f4-04c2-4ed1-a319-440600827738&width=1360&userId=&cache=v2
 */
public class PrefixOrSuffixWord {

    public static void main(String[] args) {
        List<String> words1 = Arrays.asList("back", "backdoor", "gammon", "backgammon", "comeback", "come", "door");
        System.out.println(solution(words1));  // Output should be 3

        List<String> words2 = Arrays.asList("cba", "a", "a", "b", "ba", "ca");
        System.out.println(solution(words2));  // Output should be 8

    }
    public static long solution(List<String> words) {
        long count = 0;
        int n = words.size();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    if (words.get(i).equals(words.get(j)) || words.get(i).endsWith(words.get(j)) || words.get(j).endsWith(words.get(i))) {
                        count++;
                    }
                }
            }
        }

        return count/2;

    }
}