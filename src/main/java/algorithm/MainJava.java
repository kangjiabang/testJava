package algorithm;

import java.util.HashSet;
import java.util.Set;

public class MainJava {

    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();//set集合元素无序，不重复
        int maxLength = 0;
        for (int i = 0; i < n; i++) {
            //不包含元素，添加
            if (!set.contains(s.charAt(i))) {
                set.add(s.charAt(i));
            } else {
                //包含元素
                set.clear();
                set.add(s.charAt(i));
            }
            if (set.size() > maxLength) {
                maxLength = set.size();
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {

        String s = "abczabcde";
        // "wpwakew"
        System.out.println(lengthOfLongestSubstring(s));
    }

}

