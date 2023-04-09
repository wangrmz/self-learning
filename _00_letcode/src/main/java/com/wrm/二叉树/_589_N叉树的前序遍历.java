package com.wrm.二叉树;
/**
@author wrmeng
@create 2023-30-16:29
**/

import java.util.List;

/**
 * 题目地址：https://leetcode.cn/problems/n-ary-tree-preorder-traversal/
 */
public class _589_N叉树的前序遍历 {

    public List<Integer> preorder(Node root) {

        return null;
    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

}
