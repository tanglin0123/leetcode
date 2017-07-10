package leetcode.problem486.method1;

public class Solution {
    public boolean PredictTheWinner(int[] nums) {
        if(nums == null || nums.length <= 1){
            return true;
        }
        
        int[] opSum = new int[]{0};
        int mySum = maxSum(nums, 0, nums.length - 1, 0, opSum);
        
        if(mySum >= opSum[0]){
            return true;
        } else {
            return false;
        }
    }
    
    private int maxSum(int[] nums, final int start, final int end, int mySum, int[] opSum){
        if(start == end){
            return mySum + nums[start];
        }
        
        if(end == start + 1){
            if(nums[start] >= nums[end]){
            	opSum[0] += nums[end];
                return mySum + nums[start];
            } else {
            	opSum[0] += nums[start];
                return mySum + nums[end];
            }
        }
        
        int[] tmpMySum1 = new int[]{mySum + nums[start]};
        int opSum1 = maxSum(nums, start+1, end, opSum[0], tmpMySum1);
        int[] tmpMySum2 = new int[]{mySum + nums[end]};
        int opSum2 = maxSum(nums, start, end-1, opSum[0], tmpMySum2);
        
        if(opSum1 < opSum2){
        	opSum[0] = opSum1;
            return tmpMySum1[0];
        }else{
        	opSum[0] = opSum2;
            return tmpMySum2[0];
        }
    }
    
	public static void main(String[] args) {
		int[] nums = new int[]{1,5,233,7};
		
		Solution s = new Solution();
		System.out.println(s.PredictTheWinner(nums));
	}

}
