package com.codesignal;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * <a href="https://garmadon.notion.site/Uber-scaled-solutions-Generative-AI-ddc40726ddaa4a578e20db7e16285ffc">...</a>
 * <a href="https://garmadon.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F0be809f4-04c2-4ed1-a319-440600827738%2F8162f28e-6f8c-4a93-a524-efb56b1195d8%2FScreenshot_from_2024-06-19_15-09-16.png?table=block&id=f7d413d4-95ef-413d-b46d-5fe740878bc4&spaceId=0be809f4-04c2-4ed1-a319-440600827738&width=1360&userId=&cache=v2">...</a>
 */
public class ArrayEvenOddEqualSum {

    public static String solution(List<Integer> array) {
        int sumOfOddPositions = IntStream.range(0, array.size())
                .filter(i -> i % 2 != 0) // filter odd positions
                .map(array::get)         // map to elements at these positions
                .sum();

        int sumEvenPositions = IntStream.range(0, array.size())
                .filter(i -> i % 2 == 0) // filter even positions
                .map(array::get)         // map to elements at these positions
                .sum();                  // sum elements

        return sumEvenPositions == sumOfOddPositions ? "equal" :
                (sumEvenPositions > sumOfOddPositions ? "even" : "odd");
    }

    public static void main(String[] args) {
        assert Objects.equals(solution(List.of(1, 2, 3, 4, 5)), "even");
        assert Objects.equals(solution(List.of(-1, 4, 3, -2)), "equal");
        assert Objects.equals(solution(List.of(1, 2, 3, 4, 5)), "even");
    }
}
