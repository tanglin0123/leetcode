package leetcode.problem4.method1;

public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    	if((nums1 == null || nums1.length == 0) && (nums2 == null || nums2.length == 0)){
    		return 0.0d;
    	}
    	
    	if(nums1 == null || nums1.length == 0){
    		return nums2.length % 2 == 0 ? ((double)nums2[nums2.length / 2 - 1] + (double)nums2[nums2.length / 2])/ 2.0d : (double)nums2[nums2.length / 2]; 
    	}
    	
    	if(nums2 == null || nums2.length == 0){
    		return nums1.length % 2 == 0 ? ((double)nums1[nums1.length / 2 - 1] + (double)nums1[nums1.length / 2])/ 2.0d : (double)nums1[nums1.length / 2]; 
    	}
    	
    	if(nums1[0] > nums2[0] || (nums1[0] == nums2[0] && nums1[nums1.length - 1] > nums2[nums2.length -1])){
    		int[] tmp = nums1;
    		nums1 = nums2;
    		nums2 = tmp;
    	} 
    	
    	int sumlen = nums1.length + nums2.length;
    	boolean even = sumlen % 2 == 0;
    	
    	if(nums1[nums1.length - 1] <= nums2[0]){
    		if(nums1.length < nums2.length){
    			return even ? ((double)nums2[ sumlen/2 - nums1.length - 1 ] + (double)nums2[ sumlen/2 - nums1.length ])/2.0d : (double)nums2[ sumlen/2 - nums1.length ];
    		} else if (nums1.length > nums2.length){
    			return even ? ((double)nums1[sumlen / 2 - 1] + (double)nums1[sumlen / 2])/ 2.0d : (double)nums1[sumlen / 2];
    		} else{ // ==
    			return ((double)nums1[nums1.length - 1] + (double)nums2[0])/2.0d;
    		}
    	}
    	
    	
    	// make nums1.length >= nums2.length
    	
    	if(nums1.length < nums2.length){
    		int[] tmp = nums1;
    		nums1 = nums2;
    		nums2 = tmp;
    	}
    	
    	int i = sumlen/2;
    	int j = sumlen/2 - i;
    	
    	int imin = 0;
    	int imax = nums1.length - 1;
    	
    	while( i >= 0 && i <= nums1.length){
    		j = sumlen/2 - i;
    		if( j < 0 || j > nums2.length){
    			imin = i + 1;
    			i = (imin + imax)/2;
    			continue;
    		}
    		
    		if(i==0){
    			if(j > 0 ){ 
    				if(nums1[i] >= nums2[j - 1]){ // found
    					int big = 0;
    					if( j <= nums2.length - 1){
    						big = nums1[i] < nums2[j] ? nums1[i] : nums2[j];
    					} else {
    						big = nums1[i];
    					}
    					
    					if(even){
    						return ((double)big + (double)nums2[j -1])/ 2.0d;
    					} else{
    						return big;
    					}
    				} else { // nums1[i] < nums2[j - 1]
    					imin = i + 1;
    					i = (imin + imax)/2;
    					
    				}
    			} else { // j == 0
    				// no this case, as 0 + 0 == sumlen/2 => sumlen == 1 or 0, already handled
    			}
    			continue;
    		} 
    		
    		if(j == 0 ){
    			if(i > 0){ 
    				if(nums2[j] >= nums1[i - 1]){ // found
    					int big = 0;
    					if( i <= nums1.length - 1){
    						big = nums1[i] < nums2[j] ? nums1[i] : nums2[j];
    					} else {
    						big = nums2[j];
    					}
    					
    					if(even){
    						return ((double)big + (double)nums1[i - 1])/2.0d;
    					}else {
    						return big;
    					}
    				} else { // nums2[j] < nums1[i - 1]
    					imax = i - 1;
    					i = (imin + imax)/2;
    					
    				}
    			} else { // i == 0
    				// no this case, as 0 + 0 == sumlen/2 => sumlen == 1 or 0, already handled
    			}
    			continue;
    		}
    		
    		if(i == nums1.length){
    			if(nums2[j] >= nums1[i - 1]){ // found
    				int small = 0;
    				if(j>=1){
    					small = nums1[i-1] > nums2[j-1] ? nums1[i-1]:nums2[j-1];
    				}else{
    					small = nums1[i-1];
    				}
    				
					if(even){
						return ((double)nums2[j] + (double)small)/2.0d;
					}else {
						return nums2[j];
					}
				} else { // nums2[j] < nums1[i - 1]
					imax = i - 1;
					i = (imin + imax)/2;
					
				}
    			continue;
    		}
    		
    		if(j == nums2.length){
    			if(nums1[i] >= nums2[j - 1]){ // found
    				int small = 0;
    				if(i>=1){
    					small = nums1[i-1] > nums2[j-1] ? nums1[i-1]:nums2[j-1];
    				}else{
    					small = nums2[j-1];
    				}
    				
					if(even){
						return ((double)small + (double)nums1[i])/2.0d;
					}else {
						return nums1[i];
					}
				} else { // nums1[i] < nums2[j - 1]
					imin = i + 1;
					i = (imin + imax)/2;
					
				}
    			continue;
    		}
    		    		
    		if((nums1[i-1] <= nums2[j] && nums2[j-1] <= nums1[i]) ){ // found
    			if(even){
    				int big = 0;
    				if(nums1[i] > nums2[j]){
    					big = nums2[j];
    				} else {
    					big = nums1[i];
    				}
    				int small = 0;
    				if(nums1[i-1] > nums2[j-1]){
    					small = nums1[i-1];
    				}else{
    					small = nums2[j - 1];
    				}
    				
    				return ((double)big + (double)small)/2.0d;
    			}else{
    				if(nums1[i] > nums2[j]){
    					return nums2[j];
    				} else {
    					return nums1[i];
    				}
    			}
    		} else if(nums1[i-1] > nums2[j]){
    			imax = i - 1;
    			i = (imin + imax) / 2;
    			
    		} else if(nums2[j-1] > nums1[i]){
    			imin = i + 1; 
    			i = (imin + imax)/2;
    			
    		}
    	}
    	
    	if(i == 0){
    		if(even){
    			return ((double)nums1[0] + (double)nums2[nums2.length - 1])/2.0d;
    		} else{
    			return (double)nums1[0];
    		}
    	} else if(j== 0){
    		if(even){
    			return ((double)nums2[0] + (double)nums1[nums1.length - 1])/2.0d;
    		} else{
    			return (double)nums2[0];
    		}
    	} else if(i > nums1.length){
    		if(even){
    			return ((double)nums2[0] + (double)nums1[nums1.length - 1])/2.0d;
    		} else{
    			return (double)nums2[0];
    		}
    	} else { // if(j > nums2.length)
    		if(even){
    			return ((double)nums1[0] + (double)nums2[nums2.length - 1])/2.0d;
    		} else{
    			return (double)nums1[0];
    		}
    	}
    }
    
    public static void main(String[] args){
    	Solution s = new Solution();
//    	System.out.println(s.findMedianSortedArrays(new int[]{1,3}, new int[]{2}));
//    	System.out.println(s.findMedianSortedArrays(new int[]{1,2}, new int[]{3,4}));
//    	System.out.println(s.findMedianSortedArrays(new int[]{1,2}, new int[]{1,2,3}));
//    	System.out.println(s.findMedianSortedArrays(new int[]{2}, new int[]{1,3,4}));
    	System.out.println(s.findMedianSortedArrays(new int[]{4}, new int[]{1,2,3,5}));
    }
}
