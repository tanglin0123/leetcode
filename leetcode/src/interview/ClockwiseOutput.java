package interview;

// alibaba
public class ClockwiseOutput {
	void output(int[][] nums) {
		if (nums == null || nums.length == 0 || nums[0] == null || nums[0].length == 0) {
			return;
		}

		int rmin = 0, cmin = 0, rmax = nums.length - 1, cmax = nums[0].length - 1;
		int r = 0, c = 0;

		int direct = 0;

		while (rmin <= rmax && cmin <= cmax) {
			System.out.print(nums[r][c] + ", ");
			if (direct == 0) {
				if (c < cmax) {
					++c;
				} else { // c == cmax
					++rmin;
					direct = 1;
					++r;
				}
			} else if (direct == 1) {
				if (r < rmax) {
					++r;
				} else {
					--cmax;
					direct = 2;
					--c;
				}
			} else if (direct == 2) {
				if (c > cmin) {
					--c;
				} else {
					--rmax;
					direct = 3;
					--r;
				}
			} else if (direct == 3) {
				if (r > rmin) {
					--r;
				} else {
					++cmin;
					direct = 0;
					++c;
				}
			}
		}
	}

	public static void main(String[] args) {
		int[][] a = {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}};
		
		int[][] b = {{1,2,3,4}};
		
		int[][] c = {{1}};
		
		int[][] d = {{1},{2}};
		
		ClockwiseOutput s = new ClockwiseOutput();
		
		s.output(a);
		
		System.out.println();
		s.output(b);
		
		System.out.println();
		s.output(c);
		
		System.out.println();
		s.output(d);
	}

}
