package algorithm.letterCombinations;

import org.junit.Test;

import java.util.*;

/**
 * @Author  kangjiabang
 * @CreateTime 2022-08-11  19:07
 * @Description TODO
 */
public class Solution {

    public List<String> letterCombinations(String digits) {
        if (digits.equals("")) {
            return new ArrayList();
        }
        Map<Character,String> digitToCharacter = new HashMap<>();
        digitToCharacter.put('2',"abc");
        digitToCharacter.put('3',"def");
        digitToCharacter.put('4',"ghi");
        digitToCharacter.put('5',"jkl");
        digitToCharacter.put('6',"mno");
        digitToCharacter.put('7',"pqrs");
        digitToCharacter.put('8',"tuv");
        digitToCharacter.put('9',"wxyz");

        //1、根据数字获取字符列表
        String[] values = new String[digits.length()];
        for (int i = 0;i< values.length;i++) {
            values[i] = digitToCharacter.get(digits.charAt(i));
        }
        return letterCombinations(values);
    }


    /**
     * "abc"、"def"
     * @param values
     * @return
     */
    public List<String> letterCombinations(String[] values) {

        List<String> res = new ArrayList<String>();
        StringBuffer track = new StringBuffer();
        backtrack(values,0,track,res);
        return res;
    }

    private void backtrack(String[] digits, int index,StringBuffer track,List<String> res ) {

        if (digits.length == index) {
            res.add(track.toString());
            return;
        }
        //开始某些操作
        for (int i =0;i < digits[index].length();i++) {
            Character character = digits[index].charAt(i);
            //添加字符
            track.append(character);
            backtrack(digits,index + 1,track,res);
            //删除字符
            track.deleteCharAt(index);

        }

    }

    @Test
    public void testLetterCombinations() {
        List<String> result = this.letterCombinations(new String[]{"abc","def"});
        System.out.println(result);
    }
    @Test
    public void testLetterCombinations2() {
        List<String> result = this.letterCombinations("999");
        System.out.println(result);
    }

}
