package basic;

import org.junit.Test;

/**
 * @Author：zeqi
 * @Date: Created in 18:28 12/6/18.
 * @Description:
 */
public class StringTest {

    @Test
    public void stringReverse() {
        StringBuilder  str= new StringBuilder("  Hello   world  hangzhou ");
        doReverse(str,0,str.length());
        int start = 0;
        int end = 0;
        boolean preIsSpace = false;
        for (int i=0;i< str.length();i++) {
            //如果前面是空格
            if (str.charAt(i) == ' ') {
                if (preIsSpace) {
                    continue;
                }
                end = i;
                doReverse(str,start,end);
                preIsSpace = true;

            } else {
                //如果前面一个字符是空格
                if (preIsSpace) {
                    start = i;
                    preIsSpace = false;
                }
                //到达字符串结尾
                if (str.length()-1 == i) {
                    end = str.length();
                    doReverse(str,start,end);
                }

            }

        }
        System.out.println(str);
    }

    public void doReverse(StringBuilder str,int start,int end) {
        for (int i =start;i< (start+end)/2;i++) {
            char tmp = str.charAt(i);
            str.setCharAt(i,str.charAt(end + start -1-i));
            str.setCharAt(end + start -1-i,tmp);
        }
    }
}
