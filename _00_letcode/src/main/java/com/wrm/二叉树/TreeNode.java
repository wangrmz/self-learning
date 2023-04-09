package com.wrm.二叉树;
/**
@author wrmeng
@create 2023-30-15:13
**/

public class TreeNode {

    int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
     }
}
