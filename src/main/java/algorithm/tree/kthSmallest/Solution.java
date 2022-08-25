package algorithm.tree.kthSmallest;

import algorithm.tree.TreeNode;
import org.junit.Test;

/**
 * @Author  kangjiabang
 * @CreateTime 2022-08-23  18:28
 * @Description TODO
 */
public class Solution {
    int rank = 0;

    public int kthSmallest(TreeNode root, int k) {

        return traverse(root,k);

    }

    public int traverse(TreeNode node,int k) {

        if (node == null) {
            return -1;
        }

        //左子树
        int leftResult = traverse(node.left,k);
        if (leftResult != -1) {
            return leftResult;
        }

        rank++;
        //根节点
        if (rank == k) {
            return node.val;
        }
        //右子树
        int rightResult = traverse(node.right,k);

        if (rightResult != -1) {
            return rightResult;
        }
        return -1;

    }


    @Test
    public void test() {
        TreeNode node1= new TreeNode(3);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(4);

        node1.left = node2;
        node1.right = node3;

        System.out.println(kthSmallest(node1,2));

    }
}
