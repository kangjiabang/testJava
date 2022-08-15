package algorithm.tree.zigzagLevel;

/**
 * @Author  kangjiabang
 * @CreateTime 2022-08-12  17:19
 * @Description TODO
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}