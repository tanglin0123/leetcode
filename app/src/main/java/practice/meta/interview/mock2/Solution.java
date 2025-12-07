package practice.meta.interview.mock2;

import java.util.*;

public class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }

        int[][] directions = {
            {-1, -1}, 
            {-1, 0},
            {-1, 1},
            {0, 1},
            {1, 1},
            {1, 0},
            {1, -1},
            {0, -1}
        };

        if (grid[0][0] != 0) {
            return -1;
        }

        int h = grid.length;
        int w = grid[0].length;

        if (h == 1 && w == 1) {
            return 1;
        }

        boolean[][] visited = new boolean[h][w];

        Map<String, int[]> parentMap = new HashMap<>();

        LinkedList<int[]> q = new LinkedList<>();

        parentMap.put("0,0", null);
        visited[0][0] = true;
        q.add(new int[] {0, 0, 1});

        while(!q.isEmpty()) {
            int[] node = q.poll();

            int r = node[0];
            int c = node[1];
            int depth = node[2];

            int nextDepth = depth + 1;
            
            for (int[] direction : directions) {
                int nextR = r + direction[0];
                int nextC = c + direction[1];

                if (
                        nextR < 0 || nextR >= h || 
                        nextC < 0 || nextC >= w || 
                        visited[nextR][nextC] ||
                        grid[nextR][nextC] != 0) {
                    continue;
                }

                if (nextR == h - 1 && nextC == w - 1) {
                    this.printPath(new int[]{nextR, nextC}, parentMap);
                    return nextDepth;
                }

                parentMap.put(nextR + "," + nextC, node);
                visited[nextR][nextC] = true;
                q.add(new int[]{nextR, nextC, nextDepth});
            }
        }

        return -1;
    }

    private void printPath(int[] endNode, Map<String, int[]> parentMap) {
        Stack<int[]> stk = new Stack<>();
        
        int[] curNode = endNode;

        while (curNode != null) {
            stk.push(endNode);
            curNode = parentMap.get(curNode[0] + "," + curNode[1]);
        }

        while(!stk.isEmpty()) {
            int[] node = stk.pop();
            System.out.println(node[0] + ", " + node[1]);
        }
    }

    public static void main(String[] args) {
        Map<int[], int[]> map = new HashMap<>();

        map.put(new int[]{1, 2}, new int[]{1,2,3});

        System.out.println(map.get(new int[]{1, 2}));

        
    }
}
