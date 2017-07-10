package interview;


public class FindInRollingList {
	// without duplicate numbers
	int findInRollingList_noDup(int[] nums, int key){
		if(nums == null || nums.length == 0){
			return -1;
		}
	
		int i = 0;
		int j = nums.length -1;
		
		while(i < j){
			int mid = (i+j)/2;
			if(key == nums[mid]){
				return mid;
			}
			
			if(nums[i] <= nums[mid]){ // left is good, right is bad
				if(key >= nums[i] && key < nums[mid] ){ // in the good left part
					j = mid -1;
				} else { // not in the good left part, search in the bad right part
					i = mid + 1;
				}
				
			} else { // left is bad, right is good
				if(key > nums[mid] && key <= nums[j]){ // in the good right part
					i = mid + 1;
				} else { // not in the good right part, search in the bad left part
					j = mid -1;
				}
			}
			
		}
		
		if(key == nums[i]){
			return i;
		}
		
		return -1;
	}
	
	
	
	 
	public static void main(String[] args){
		FindInRollingList f = new FindInRollingList();
		
		int[] nums = new int[]{5,6,7,8,9,10,1,2,3};
		int key = 2;
		
		System.out.println(f.findInRollingList_noDup(nums, key));
		
	}
	
	
}
