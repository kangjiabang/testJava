package algorithm.maxAreaOfIsland;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author  kangjiabang
 * @CreateTime 2022-08-21  12:57
 * @Description TODO
 */
public class Solution {
    public int maxAreaOfIsland(int[][] grid) {

        Deque<Index> stack = new LinkedList<Index>();

        int maxSize = 0;
        //深度优先搜索
        for (int i=0;i<grid.length;i++) {
            for (int j=0;j<grid[0].length;j++) {
                AtomicInteger currSize = new AtomicInteger();
                dfs(stack,grid,i,j,currSize);
                maxSize = Math.max(currSize.get(),maxSize);
            }
        }
        return maxSize;
    }

    public void dfs(Deque<Index> stack,int[][] grid,int i,int j,AtomicInteger total) {

        if (grid[i][j] == 1) {
            grid[i][j] = 0;
            total.incrementAndGet();
        } else {
            return;
        }
        //入栈
        // stack.push(new Index(i,j));
        //如果节点的上下左右均为0，则做stack的出栈操作。
        if ((i-1 >=0 && grid[i-1][j] == 0) && (i+1 < grid.length && grid[i+1][j] == 0) &&
                (j-1 >= 0 && grid[i][j-1] == 0) && (j+1 < grid[0].length && grid[i][j+1] == 0) ) {
            //stack.pop();
            return;
        }

        //判断队列的上，有1的都入栈
        if (i-1 >=0 && grid[i-1][j] == 1) {
            //判断是否已经入栈
            dfs(stack,grid,i-1,j,total);
        }
        //判断队列的下，有1的都入栈
        if (i+1 < grid.length && grid[i+1][j] == 1) {
            dfs(stack,grid,i+1,j,total);
        }
        //判断队列的左，有1的都入栈
        if (j-1 >= 0 && grid[i][j-1] == 1) {
            dfs(stack,grid,i,j-1,total);
        }
        //判断队列的右，有1的都入栈
        if (j+1 < grid[0].length && grid[i][j+1] == 1) {
            dfs(stack,grid,i,j+1,total);
        }
    }

    static class Index {
        int i;
        int j;
        public Index(int i,int j) {
            this.i = i;
            this.j = j;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            return this.i == ((Index)o).i && this.j == ((Index)o).j;
        }
    }

    @Test
    public void test() {
        int[][] arr = {{1,1,0,0,0},{1,1,0,0,0},{0,0,0,1,1},{0,0,0,1,1}};
        System.out.println(this.maxAreaOfIsland(arr));
    }
}
