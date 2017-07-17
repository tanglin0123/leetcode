package interview;

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
				children[26] = new TrieNode('$');
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
	
	public Wordament(String[] dict){
		if(dict != null){
			for(String s : dict){
				trie.addWord(s);
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
		
		// r
		if(c - 1 >= 0 && !visited[r][c-1]){
			visited[r][c-1] = true;
			char v = board[r][c-1];
			curStr.append(v);
			backtrace(curNode.getChild(v), r, c-1, visited, board, rst, curStr);
			visited[r][c-1] = false;
			curStr.deleteCharAt(curStr.length()-1);
		}
		
		if(c + 1 < clen && !visited[r][c+1]){
			visited[r][c+1] = true;
			char v = board[r][c+1];
			curStr.append(v);
			backtrace(curNode.getChild(v), r, c+1, visited, board, rst, curStr);
			visited[r][c+1] = false;
			curStr.deleteCharAt(curStr.length()-1);
		}
		
		// r + 1
		if(r + 1 < rlen && !visited[r+1][c]){
			visited[r+1][c] = true;
			char v = board[r+1][c];
			curStr.append(v);
			backtrace(curNode.getChild(v), r+1, c, visited, board, rst, curStr);
			visited[r+1][c] = false;
			curStr.deleteCharAt(curStr.length()-1);
		}
		
		if(r + 1 < rlen && c - 1 >= 0 && !visited[r+1][c-1]){
			visited[r+1][c-1] = true;
			char v = board[r+1][c-1];
			curStr.append(v);
			backtrace(curNode.getChild(v), r+1, c-1, visited, board, rst, curStr);
			visited[r+1][c-1] = false;
			curStr.deleteCharAt(curStr.length()-1);
		}
		
		if(r + 1 < rlen && c + 1 < clen && !visited[r+1][c+1]){
			visited[r+1][c+1] = true;
			char v = board[r+1][c+1];
			curStr.append(v);
			backtrace(curNode.getChild(v), r+1, c+1, visited, board, rst, curStr);
			visited[r+1][c+1] = false;
			curStr.deleteCharAt(curStr.length()-1);
		}
		
		// r - 1
		if(r - 1 >= 0 && !visited[r-1][c]){
			visited[r-1][c] = true;
			char v = board[r-1][c];
			curStr.append(v);
			backtrace(curNode.getChild(v), r-1, c, visited, board, rst, curStr);
			visited[r-1][c] = false;
			curStr.deleteCharAt(curStr.length()-1);
		}

		if(r - 1 >= 0 && c + 1 < clen && !visited[r-1][c+1]){
			visited[r-1][c] = true;
			char v = board[r-1][c+1];
			curStr.append(v);
			backtrace(curNode.getChild(v), r-1, c+1, visited, board, rst, curStr);
			visited[r-1][c+1] = false;
			curStr.deleteCharAt(curStr.length()-1);
		}
		
		if(r - 1 >= 0 && c - 1 >= 0 && !visited[r-1][c-1]){
			visited[r-1][c-1] = true;
			char v = board[r-1][c-1];
			curStr.append(v);
			backtrace(curNode.getChild(v), r-1, c-1, visited, board, rst, curStr);
			visited[r-1][c-1] = false;
			curStr.deleteCharAt(curStr.length()-1);
		}
		
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
