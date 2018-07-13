package leetcode.problem753.method3;

import java.util.*;

// DFS
class Solution {
	public String crackSafe(int n, int k) {
		if (n == 1) {
			String s = "";
			for (int i = 0; i < k; ++i) {
				s += i;
			}
			return s;
		}

		if (k == 1) {
			String s = "";
			for (int i = 0; i < n; ++i) {
				s += 0;
			}
			return s;
		}

		String s = "";
		for (int i = 0; i < n; ++i) {
			s += 0;
		}

		Set<String> visited = new HashSet<>();
		visited.add(s);

		return dfs(s, visited, n, k);
	}
	
	String dfs(String s, Set<String> visited, int n, int k) {
		String prefix = s.substring(s.length() - n + 1);
		for(int i = 0; i < k; ++i) {
			String node = prefix + i;
			if(!visited.contains(node)) {
				String news = s + i;
				if (visited.size() == (int) Math.pow(k, n) - 1) {
					return news;
				}
				
				visited.add(node);
				String r = dfs(news, visited, n, k);
				if(r == null) {
					visited.remove(node);
				} else {
					return r;
				}
			}
		}
		
		return null;
	}

	public static void main(String[] args) {
		int n = 2, k = 2;

		Solution s = new Solution();
		System.out.println(s.crackSafe(n, k));

	}

}
