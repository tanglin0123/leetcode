package practice.leetcode.problem15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Time Limit Exceeded
public class Solution1 {

    public List<List<Integer>> threeSum(int[] nums) {
        if(nums == null || nums.length < 3) {
            return new ArrayList<>();
        }

        Map<Integer, Set<Integer>> indexMap = new HashMap<>();
        
        for (int i = 0; i < nums.length; ++i) {
            Set<Integer> set = indexMap.get(nums[i]);
            if (set == null) {
                set = new HashSet<>();
                indexMap.put(nums[i], set);
            }
            set.add(i);
            indexMap.put(nums[i], set);
        }

        List<List<Integer>> result = new ArrayList<>();

        Set<String> usedValues = new HashSet<>();

        for (int i = 0; i < nums.length; ++i) {

            for (int j = 0; j < i; ++j) {
                if (i == j) {
                    continue;
                }

                int negSum = -(nums[i] + nums[j]);

                Set<Integer> idxSet = indexMap.get(negSum);

                if (idxSet != null && !idxSet.isEmpty()) {
                    Set<Integer> remaining = new HashSet<>(idxSet);
                    remaining.removeAll(Set.of(i, j));

                    if (!remaining.isEmpty()) {
                        int min = Math.min(negSum, Math.min(nums[i], nums[j]));
                        int max = Math.max(negSum, Math.max(nums[i], nums[j]));

                        int mid = 0 - min - max;

                        String key = min + "_" + mid + "_" + max; 

                        if(!usedValues.contains(key)) {
                            result.add(List.of(min, mid, max));
                            usedValues.add(key);
                        }
                    }
                }

            }

        }

        return result;
        
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
