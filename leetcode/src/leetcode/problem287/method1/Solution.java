package leetcode.problem287.method1;


// O(n^2)
public class Solution {
    public int findDuplicate(int[] nums) {
        if(nums == null || nums.length == 0){
        	return 0;
        }
        
        int n = nums.length - 1;
        int iCount = 0;
        for(int i = 0; i <=n ; ++i ){
        	iCount = 0;
        	for(int j : nums){
        		if(i==j){
        			++iCount;
        		}
        	}
        	
        	if(iCount > 1){
    			return i;
    		}
        }
        
        return 0;
    }
}
