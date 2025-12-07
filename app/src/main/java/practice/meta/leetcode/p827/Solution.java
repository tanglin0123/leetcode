package practice.meta.leetcode.p827;

import java.util.*;

public class Solution {
    public static final int[][] DIRECTIONS = {
        {-1, 0}, // up
        {0, 1}, // right
        {1, 0}, // down
        {0, -1} // left
    };
    
    public int largestIsland(int[][] grid) {
        Map<String, List<Integer>> adjMap = markMap(grid);

        if (adjMap.isEmpty()) {
            if (grid[0][0] == 1) {
                return grid.length * grid[0].length;
            } else {
                return 1;
            }
        } 

        int max = 0;
        for (List<Integer> areaList : adjMap.values()) {
            int sum = 0;
            for (int a : areaList) {
                sum += a;
            }
            max = Math.max(max, sum);
        }

        return max + 1;
    }
    
    private Map<String, List<Integer>> markMap(int[][] grid) {
        int h = grid.length;
        int w = grid[0].length;

        boolean[][] visited = new boolean[h][w];
        
        Map<String, List<Integer>> adjMap = new HashMap<>();

        // mark islands
        for (int r = 0; r < h; ++r) {
            for (int c = 0; c < w; ++c) {
                if (visited[r][c]) {
                    continue;
                }

                if (grid[r][c] == 1) {
                    markIsland(r, c, grid, visited, adjMap);
                }
            }
        }

        return adjMap;
    }

    

    private void markIsland(int startRow, int startCol, int[][] grid, boolean[][] visited, Map<String, List<Integer>> adjMap) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{startRow, startCol});

        int h = grid.length;
        int w = grid[0].length;

        int area = 0;
        Set<String> adjSeaSet = new HashSet<>();
        
        while (!q.isEmpty()) {
            int[] node = q.poll();
            int r = node[0];
            int c = node[1];

            if(visited[r][c]) {
                continue;
            }

            visited[r][c] = true;
            area++;

            for (int[] dir : DIRECTIONS) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr >= 0 && nr < h && nc >= 0 && nc < w && !visited[nr][nc]) {
                    if (grid[nr][nc] == 1) {
                        q.add(new int[]{nr, nc});
                    } else {
                        adjSeaSet.add(nr + "," + nc);
                    }
                }
            }
        }

        for (String key : adjSeaSet) {
            List<Integer> islandAreaSet = adjMap.get(key);
            if (islandAreaSet == null) {
                islandAreaSet = new ArrayList<>();
                adjMap.put(key, islandAreaSet);
            }
            islandAreaSet.add(area);
        }
    }

}
