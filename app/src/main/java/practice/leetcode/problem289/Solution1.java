package practice.leetcode.problem289;

import java.util.Arrays;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Solution1 {
    public void gameOfLife(int[][] board) {
        int h = board.length;
        int w = board[0].length;

        int[][] newBoard = new int[h][w];
        
        for(int i = 0; i < h; ++i) {
            for (int j = 0; j < w; ++j) {
                // if ( i == 2 && j == 2){
                //     log.debug("pause");
                // }
                int liveCount = getLiveNeighborCount(board, i, j);
                if (liveCount < 2) {
                    newBoard[i][j] = 0;
                } else if (liveCount > 3) {
                    newBoard[i][j] = 0;
                } else if (liveCount == 3) {
                    newBoard[i][j] = 1;
                } else { // liveCount == 2, no change
                    newBoard[i][j] = board[i][j];
                }
                
            }
        }
        for(int i = 0; i < h; ++i) {
            board[i] = newBoard[i];
        }
    }

    private int getLiveNeighborCount(int[][] board, int i, int j) {
        int h = board.length;
        int w = board[0].length;
        
        int count = 0;

        if(i > 0 && board[i - 1][j] == 1) {
            ++ count;
        } 
        if(i < h - 1 && board[i + 1][j] == 1) {
            ++ count;
        }
        if(j > 0 && board[i][j - 1] == 1) {
            ++ count;
        }
        if(j < w - 1 && board[i][j + 1] == 1) {
            ++ count;
        }

        if(i > 0 && j > 0 && board[i - 1][j - 1] == 1) {
            ++ count;
        }
        if(i > 0 && j < w - 1 && board[i - 1][j + 1] == 1) {
            ++ count;
        } 
        if(i < h - 1 && j > 0 && board[i + 1][j - 1] == 1) {
            ++ count;
        }
        if(i < h - 1 && j < w - 1 && board[i + 1][j + 1] == 1) {
            ++ count;
        }

        return count;
    }

    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();


        int[][] board = {{0,1,0},{0,0,1},{1,1,1},{0,0,0}};

        solution1.gameOfLife(board);

        for (int[] is : board) {
            log.info(Arrays.toString(is));
        }
        
    }
}
