package practice.meta.leetcode.p560;

public class Solution1 {
    // Memory Limit Exceeded 61 / 93 testcases passed
    public int subarraySum1(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;

        int count = 0;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; ++i) {
            dp[0][i] = nums[i];
            if (nums[i] == k) {
                ++ count;
            } 
        }

        for (int len = 2; len <= n; ++len) {
            for (int end = 0 + len - 1 ; end < n; ++end ) {
                dp[len - 1][end] = dp[len - 2][end - 1] + nums[end];
                if (dp[len - 1][end] == k) {
                    ++ count;
                }
            }
        }

        return count;
    }

    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;

        int count = 0;
        int[] dpLast = new int[n];

        for (int i = 0; i < n; ++i) {
            dpLast[i] = nums[i];
            if (nums[i] == k) {
                ++ count;
            } 
        }

        int[] dpCur = new int[n];
        for (int len = 2; len <= n; ++len) {
            for (int end = 0 + len - 1 ; end < n; ++end ) {
                dpCur[end] = dpLast[end - 1] + nums[end];
                if (dpCur[end] == k) {
                    ++ count;
                }
            }
            int[] tmp = dpCur;
            dpCur = dpLast;
            dpLast = tmp;
        }

        return count;
    }

    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();

        int[] nums1 = {1,1,1};
        
        int k1 = 2;

        System.out.println(solution1.subarraySum(nums1, k1));
    }
}
