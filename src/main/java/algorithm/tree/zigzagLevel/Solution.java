package algorithm.tree.zigzagLevel;

/**
 * @Author  kangjiabang
 * @CreateTime 2022-08-12  17:18
 * @Description TODO
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList();
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        boolean isLeft = true;
        queue.addFirst(root);
        List<List<Integer>> result = new ArrayList<>();

        while (queue.size() != 0) {
            LinkedList<Integer> list = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = queue.poll();
                if (isLeft) {
                    list.addLast(treeNode.val);
                } else {
                    list.addFirst(treeNode.val);
                }

                if (treeNode.left != null) {
                    queue.addLast(treeNode.left);
                }

                if (treeNode.right != null) {
                    queue.addLast(treeNode.right);
                }

            }
            isLeft = !isLeft;
            result.add(list);
        }
        return result;
    }

    @Test
    public void test() {

    }
}
