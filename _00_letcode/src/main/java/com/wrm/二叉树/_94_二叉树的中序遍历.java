package com.wrm.二叉树;
/**
@author wrmeng
@create 2023-30-16:16
**/

import java.util.*;

/**
 * 题目地址：https://leetcode.cn/problems/binary-tree-inorder-traversal/
 */
public class _94_二叉树的中序遍历 {

    public List<Integer> inorderTraversal(TreeNode root) {

        // left - root  -right
        //假设一个树：
        //      5
        //   4      6
        // 2    1
        // 将头节点压入栈中
        // 比如树的中序遍历结果是：2，4，1，5，6 也就是我们需要保存在list中的顺序

//       1、 将根节点放入栈
//       2、如果根节点有左子树，则将左子树的根节点放入栈
//       3、重复步骤1和2.继续遍历左子树
//       4、从栈中弹出节点，进行访问，然后遍历右子树(重复步骤1和2)
//       5、如果栈为空，则遍历完成

        //用来存储节点数据
        List<Integer> levelList = new ArrayList<>();
        if (root == null) return levelList;
        // 申请栈
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node=root;
        while (!stack.isEmpty() || node!=null) {
            // 处理逻辑
            // left-root-right
            if(node!=null){
                stack.push(node);
                node=node.left;

            }else{ //说明左子树为null
                // 弹出访问
                TreeNode popNode = stack.pop();
                // 存储节点数据
                levelList.add(popNode.val);
                // 访问右子树
                node=popNode.right;
            }

        }
        return levelList;

    }



}
