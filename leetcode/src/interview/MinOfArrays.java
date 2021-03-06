package interview;

// amazon interview
public class MinOfArrays {

	// p1,p2,p3 are from sorted array pp1,pp2,pp3 respectively, get
	// min(abs(p1-p2)+abs(p1-p3)+abs(p2-p3))
	public int getMinSumOfThree(int[] pp1, int[] pp2, int[] pp3) {

		if (pp1 == null || pp2 == null || pp3 == null || pp1.length == 0
				|| pp2.length == 0 || pp3.length == 0) {
			return -1;
		}

		int n1 = pp1.length, n2 = pp2.length, n3 = pp3.length;

		int[] i = { -1, 0, 0, 0 }; // index 0 is not used

		int min = Integer.MAX_VALUE;

		while (i[1] < n1 && i[2] < n2 && i[3] < n3) {
			int sum = move(pp1, pp2, pp3, i);
			if (sum == 0) { // max == min
				return 0;
			}
			
			min = Math.min(sum, min);
		}

		return min;
	}

	private int move(int[] pp1, int[] pp2, int[] pp3, int[] i) {

		int max = Math.max(pp1[i[1]], Math.max(pp2[i[2]], pp3[i[3]]));
		int min = Math.min(pp1[i[1]], Math.min(pp2[i[2]], pp3[i[3]]));

		if (max == min) {
			return 0; // means 3 nums are same, so final result is 0
		}

		if (pp1[i[1]] == max) {
			if (pp2[i[2]] < pp3[i[3]]) {
				++i[2];			
			} else if (pp2[i[2]] > pp3[i[3]]) {
				++i[3];	
			} else { // p2 == p3
				int p2 = pp2[i[2]], p3 = pp3[i[3]];
				while (i[2] < pp2.length && i[3] < pp3.length && p2 == pp2[i[2]] && p3 == pp3[i[3]]) {
					++i[2];
					++i[3];
				}
			}

		} else if (pp2[i[2]] == max) {
			if (pp1[i[1]] < pp3[i[3]]) {
				++i[1];
			} else if (pp1[i[1]] > pp3[i[3]]) {
				++i[3];
			} else {
				int p1 = pp1[i[1]], p3 = pp3[i[3]];
				while (i[1] < pp1.length && i[3] < pp3.length && p1 == pp1[i[1]] && p3 == pp3[i[3]]) {
					++i[1];
					++i[3];
				}
			}
		} else { // pp3[i[3]] == max
			if (pp1[i[1]] < pp3[i[2]]) {
				++i[1];
			} else if (pp1[i[1]] > pp2[i[2]]) {
				++i[2];
			} else {
				int p2 = pp2[i[2]], p1 = pp1[i[1]];
				while (i[2] < pp2.length && i[1] < pp1.length && p1 == pp1[i[1]] && p2 == pp2[i[2]]) {
					++i[2];
					++i[1];
				}
			}
		}

		return 2 * (max - min);

	}
	
	public int getMinSumOfThree_2(int[] nums1, int[] nums2, int[] nums3) {

		if (nums1 == null || nums2 == null || nums3 == null || nums1.length == 0
				|| nums2.length == 0 || nums3.length == 0) {
			return -1;
		}

		int n1 = nums1.length, n2 = nums2.length, n3 = nums3.length;

		int p1 = 0, p2 = 0, p3 = 0;

		int r = Integer.MAX_VALUE;

		while (p1 < n1 && p2 < n2 && p3 < n3) {
			int max = Math.max(nums1[p1], Math.max(nums2[p2], nums3[p3]));
			int min = Math.min(nums1[p1], Math.min(nums2[p2], nums3[p3]));
			
			int sum = (max - min) * 2;
			if (sum == 0) { // max == min
				return 0;
			}
			
			r = Math.min(r, sum);
			
			if(min == nums1[p1]) {
				if(p1 == n1 - 1) {
					return r;
				}
				++p1;
			} else if(min == nums2[p2]) {
				if(p2 == n2 - 1) {
					return r;
				}
				++p2;
			} else {
				if(p3 == n3 - 1) {
					return r;
				}
				++p3;
			}
		}

		return r;
	}

	public static void main(String[] args) {
		int[] pp1 = { 1, 3, 5, 7 };
		int[] pp2 = { 2, 4, 6, 8 };
		int[] pp3 = { 5, 6, 7, 9 };

		MinOfArrays m = new MinOfArrays();
		System.out.println(m.getMinSumOfThree(pp1, pp2, pp3));
		System.out.println(m.getMinSumOfThree_2(pp1, pp2, pp3));
	}

}
