package leetcode.problem153.method1;

public class Solution {
    public int findMin(int[] nums) {
        if(nums==null || nums.length == 0){
            return Integer.MAX_VALUE;
        }
        
        int n = nums.length;
        
        if(n==1){
            return nums[0];
        }
        
        if(n==2){
            return nums[0]<nums[1]?nums[0]:nums[1];
        }
        
        
        int i=0,j=n-1;
        while(i<j){
            if(nums[j] > nums[i]){
                return nums[i];
            }
            
            int m = (i+j)/2;
            if(m==0){
                if(nums[0] < nums[1] && nums[0] < nums[n-1]){
                   return nums[0];
                }
            } else if(m==n-1){
                if(nums[n-1] < nums[0] && nums[n-1] < nums[n-2]){
                   return nums[n-1];
                }        
            } else {
                if(nums[m] <nums[m-1] && nums[m] < nums[m+1]){
                    return nums[m];
                }    
            }
            
            if(nums[i] <= nums[m]){
                i=m+1;
            }else{
                j=m-1;
            }    
            
        }
        
        if(i==0){
            if(nums[0] < nums[1] && nums[0] < nums[n-1]){
               return nums[0];
            }
        } else if(i==n-1){
            if(nums[n-1] < nums[0] && nums[n-1] < nums[n-2]){
               return nums[n-1];
            }        
        } else {
            if(nums[i] <nums[i-1] && nums[i] < nums[i+1]){
                return nums[i];
            }    
        }
        
        return Integer.MAX_VALUE;
    }

	public static void main(String[] args) {
		int[] nums = new int[]{2,3,4,5,1};
		
		Solution s = new Solution();
		System.out.println(s.findMin(nums));

	}

}
