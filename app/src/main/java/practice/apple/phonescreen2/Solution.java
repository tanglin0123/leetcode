package practice.apple.phonescreen2;

import java.util.*;
import utils.TreeNode;

/*
    print all the routes from root to leaves of a tree, with indentation
        A
      /   \
    B       E
  /   \
C       D

Result:
        A
    B
C

    A
B
    D

A
    E
*/
public class Solution {

    public void printAllRoutes(TreeNode root) {
        if (root == null) {
            return;
        }

        List<TreeNode> path = new ArrayList<>();
        List<Integer> indentations = new ArrayList<>(); 

        path.add(root);
        indentations.add(0);

        this.printAllRoutes(root, 0, path, indentations);
    }

    private void printAllRoutes(TreeNode node, int indentation, List<TreeNode> path, List<Integer> indentations) {
        if (node.left == null && node.right == null) {
            printRoute(path, indentations);
            return;
        }

        if (node.left != null) {
            List<TreeNode> newPath = new ArrayList<>(path);
            List<Integer> newIndentations = new ArrayList<>(indentations);

            newPath.add(node.left);
            newIndentations.add(indentation - 1);

            printAllRoutes(node.left, indentation - 1, newPath, newIndentations);
        }

        if (node.right != null) {
            List<TreeNode> newPath = new ArrayList<>(path);
            List<Integer> newIndentations = new ArrayList<>(indentations);

            newPath.add(node.right);
            newIndentations.add(indentation + 1);

            printAllRoutes(node.right, indentation + 1, newPath, newIndentations);
        }
    }

    private void printRoute(List<TreeNode> path, List<Integer> indentations) {
        int minInd = Integer.MAX_VALUE;

        for(int i : indentations) {
            minInd = Math.min(i, minInd);
        }

        for (int i = 0; i < path.size(); ++ i) {
            TreeNode node = path.get(i);
            int indentation = indentations.get(i) - minInd;

            for (int j = 0; j < indentation ; ++j`) {
                System.out.print(" ");
            }
            
            System.out.println(node.val);
        }
    }

}
