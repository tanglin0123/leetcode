package leetcode.problem547.method1;

import java.util.*;

public class Solution {
	public int findCircleNum(int[][] m) {
		if (m == null) {
			return 0;
		}

		int n = m.length;
		if (n <= 1) {
			return n;
		}

		Set<Integer>[] cir = new Set[n];

		int count = 0;

		for (int i = 1; i < n; ++i) {
			for (int j = 0; j < i; ++j) {
				if (m[i][j] == 1) {
					if (cir[i] == null && cir[j] != null) {
						cir[i] = cir[j];
						cir[i].add(i);
					} else if (cir[j] == null && cir[i] != null) {
						cir[j] = cir[i];
						cir[j].add(j);
					} else if (cir[i] == null && cir[j] == null) {
						// count += getCircle(i, j, m, cir);

						++count;
						cir[i] = cir[j] = new HashSet<Integer>();
						populate(i, j, m, cir, cir[i]);
						
					} else { // already has set, no action
						// cir[i].add(i);
						// cir[i].add(j);
					}
				}
			}
		}

		for (int i = 0; i < n; ++i) {
			if (cir[i] == null) {
				++count;
			}
		}

		return count;
	}

	void populate(int i, int j, int[][] m, Set<Integer>[] cir, Set<Integer> set) {
		int n = m.length;

		set.add(i);
		set.add(j);
		
		for(int r = 0; r < n; ++r) {
			if(cir[r] == null && m[r][j] == 1) {
				cir[r] = set;
				populate(r, j, m, cir, set);
			}
		}
		
		for(int c = 0; c < n; ++c) {
			if(cir[c] == null && m[i][c] == 1) {
				cir[c] = set;
				populate(i, c, m, cir, set);
			}
		}
	}

//	// wrong
//	int getCircle(int i, int j, int[][] m, Set<Integer>[] cir) {
//		int n = m.length;
//
//		int count = 0;
//
//		Set<Integer> set = null;
//
//		for (int c = 0; c < n && set == null; ++c) {
//			if (m[i][c] == 1 && cir[c] != null) {
//				set = cir[c];
//			}
//		}
//
//		for (int r = 0; r < n && set == null; ++r) {
//			if (m[r][j] == 1 && cir[r] != null) {
//				set = cir[r];
//			}
//		}
//
//		if (set == null) {
//			count = 1;
//			set = new HashSet<Integer>();
//			set.add(i);
//			set.add(j);
//			cir[i] = set;
//			cir[j] = set;
//		}
//
//		for (int c = 0; c < n; ++c) {
//			if (m[i][c] == 1 && cir[c] == null) {
//				set.add(c);
//				cir[c] = set;
//			}
//		}
//
//		for (int r = 0; r < n; ++r) {
//			if (m[r][j] == 1 && cir[r] == null) {
//				set.add(r);
//				cir[r] = set;
//			}
//		}
//
//		return count;
//	}

	public static void main(String[] args) {
		int[][] a = { 
				{ 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 }, 
				{ 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0 },
				{ 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0 }, 
				{ 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0 }, 
				{ 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1 },
				{ 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 } };

		Solution s = new Solution();
		
		System.out.println(s.findCircleNum(a));
		
		
	}

}
