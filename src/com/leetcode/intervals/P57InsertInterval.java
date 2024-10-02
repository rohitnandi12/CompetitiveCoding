package com.leetcode.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/insert-interval/description/">57. Insert Interval</a>
 * You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
 * <p>
 * Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
 * <p>
 * Return intervals after the insertion.
 * <p>
 * Note that you don't need to modify intervals in-place. You can make a new array and return it.
 * <p>
 * Example 1:
 * <p>
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 * Example 2:
 * <p>
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 * <p>
 * Constraints:
 * <p>
 * 0 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 105
 * intervals is sorted by starti in ascending order.
 * newInterval.length == 2
 * 0 <= start <= end <= 105
 * Level:Medium
 */
public class P57InsertInterval {

    public static void main(String[] args) {
        System.out.println(
                Arrays.deepToString(insert(new int[0][2], new int[]{1, 2}))
        );
    }

    public static int[][] insert(int[][] intervals, int[] newInterval) {

        List<int[]> res = new ArrayList<>();
        int[] runningInt = newInterval;

        for (int[] currInt : intervals) {

            if (currInt[1] < runningInt[0]) {
                res.add(currInt);
            } else if (runningInt[1] < currInt[0]) {
                res.add(runningInt);
                runningInt = currInt;
            } else {
                runningInt[0] = Math.min(runningInt[0], currInt[0]);
                runningInt[1] = Math.max(runningInt[1], currInt[1]);
            }
        }
        res.add(runningInt);

        return res.toArray(new int[res.size()][]);
    }
}
