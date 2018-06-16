package leetcode.problem126.method2;

import java.util.*;

public class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if(wordList == null || wordList.isEmpty()){
            return new ArrayList<List<String>>(0);
        }
        
        int wlen = beginWord.length();
        
        Map<String, Info> dictMap = new HashMap<>(); 
        for(String s : wordList){
            dictMap.put(s, new Info(s));
        }
        
        if(dictMap.get(endWord) == null){
            return new ArrayList<List<String>>(0);
        }
        
        Set<Info> q = new HashSet<>();
        
        Info binfo = new Info(beginWord);
        binfo.dist = 0;
        List<String> l = new ArrayList<>();
        l.add(binfo.str);
        binfo.list.add(l);
        q.add(binfo);
        
        int dist = 1;
        while(!q.isEmpty()){
            Set<Info> newq = new HashSet<>();
            for(Info info : q){
            	char[] chs = info.str.toCharArray();
                for(int i = 0; i < wlen; ++i){
                    char c = chs[i];
                    for(char newc = 'a'; newc <= 'z'; ++newc){
                        if(c == newc) continue;
                        
                        chs[i] = newc;
                        String news = String.valueOf(chs);
                        
                        Info newInfo = dictMap.get(news);
                        if(newInfo == null) continue;
                        
                        if(newInfo.dist >= dist){
                            newInfo.dist = dist;
                            
                            for(List<String> ll : info.list) {
                            	List<String> newll = new ArrayList<>(ll);
                            	newll.add(newInfo.str);
                            	newInfo.list.add(newll);
                            }

                            if(!newInfo.str.equals(endWord)) {
                                newq.add(newInfo);
                            }
                        }
                    }
                    chs[i] = c;
                }
            }
            q = newq;
            ++dist;
        }
        
        return dictMap.get(endWord).list;
    }
    
    static class Info{
        String str;
        int dist = Integer.MAX_VALUE;
        List<List<String>> list = new ArrayList<>();
        
        Info(String str){
            this.str = str;
        }
    }

	public static void main(String[] args) {
		String beginWord = "hit";
		String endWord = "cog";
		String[] dict = {"hot","dot","dog","lot","log","cog"};
		
		List<String> wordList = new ArrayList<>(dict.length);
		for(String s : dict) {
			wordList.add(s);
		}
		
		Solution s = new Solution();
		System.out.println(s.findLadders(beginWord, endWord, wordList));

	}

}
