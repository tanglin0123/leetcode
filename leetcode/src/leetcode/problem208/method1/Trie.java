package leetcode.problem208.method1;

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        root.insert(word);
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        return root.search(word);
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        return root.startsWith(prefix);
    }
    
    public static void main(String[] args){
    	String[] dict = {"ab"};
    	String[] search = {"a", "ab"};
    	String[] prefix = {"a", "ab"};
    	
    	Trie t = new Trie();
    	for(String s:dict){
    		t.insert(s);
    	}
    	
    	for(String s:search){
    		System.out.println(t.search(s));
    	}
    	
    	for(String s:prefix){
    		System.out.println(t.startsWith(s));
    	}
    	
    }
}

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");