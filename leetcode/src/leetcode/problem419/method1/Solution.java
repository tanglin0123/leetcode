package leetcode.problem419.method1;

public class Solution {
    public int countBattleships(char[][] board) {
        if(board == null || board.length == 0 || board[0].length == 0){
            return 0;
        }
        
        int vnum = 0;
        int hnum = 0;
        
        int m = board.length;
        int n = board[0].length;
        
        int[] vpos = new int[n/32+1];
        
        
        for(int i = 0; i < m; ++i ){
        	int[] newvpos = new int[n/32+1];
            for(int j = 0; j < n; ++j){
                if(board[i][j] == 'X'){
                    if((i+1 < m && board[i+1][j] == 'X') || (i-1 >= 0 && board[i-1][j] == 'X')){ // vertical
                        int idx = j / 32;
                    	int shift = j % 32;
                    	int curvpos = 1 << shift;
                        if((vpos[idx] & curvpos) == 0 ){ // a new v ship
                            ++vnum;
                        }
                        newvpos[idx] = newvpos[idx] | curvpos;
                    } else { // horizontal
                        ++hnum;
                        while(j < n && board[i][j] == 'X'){
                            ++j;
                        }
                        j = j-1;
                        
                    }
                }
            }
            vpos = newvpos;
        }
        
        return vnum+hnum;
    }
    
    

