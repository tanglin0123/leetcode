package practice.meta.leetcode.p766;

class Solution {
    public boolean isToeplitzMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || 
                matrix[0] == null || matrix[0].length == 0) {
            return false;
        }

        int h = matrix.length;
        int w = matrix[0].length;

        if(h == 1 || w == 1) {
            return true;
        }

        
        for(int i = 0; i < w; ++i) {
            int value = matrix[0][i];
            for(int r = 1, c = i + 1; r < h && c < w ; ++ r, ++c) {
                if(matrix[r][c] != value) {
                    return false;
                }
            }
        }

        for(int i = 1; i < h; ++i) {
            int value = matrix[i][0];
            for(int r = i + 1, c = 1; r < h && c < w ; ++ r, ++c) {
                if(matrix[r][c] != value) {
                    return false;
                }
            }
        }

        return true;
    }
}
