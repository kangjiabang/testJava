package utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @Author：zeqi
 * @Date: Created in 00:14 3/1/18.
 * @Description:
 */
public class StringUtils {

    public static String[] tokenizeToStringArray(String str,String delim) {

        return tokenizeToStringArray(str, delim, true,true);
    }

    public static String[] tokenizeToStringArray(String str,String delim,boolean isTrim,boolean isIgnoreEmpty) {

        if (str == null || "".equals(str)) {
            return null;
        }
        StringTokenizer stringToken = new StringTokenizer(str,delim);
        List<String> lists = new ArrayList<String>();
        while (stringToken.hasMoreTokens()) {
            String ele  = stringToken.nextToken();

            if (isTrim) {
                ele = ele.trim();
            }
            if (ele.length() > 0 || !isIgnoreEmpty) {
                lists.add(ele);
            }

        }
        if (lists != null && !lists.isEmpty()) {

            return toArray(lists);
        }
        return null;
    }

    public static String[] toArray(Collection<String> col) {
        if (col == null || col.isEmpty()) {
            return null;
        }
        return col.toArray(new String[col.size()]);
    }
}
