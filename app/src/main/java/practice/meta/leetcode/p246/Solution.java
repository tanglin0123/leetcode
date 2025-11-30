package practice.meta.leetcode.p246;

import java.util.*;


public class Solution {
    public boolean isStrobogrammatic(String num) {
        if (num == null || num.isEmpty()) {
            return false;
        }

        Map<Character, Character> numMap = new HashMap<>();
        numMap.put('0', '0');
        numMap.put('1', '1');
        numMap.put('6', '9');
        numMap.put('8', '8');
        numMap.put('9', '6');

        char[] newNum = new char[num.length()];

        for (int i = 0; i < num.length(); ++ i) {
            char c = num.charAt(i);
            Character newC = numMap.get(c);
            if (newC == null) {
                return false;
            }

            int j = num.length() - 1 - i;
            newNum[j] = newC;
        }

        String s = new String(newNum);

        return s.equals(num);
    }
}
