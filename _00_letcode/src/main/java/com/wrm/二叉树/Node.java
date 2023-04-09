package com.wrm.二叉树;

import java.util.List;

/**
@author wrmeng
@create 2023-30-16:33
**/
public class Node {

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
}
