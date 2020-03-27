package algorithm;

import java.util.HashMap;
import java.util.Map;

public class LongestNoRepeatString {

    public static void main(String[] args) {
        String input = " aa bcd  ewdsc sasfewt sfrewqt ";
        System.out.println(computeLongestNoRepeatString(input));
    }

    public static String computeLongestNoRepeatString(String input) {

        Map<Character, Integer> characterToCount = new HashMap<Character, Integer>();

        //初始化值
        int strBeginIndex = -1;
        int stringLength = 0;
        int maxStrLength = 0;
        String maxStr = "";

        for (int i = 0; i < input.length(); i++) {

            //if space
            if (input.charAt(i) == ' ') {
                continue;
            }
            //no space
            if (strBeginIndex == -1) {
                strBeginIndex = i;
            }

            //compute total length;
            //is contains in map
            if (characterToCount.containsKey(input.charAt(i))) {
                //skipToNextString,clear map
                characterToCount.clear();
                stringLength = 0;
                strBeginIndex = -1;
                continue;
            } else {
                stringLength++;
                characterToCount.put(input.charAt(i), 1);
            }
            //if i == str.length-1 or next is space,end for this string
            if (i == input.length() -1  || input.charAt(i + 1) == ' ') {
                //compare with previous length
                if (stringLength > maxStrLength) {
                    maxStrLength = stringLength;
                    maxStr = input.substring(strBeginIndex, i + 1);
                }
                //after this string ,clear resource
                characterToCount.clear();
                stringLength = 0;
                strBeginIndex = -1;
            }

        }
        return maxStr;
    }

}
