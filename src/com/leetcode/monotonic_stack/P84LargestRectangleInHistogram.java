package com.leetcode.monotonic_stack;

import java.util.Stack;

/**
 * <a href="https://leetcode.com/problems/largest-rectangle-in-histogram/">84. Largest Rectangle in Histogram</a>
 * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.
 *
 * Example 1:
 *
 * Input: heights = [2,1,5,6,2,3]
 * Output: 10
 * Explanation: The above is a histogram where width of each bar is 1.
 * The largest rectangle is shown in the red area, which has an area = 10 units.
 * Example 2:
 *
 * Input: heights = [2,4]
 * Output: 4
 *
 * Constraints:
 *
 * 1 <= heights.length <= 105
 * 0 <= heights[i] <= 104
 * Level:Hard
 */
public class P84LargestRectangleInHistogram {

    public static void main(String[] args) {
        System.out.println(largestRectangleArea(
                new int[]{2,1,5,6,2,3}) // 10
        );
    }

    public static int largestRectangleArea(int[] heights) {
        Stack<Integer> st = new Stack<>();

        int N = heights.length;

        int[] leftMinIdxArray = new int[N];

        for(int i=0; i<N; i++) {
            int currHeight = heights[i];

            while(!st.isEmpty() && currHeight <= heights[st.peek()]) {
                st.pop();
            }
            leftMinIdxArray[i] = st.isEmpty() ? -1 : st.peek();
            st.add(i);
        }

        // System.out.println(Arrays.toString(leftMinIdxArray));

        st.clear();
        int largestRectangleArea = Integer.MIN_VALUE;

        for(int i=N-1; i>=0; i--) {
            int currHeight = heights[i];

            while(!st.isEmpty() && currHeight <= heights[st.peek()]) {
                st.pop();
            }
            int rightMinIdx = st.isEmpty() ? N : st.peek();
            // System.out.print(rightMinIdx+", ");
            largestRectangleArea = Math.max(
                    largestRectangleArea,
                    (rightMinIdx - leftMinIdxArray[i] - 1) * currHeight
            );
            st.add(i);
        }
        // System.out.println();

        return largestRectangleArea;
    }
}
