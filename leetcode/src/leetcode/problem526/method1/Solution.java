package leetcode.problem526.method1;

import java.util.*;

public class Solution {
	public int countArrangement(int n) {
		int[] set = new int[n + 1];

		for (int i = 1; i <= n; ++i) {
			Set<Integer> div = getDiv(i);
			for (int j : div) {
				set[j] |= 1 << i;
				set[i] |= 1 << j;
			}
		}

		Set<Integer> picked = new HashSet<Integer>();
		return backtrace(1, set, n, picked);
	}

	int backtrace(int pos, int[] set, int n, Set<Integer> picked) {
		int count = 0;

		for (int i = 1; i <= n; ++i) {
			if ((set[pos] & 1 << i) != 0 && !picked.contains(i)) {
				picked.add(i);

				count += pos == n ? 1: backtrace(pos + 1, set, n, picked);

				picked.remove(i);
			}

		}

		return count;
	}

	public Set<Integer> getDiv(int n) {
		Set<Integer> r = new HashSet<Integer>();

		for (int i = 1; i <= Math.sqrt(n); ++i) {
			if (n % i == 0) {
				r.add(i);
				r.add(n / i);
			}
		}

		return r;
	}

	public static void main(String[] args) {
		int a = 3;

		Solution s = new Solution();
		System.out.println(s.countArrangement(a));
	}

}
