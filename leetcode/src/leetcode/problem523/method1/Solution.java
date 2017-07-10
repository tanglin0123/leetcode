package leetcode.problem523.method1;


// dp, time limit exceeded
public class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if(nums == null || nums.length <= 1){
            return false;
        }
        
        int[][] dp = new int[nums.length+1][nums.length];
        
        for(int i = 0; i < nums.length; ++i){
            dp[1][i] = k == 0 ? nums[i] : nums[i] % k;
        }
        
        for(int len = 2; len <= nums.length; ++len){
            for(int i = 0; i + len <= nums.length ; ++ i){
                dp[len][i] = k == 0 ? dp[len-1][i+1] + nums[i] : (dp[len-1][i+1] + nums[i]) % k;
                if(dp[len][i] == 0){
                    return true;
                }
            }
        }
        
        return false;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
