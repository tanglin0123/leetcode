package interview;

import java.util.*;

// amazon interview
public class SortedStrings {

	// given some sorted strings with some specific char order,
	// output a list of char obeying the specific order 
	public List<Character> getOutput(String[] strs) {
		char[][] chs = new char[strs.length][];
		for (int i = 0; i < strs.length; ++i) {
			chs[i] = strs[i].toCharArray();
		}

		Map<Character, Set<Character>> graph = new HashMap<Character, Set<Character>>();
		Map<Character, Integer> in = new HashMap<Character, Integer>();

		fillGraph(graph, in, 0, chs, 0, chs.length - 1);

		Object[] nodes = graph.keySet().toArray();
		List<Character> r = new ArrayList<Character>(nodes.length);

		while(nodes.length != 0){
			for(Object node : nodes){
				int inDegree = in.getOrDefault(node, 0);
				if(inDegree == 0){
					r.add((Character)node);
					
					Set<Character> dests = graph.remove(node);
					for(Character dest : dests){
						in.put(dest, in.get(dest) - 1);
					}
				}
			}
			
			nodes = graph.keySet().toArray();
		}
		
		return r;
	}

	private void fillGraph(Map<Character, Set<Character>> graph,
			Map<Character, Integer> in, int cidx,
			char[][] chs, int gstart, int gend) {

		if (gstart == gend) {
			for (int i = cidx; i < chs[gstart].length; ++i) {
				if (!graph.containsKey(chs[gstart][i])) { // put node in the graph
					graph.put(chs[gstart][i], new HashSet<Character>());
				}
			}
			return;
		}

		char pre = cidx >= chs[gstart].length ? '\0' : chs[gstart][cidx];

		if (!graph.containsKey(pre)) {
			graph.put(pre, new HashSet<Character>());
		}

		int newstart = gstart;
		int newend = gstart;

		for (int i = gstart + 1; i <= gend; ++i) {
			char cur = cidx >= chs[i].length ? '\0' : chs[i][cidx];
			
			if (cur == pre) {
				newend = i;

			} else {
				if (cur != '\0') {
					if (pre != '\0') {
						graph.get(pre).add(cur);
						in.put(cur, in.getOrDefault(cur, 0) + 1);
					}

					if (!graph.containsKey(cur)) {
						graph.put(cur, new HashSet<Character>());
					}
				}

				if (pre != '\0') {
					fillGraph(graph, in, cidx + 1, chs, newstart, newend);
				}

				pre = cur;
				newstart = i;
				newend = i;
			}
		}

		// the last group
		if (pre != '\0') {
			fillGraph(graph, in, cidx + 1, chs, newstart, newend);
		}
	}

	public static void main(String[] args) {
		String[] strs = { "wrt", "wam", "ert", "etw" };
		
		SortedStrings s = new SortedStrings();
		System.out.println(s.getOutput(strs));
	}

	// test construct graph
	public static void main2(String[] args) {

		String[] strs = { "wrt", "wam", "ert", "etw" };
		char[][] chs = new char[strs.length][];
		for (int i = 0; i < strs.length; ++i) {
			chs[i] = strs[i].toCharArray();
		}

		Map<Character, Set<Character>> graph = new HashMap<Character, Set<Character>>();
		Map<Character, Integer> in = new HashMap<Character, Integer>();

		SortedStrings s = new SortedStrings();
		s.fillGraph(graph, in, 0, chs, 0, chs.length - 1);
		System.out.println(graph);
		System.out.println(in);
	}
}
