package practice.linkedin.example1;

import java.util.*;

public class Solution {
    // assume there always a route between any 2 vertex

    public static interface GraphNode {
        Set<GraphNode> getNeighbors();

        Color getColor();

        void setColor(Color c);
    }

    public static enum Color {
        RED,
        BLUE
    }

    public boolean twoColorable (GraphNode startNode) {
        if(startNode == null) {
            return false;
        }

        if (startNode.getNeighbors() == null || startNode.getNeighbors().isEmpty()) {
            return true;
        }

        Set<GraphNode> visitedNode = new HashSet<>();
        
        List<GraphNode> q = new ArrayList<>();
        q.add(startNode);

        while (!q.isEmpty()) {
            GraphNode node = q.removeFirst();
            visitedNode.add(node);


            Set<GraphNode> neighbors = node.getNeighbors();

            for (GraphNode neighbor : neighbors) {
                if (neighbor == node) {
                    continue;
                }

                Set<GraphNode> intersection = new HashSet<>(neighbors);
                intersection.retainAll(neighbor.getNeighbors());
                intersection.remove(node);

                if (!intersection.isEmpty()) {
                    return false;
                }

                if (!visitedNode.contains(neighbor)) {
                    q.add(neighbor);
                }
            }
        }

        return true;
    }

    
}
