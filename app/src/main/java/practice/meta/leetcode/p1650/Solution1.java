package practice.meta.leetcode.p1650;

import java.util.*;

public class Solution1 {
    public static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    };

    public Node lowestCommonAncestor(Node p, Node q) {
        Set<Node> pAncestors = new HashSet<>();

        Node cur = p;
        while (cur != null) {
            pAncestors.add(cur);
            cur = cur.parent;
        }

        cur = q;
        while (cur != null) {
            if (pAncestors.contains(cur)) {
                return cur;
            }
            cur = cur.parent;
        }

        return null;
    }
}
