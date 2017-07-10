package leetcode.problem310.method1;

import java.util.*;

// exceed time limit
public class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        int[][] dist = new int[n][n];
        
        for(int i = 0; i < n; ++i){
            for(int j = 0; j < n; ++j){
                dist[i][j] = Integer.MAX_VALUE;
            }
            dist[i][i] = 0;
        }
        
        for(int[] e : edges){
            int from = e[0];
            int to = e[1];
            
            dist[from][to] = 1;
            dist[to][from] = 1;
            for(int i = 0; i < n; ++i){
                if(dist[i][from] != Integer.MAX_VALUE){
                    if(dist[i][to] > dist[i][from] + 1){
                        dist[i][to] = dist[i][from] + 1;
                        dist[to][i] = dist[i][from] + 1;
                    }
                    
                }
                if(dist[i][to] != Integer.MAX_VALUE){
                    if(dist[i][from] > dist[i][to] + 1){
                        dist[i][from] = dist[i][to] + 1;
                        dist[from][i] = dist[i][to] + 1;
                    }
                }
                
            }
        }
        
        int[] longest = new int[n];
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < n; ++i){
            longest[i] = 0;
            for(int j = 0; j < n; ++j){
                if(longest[i] < dist[i][j]){
                    longest[i] = dist[i][j];
                }
            }
            if(min > longest[i]){
                min = longest[i];
            }
        }
        
        List<Integer> r = new LinkedList<Integer>();
        for(int i = 0; i < n; ++i){
            if(longest[i] == min){
                r.add(i);
            }
        }
        
        return r;
    }
}
