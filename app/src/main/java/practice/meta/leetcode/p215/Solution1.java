package practice.meta.leetcode.p215;

import java.util.PriorityQueue;

public class Solution1 {
    public int findKthLargest(int[] nums, int k) {

        PriorityQueue<Integer> minq = new PriorityQueue<>();

        for (int i : nums) {
            minq.add(i);
            if (minq.size() > k) {
                minq.poll();
            }
        }
        
        return minq.peek(); 
    }

    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();

        int[] nums1 = new int[]{3,2,1,5,6,4};

        int k1 = 2;

        System.out.println(solution1.findKthLargest(nums1, k1));
    }
}
