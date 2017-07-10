package leetcode.problem525.method1;

// time limit exceeded
public class Solution {
    public int findMaxLength(int[] nums) {
        if(nums == null || nums.length < 2){
            return 0;
        }
        
        int[] zero = new int[nums.length+1];
        int[] one = new int[nums.length+1];
        
        for(int i = 0; i < nums.length; ++i){
            int n = nums[i];
            zero[i+1] = zero[i];
            one[i+1] = one[i];
            if(n == 0){
                ++zero[i+1];
            } else if(n==1){
                ++one[i+1];
            }
        }
        
        for(int len = nums.length; len >= 1; --len){
            for(int i = 0; i + len <= nums.length; ++i){
                int zcount = zero[i+len] - zero[i];
                int ocount = one[i+len] - one[i];
                if(zcount == ocount){
                    return len;
                }
            }
        }
        
        return 0;
    }

	public static void main(String[] args) {
		int[] a = {0, 1};
		
		Solution s = new Solution();
		System.out.println(s.findMaxLength(a));
	}

}
