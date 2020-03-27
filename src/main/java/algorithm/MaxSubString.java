package algorithm;

public class MaxSubString {
    public static int maxSubString(String str) {
        int len = str.length();
        int[] pos = new int[26];//记录出现过的字符在str中的位置
        for (int i = 0; i < 26; i++) {
            pos[i] = -1;
        }
        int pre = 0;//当前字符的前一个字符作为子串最后一位所能构成的不重复最长子串长度。
        int max = 0;//最大不重复子串
        int currLen = 0;//当前字符作为子串最后一位所能构成的不重复子串长度。
        for (int i = 0; i < len; i++) {
            int curr = str.charAt(i) - 'a';
            if (pos[curr] == -1 || pos[curr] < (i - pre)) {  //当前字符第一次出现 或者 当前字符不是第一次出现
                // 但是他上次出现的位置 在其前一个字符做为不重复子串最后一位时的子串首位 后面
                //所以当前字符最为末尾所能构成的最长不重复子串长度为 前一个字符加一。
                currLen = pre + 1;
            } else {                                       //  当前字符不是第一次出现
                //  但是他上次出现的位置 在其前一个字符做为不重复子串最后一位时的子串首位 前面
                currLen = i - pos[curr];                   //所以当前字符最为末尾所能构成的最长不重复子串长度为 当前字符减去上次出现的位置。
            }
            max = Math.max(max, currLen);
            pre = currLen;
            pos[curr] = i;
        }
        return max;
    }

    public static void main(String[] args) {
        String str = "jiaorenzhan";
        System.out.println(maxSubString(str));
    }
}