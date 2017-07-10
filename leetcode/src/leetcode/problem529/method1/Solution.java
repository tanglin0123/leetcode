package leetcode.problem529.method1;

public class Solution {
	public char[][] updateBoard(char[][] board, int[] click) {
		int r = click[0];
		int c = click[1];

		int rmax = board.length;
		int cmax = board[0].length;

		if (board[r][c] == 'M') {
			board[r][c] = 'X';
			return board;

		} else if (board[r][c] == 'E') {
			int count = 0;

			count += (r - 1 >= 0 && c - 1 >= 0 && board[r - 1][c - 1] == 'M') ? 1 : 0;
			count += (r - 1 >= 0 && board[r - 1][c] == 'M') ? 1 : 0;
			count += (r - 1 >= 0 && c + 1 < cmax && board[r - 1][c + 1] == 'M') ? 1 : 0;
			count += (c - 1 >= 0 && board[r][c - 1] == 'M') ? 1 : 0;
			count += (c + 1 < cmax && board[r][c + 1] == 'M') ? 1 : 0;
			count += (r + 1 < rmax && c - 1 >= 0 && board[r + 1][c - 1] == 'M') ? 1 : 0;
			count += (r + 1 < rmax && board[r + 1][c] == 'M') ? 1 : 0;
			count += (r + 1 < rmax && c + 1 < cmax && board[r + 1][c + 1] == 'M') ? 1 : 0;

			if (count > 0) {
				board[r][c] = (char) ('0' + count);
				return board;

			} else {
				board[r][c] = 'B';
				
				if (r - 1 >= 0 && c - 1 >= 0) {
					updateBoard(board, new int[] { r - 1, c - 1 });
				}
				if (r - 1 >= 0) {
					updateBoard(board, new int[] { r - 1, c });
				}
				if (r - 1 >= 0 && c + 1 < cmax) {
					updateBoard(board, new int[] { r - 1, c + 1 });
				}
				if (c - 1 >= 0) {
					updateBoard(board, new int[] { r, c - 1 });
				}
				if (c + 1 < cmax) {
					updateBoard(board, new int[] { r, c + 1 });
				}
				if (r + 1 < rmax && c - 1 >= 0) {
					updateBoard(board, new int[] { r + 1, c - 1 });
				}
				if (r + 1 < rmax) {
					updateBoard(board, new int[] { r + 1, c });
				}
				if (r + 1 < rmax && c + 1 < cmax) {
					updateBoard(board, new int[] { r + 1, c + 1 });
				}
				return board;

			}

		} else {
			return board;

		}
	}

	public static void main(String[] args) {
		String[] a = { "EEEEE", "EEMEE", "EEEEE", "EEEEE" };
		int[] b = { 3, 0 };
		
		char[][] c = new char[a.length][];
		
		for(int i = 0; i < a.length; ++i) {
			c[i] = a[i].toCharArray();
		}
		
		Solution s = new Solution();
		s.updateBoard(c, b);
		
		for(char[] chs : c) {
			for(char ch : chs) {
				System.out.print(" " + ch);
			}
			System.out.println();
		}
	}

}
