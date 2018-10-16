package interview;

public class QuickSortStable {
	
	public <T> void quickSort(Comparable<T>[] nums) {
		Comparable[][] newnums = new Comparable[nums.length][2];
		for(int i = 0; i < nums.length; ++i) {
			newnums[i][0] = nums[i];
			newnums[i][1] = i;
		}
		
		quickSort(newnums, 0, nums.length-1);
		
		for(int i = 0; i < nums.length; ++i) {
			nums[i] = newnums[i][0];
		}
	}
	
	private void quickSort(Comparable[][] nums, int start, int end) {
		if(end - start < 1) {
			return;
		}
		
		int i = start + 1; 
		int j = end;
		
		Comparable[] pivot = nums[start];
		while(i < j) {
			while(i < j && compare(nums[i], pivot) < 0) {
				++i;
			}
			while(i < j && compare(nums[j], pivot) > 0) {
				--j;
			}
			swap(nums, i, j);
		}
		
		int mid = compare(nums[i], pivot) > 0 ? i-1 : i;
		swap(nums, start, mid);
		
		quickSort(nums, start, mid-1);
		quickSort(nums, mid+1, end);
	}
	
	private void swap(Comparable[][] nums, int i, int j) {
		if(i != j) {
			Comparable[] tmp = nums[i];
			nums[i] = nums[j];
			nums[j] = tmp;
		}
	}
	
	private int compare(Comparable[] a, Comparable[] b) {
		int c = a[0].compareTo(b[0]);
		if(c != 0) {
			return c;
		} else {
			return a[1].compareTo(b[1]);
		}
	}
	
	
	public static void main(String[] args) {
		Integer[] nums1 = {2, 5, 7, 1, 7, 4};

		QuickSortStable q = new QuickSortStable();
		q.quickSort(nums1);
		
		for (Integer n : nums1) {
			System.out.println(n);
		}
	}

}
