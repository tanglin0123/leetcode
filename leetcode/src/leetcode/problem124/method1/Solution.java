package leetcode.problem124.method1;

import java.util.*;
import leetcode.commons.*;

// time limit exceeded
class Solution {
    public int maxPathSum(TreeNode root) {
        if(root == null) {
            return 0;
        }
        
        Map<TreeNode, List<TreeNode>> linkMap = new HashMap<>();
        List<TreeNode> nodeList = new ArrayList<>();
        
        int maxnode = traverse(root, nodeList, linkMap, Integer.MIN_VALUE);
        
        int max = maxnode;
        Set<TreeNode> visited = new HashSet<>();
        for(TreeNode node : nodeList){
            visited.add(node);
            int newmax = getMaxSum(node, node.val, visited, linkMap);
            max = Math.max(max, newmax);
            visited.remove(node);
        }
        
        return max;
    }
    
    int getMaxSum(TreeNode node, int sum, Set<TreeNode> visited, Map<TreeNode, List<TreeNode>> linkMap){
        int max = sum;
        
        List<TreeNode> list = linkMap.get(node);
        if(list == null){
            return max;
        }
        
        for(TreeNode next : list){
            if(visited.contains(next)){
                continue;
            }
            visited.add(next);
            int newmax = getMaxSum(next, sum + next.val, visited, linkMap);
            max = Math.max(max, newmax);
            visited.remove(next);
        }
        
        return max;
    }
    
    int traverse(TreeNode node, List<TreeNode> nodeList, Map<TreeNode, List<TreeNode>> linkMap, int max){
        nodeList.add(node);
        max = Math.max(max, node.val);
        
        if(node.left != null) { 
            addLinkMap(node, node.left, linkMap);
            addLinkMap(node.left, node, linkMap);
            traverse(node.left, nodeList, linkMap, max);
        }
        if(node.right != null) {
            addLinkMap(node, node.right, linkMap);
            addLinkMap(node.right, node, linkMap);
            traverse(node.right, nodeList, linkMap, max);
        }
        
        return max;
    }
    
    void addLinkMap(TreeNode key, TreeNode value, Map<TreeNode, List<TreeNode>> linkMap){
        List<TreeNode> list = linkMap.get(key);
        if(list == null){
            list = new ArrayList<>(2);
            linkMap.put(key, list);
        }
        list.add(value);
    }
}
