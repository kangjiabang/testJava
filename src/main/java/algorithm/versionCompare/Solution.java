package algorithm.versionCompare;

import org.junit.Test;

/**
 * @Author  kangjiabang
 * @CreateTime 2022-08-18  14:05
 * @Description TODO
 */
public class Solution {

    public int compareVersion(String version1, String version2) {
        int n = version1.length();
        int m = version2.length();
        int i=0;
        int j=0;
        while (i < n || j < m) {
            int x = 0;
            int y = 0;
            for (;i< n && version1.charAt(i) != '.';i++) {
                x = x*10 + version1.charAt(i) - '0';
            }
            //跳过逗号
            i++;
            for (;j < m && version2.charAt(j) != '.';j++) {
                y = y*10 + version2.charAt(j) - '0';
            }
            //跳过逗号
            j++;
            if (x > y) {
                return 1;
            }
            if (x < y) {
                return -1;
            }
        }
        return 0;

    }

    @Test
    public void testCompare() {
        int result = compareVersion("1.01","1.001");
        System.out.println(result);
    }
}
