package interview;

public class QuickSort {
	public void sort(int[] nums){
		this.sort(nums, 0, nums.length -1);
	}
	
	public void sort(int[] nums, int start, int end){
		if(start >= end){
			return;
		}
		
		int i = start; 
		int j = end; 
		int key = nums[i];
		
		while(i < j){
			while(j > i && nums[j] >= key){
				--j;
			}
			
			nums[i] = nums[j];
			
			while(i < j && nums[i] <= key){
				++i;
			}
			
			nums[j] = nums[i];
		}
		
		nums[i] = key;
		sort(nums, start, i -1);
		sort(nums, i + 1, end);
		
	}
	
	public static void main(String[] args) {
		

	}

}
