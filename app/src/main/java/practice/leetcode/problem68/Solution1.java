package practice.leetcode.problem68;

import java.util.ArrayList;
import java.util.List;

public class Solution1 {
    public List<String> fullJustify(String[] words, int maxWidth) {

        int currentLength = 0;
        int currentWordCount = 0;

        List<String> result = new ArrayList<>();

        for(int i = 0; i < words.length; ++i) {
            currentWordCount ++;
            currentLength += words[i].length();

            if(i + 1 < words.length) {
                int nextLength = currentLength + currentWordCount + words[i + 1].length();

                if (nextLength > maxWidth) {

                    if (currentWordCount > 1) {
                        int spaceSize = (maxWidth - currentLength) / (currentWordCount - 1);
                        int remainingSpaces = (maxWidth - currentLength) % (currentWordCount - 1);

                        StringBuffer line = new StringBuffer();
                        for(int j = i - currentWordCount + 1; j < i; ++j) {
                            line.append(words[j]);
                            for(int k = 0; k < spaceSize; ++k) {
                                line.append(" ");
                            }
                            if (remainingSpaces > 0) {
                                line.append(" ");
                                remainingSpaces --;
                            }
                        }
                        line.append(words[i]);

                        result.add(line.toString());

                    } else { 
                        StringBuffer line = new StringBuffer();
                        line.append(words[i]);

                        int paddingSize = maxWidth - line.length();
                        for(int k = 0; k < paddingSize; ++k) {
                            line.append(" ");
                        }

                        result.add(line.toString());
                    }
                    

                    // reset line
                    currentLength = 0;
                    currentWordCount = 0;
                }

            } else { // reached the last word
                StringBuffer line = new StringBuffer();
                for(int j = i - currentWordCount + 1; j < i; ++j) {
                    line.append(words[j]);
                    line.append(" ");

                }
                line.append(words[i]);

                int paddingSize = maxWidth - line.length();
                for(int k = 0; k < paddingSize; ++k) {
                    line.append(" ");
                }

                result.add(line.toString());
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Solution1 sln = new Solution1();
        
        String[] words1 = {"This", "is", "an", "example", "of", "text", "justification."};
        int maxWidth1 = 16;

        List<String> result1 = sln.fullJustify(words1, maxWidth1);

        System.out.println(result1);


        String[] words2 = {"What","must","be","acknowledgment","shall","be"};
        int maxWidth2 = 16;

        List<String> result2 = sln.fullJustify(words2, maxWidth2);

        System.out.println(result2);

    }

}
