package algorithm.validbracket;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Solution {

    static Map<Character,Character> map = new HashMap();

    static {
        map.put(')','(');
        map.put(']','[');
        map.put('}','{');

    }
    @Test
    public void test() {
        Assert.assertTrue(isValid("()[]{}"));
        Assert.assertTrue(isValid("([])"));
        Assert.assertTrue(!isValid("([)]"));
        Assert.assertTrue(!isValid(")[)]"));
    }
    /**
     * ()[]{}
     * ([])
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();


        for (int i =0;i<s.length();i++) {

            //if it was closing bracket
            if (map.containsKey(s.charAt(i))) {
                char topElement = stack.isEmpty() ? '#' : stack.pop();
                if (topElement != map.get(s.charAt(i))) {
                    return false;
                }

            } else {
                stack.push(s.charAt(i));
            }
        }
        if (stack.isEmpty()) {
            return true;
        }
        return false;
    }

    private void push(String s, Stack<Character> stack, int i) {
        stack.push(s.charAt(i));
    }
}