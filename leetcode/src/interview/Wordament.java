package interview;

import java.io.*;
import java.util.*;

public class Wordament{
	public static class TrieNode{
		char c;
		TrieNode[] children = new TrieNode[27];
		
		public TrieNode(char c){
			this.c = c;
		}
		
		public void addWord(char[] chs, int idx){
			int i = chs[idx] - 'a';
			if(children[i] == null){
				children[i] = new TrieNode(chs[idx]);
			}
			
			if(idx < chs.length-1){
				children[i].addWord(chs, idx + 1);
			} else {
				children[i].children[26] = new TrieNode('$');
			}
		}
		
		public void addWord(String s){
			addWord(s.toCharArray(), 0);
		}
		
		public TrieNode getChild(char c){
			return children[c - 'a'];
		}
		
	}
	
	TrieNode trie = new TrieNode('#');
	
	public Wordament(String dict) {
		FileReader fin = null;
		BufferedReader reader = null;
		try {
			fin = new FileReader(new File(dict));
			reader = new BufferedReader(fin);

			String s = reader.readLine();
			while (s != null) {
				System.out.println(s);
				
				trie.addWord(s);
				s = reader.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (fin != null) {
				try {
					fin.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	}
	
	public Set<String> getAllWords(char[][] board){
		Set<String> rst = new HashSet<String>();
		
		if(board == null){
			return rst;
		}
		
		int rlen = board.length;
		if(rlen == 0){
			return rst;
		}
		
		if(board[0] == null){
			return rst;
		}
		
		int clen = board[0].length;
		if(clen == 0){
			return rst;
		}
		
		for(int r = 0; r < rlen; ++r){
			for(int c = 0; c < clen; ++c){
				char v = board[r][c];
				boolean[][] visited = new boolean[rlen][clen];
				StringBuffer curStr = new StringBuffer();
				
				visited[r][c] = true; 
				curStr.append(v);
				
				backtrace(trie.getChild(v), r, c, visited, board, rst, curStr);
				
				visited[r][c] = false;
				curStr.deleteCharAt(curStr.length() - 1);
			}
		}
		
		return rst;
	}
	
	void backtrace(TrieNode curNode, int r, int c, boolean[][] visited, char[][] board, Set<String> rst, StringBuffer curStr){
		if(curNode == null){
			return;
		}
		
		if(curNode.children[26] != null){
			String s = curStr.toString();
			rst.add(s);
		}
		
		int rlen = board.length;
		int clen = board[0].length;
		
		int[][] dir = {{1,1},{1,0},{1,-1},{0,1},{0,-1},{-1,1},{-1,0},{-1,-1}};
		
		for(int[] d : dir){
			int newr = r + d[0];
			int newc = c + d[1];
			
			if(newr >= 0 && newc >= 0 && newr < rlen && newc < clen && !visited[newr][newc]){
				visited[newr][newc] = true;
				char v = board[newr][newc];
				curStr.append(v);
				backtrace(curNode.getChild(v), newr, newc, visited, board, rst, curStr);
				visited[newr][newc] = false;
				curStr.deleteCharAt(curStr.length()-1);
			}
			
		}
	}

	public static void main(String[] args) {
		String source = "wordsEn.txt";
		
		String[] a = { "abcd", "efgh", "ijkl", "mnop", };
		
		char[][] b = new char[4][];
		for(int i = 0; i < a.length; ++i){
			b[i] = a[i].toCharArray();
		}
		
		Wordament w = new Wordament(source);
		for(String s : w.getAllWords(b)){
			System.out.println(s);
		}
	}

}
