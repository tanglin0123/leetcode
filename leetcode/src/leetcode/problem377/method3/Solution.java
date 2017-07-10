package leetcode.problem377.method3;

import java.util.Arrays;

// dp. pass
public class Solution {

	public int combinationSum4(int[] nums, int target) {
		if(nums == null || nums.length == 0){
			return 0;
		}
		
		Arrays.sort(nums);
		
		int[] dp = new int[target+1];
		
		for(int i = 1; i <= target; ++i){
			if(i < nums[0]){
				dp[i] = 0;
				continue;
			} 

			for(int j = 0; j < nums.length; ++j){
				int r = i-nums[j];
				if(r > 0){
					dp[i] += dp[r];
				} else if(r == 0){
					dp[i] += 1;
				}
			}
		}
		
		return dp[target];
	}
	


	public static void main(String[] args) {
//		int[] nums = new int[]{1,2,3};
//		int target = 32;

		int[] nums = new int[]{1,2,3};
		int target = 4;
		
		Solution s = new Solution();
		System.out.println(s.combinationSum4(nums, target));

	}

}
