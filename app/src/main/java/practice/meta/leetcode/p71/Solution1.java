package practice.meta.leetcode.p71;

import java.util.*;

public class Solution1 {
    public String simplifyPath(String path) {
        String[] ss = path.split("/");

        List<String> result = new ArrayList<>();
        // assume ss[0] is always empty string
        for (String s : ss) {
            s = s.trim();
            if (s.equals(".")) {
                continue;
            } else if (s.equals("..")) {
                if(!result.isEmpty()) {
                    result.removeLast();
                }
            } else if(s.isEmpty()) {
                continue;
            } else {
                result.add(s);
            }
        }

        return "/" + String.join("/", result);
    }
}
