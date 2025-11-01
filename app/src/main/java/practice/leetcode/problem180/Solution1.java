package practice.leetcode.problem180;

import java.util.Arrays;

public class Solution1 {
    public void rotate(int[] nums, int k) {
        if (k == 0) {
            return;
        }

        int n = k % nums.length;


        int[] tmp = new int[n];

        for(int i = n - 1, j = nums.length - 1; i >= 0; --i, --j ) {
            tmp[i] = nums[j];
        }

        for(int r = nums.length - 1, l = nums.length - 1 - n; l >= 0; --r, --l ) {
            nums[r] = nums[l];
        }

        for(int i = 0; i < n; ++i) {
            nums[i] = tmp[i];
        }
    }

    public static void main(String[] args) {
        Solution1 sln = new Solution1();

        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;

        sln.rotate(nums, k);

        System.out.println(Arrays.toString(nums));
    }
}
