package leetcode.problem456.method1;

import java.util.*;

public class Solution {
	public boolean find132pattern(int[] nums) {
		if (nums == null || nums.length <= 2) {
			return false;
		}

		int trend = 0;
		int pre = nums[0];
		int min = nums[0];
		int max = nums[0];

		List<int[]> list = new ArrayList<int[]>();

		for (int i = 1; i < nums.length; ++i) {
			int cur = nums[i];

			if (cur == pre) {
				continue;
			}
			
			if (cur > pre) {
				if (trend != 1 && pre < min) {
					min = pre;
				}
				max = cur;
				trend = 1;
				pre = cur;
			
			} else { // cur < pre)
				trend = -1;
				max = pre;

				boolean add = true;

				for (int j = list.size() - 1; j >= 0; --j) {
					int[] item = list.get(j);
					if (item[0] < min && item[1] > max) {
						add = false;
						break;
					}
				}

				if (add) {
					list.add(new int[] { min, max });
				}

				pre = cur;
			}
			
			for (int j = list.size() - 1; j >= 0; --j) {
				int[] item = list.get(j);
				if (item[0] < cur && item[1] > cur) {
					return true;
				}
			}
		}

		return false;
	}

	public static void main(String[] args) {
		int[] a = { 3, 1, 4, 2 };

		Solution s = new Solution();

		System.out.println(s.find132pattern(a));

	}

}
