package leetcode.problem753.method2;

import java.util.*;

// DFS. Time limited exceeded
class Solution {
	public static class Path {
		String s;
		Set<String> visited;

		Path(String s, Set<String> visited) {
			this.s = s;
			this.visited = visited;
		}
	}

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

		int max = (int) Math.pow(k, n);
		Stack<Path> candidates = new Stack<>();

		String s = "";
		for (int i = 0; i < n; ++i) {
			s += 0;
		}

		Set<String> visited = new HashSet<>();
		visited.add(s);
		candidates.push(new Path(s, visited));

		while (!candidates.isEmpty()) {
//			System.out.println(candidates);
			
			Path p = candidates.pop();

			String key = p.s;
			String prefix = key.substring(key.length() - n + 1, key.length());
			visited = p.visited;
			for (int c = 0; c < k; ++c) {
				String news = prefix + c;
				if (!visited.contains(news)) {
					String newkey = key + c;
					if (visited.size() == max - 1) {
						return newkey;
					}

					Set<String> newvisited = new HashSet<>(visited);
					newvisited.add(news);
					candidates.push(new Path(newkey, newvisited));
				}
			}
		}

		return null;
	}

	public static void main(String[] args) {
		int n = 4, k = 7;

		Solution s = new Solution();
		System.out.println(s.crackSafe(n, k));

	}

}
