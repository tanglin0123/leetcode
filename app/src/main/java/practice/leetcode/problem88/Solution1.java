package practice.leetcode.problem88;

import java.util.Arrays;

public class Solution1 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if(nums2.length == 0) {
            return;
        }

        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;

        while (j >= 0 && i >= 0) {
            int n1 = nums1[i];
            int n2 = nums2[j];

            if(n1 > n2) {
                nums1[k] = n1;
                nums1[i] = 0;
                --i;
            } else {
                nums1[k] = n2;
                --j;
            }

            --k;
        }

        if (i < 0) {
            while (j >= 0) {
                nums1[k--] = nums2[j--];
            }
        }
    }

    public static void main(String[] args) {
        Solution1 sln = new Solution1();

        // int[] nums1 = {1, 2, 3, 0, 0, 0};
        // int m = 3;
        // int[] nums2 = {2, 5, 6};
        // int n = 3;

        int[] nums1 = {0};
        int m = 0;
        int[] nums2 = {1};
        int n = 1;


        sln.merge(nums1, m, nums2, n);

        System.out.println(Arrays.toString(nums1));
    }
}