	public static void main(String[] args) {
		String[] a = {"............XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX............................X.........","..........................................XXXXXXXXXXXXXXXXXXXX........X.........","X....X........................................................XXXXXXX.X....X....","X...X.X.........XXXXXX.X.............XXXXXXXXXXXXXXXXXXXXXXXXX........X.....X...","X...X.X...X............X.......................................X......X.....X..X","X...X.X...X..X.....X...X.XXX......XXXXXXXXXXXXXXXXXXXXXXXX............X.....X...","X...X.X.X.X..X..X...XX.X..................................XXXXXXXXXXX.X.....X..X","X..X..X.X.X...X........X.......XXXXXXXXXXXXXXX........................X.....X..X","X...X.X..X.X..X.XXXXXX.X.XXXXXX...............XXXXXXXXXXXXXXXXX.X.X...X.....X..X","....X.X..X..X.X........X.......X...............................X..X...X.....X..X","X...X.X..X..X..........X.......X......XXX......................X..X...X.....X..X","X...X.X..X..X.XXXXXXXX.X.....X.X.........XXXXXXXXXXXXXXXXXXXX..X......X.....X..X","X...X.X..X..X..........X........X....XXXX....................X.X......X.X.X.X..X","X..X........X..........X........X...X.......................................X..X","X...XXXXXXXX....XXXXXX.X........X...X.........XXXXXXXXXXXXXXXXXXXXXXXXXXXX..X.X.","X......................X..X..X..X.X.X.....................................X.X..X","X..........X..XXX.X.X..X..X..X..X.X.X..XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X..X","X..........X......X..X.X..X..X..X.X.X.....................................X.X..X","X..........X......X....X..X..X..X.X.X..X.XXXXXXXXXXXXXXXXXXXX.X...........X....X","X.XXXX.X...X..X...X....X..X..X..X.X.X...X.....................X...........X.X..X","X......X...X...........X..X..X..X.X.X....X...XXXX.XXXXXXXX.X.X.X.....X..........","X.X..X.X...X.XXXXXXXXX.X..X..X..X.X.X....X.................X.X.X.....X...XXXXXXX","X.X..X.X...X...........X...X.X..X.X.X.X..X..XXX......................X..........","X.X..X.X...X....X......X........X.X.X..X.X.X...XXXXXXXXXXXXXXXXXXXXXX.........XX","X.X..X.X...X..X.X......X..XX.XX.X.X....X...X............................X.......","X.X..X.X...X....X......X........X.X.X..X.X.X............X......X.X..............","X.X..X.X..X.....X......X..XX...............X....X....X..X...X....X...X.XXXXXXXXX","X.X..X.X...X....X...X..X....XXXXXXXXXXXXXXX.XXX.X.......X...X....X...X..........","X.X..X.X...X....X...X..X........................X....X..X..X.....X...X....XXX.X.","X.X....X...X..X.X...X..X.........X...................X..X..X.....X............X.","X.X..XX....X..X..XX.X..X.........X...........XX.XXXX.X.....X.XXXX.XXXX.XXXX...X.","X.X.X......X..X.....X..X..X.X..X.X..X...X..XX........X.XXX.X....................","X.X........X..X.....X..X...X.X.X.X..X....X...X.......X.....X.....XXXXXXXXXXXXXXX","X.X....XXX.X..X.....X..X......X..X..X....X...X.......X..X..X....................","X.X........X..X.....X..X......X..X...X...X...X.......X..X...........XXX.X.....X.","X..X.......X..X.....X..X..X.X.X...XX.X...X.X.X.......X...XXXXXXXXXX.............","X..X.......X..X.....X..X..X.X.X......X...X...X.X.....X.............XXXXXXXXXXXXX","...X................X..X..X.X.X......X...X...X.X.....X...X..X...................","...X...XXXXXXXXXXXX.X..X..X..........................X...X.X.XXXXXXXXXX.XXXXXXXX","...X................X..X.X.XXXXXXXXXXXXXXXXXXXXXXXXX.X...X......................","XX.X.XXXXXXXXX......X..X.X...........................X...X.X.........XX.......X.","...X..........XXXXX.X..X...X.....X....XXXXXXXXXXXXXX.X...X.X..............XXX.X.","....................X..X..X.XXXX.X...................X...X....................X.",".X.X..........XXXXX.X..X..X......X...XXXXXXXXXXXXXXX.X...X.X..................X.","X..X..X.X...........X..X..X..X...X..X................X..X........X..........X.X.","X..X..X.X..X........X..X..X..X...X..X............XX..X..X......XX...XX.X.X....X.","X.....X.X..X.....XX.X..X..X..X...X..X.........XXX..X.X..X.XXX..........X.X..X.X.","X..X..X.X..X........X.....X..X...X..X..X..X..........X..X....X.........X....X.X.","X.....X.X..X.X......X..XX.X..X...X..X..X..X..........X..X....X.X....X....X....X.","X..X..X.X.X..X......X.....X..X...X..X..X..X.XXXXX.XX.X.......X.X.X..X..X..XXX.X.","X..X..X.X.X..X......X.....X...XX.X.X...X..X..........X.......X.X.X..............","X..X......X..X.XXXX.X...X.X......X.X...X.............X..XXXX.X.X..XXXXXXXXXXXXXX","X..X..XXX.X.X.......X...X.X......X.X....XXXXXXXXXXXX.X.......X.X................","X..X......X.X.X.X...X.X.X.X.X....X.X.................X.......X.X.....X.X.XX.X...","..........X.X...X.X.X.X.X.X.X....X.X.................X..........................","XXXXXXXXX.X.X...X...X.X.X.X.X..X.X..............X....X.......XXXXXXXXXXXXXXXXXXX","..........X.X...X...X.....X.X..X.X.XXXXXXXXXX.X.X....X.X.X......................","..........X.X.X.X...X.X.X.X.X..X.X............X.X....X.X.X......................","..X..X....X.X...X...X.X.X.X.X..X.X........X...X.X.X..X.X.X....XX.X....XX.XXXXXXX","..X...XXX.X.X...X...X.X.X.X....X.X......X.X....X..X..X.X.X.......X..............","..X..X....X.X.X.X...X.X.X.X.X....X.XXXX.X.X.X..X..X..X.X.X..XXXX.X.X.X..........","....................X.X.X.X.X..X.X......X.X....X.X...X.X.X.......X.X.X...X......","X.X..XXXXXXXXXXXXXX.X.X.X.X.X..X.X..X....X..XX.X.X...X.X.X.X.....X..X...........","X.X.................X.X.X.X.X..X.X..X..X.X.....X.X.X.X.X.X.X.....X...XXXXXXXXXXX","X..XXXXXXXXXXXXXXXX.X.X.X.X....X.X..X....X.....X.X.X.X.X.X.X.....X..............","X...................X.X.X.X..X.X.X..X....X.X...X.X.X.X.X.X.X.....X......X..X....","X...XXXXXXXXXXXXXXX.X.X.X.X....X.X..X....X.X...X.X.X.X.X.X.X..XX.X......X..X....","X...................X.X.X.X.XX.X.X..X....X.X...X.X.X.X.X.X.X.....X......X..X....","X....X.....X..X.....X.X.X.X....X.X..X..X.X.....X.X.X.X.X.X.X..X..X......X...XXXX","X....X.....X..X.....X.X.X.X....X.X..X..X.X.X.X.X.....X.X.X.X..X..X.XX...X.X.....","X....X.....X..X.....X.X.X.X....X.X..X..X.X...X.X.XXX.X.X.X.X..X..X...X..X..XXXXX","X....X.....X..X...X.X.X.X.X....X.X.X...X.X...X.X.....X.X.X.X..X..X......X.......","X..........X..X.....X.X.X.X..X...X..XX.X.X...X.X...X.X.X.X.X.....X...XX.X.XX...X","X....XXX...X........X.X.X.X..........................X.X.X.X..XX.X......X...X...","X.......X..X..XXXXX.X.X.X.X.....XXXXXXXXXXXXXXXXXXXX.X.X.X.X.....X...XX.X...X...","X.......X..X........X.X.X.X....X.....................X.X.X.X.....X......X...X...","X.....X.X..X........X...X.X.XX.X..X..................X.X.X.X..XX.X......X...X...","X...X.X.X..X........X.X.X.X....X...........XXXXXXXXX.X.X.X.X.X...X...X..X...X...","X..X..X.X..X...X....X...X......X..X..X....X..........X.X.X.X.....X.X..X.....X...","X..X..X.X..X.X.X....X...X......X..X..X...............X.X.X.X.....X.X..X...X.X..."};
		
		
		
		char[][] b = new char[a.length][];
		
		for(int i = 0; i < a.length; ++i){
			b[i] = a[i].toCharArray();
		}
		
		Solution s = new Solution();
		System.out.println(s.countBattleships(b));
		
		for(String str : a ){
			System.out.println(str);
		}
	}

}
