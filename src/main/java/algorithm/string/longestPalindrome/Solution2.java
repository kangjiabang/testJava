package algorithm.string.longestPalindrome;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author  kangjiabang
 * @CreateTime 2022-08-15  14:09
 * @Description TODO
 */
public class Solution2 {
    public String longestPalindrome(String s) {
        if (s == null || s.equals("")) {
            return "";
        }
        if (s.length() < 2) {
            return s;
        }
        boolean[][] arr = new boolean[s.length()][s.length()];
        int begin = 0;
        int maxLength = 1;
        for (int i= 0;i< s.length();i++) {
            arr[i][i] = true;
        }
        for (int j=1;j < s.length();j++) {
            for (int i=0; i<j; i++) {
                if (isPalindrome(arr,s,i,j)) {
                    arr[i][j] = true;
                    if (maxLength < j - i + 1) {
                        maxLength = j -i + 1;
                        begin = i;
                    }
                }
            }
        }
        return s.substring(begin,begin + maxLength);
    }

    public boolean isPalindrome(boolean[][] arr,String s,int i,int j) {
        if (s.charAt(i) == s.charAt(j)) {
            if (j - i < 3) {
                return true;
            } else {
                return arr[i+1][j-1];
            }
        }
        return false;
    }

    @Test
    public void test() {
        Assert.assertTrue(this.longestPalindrome("aaaa").equals("aaaa"));
    }
}