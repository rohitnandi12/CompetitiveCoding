package com.scaler.sorting.mergesort;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem Description
 * Given two sorted integer arrays A and B, merge B and A as one sorted array and return it as an output.
 *
 * Note: A linear time complexity is expected and you should avoid use of any library function.
 *
 *
 * Problem Constraints
 * -2×109 <= A[i], B[i] <= 2×109
 * 1 <= |A|, |B| <= 5×104
 *
 *
 * Input Format
 * First Argument is a 1-D array representing  A.
 * Second Argument is also a 1-D array representing B.
 *
 *
 * Output Format
 * Return a 1-D vector which you got after merging A and B.
 *
 *
 * Example Input
 * Input 1:
 *
 * A = [4, 7, 9]
 * B = [2, 11, 19]
 * Input 2:
 *
 * A = [1]
 * B = [2]
 *
 *
 * Example Output
 * Output 1:
 *
 * [2, 4, 7, 9, 11, 19]
 * Output 2:
 *
 * [1, 2]
 *
 *
 * Example Explanation
 * Explanation 1:
 *
 * Merging A and B produces the output as described above.
 * Explanation 2:
 *
 *  Merging A and B produces the output as described above.
 */
public class MergeTwoSortedArrays {

    public static void main(String[] args) {
        System.out.println(solve(List.of(1,3,5), List.of(2,4)));
    }

    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public static ArrayList<Integer> solve(final List<Integer> A, final List<Integer> B) {

        ArrayList<Integer> ans = new ArrayList<>();
        int a = 0;
        int b = 0;

        // Merge the lists until one of them is exhausted
        while (a < A.size() && b < B.size()) {
            if (A.get(a) < B.get(b)) {
                ans.add(A.get(a++));
            } else {
                ans.add(B.get(b++));
            }
        }

        // Add the remaining elements from list A (if any)
        while (a < A.size()) {
            ans.add(A.get(a++));
        }

        // Add the remaining elements from list B (if any)
        while (b < B.size()) {
            ans.add(B.get(b++));
        }

        return ans;
    }
}
