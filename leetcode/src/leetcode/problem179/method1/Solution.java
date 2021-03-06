package leetcode.problem179.method1;

public class Solution {
    public String largestNumber(int[] nums) {
        if(nums == null || nums.length == 0){
            return "";
        }
        
        sort(nums, 0, nums.length-1);
        
        if(nums[0] == 0) {
        	return "0";
        }
        
        String r = "";
        for(int n:nums){
            r+= n;
        }
        
        return r;
    }
    
    private void sort(int[] nums, int start, int end){
        if(start >= end ){
            return;
        }
        
        int i = start;
        int j = end;
        int key = nums[start];
        while(i < j){
            while(j > i && compare(nums[j], key) <= 0){
                --j;
            }
            
            nums[i] = nums[j];
            
            while(i < j && compare(nums[i], key) >= 0 ){
                ++i;
            }
            
            nums[j] = nums[i];
             
        }
        
        nums[i] = key;
        
        sort(nums, start, i-1);    
        sort(nums, i+1, end);
    }
    
    private int compare(int n1, int n2){
        String s1 = n1+""+n2;
        String s2 = n2+""+n1;
        
        return s1.compareTo(s2);
    }

	public static void main(String[] args) {
		int[] nums = new int[]{1, 2};
		
		Solution s = new Solution();
		System.out.println(s.largestNumber(nums));
		

	}

}
