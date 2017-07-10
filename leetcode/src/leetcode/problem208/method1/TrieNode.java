package leetcode.problem208.method1;

class TrieNode {
    // Initialize your data structure here.
    char ch;
    String word = null;
    TrieNode[] children = new TrieNode[26];
    
    public TrieNode() {
        ch='#';
        word = null;
    }
    
    public void insert(String w){
        if(w == null || w.isEmpty()){
            return;
        }
        char c = w.charAt(0);
        int i = c - 'a';
        
        if(children[i] == null){
            children[i] = new TrieNode();
        }
        children[i].insert(w, 0);
    }
    
    public void insert(String w, int index){
        this.ch = w.charAt(index);
        if(index == w.length() -1){ // end
            this.word = w;
            return;
        }
        
        // not end
        int i = w.charAt(++index) - 'a'; 
        if(children[i] == null){
            children[i] = new TrieNode();
        }
        children[i].insert(w, index);
    }
    
    public boolean search(String w){
        if(w == null || w.isEmpty()){
            return false;
        }
        
        char c = w.charAt(0);
        int i = c - 'a';
        
        if(children[i] == null){
            return false;
        }
        
        return children[i].search(w, 0);
    }
    
    public boolean search(String w, int index){
        if(index == w.length() -1){
            return word != null;
        }

        char c = w.charAt(index + 1);
        int i = c - 'a';
        
        if(children[i] == null){
            return false;
        }
        
        return children[i].search(w, index + 1);
    }
    
    public boolean startsWith(String w){
        if(w == null || w.isEmpty()){
            return false;
        }
        
        char c = w.charAt(0);
        int i = c - 'a';
        
        if(children[i] == null){
            return false;
        }
        
        return children[i].startsWith(w, 0);
    }
    
    public boolean startsWith(String w, int index){
        if(index == w.length() -1){
            return true;
        }

        char c = w.charAt(index + 1);
        int i = c - 'a';
        
        if(children[i] == null){
            return false;
        }
        
        return children[i].startsWith(w, index + 1);
    }
}