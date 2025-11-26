package practice.meta.leetcode.p339;

import java.util.List;

public class Solution1 {
    public int depthSum(List<NestedInteger> nestedList) {
        int s = 0;
        for (NestedInteger item : nestedList) {
            s += sum(1, item);
        }
        return s;
    }

    private int sum(int curDepth, NestedInteger integer) {
        if (integer.isInteger()) {
            return curDepth * integer.getInteger();
        }
        
        int s = 0;
        for (NestedInteger item : integer.getList()) {
            s += sum(curDepth + 1, item);
        }
        return s;
    }
}
