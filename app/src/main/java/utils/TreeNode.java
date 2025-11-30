package utils;

import java.util.*;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode() {}
    public TreeNode(int val) { this.val = val; }
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static TreeNode makeTree(Integer[] sequence) {
        TreeNode root = new TreeNode(sequence[0]);

        List<TreeNode> q = new ArrayList<>();
        q.add(root);

        int i = 0;

        while (!q.isEmpty() && i < sequence.length) {
            TreeNode node = q.removeFirst();

            Integer leftVal = i + 1 < sequence.length ? sequence[i + 1] : null;
            Integer rightVal = i + 2 < sequence.length ? sequence[i + 2] : null;

            if (leftVal != null) {
                TreeNode leftNode = new TreeNode(leftVal);
                node.left = leftNode;
                q.add(leftNode);
            }

            if (rightVal != null) {
                TreeNode rightNode = new TreeNode(rightVal);
                node.right = rightNode;
                q.add(rightNode);
            }
            
            i = i + 2;
        }

        return root;
    }

    public static void main(String[] args) {
        TreeNode root1 = makeTree(new Integer[]{1,2,3,4,5});

        TreeNode root2 = makeTree(new Integer[]{1,null,3,null,5});

        System.out.println(root1);
        System.out.println(root2);

    }
}
