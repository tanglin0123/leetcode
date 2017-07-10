package leetcode.problem488.method1;

import java.util.*;

// pass. branch and bound
public class Solution {
	public int findMinStep(String board, String hand) {
		Comparator<Board> comp = new Comparator<Board>() {

			@Override
			public int compare(Board o1, Board o2) {
				if (o1.b.size() != o2.b.size()) {
					return o1.b.size();
				}

				return o2.twoAdjacent - o1.twoAdjacent;
			}

		};

		PriorityQueue<Board> brs = new PriorityQueue<Board>(1, comp);

		Board initBd = new Board(board, hand);

		// trim the original
		// for(int i = 0; i+2 < initBd.b.size(); ++i){
		// if(initBd.b.get(i) == initBd.b.get(i+1) && initBd.b.get(i) ==
		// initBd.b.get(i+2)){
		// initBd.trim(i, i+2);
		// i = 0;
		// }
		// }

		initBd.calculateAdjacent();
		brs.add(initBd);

		for (int round = 1; round <= hand.length(); ++round) {
			PriorityQueue<Board> nextBrs = new PriorityQueue<Board>(1, comp);
			while (!brs.isEmpty()) {
				Board curBd = brs.poll();

				// wrong logic, think case board = "BRYRBB"; hand = "RYGYR";
//				if (curBd.b.size() > totalBestSize
//						|| (curBd.b.size() == totalBestSize && curBd.twoAdjacent < totalBestAdj)) {
//					// not handle the remaining as they are worse situation
//					break;
//				}
				
				Map<Character, Integer> chCount = new HashMap<Character, Integer>();
				for(char key: curBd.b){
					Integer count = chCount.get(key);
					if(count == null){
						count = 1;
					}else{
						++count;
					}
					chCount.put(key, count);
				}
				
				boolean go = true;
				for(char key: chCount.keySet()){
					int bcount = chCount.get(key);
					if(bcount < 3){
						Integer hcount = curBd.h.get(key);
						if(hcount == null || hcount + bcount < 3){
							go = false;
							break;
						}
					}
				}
				
				if(!go){ // this branch cannot solve it
					continue;
				}

				for (char c : curBd.h.keySet()) {
					List<Integer> bestPos = new ArrayList<Integer>(
							curBd.b.size() + 1);
					for (int j = 0; j < curBd.b.size(); ++j) {
						if (curBd.b.get(j) == c
								&& (j == 0 || curBd.b.get(j - 1) != c)) {
							bestPos.add(j);
						}
					}

					if (bestPos.isEmpty()) {
						for (int j = 0; j <= curBd.b.size(); ++j) {
							bestPos.add(j);
						}
					}

					for (int j : bestPos) {
						Board nb = curBd.put(j, c);

						if (j + 1 < nb.b.size() && nb.b.get(j + 1) == c) {
							nb.trim(j, j + 1);
						}

						if (nb.b.isEmpty()) {
							return round;
						}

						nb.calculateAdjacent();

						nextBrs.add(nb);
					}
				}

			}
			brs = nextBrs;
			nextBrs = new PriorityQueue<Board>(1, comp);
		}
		
		return -1;
	}

	static class Board {
		LinkedList<Character> b = new LinkedList<Character>();
		Map<Character, Integer> h = new HashMap<Character, Integer>();
		int twoAdjacent = 0;

		Board(String s, String hand) {
			for (int i = 0; i < s.length(); ++i) {
				b.add(s.charAt(i));
			}

			for (int i = 0; i < hand.length(); ++i) {
				char c = hand.charAt(i);
				Integer count = h.get(c);
				if (count == null) {
					count = 1;
				} else {
					++count;
				}
				h.put(c, count);
			}
		}

		Board(List<Character> b, Map<Character, Integer> h) {
			this.b.addAll(b);
			for (char key : h.keySet()) {
				this.h.put(key, h.get(key));
			}
		}

		void calculateAdjacent() {
			if (b.size() == 0) {
				this.twoAdjacent = 0;
			} else {
				for (int i = 0; i + 1 < b.size(); ++i) {
					if (b.get(i) == b.get(i + 1)) {
						++this.twoAdjacent;
					}
				}
			}
		}

		Board put(int pos, char c) {
			Board nb = new Board(b, h);
			nb.b.add(pos, c);
			Integer count = nb.h.get(c);
			if (count == 1) {
				nb.h.remove(c);
			} else {
				nb.h.put(c, count - 1);
			}
			return nb;
		}

		void trim(int trim1, int trim2) {
			int start = trim1 - 1;
			for (; start >= 0 && b.get(start) == b.get(trim1); --start)
				;
			start += 1;

			int end = trim2 + 1;
			for (; end < b.size()  && b.get(end) == b.get(trim2); ++end)
				;
			end -= 1;

			int len = end - start + 1;
			if (len < 3) {
				return;
			}

			// len >= 3
			for (int i = 0; i < len; ++i) {
				b.remove(start);
			}

			if (start - 1 >= 0 && start < b.size()
					&& b.get(start - 1) == b.get(start)) {
				trim(start - 1, start);
			}
		}
	}

	public static void main(String[] args) {
//		exp = 2
//		String board = "WWRRBBWW";
//		String hand = "WRBRW"; 

		// exp = 3
//		String board = "BRYRBB";
//		String hand = "RYGYR";
		
		String board = "WBRBBYWYBBWRW";
		String hand = "GBBRY";
		
		Solution s = new Solution();
		System.out.println(s.findMinStep(board, hand));
	}
}
