package com.leetcode.array;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/non-overlapping-intervals/description/">Non-overlapping Intervals</a>
 * Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 * <p>
 * Example 1:
 * <p>
 * Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * Output: 1
 * Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
 * Example 2:
 * <p>
 * Input: intervals = [[1,2],[1,2],[1,2]]
 * Output: 2
 * Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
 * Example 3:
 * <p>
 * Input: intervals = [[1,2],[2,3]]
 * Output: 0
 * Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 * <p>
 * Input: intervals = [[-52,31],[-73,-26],[82,97],[-65,-11],[-62,-49],[95,99],[58,95],[-31,49],[66,98],[-63,2],[30,47],[-40,-26]]
 * Output: 7
 * Constraints:
 * <p>
 * 1 <= intervals.length <= 105
 * intervals[i].length == 2
 * -5 * 104 <= starti < endi <= 5 * 104
 */
public class P435NonOverlappingIntervals {

    public static void main(String[] args) {
        int[][] intervals = {{-52, 31}, {-73, -26}, {82, 97}, {-65, -11}, {-62, -49}, {95, 99}, {58, 95}, {-31, 49}, {66, 98}, {-63, 2}, {30, 47}, {-40, -26}};
        int count = eraseOverlapIntervals(intervals);
        System.out.println(count);
    }

    public static int eraseOverlapIntervals(int[][] intervals) {

        Arrays.sort(intervals, (f, s) -> f[1] == s[1] ? f[0] - s[0] : f[1] - s[1]);
        int lastEnd = intervals[0][1];
        int count = 0;
        for (int i = 1; i < intervals.length; i++) {

            if (lastEnd > intervals[i][0]) { // lastEnd > start of next
                count += 1;
            } else {
                lastEnd = intervals[i][1];
            }
        }

        return count;
    }
}
