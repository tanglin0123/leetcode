package practice.meta.leetcode.p680;

public class Solution1 {
    public boolean validPalindrome(String s) {
        if(s.isEmpty()) {
            return true;
        }

        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return validPalindrome(s, left+1, right) || validPalindrome(s, left, right-1);
            }

            ++ left;
            -- right;
        }
        return true;
    }

    public boolean validPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            ++ left;
            -- right;
        }
        return true;
    }
}
