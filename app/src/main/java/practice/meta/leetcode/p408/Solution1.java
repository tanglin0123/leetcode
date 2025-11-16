package practice.meta.leetcode.p408;

public class Solution1 {
    public boolean validWordAbbreviation(String word, String abbr) {
        if (abbr.isEmpty() && !word.isEmpty()) {
            return false;
        }

        if (!abbr.isEmpty() && word.isEmpty()) {
            return false;
        }

        int wp = 0;
        int ap = 0;
        
        while(ap < abbr.length()) {
            if(abbr.charAt(ap) == '0') {
                return false;
            }

            if(abbr.charAt(ap) >= '1' && abbr.charAt(ap) <= '9') {
                int chCount = 0;

                while(ap < abbr.length() && abbr.charAt(ap) >= '0' && abbr.charAt(ap) <= '9') {
                    char ac = abbr.charAt(ap);
                    chCount = chCount * 10 + (ac - '0');
                    ++ ap;
                }

                wp += chCount;
            }

            if (wp > word.length()) {
                return false;
            } else if (wp == word.length()) {
                return ap == abbr.length();
            } else { // if wp < word.length
                if (ap >= abbr.length()) { // extra in abbr
                    return false;
                }

                if (word.charAt(wp) != abbr.charAt(ap)) {
                    return false;
                } 
            }

            ++ wp;
            ++ ap;
        } 


        return wp == word.length();
        
    }

    public static void main(String[] args) {
        Solution1 sln = new Solution1();

        String word1 = "internationalization", abbr1 = "i12iz4n";

        boolean result1 = sln.validWordAbbreviation(word1, abbr1);

        System.out.println(result1);


        String word2 = "ab", abbr2 = "a";

        boolean result2 = sln.validWordAbbreviation(word2, abbr2);

        System.out.println(result2);
    }
}
