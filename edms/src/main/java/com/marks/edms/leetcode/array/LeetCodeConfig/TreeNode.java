package com.marks.edms.leetcode.array.LeetCodeConfig;

public class TreeNode {
    int val;//数据
    TreeNode left;//左孩子的引用，常常代表左孩子为根的整棵左子树
    TreeNode right;//右孩子的引用，常常代表右孩子为根的整棵右子树

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}
