1import java.util.*;
2
3class Solution {
4    public int removeCoveredIntervals(int[][] intervals) {
5        Arrays.sort(intervals, (a, b) -> {
6            if (a[0] == b[0]) return b[1] - a[1];
7            return a[0] - b[0];
8        });
9
10        int count = 0;
11        int prevEnd = 0;
12
13        for (int[] interval : intervals) {
14            if (interval[1] > prevEnd) {
15                count++;
16                prevEnd = interval[1];
17            }
18        }
19        return count;
20    }
21}
22