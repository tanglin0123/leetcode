package practice.leetcode.problem452;

import java.util.Arrays;

public class Solution1 {
    public int findMinArrowShots(int[][] points) {

        if (points == null || points.length == 0) {
            return 0;
        }

        // the commented line will exceed int boundary
        // Arrays.sort(points, (o1, o2) -> (o1[1] - o2[1]));

        Arrays.sort(points, (o1, o2) -> {
            if(o1[1] > o2[1]) return 1;
            if(o1[1] < o2[1]) return -1;
            return 0;
        });

        int arrowCount = 1;
        int currentEnd = points[0][1];

        for (int p[] : points) {
            if (p[0] > currentEnd) {
                arrowCount ++;
                currentEnd = p[1];
            }
        }
        
        return arrowCount;
    }
}
