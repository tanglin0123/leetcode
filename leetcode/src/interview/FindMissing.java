package interview;

// http://www.1point3acres.com/bbs/thread-425526-1-1.html
public class FindMissing {
	
	public int findOne(int[] nums) {
		int len = nums.length;
		int min = nums[0];
		int max = nums[len-1];
		
		if(max - min == len-1) {
			return Integer.MIN_VALUE;
		}
		
		int s = 0;
		int e = len-1;
		
		while(s < e) {
			if(e - s == 1) {
				if(nums[e] - nums[s] > e - s) {
					return nums[s] + 1;
				} else {
					return Integer.MIN_VALUE;
				}
			}
			
			int m = s + (e - s) / 2;
			
			if(nums[e] - nums[m] > e - m) {
				s = m;
			} else {
				e = m;
			}
		}
		
		return Integer.MIN_VALUE;
	}
	
	public int findNth(int[] nums, int i) {
		int len = nums.length;
		int min = nums[0];
		int max = nums[len-1];
		
		if(max - min == len-1) {
			return Integer.MIN_VALUE;
		}
		
		int s = 0;
		int e = len-1;
		
		while(s < e) {
			int gap = (nums[e] - nums[s]) - (e - s);
			if(gap < i ) {
				return Integer.MIN_VALUE;
			}
			if(e - s == 1) {
				return nums[s] + i;
			}
			
			int m = s + (e - s) / 2;
			
			int left = (nums[m] - nums[s]) - (m - s);
			
			if( left < i) {
				s = m;
				i = i - left;
			} else {
				e = m;
			}
		}
		
		return Integer.MIN_VALUE;
	}
	
	public static void main(String[] args) {
		int[] nums = {2, 3, 4, 6, 7};
		
		FindMissing f = new FindMissing();
		System.out.println(f.findOne(nums));
		
		int[] nums2 = {0, 2, 3, 4, 6, 7, 8, 9, 10, 12, 15, 16, 18};
		int i = 4;
		System.out.println(f.findNth(nums2, i));
	}

}
