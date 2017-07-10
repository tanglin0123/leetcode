package leetcode.problem462.method1;

public class Solution {
	public int minMoves2(int[] nums) {
		if (nums == null || nums.length <= 1) {
			return 0;
		}

		int n = nums.length / 2 + 1;
		int m = this.findNth(nums, n, 0, nums.length - 1);

		int steps = 0;

		for (int i : nums) {
			steps += Math.abs(i - m);
		}

		return steps;

	}

	int findNth(int[] nums, int n, int start, int end) {
		int i = start;
		int j = end;

		while (i < j) {
			int v = nums[i];
			while (j > i && nums[j] > v) {
				--j;
			}

			if (j == i) {
				break;
			} else {
				nums[i] = nums[j];
				nums[j] = v;
			}

			while (i < j && nums[i] <= v) {
				++i;
			}

			if (i == j) {
				break;
			} else {
				nums[j] = nums[i];
				nums[i] = v;
			}
		}

		int leftCnt = i - start + 1;

		if (leftCnt == n) {
			return nums[i];
		} else if (leftCnt > n) {
			return this.findNth(nums, n, start, i - 1);
		} else { // nth < n
			return this.findNth(nums, n - leftCnt, i + 1, end);
		}

	}

	public static void main(String[] args) {
		int[] a = { 1, 0, 0, 8, 6 };

		Solution s = new Solution();

		System.out.println(s.minMoves2(a));
	}

}
