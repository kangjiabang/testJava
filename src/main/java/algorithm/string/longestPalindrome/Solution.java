package algorithm.string.longestPalindrome;

/**
 * @Author  kangjiabang
 * @CreateTime 2022-08-15  11:34
 * @Description TODO
 */
class Solution {
    public String longestPalindrome(String s) {
        int maxLen = 0;
        int begin = 0;
        for (int i=0;i<= s.length()-1;i++) {
            //奇数最大回文长度
            int oddLen = expand(s,i,i);
            //偶数最大回文长度
            int evenLen = expand(s,i,i+1);

            if (maxLen < Math.max(oddLen,evenLen)) {
                maxLen = Math.max(oddLen,evenLen);
                begin = i - (maxLen - 1)/2;
            }
        }
        return s.substring(begin,begin + maxLen);
    }

    public int expand(String s,int left,int right) {
        int maxLen = 0;
        while (left >= 0 && right <= s.length() - 1) {
            //奇数第一次计算
            if (left == right) {
                maxLen = 1;
                left--;
                right++;
            } else {
                if (s.charAt(left) == s.charAt(right)) {
                    maxLen += 2;
                } else {
                    break;
                }
                left--;
                right++;
            }
        }
        return maxLen;

    }
}
