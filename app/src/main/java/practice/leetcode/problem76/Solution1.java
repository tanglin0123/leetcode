package practice.leetcode.problem76;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import utils.Utils;

public class Solution1 {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        Map<Character, Integer> neededCountMap = new HashMap<>();

        for (int i = 0; i < t.length(); ++i) {
            char c = t.charAt(i);
            Integer count = neededCountMap.getOrDefault(c, 0);
            neededCountMap.put(c, count + 1);
        }

        int l = 0; 
        int r = 0;

        Map<Character, Integer> windowCharCountMap = new HashMap<>();
        Set<Character> readyChars = new HashSet<>();

        List<int[]> resultRanges = new ArrayList<>();
        
        while(r < s.length()) {
            char rc = s.charAt(r); 
            
            int count = windowCharCountMap.getOrDefault(rc, 0);
            windowCharCountMap.put(rc, ++count);

            int neededCount = neededCountMap.getOrDefault(rc, 0);
            if(count == neededCount) {
                // System.out.println("Ready: " + rc);
                readyChars.add(rc);
            }

            // right ready, shrink left
            if(readyChars.size() == neededCountMap.size()) {
                
                while(l <= r) {
                    char lc = s.charAt(l);
                    Integer existingCount = windowCharCountMap.get(lc);
                    windowCharCountMap.put(lc, --existingCount); 
                    ++ l;

                    int neededCount2 = neededCountMap.getOrDefault(lc, 0);
                    if (existingCount < neededCount2) {
                        readyChars.remove(lc);
                        resultRanges.add(new int[]{l - 1, r});
                        break;
                    }
                }

            }
            
            ++ r;
        }

        // System.out.println("neededCountMap: " + neededCountMap);
        // System.out.println("windowCharCountMap: " + windowCharCountMap);
        // System.out.println("readyChars: " + readyChars);


        if(resultRanges.isEmpty()) {
            return "";
        }

        int[] minRange = resultRanges.get(0);
        int minLen = minRange[1] - minRange[0];
        for(int i = 1; i < resultRanges.size(); ++ i) {
            int[] curRange = resultRanges.get(i);
            int curLen = curRange[1] - curRange[0];
            if (curLen < minLen) {
                minLen = curLen;
                minRange = curRange;
            }
        }

        return s.substring(minRange[0], minRange[1] + 1);
    }


    public static void main(String[] args) {
        Solution1 sln = new Solution1();

        String s1 = "ADOBECODEBANC", t1 = "ABC";
        String result1 = sln.minWindow(s1, t1);
        System.out.println("result1: " + result1);

        String s2 = "a", t2 = "a";
        String result2 = sln.minWindow(s2, t2);
        System.out.println("result2: " + result2);

        String s3 = "a", t3 = "aa";
        String result3 = sln.minWindow(s3, t3);
        System.out.println("result3: " + result3);


        try {
            String s4 = Utils.readFileFromSrc("main/resources/lc_p76_s.txt");
            // System.out.println("s4: " + s4);
            String t4 = Utils.readFileFromSrc("main/resources/lc_p76_t.txt");
            // System.out.println("t4: " + t4);
            String result4 = sln.minWindow(s4, t4);
            System.out.println("result4: " + result4);

        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
