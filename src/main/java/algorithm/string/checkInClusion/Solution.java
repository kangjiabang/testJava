package algorithm.string.checkInClusion;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Author  kangjiabang
 * @CreateTime 2022-08-11  15:29
 * @Description TODO
 */
public class Solution {

    public String checkInclusion(String s1, String s2) {
        if (s2 == null || s2.equals("")) {
            return "";
        }
        HashMap<Character, Integer> needs = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();

        int left = 0;
        int right = 0;
        boolean isValid = false;
        //初始化needs {a:1,b:1}
        for (int i = 0; i < s1.length(); i++) {
            needs.put(s1.charAt(i),needs.getOrDefault(s1.charAt(i),0) + 1);
        }
        String subString = "";
        int length = Integer.MAX_VALUE;
        while (right < s2.length()) {
            //满足条件，加入窗口
            if (needs.containsKey(s2.charAt(right))) {
                //放入window
                window.put(s2.charAt(right),window.getOrDefault(s2.charAt(right),0) + 1);
            } else {
                //清空窗口
                window.clear();
            }

            //窗口右移
            right++;

            //判断是否满足条件，window和needs中所有值均相同
            isValid = isValid(needs, window);
            //如果满足条件，
            if (isValid) {

                //收缩widows
                while (isValid(needs,window)) {
                    if (window.containsKey(s2.charAt(left))) {
                        int countInWindow = window.get(s2.charAt(left));
                        window.put(s2.charAt(left),--countInWindow);
                    }
                    left++;
                }
                return s2.substring(left - 1,right);

            }

        }
        return "";
    }

    public boolean checkInclusionBoolean(String s1, String s2) {
        if (s2.equals("")) {
            return false;
        }
        HashMap<Character, Integer> needs = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();

        int left = 0;
        int right = 0;
        //初始化needs {a:1,b:1}
        for (int i = 0; i < s1.length(); i++) {
            needs.put(s1.charAt(i),needs.getOrDefault(s1.charAt(i),0) + 1);
        }
        while (right < s2.length()) {
            //满足条件，加入窗口
            if (needs.containsKey(s2.charAt(right))) {
                //判断是否window中的key对应的count大于needs中的count
                if (window.get(s2.charAt(right)) != null && window.get(s2.charAt(right)) >= needs.get(s2.charAt(right))) {
                    //向左移动left指针，知道不满足条件为止
                    while (window.get(s2.charAt(right)) != null && window.get(s2.charAt(right)) >= needs.get(s2.charAt(right))) {
                        if (window.get(s2.charAt(left)) != null) {
                            int count = window.get(s2.charAt(left));
                            if (count == 1) {
                                window.remove(s2.charAt(left));
                            } else {
                                window.put(s2.charAt(left), --count);
                            }
                        }
                        left++;
                    }
                }
                //放入window
                window.put(s2.charAt(right),window.getOrDefault(s2.charAt(right),0) + 1);
            } else {
                //清空窗口
                window.clear();
            }

            //窗口右移
            right++;

            //判断是否满足条件，window和needs中所有值均相同
            if (isValid(needs, window)) {
                return true;
            }

        }
        return false;
    }


    /**
     * 判断是否满足
     * @param needs
     * @param window
     */
    private boolean isValid(HashMap<Character, Integer> needs, HashMap<Character, Integer> window) {
        AtomicBoolean isValid = new AtomicBoolean(true);
        needs.forEach((key,value) -> {
            if (!(window.containsKey(key) && window.get(key).equals(value))) {
                isValid.set(false);
            }
        });
        return isValid.get();
    }

    @Test
    public void test() {
        String result = this.checkInclusion("ab","eidbaooo");
        Assert.assertTrue(result.equals("ba"));
    }

    @Test
    public void testBooelan() {
        Assert.assertTrue(this.checkInclusionBoolean("ab","eidbaooo"));
    }

    @Test
    public void testBooelan2() {
        Assert.assertTrue(this.checkInclusionBoolean("adc","dcda"));
    }
    @Test
    public void testBooelan3() {
        Assert.assertFalse(this.checkInclusionBoolean("mart","karma"));
    }




}
