package com.wrm.二叉树;
/**
@author wrmeng
@create 2023-30-16:17
**/

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 题目地址：https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
 */
public class _145_二叉树的后序遍历 {

    /**
     * 后序遍历
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {

        // left -right-root
        //假设一个树：
        //      5
        //   4      6
        // 2    1
        // 将头节点压入栈中
        // 比如树的后序遍历结果是：2，1，4，6，5 也就是我们需要保存在list中的顺序

//        用非递归方式遍历二叉树，需要引入额外的数据结构栈(stack),其基本流程如下：
//        1、申请两个栈stack，然后将头节点压入指定stack中。
//        2、从stack中弹出栈顶节点，放入另外一个栈中
//        3、将其左孩子节点（不为空的话）先压入stack中
//        4、将其右孩子节点（不为空的话）压入stack中。
//        5、不断重复步骤2、3、4，直到stack为空。
//        6、重复访问另外一个栈，直至栈空

        // 用来存储节点数据
        List<Integer> levelList = new ArrayList<>();
        if (root == null) return levelList;
        // 申请栈
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        TreeNode node=root;
        stack1.push(node);
        while (!stack1.isEmpty()) {

            TreeNode node1 = stack1.pop();
            // 压入stack2
            stack2.push(node1);
            // 把左节点压入栈中
            if(node1.left!=null){
                stack1.push(node1.left);
            }

            if(node1.right!=null){
                stack1.push(node1.right);
            }
        }
        // 循环结束表明所有节点都被压入了stack2

        //  访问stack2,弹出所有元素
       while (!stack2.isEmpty()){
           //弹出元素访问
           TreeNode pop = stack2.pop();
           levelList.add(pop.val);
       }

        return levelList;
    }

}
