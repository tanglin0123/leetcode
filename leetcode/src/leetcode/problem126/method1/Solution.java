package leetcode.problem126.method1;

import java.util.*;

public class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        List<List<String>> r = new LinkedList<List<String>>();
        
        if(beginWord == null || endWord == null || beginWord.trim().isEmpty() || endWord.trim().isEmpty()){
            return r;
        }
        
        beginWord = beginWord.trim().toLowerCase();
        endWord = endWord.trim().toLowerCase();
        wordList.add(beginWord);
        wordList.add(endWord);
        
        if(beginWord.equals(endWord)){
            List<String> r1 = new LinkedList<String>();
            r1.add(beginWord);
            r.add(r1);
            return r;
        }
        
        LadderString begin = new LadderString(beginWord, -1, null);
        findLadders(begin, endWord, wordList, r);
        
        return r;
    }
    
    void findLadders(LadderString begin, String end, Set<String> wordList, List<List<String>> r){
    	Set<String> visited  = new HashSet<String>();
        List<LadderString> curLayer = new LinkedList<LadderString>();
        curLayer.add(begin);
        while(!curLayer.isEmpty()){
            List<LadderString> nextLayer = new LinkedList<LadderString>();
        
            for(LadderString ladStr : curLayer){
               visited.add(ladStr.s);
            	
                char[] chs = ladStr.s.toCharArray();
                for(int i = 0; i < chs.length; ++ i){
                    if(i == ladStr.prePos){
                        continue;
                    }
                    
                    char c = chs[i];
                    
                    for(char newc = 'a'; newc <= 'z'; ++ newc){
                        if(newc == c){
                            continue;
                        }
                        
                        chs[i] = newc;
                        String news = new String(chs);
                        
                        if(news.equals(end)){ // found
                        	List<String> r1 = new LinkedList<String>();
                        	r1.add(end);
                            LadderString curNode = ladStr;
                            while(curNode != null){
                                r1.add(0, curNode.s);
                                curNode = curNode.parent;
                            }
                            r.add(r1);
                            break;
                        }
                        
                        if(!wordList.contains(news)){ // not in the dict
                            continue;
                        }
                        
                        if(!visited.contains(news)){ // create a new LadderString for next layer
                            LadderString newLadStr = new LadderString(news, i , ladStr);
                            nextLayer.add(newLadStr);
                        }
                    }
                    
                    chs[i] = c;
                }
            
            }
            
            if(!r.isEmpty()){
                break;
            } else {
                curLayer = nextLayer;
            }
            
        }
        
    }
    
    public static class LadderString {
        String s; 
        int prePos = -1;
        LadderString parent;
        
        LadderString(String s, int prePos, LadderString parent){
            this.s = s;
            this.prePos = prePos;
            this.parent = parent;
        }
    }
    
    public static void main(String[] args){
    	String beginWord = "cet";
    	String endWord = "ism";
    	String[] ws = new String[]{"kid","tag","pup","ail","tun","woo","erg","luz","brr","gay","sip","kay","per","val","mes","ohs","now","boa","cet","pal","bar","die","war","hay","eco","pub","lob","rue","fry","lit","rex","jan","cot","bid","ali","pay","col","gum","ger","row","won","dan","rum","fad","tut","sag","yip","sui","ark","has","zip","fez","own","ump","dis","ads","max","jaw","out","btu","ana","gap","cry","led","abe","box","ore","pig","fie","toy","fat","cal","lie","noh","sew","ono","tam","flu","mgm","ply","awe","pry","tit","tie","yet","too","tax","jim","san","pan","map","ski","ova","wed","non","wac","nut","why","bye","lye","oct","old","fin","feb","chi","sap","owl","log","tod","dot","bow","fob","for","joe","ivy","fan","age","fax","hip","jib","mel","hus","sob","ifs","tab","ara","dab","jag","jar","arm","lot","tom","sax","tex","yum","pei","wen","wry","ire","irk","far","mew","wit","doe","gas","rte","ian","pot","ask","wag","hag","amy","nag","ron","soy","gin","don","tug","fay","vic","boo","nam","ave","buy","sop","but","orb","fen","paw","his","sub","bob","yea","oft","inn","rod","yam","pew","web","hod","hun","gyp","wei","wis","rob","gad","pie","mon","dog","bib","rub","ere","dig","era","cat","fox","bee","mod","day","apr","vie","nev","jam","pam","new","aye","ani","and","ibm","yap","can","pyx","tar","kin","fog","hum","pip","cup","dye","lyx","jog","nun","par","wan","fey","bus","oak","bad","ats","set","qom","vat","eat","pus","rev","axe","ion","six","ila","lao","mom","mas","pro","few","opt","poe","art","ash","oar","cap","lop","may","shy","rid","bat","sum","rim","fee","bmw","sky","maj","hue","thy","ava","rap","den","fla","auk","cox","ibo","hey","saw","vim","sec","ltd","you","its","tat","dew","eva","tog","ram","let","see","zit","maw","nix","ate","gig","rep","owe","ind","hog","eve","sam","zoo","any","dow","cod","bed","vet","ham","sis","hex","via","fir","nod","mao","aug","mum","hoe","bah","hal","keg","hew","zed","tow","gog","ass","dem","who","bet","gos","son","ear","spy","kit","boy","due","sen","oaf","mix","hep","fur","ada","bin","nil","mia","ewe","hit","fix","sad","rib","eye","hop","haw","wax","mid","tad","ken","wad","rye","pap","bog","gut","ito","woe","our","ado","sin","mad","ray","hon","roy","dip","hen","iva","lug","asp","hui","yak","bay","poi","yep","bun","try","lad","elm","nat","wyo","gym","dug","toe","dee","wig","sly","rip","geo","cog","pas","zen","odd","nan","lay","pod","fit","hem","joy","bum","rio","yon","dec","leg","put","sue","dim","pet","yaw","nub","bit","bur","sid","sun","oil","red","doc","moe","caw","eel","dix","cub","end","gem","off","yew","hug","pop","tub","sgt","lid","pun","ton","sol","din","yup","jab","pea","bug","gag","mil","jig","hub","low","did","tin","get","gte","sox","lei","mig","fig","lon","use","ban","flo","nov","jut","bag","mir","sty","lap","two","ins","con","ant","net","tux","ode","stu","mug","cad","nap","gun","fop","tot","sow","sal","sic","ted","wot","del","imp","cob","way","ann","tan","mci","job","wet","ism","err","him","all","pad","hah","hie","aim","ike","jed","ego","mac","baa","min","com","ill","was","cab","ago","ina","big","ilk","gal","tap","duh","ola","ran","lab","top","gob","hot","ora","tia","kip","han","met","hut","she","sac","fed","goo","tee","ell","not","act","gil","rut","ala","ape","rig","cid","god","duo","lin","aid","gel","awl","lag","elf","liz","ref","aha","fib","oho","tho","her","nor","ace","adz","fun","ned","coo","win","tao","coy","van","man","pit","guy","foe","hid","mai","sup","jay","hob","mow","jot","are","pol","arc","lax","aft","alb","len","air","pug","pox","vow","got","meg","zoe","amp","ale","bud","gee","pin","dun","pat","ten","mob"};
    	
//    	String beginWord = "hit";
//    	String endWord = "cog";
//    	String[] ws = new String[]{"hot","dot","dog","lot","log"};
    	
//    	String beginWord = "a";
//    	String endWord = "c";
//    	String[] ws = new String[]{};
    	
    	Set<String> wordList = new HashSet<String>(Arrays.asList(ws));
    
    	Solution s = new Solution();
    	List<List<String>> r = s.findLadders(beginWord, endWord, wordList);
    	
    	for(List<String> r1 : r){
    		System.out.print("[");
    		for(String s1 : r1){
    			System.out.print(s1 + "->");
    		}
    		System.out.println("]");
    	}
    }
}