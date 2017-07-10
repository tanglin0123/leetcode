package leetcode.problem416.method3;

// knapsack problem. dp
public class Solution {
    public boolean canPartition(int[] nums) {
    	int len = nums.length;
    	if( len == 1){
    		return false;
    	}
    	
    	int sum = 0;
    	int max = 0;
    	for(int i = 0 ; i < len ; ++i){
    		int cur = nums[i];
    		sum += cur;
    		if(cur > max){
    			max = cur;
    		}
    	}
    	
    	if(sum % 2 != 0){
    		return false;
    	}
        
    	int target = sum/2;
    	if(max > target){
    		return false;
    	}
    	
    	// not optimize space
    	boolean[][] dp = new boolean[len][target+1];
    	
    	for(int i = 0; i < len; ++i){
    		dp[i][0] = true;
    	}
    	 
    	for(int i = 1; i <= target; ++i){
    		dp[0][i] = (nums[0] == i);
    		for(int j = 1; j < len; ++j){
    			dp[j][i] = dp[j-1][i] || (i >= nums[j] && dp[j-1][i - nums[j]]);
    		}
    	}
    	
    	return dp[len-1][target];
    }
    

	public static void main(String[] args) {
		// int[] a = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,100};
		//int[] a = {1,2,3,6};
		//int[] a = {1,2,4,6};
		// int[] a = {2,2,2,6,4};
		
		int[] a = {1,5,11,5};
		//int[] a = {3,3,3,4,5};
		// int[] a = {1,2,3,4,5,6,7};
		// int[] a = {28,63,95,30,39,16,36,44,37,100,61,73,32,71,100,2,37,60,23,71,53,70,69,82,97,43,16,33,29,5,97,32,29,78,93,59,37,88,89,79,75,9,74,32,81,12,34,13,16,15,16,40,90,70,17,78,54,81,18,92,75,74,59,18,66,62,55,19,2,67,30,25,64,84,25,76,98,59,74,87,5,93,97,68,20,58,55,73,74,97,49,71,42,26,8,87,99,1,16,79};
		
		Solution s = new Solution();
		System.out.println(s.canPartition(a));

	}

}
