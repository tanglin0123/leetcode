package practice.meta.leetcode.p1762;

import java.util.*;

public class Solution {
    public int[] findBuildings(int[] heights) {
        Stack<Integer> stk = new Stack<>();

        int max = Integer.MIN_VALUE;

        for (int i = heights.length - 1 ; i >= 0 ; --i ) {
            if (heights[i] > max) {
                stk.push(i);
                max = heights[i];
            }
        }

        int[] result = new int[stk.size()];
        int i = 0;
        while (!stk.isEmpty()) {
            result[i++] = stk.pop(); 
        }

        return result;
    }
}
