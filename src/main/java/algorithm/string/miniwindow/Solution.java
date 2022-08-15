package algorithm.string.miniwindow;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Author  kangjiabang
 * @CreateTime 2022-08-11  14:17
 * @Description TODO
 */
public class Solution {
    public String minWindow(String s, String t) {

        if (t.equals("")) {
            return "";
        }
        HashMap<Character, Integer> needs = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();

        int left = 0;
        int right = 0;
        boolean isValid = false;
        //初始化needs {A:1,B:1,C:1}
        for (int i = 0; i < t.length(); i++) {
            needs.put(t.charAt(i),needs.getOrDefault(t.charAt(i),0) + 1);
        }
        String subString = "";
        int length = Integer.MAX_VALUE;
        while (right < s.length()) {
            //放入window
            window.put(s.charAt(right),window.getOrDefault(s.charAt(right),0) + 1);

            //窗口右移
            right++;

            //判断是否满足条件，window包含needs中所有值
            isValid = isValid(needs, window);
            //如果满足条件，
            if (isValid) {

                //收缩widows
                while (isValid(needs,window)) {
                    int countInWindow = window.get(s.charAt(left));
                    window.put(s.charAt(left),--countInWindow);
                    left++;
                }
                //取最小的子串
                if (right - left + 1 < length) {
                    subString = s.substring(left - 1,right);
                    length = right - left + 1;
                }

            }

        }
        return subString;

    }

    /**
     * 判断是否满足
     * @param needs
     * @param window
     */
    private boolean isValid(HashMap<Character, Integer> needs, HashMap<Character, Integer> window) {
        AtomicBoolean isValid = new AtomicBoolean(true);
        needs.forEach((key,value) -> {
            if (!(window.containsKey(key) && window.get(key) >= value)) {
                isValid.set(false);
            }
        });
        return isValid.get();
    }

    @Test
    public void testMinWindow() {
        String result = this.minWindow("ADOBECODEBANC","ABC");
        System.out.println(result);
    }

    @Test
    public void testMinWindow2() {
        String result = this.minWindow("a","a");
        System.out.println(result);
    }
}
