package algorithm.sort.quickSort;

import org.junit.Test;

import java.util.Arrays;

import static com.sun.tools.javac.jvm.ByteCodes.swap;

/**
 * @Author  kangjiabang
 * @CreateTime 2022-08-21  18:49
 * @Description TODO
 */
public class Solution {

    public void quickSort(int[] arr) {

        quickSortRecursiveLy(arr,0,arr.length -1);

    }

    private void quickSortRecursiveLy(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivot = arr[left];
        int i = left;
        int j = right;
        while (i < j) {

            //右边找到小于pivot
            while (i<j && arr[j] >= pivot) {
                j--;
            }
            //左边找到大于pivot
            while (i<j && arr[i] <= pivot) {
                i++;
            }

            swap(arr,i,j);
        }
        //交换i和pivot位置
        swap(arr,left,i);
        quickSortRecursiveLy(arr,left,i-1);
        quickSortRecursiveLy(arr,i+1,right);
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    @Test
    public void test() {
        int[] arr = new int[]{5,6,1,2,7,8};
        this.quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
