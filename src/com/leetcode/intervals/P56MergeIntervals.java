package com.leetcode.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/merge-intervals/description/">56. Merge Intervals</a>
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
 * <p>
 * Example 1:
 * <p>
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 * Example 2:
 * <p>
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 * <p>
 * Constraints:
 * <p>
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 * Level:Medium
 */
public class P56MergeIntervals {

    public static void main(String[] args) {
        System.out.println(
                Arrays.deepToString(merge(new int[][]{
                        {1, 3}, {2, 6}, {8, 10}, {15, 18}
                }))
        ); // [[1,6],[8,10],[15,18]]
    }

    public static int[][] merge(int[][] intervals) {

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
//        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> ans = new ArrayList<>();
        int[] lastInterval = intervals[0];

        for (int[] currInterval : intervals) {
            if (lastInterval[1] < currInterval[0]) {
                ans.add(lastInterval);
                lastInterval = currInterval;
            } else {
                lastInterval[1] = Math.max(lastInterval[1], currInterval[1]);
            }
        }

        ans.add(lastInterval);
        return ans.toArray(new int[ans.size()][]);
    }

    public static int[][] mergeBest(int[][] intervals) {

        if (intervals.length == 1) {
            return intervals;
        }
        int max = -1;
        for (int i = 0; i < intervals.length; i++) {
            max = Math.max(intervals[i][0], max);
        }
        if (max == -1) {
            return new int[][]{intervals[0]};
        }
        int[] timeTable = new int[max + 1];
        for (int i = 0; i < intervals.length; i++) {
            int startTime = intervals[i][0];
            int endTime = intervals[i][1];
            timeTable[startTime] = Math.max(endTime + 1, timeTable[startTime]);
        }
        int resultSize = 0, currEnd = -1, currStart = -1;
        for (int i = 0; i < timeTable.length; i++) {
            if (timeTable[i] != 0) {
                if (currStart == -1) {
                    currStart = i;
                }
                currEnd = Math.max(timeTable[i] - 1, currEnd);
            }
            if (currEnd == i) {
                intervals[resultSize++] = new int[]{currStart, currEnd};
                currEnd = -1;
                currStart = -1;
            }
        }
        if (currStart != -1) {
            intervals[resultSize++] = new int[]{currStart, currEnd};
        }
        if (intervals.length == resultSize) {
            return intervals;
        }
        int[][] res = new int[resultSize][];
        for (int i = 0; i < resultSize; i++) {
            res[i] = intervals[i];
        }
        return res;

    }
}
