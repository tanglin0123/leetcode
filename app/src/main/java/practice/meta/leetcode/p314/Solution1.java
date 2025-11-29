package practice.meta.leetcode.p314;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.TreeNode;

public class Solution1 {
    public static class NodeDist {
        TreeNode node;
        int dist;

        public NodeDist(TreeNode node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }



    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) {
            return result;
        }

        int leftMost = 0;
        int rightMost = 0;

        Map<Integer, List<Integer>> distMap = new HashMap<>();

        List<NodeDist> q = new ArrayList<>();
        q.add(new NodeDist(root, 0));

        while (!q.isEmpty()) {
            NodeDist nodeDist = q.removeFirst();

            List<Integer> distList = distMap.get(nodeDist.dist);
            if (distList == null ) {
                distList = new ArrayList<>();
                distMap.put(nodeDist.dist, distList);
            }
            distList.add(nodeDist.node.val);

            if (nodeDist.dist < leftMost) {
                leftMost = nodeDist.dist;
            }

            if (nodeDist.dist > rightMost) {
                rightMost = nodeDist.dist;
            }

            if (nodeDist.node.left != null) {
                q.add(new NodeDist(nodeDist.node.left, nodeDist.dist - 1));
            }

            if (nodeDist.node.right != null) {
                q.add(new NodeDist(nodeDist.node.right, nodeDist.dist + 1));
            }
        }

        for (int i = leftMost; i <= rightMost; ++i) {
            List<Integer> list = distMap.get(i);
            if (list != null) {
                result.add(list);
            }
        }

        return result;
    }

}
