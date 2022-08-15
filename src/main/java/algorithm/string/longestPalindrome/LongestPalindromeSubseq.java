package algorithm.string.longestPalindrome;

public class LongestPalindromeSubseq {

    /**
     * 求最长回文子序列，使用动态规划算法
     *
     * @param s
     * @return
     */
    public static int longestPalindromeSubseq(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        for (int i = len - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j))
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                else
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }
        return dp[0][len - 1];
    }

    public static void main(String[] args) {
        //result is bbbb
        System.out.println(longestPalindromeSubseq("bbbab"));
        System.out.println(longestPalindromeSubseqUseRecursive("bbbab"));
    }


    /**
     * 求最长回文子序列，使用动态规划算法
     *
     * @param s
     * @return
     */
    public static int longestPalindromeSubseqUseRecursive(String s) {
        int start = 0;
        int end = s.length() - 1;
        return lips(s,start,end);
    }

    public static int lips(String s, int start, int end) {
        if (start > end) {
            return 0;
        }
        if (start == end) {
            return 1;
        }
        //if s[i] == s[j], lips(s,i,j) = lips(s,i+1,j-1)
        //else lips(s,i,j) = Math.max(lips(s,i+11,j),lips(s,i,j-1)
        if (s.charAt(start) == s.charAt(end)) {
            return lips(s, start + 1 , end - 1) + 2;
        } else {
            return Math.max(lips(s, start + 1, end), lips(s, start, end - 1));
        }
    }
}
