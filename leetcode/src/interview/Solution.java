package interview;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {

	public List<String> findPath(String begin, String end, Set<String> dict){
		List<String> r = new ArrayList<String>();
		
		if(begin == null || end == null || begin.trim().isEmpty() || end.trim().isEmpty()){
			return r;
		}
		
		begin = begin.trim().toLowerCase();
		end = end.trim().toLowerCase();
		
		dict.add(begin);
		dict.add(end);
		
		this.findPath(begin, end, dict, -1, r, new HashSet<String>());
		
		return r;
	}
	
	public boolean findPath(String begin, String end, Set<String> dict, int lastPos, List<String> curPath, Set<String> curPathSet){
		if(begin.equals(end)){
			curPath.add(end);
			return true;
		}
		
		curPath.add(begin);
		curPathSet.add(begin);
		
		char[] chs = begin.toCharArray();
		boolean found = false;
		for(int i = 0; i < chs.length; ++i){
			if(lastPos == i){
				continue;
			}
			
			char c = chs[i];
			
			for(char newc = 'a'; newc <= 'z' ; ++newc){
				chs[i] = newc;
				String news = new String(chs);
				if(dict.contains(news) && !curPathSet.contains(news)){
					found = findPath(news, end, dict, i, curPath, curPathSet);
					if(found){
						return true;
					}
				}
			}
			
			chs[i] = c;
		}
		
		curPath.remove(curPath.size() - 1);
		curPathSet.remove(begin);
		return found;
	}
	
	
	
	public static void main(String[] args) {
		Set<String> dict = new HashSet<String>();
		dict.add("hot");
		dict.add("dot");
		dict.add("dog");
		dict.add("lot");
		dict.add("log");
		
		String begin = "hit";
		String end = "cog";
		
		Solution s = new Solution();
		
		List<String> r = s.findPath(begin, end, dict);
		
		for(String str : r){
			System.out.println(str);
		}
		
	}

}
