package leetcode.problem377.method1;

// backtrace. time limit exceeded
public class Solution {

	public int combinationSum4(int[] nums, int target) {

		if (nums == null || nums.length == 0) {
			return 0;
		}

		int[] total = new int[] { 0 };
		backtrace(nums, target, total);

		return total[0];
	}

	void backtrace(int[] nums, int target, int[] total) {
		if (target == 0) {
			++total[0];
			return;
		}

		if (target < 0) {
			return;
		}

		for (int i = 0; i < nums.length; ++i) {
			backtrace(nums, target - nums[i], total);
		}
	}

	public static void main(String[] args) {
		int[] nums = new int[]{1,2,3};
		int target = 32;
		
		Solution s = new Solution();
		System.out.println(s.combinationSum4(nums, target));

	}

}
