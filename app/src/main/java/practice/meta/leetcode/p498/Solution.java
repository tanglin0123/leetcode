package practice.meta.leetcode.p498;

public class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        int[][] direction = {
            {-1, 1}, // up right
            {1, -1} // down left
        };
        int curDir = 0;

        int h = mat.length;
        int w = mat[0].length;

        int r = 0, c = 0;
        int[] result = new int[h * w];
        
        int i = 0;

        while (r >= 0 && r < h && c >= 0 && c < w) {
            result[i++] = mat[r][c];
            
            if (r == h-1 && c == w-1) {
                return result;
            }

            int nextR = r + direction[curDir][0];
            int nextC = c + direction[curDir][1];

            if (nextR >= 0 && nextR < h && nextC >= 0 && nextC < w) { // within the boundary
                r = nextR;
                c = nextC;

            } else if (curDir == 0) {
                if (nextC >= w) {
                    r = r + 1;
                    c = w - 1;
                } else if (nextR < 0) {
                    r = 0;
                    c = nextC;
                }
                curDir = 1;
            } else if (curDir == 1) {
                if (nextR >= h) {
                    r = h - 1;
                    c = c + 1;
                } else if (nextC < 0) {
                    r = nextR;
                    c = 0;
                }
                curDir = 0;
            }
        }

        return result;
    }
}
