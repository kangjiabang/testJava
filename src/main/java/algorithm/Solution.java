package algorithm;

import java.util.Arrays;

class Solution {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcded"));
    }
    public static int lengthOfLongestSubstring(String s) {
        int[] m = new int[256];
        Arrays.fill(m, -1);
        int res = 0, left = -1;
        for (int i = 0; i < s.length(); ++i) {
            //当出现重复的时候，从这个重复的位置重新开始，实际上是从下一个位置开始的
            left = Math.max(left, m[s.charAt(i)]);
            m[s.charAt(i)] = i;//字母当下标，位置当值
            //求每次不重复的子串
            res = Math.max(res, i - left);
        }
        return res;
    }
}
