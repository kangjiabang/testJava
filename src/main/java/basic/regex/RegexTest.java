package basic.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {

    public static void main(String[] args)
    {
        String str = "<div title=aaa>bbbb</div>ccc<div title=aaa1>bbbb1</div>ccc<div title=aaa2>bbbb2</div>ccc<div title=aaa3>bbbb3</div>ccc";
        String pat = "<div title=(.*?)>(.*?)</div>{1,4}";
        Pattern p = Pattern.compile(pat);
        Matcher m = p.matcher(str);
        while (m.find())
        {
            for (int i = 0; i < m.groupCount() + 1; i++)
            {
                System.out.println(m.group(i));
            }
            System.out.println("====================");
        }
    }
}
