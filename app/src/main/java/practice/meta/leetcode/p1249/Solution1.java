package practice.meta.leetcode.p1249;

import java.util.*;

public class Solution1 {
    public String minRemoveToMakeValid(String s) {
        if(s == null || s.length() == 0) {
            return s;
        }
        
        List<Integer> unmatchedLeft = new ArrayList<>();
        List<int[]> rangeList = new ArrayList<>();

        int start = -1;
        int end = -1;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);

            if(c == '(') {
                unmatchedLeft.add(i);

                if(start != -1) {
                    rangeList.add(new int[]{start, end});
                }

                rangeList.add(new int[]{i, i});
                
                start = -1;
                end = -1;

            } else if (c == ')') {
                if(!unmatchedLeft.isEmpty()) {
                    unmatchedLeft.removeLast();

                    if(start != -1) {
                        rangeList.add(new int[]{start, end});
                    }
                
                    rangeList.add(new int[]{i, i});

                    start = -1;
                    end = -1;
                } else { // no matching left
                    if(start != -1) {
                        rangeList.add(new int[]{start, end});
                    }
                    start = -1;
                    end = -1;
                }
                
            } else { // not parentheses
                if (start == -1) { // new start
                    start = i;
                } 
                end = i;
            }
        }
        
        if(start != -1) {
            rangeList.add(new int[]{start, end});
        }

        Set<Integer> unmatchedLeftSet = new HashSet<>();
        unmatchedLeftSet.addAll(unmatchedLeft);

        StringBuffer sb = new StringBuffer(s.length());

        for(int[] range : rangeList) {
            if(s.charAt(range[0]) == '(') { // is a parenthesis
                if(!unmatchedLeftSet.contains(range[0])) {
                    sb.append(s.charAt(range[0]));
                }
            } else {
                sb.append(s.substring(range[0], range[1] + 1));
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Solution1 sln = new Solution1();

        String s1 = "lee(t(c)o)de)";

        String result1 = sln.minRemoveToMakeValid(s1);

        System.out.println(result1);

        String s2 = "))((";

        String result2 = sln.minRemoveToMakeValid(s2);

        System.out.println(result2);

    }
}
