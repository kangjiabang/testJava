package algorithm.string.lengthOfLonestSubstring;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Author  kangjiabang
 * @CreateTime 2022-08-11  10:29
 * @Description TODO
 */
public class Solution {

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        Map<Character,Integer> map = new HashMap<Character,Integer>();
        int left = 0;
        int maxLength = 0;
        for (int i = 0;i< s.length();i++) {

            //如果包含,left左移到出现位置的右边，并 计算maxLength
            if (map.containsKey(s.charAt(i))) {
                left = Math.max(left,map.get(s.charAt(i)) + 1);
            }
            maxLength = Math.max(maxLength,i - left + 1);
            //放入map
            map.put(s.charAt(i),i);

        }

        return maxLength;

    }

    @Test
    public void test() {
        int result = this.lengthOfLongestSubstring("abba");
        Assert.assertTrue(result == 2);
    }

}
