package leetcode.problem477.method1;

public class Solution {
    public int totalHammingDistance(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        
        int max = Integer.MIN_VALUE;
        for(int n : nums){
            max = Math.max(max, n);
        }
        
        int sum = 0;
        int mask = 1;
        while(mask <= max){
            int ones = 0;
            int zeros = 0;
            
            for(int n : nums){
                if((n & mask) != 0){
                    ++ones;
                } else {
                    ++zeros;
                } 
            }
            
            sum += zeros * ones;
            
            mask <<= 1;
        }
        
        return sum;
    }


	public static void main(String[] args) {
		int[] a = {14, 4, 2};
		
		Solution s = new Solution();
		System.out.println(s.totalHammingDistance(a));

	}

}
