package algorithm.array.maxSubArray;

import org.junit.Test;

/**
 * @Author  kangjiabang
 * @CreateTime 2022-08-15  21:04
 * @Description TODO
 */
public class Solution {
    public int maxSubArray(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int[][] result = new int[nums.length][nums.length];
        int max = Integer.MIN_VALUE;
        for (int i=0;i<result.length;i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
            result[i][i] = nums[i];
        }

        for (int j=1;j< nums.length;j++) {
            for(int i=0;i<j;i++) {
                result[i][j] = result[i][j-1] + nums[j];
                if (max < result[i][j]) {
                    max = result[i][j];
                }
            }
        }
        return max;
    }

    @Test
    public void test() {
        int[] nums = {-57,9,-72};
        int result = maxSubArray(nums);
        System.out.println(result);
    }
}