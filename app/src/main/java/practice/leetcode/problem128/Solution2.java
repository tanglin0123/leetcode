package practice.leetcode.problem128;

import lombok.extern.log4j.Log4j2;


// Memory Limit Exceeded at case 74

@Log4j2
public class Solution2 {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int min = nums[0];
        int max = nums[0];
        for (int n : nums) {
            min = Math.min(min, n);
            max = Math.max(max, n);
        }

        int gap = max - min + 1;
        int byteCount = gap / 8 + 1;

        byte[] bitmap = new byte[byteCount];

        for(int n : nums) {
            int pos = n - min;
            int i = pos / 8;
            int j = pos % 8;

            byte mask = (byte) (1 << j);
            bitmap[i] |= mask;
        }

        int maxLen = 0;
        for(int n : nums) {
            if (!isInArray(n - 1, min, bitmap)) { // n - 1 not in the bitmap
                int curNum = n;

                while (isInArray(curNum + 1, min, bitmap)) {
                    curNum ++;
                }

                maxLen = Math.max(maxLen, curNum - n + 1);
            }
        }

        return maxLen;
    }

    boolean isInArray(int n, int min, byte[] bitmap) {
        int pos = n - min;
        int i = pos / 8;
        int j = pos % 8;

        byte mask = (byte) (1 << j);
        return (bitmap[i] & mask) != 0;
    }

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();

        int[] nums = {0,3,7,2,5,8,4,6,0,1};

        log.info(solution2.longestConsecutive(nums));
    }
}
