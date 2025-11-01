package practice.leetcode.problem80;

import java.util.Arrays;

public class Solution1 {
    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0) {
            return -1;
        }

        int slotIdx = nums.length - 1;
        
        int count = 0;
        int currentNumber = 0;

        for(int i = nums.length - 1 ; i >= 0; --i ) {
            if (count == 0) {
                count = 1;
                currentNumber = nums[i];
                nums[slotIdx--] = currentNumber;
            } else {
                if(currentNumber == nums[i]) {
                    count ++;
                    if(count <= 2) {
                        nums[slotIdx--] = currentNumber;
                    }
                } else {
                    count = 1;
                    currentNumber = nums[i];
                    nums[slotIdx--] = currentNumber;
                }
            }
        }

        int i = 0;
        for(int j = slotIdx + 1; j < nums.length; ++i, ++j) {
            nums[i] = nums[j];
        }

        return i;
    }

    public static void main(String[] args) {
        Solution1 sln = new Solution1();

        int[] nums1 = {0,0,1,1,1,1,2,3,3}; 

        int count1 = sln.removeDuplicates(nums1);

        System.out.println(count1);
        System.out.println(Arrays.toString(nums1));


        int[] nums2 = {1,1,1,2,2,3}; 

        int count2 = sln.removeDuplicates(nums2);

        System.out.println(count2);
        System.out.println(Arrays.toString(nums2));
    }
}
