package leetcode.problem215.method1;

public class Solution {
    public int findKthLargest(int[] nums, int k) {
        int sk = nums.length - k + 1;
        int idx = findKthSmallest(nums, sk, 0, nums.length-1);
        return nums[idx];
    }
    
    public int findKthSmallest(int[] nums, int k, int start, int end){
        if(start == end){
            return start;
        }
        
        int pivot = nums[start];
        
        int i = start;
        int j = end;
        
        while(i<j){
            while(i<j && nums[j]>=pivot){
                --j;
            }
            
            if(i<j){
                swap(nums, i, j);
                ++i;
            }
            
            while(i<j && nums[i]<=pivot){
                ++i;
            } 
            
            if(i<j){
                swap(nums, i, j);
                --j;
            }
        }
        
        if(k == i-start+1){
            return i;
        } else if(k < i-start+1){
            return findKthSmallest(nums, k, start, end - 1);
        } else {// (k > i+start+1
            return findKthSmallest(nums, k - (i-start+1), i+1, end );
        }
        
        
    }
    
    public void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


	public static void main(String[] args) {
		int[] nums = new int[]{1,2,3,4,5,6};
		int k = 1;
		
		Solution s = new Solution();
		System.out.println(s.findKthLargest(nums, k));

	}

}
