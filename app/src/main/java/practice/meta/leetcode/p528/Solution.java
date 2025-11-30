package practice.meta.leetcode.p528;

import java.util.Random;

public class Solution {

    private int[][] ranges;
    private int[] weight;
    private int totalWeight;

    public Solution(int[] w) {
        this.weight = w;
        this.populateRanges();
    }
    
    // binary search
    public int pickIndex() {
        Random random = new Random();

        int num = random.nextInt(totalWeight);

        int l = 0;
        int r = ranges.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (ranges[mid][0] <= num && num <= ranges[mid][1]) {
                return mid;
            }

            if (num > ranges[mid][1]) {
                l = mid + 1;
            } else if (num < ranges[mid][0]) {
                r = mid - 1;
            }
        }
        
        return -1;
    }

    // linear search, accpeted, beat 5.10%
    public int pickIndex1() {
        Random random = new Random();

        int r = random.nextInt(totalWeight);

        for (int i = 0; i < ranges.length; ++i) {
            if (ranges[i][0] <= r && r <= ranges[i][1]) {
                return i;
            }
        }

        return -1;
    }

    private void populateRanges() {
        this.ranges = new int[this.weight.length][2];

        int start = 0;
        int end;
        for(int i = 0; i < weight.length; ++i) {
            end = start + weight[i] - 1;
            totalWeight += weight[i];
            ranges[i] = new int[] {start, end} ;
            start = end + 1;
        }
    }

}
