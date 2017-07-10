package leetcode.problem416.method2;

import java.util.*;


// knapsack problem. modified backtrace. time limit exceed
public class Solution {
    public boolean canPartition(int[] nums) {
    	int len = nums.length;
    	if( len == 1){
    		return false;
    	}
    	
    	Arrays.sort(nums);
    	
    	
    	List<int[]> stats = new ArrayList<int[]>(len);
    	
    	int right = nums[len-1];
    	int curCount = 1;
    	int sum = right;
    	for(int i = len - 2 ; i >=0 ; --i){
    		int cur = nums[i];
    		
    		if(cur != right){
    			stats.add(new int[]{right, curCount});
    			curCount = 1;
    		} else { // cur == right
    			curCount += 1;
    		}
    		sum += cur;
    		right = cur;
    	}
    	stats.add(new int[]{right, curCount});
    	
    	if(sum % 2 != 0){
    		return false;
    	}
        
    	int target = sum/2;
    	for(int[] item: stats){
    		if(item[0] > target){
    			return false;
    		}
    	}
    	
    	int s = 0;
    	return pick(stats, 0, s, target);

    }
    
    boolean pick(List<int[]> stats, int idx, int sum, int target){
    	
    	if(sum == target){
    		return true;
    	}
    	
    	if(sum > target){
    		return false;
    	}
    	
    	for(int i = idx; i < stats.size(); ++i){
    		int[] item = stats.get(i);
    		for(int j = 0; j <= item[1]; ++j){
    			sum += j * item[0];
    			boolean succ = pick(stats, i+1, sum, target);
    			if(succ){
        			return true;
        		}
    			sum -= j * item[0];
    		}
    	}
    	
    	return false;
    }

	public static void main(String[] args) {
		// int[] a = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,100};
		//int[] a = {1,2,3,6};
		//int[] a = {1,2,4,6};
		// int[] a = {2,2,2,6,4};
		
		int[] a = {28,63,95,30,39,16,36,44,37,100,61,73,32,71,100,2,37,60,23,71,53,70,69,82,97,43,16,33,29,5,97,32,29,78,93,59,37,88,89,79,75,9,74,32,81,12,34,13,16,15,16,40,90,70,17,78,54,81,18,92,75,74,59,18,66,62,55,19,2,67,30,25,64,84,25,76,98,59,74,87,5,93,97,68,20,58,55,73,74,97,49,71,42,26,8,87,99,1,16,79};
		
		Solution s = new Solution();
		System.out.println(s.canPartition(a));

	}

}
