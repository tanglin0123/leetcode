package leetcode.problem375.method1;

// wrong idea
public class Solution {
	public int getMoneyAmount(int n) {
		int[] acc = new int[] { 0 };
		this.accumulate(1, n, acc);

		return acc[0];
	}

	public void accumulate(int start, int end, int[] acc) {
		if (start == end) {
			return;
		}

		int half = (start + end) / 2;
		acc[0] += half;
		this.accumulate(half + 1, end, acc);

	}

	public static void main(String[] args) {
		int i = 4;
		
		Solution s = new Solution();
		System.out.println(s.getMoneyAmount(i));

	}

}
