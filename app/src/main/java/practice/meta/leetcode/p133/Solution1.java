package practice.meta.leetcode.p133;

import java.util.*;

public class Solution1 {
    public static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public Node cloneGraph(Node node) {
        if(node == null) {
            return null;
        }

        Map<Node, Node> clonedMap = new HashMap<>();

        List<Node> q = new ArrayList<>();
        q.add(node);
        clonedMap.put(node, new Node(node.val));

        while (!q.isEmpty()) {
            Node oldNode = q.removeFirst();
            Node newNode = clonedMap.get(oldNode);

            for (Node oldNeighbor : oldNode.neighbors) {
                Node newNeighbor = clonedMap.get(oldNeighbor);
                if (newNeighbor == null) {
                    q.add(oldNeighbor);
                    newNeighbor = new Node(oldNeighbor.val);
                    clonedMap.put(oldNeighbor, newNeighbor);
                }
                newNode.neighbors.add(newNeighbor);
            }
        }

        return clonedMap.get(node);
    }
}
