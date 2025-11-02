package practice.leetcode.problem15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution2 {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length && nums[i] <= 0; ++i) {
            if (i == 0 || nums[i-1] != nums[i]) {
                twoSum(nums, i, result);
            }
        }

        return result;
    }

    void twoSum(int[] nums, int i, List<List<Integer>> result) {
        int l = i + 1;
        int r = nums.length - 1;

        while (l < r) {
            int sum = nums[i] + nums[l] + nums[r];

            if(sum == 0) {
                result.add(List.of(nums[i], nums[l++], nums[r--]));
                while(l < r && nums[l] == nums[l-1]) {
                    l ++;
                }
            } else if (sum < 0) {
                l ++;
            } else {
                r --;
            }
        }
    }

    public static void main(String[] args) {
        Solution1 sln = new Solution1();

        int[] nums1 = {-1,0,1,2,-1,-4};

        List<List<Integer>> result1 = sln.threeSum(nums1);

        System.out.println(result1);


        int[] nums2 = {0,1,1};

        List<List<Integer>> result2 = sln.threeSum(nums2);

        System.out.println(result2);


        int[] nums3 = {0,0,0};

        List<List<Integer>> result3 = sln.threeSum(nums3);

        System.out.println(result3);
    }
}
