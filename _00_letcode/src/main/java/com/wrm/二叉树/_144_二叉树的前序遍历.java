package com.wrm.二叉树;
/**
 * @author wrmeng
 * @create 2023-30-16:14
 **/

import java.util.*;

/**
 * 题目地址：https://leetcode.cn/problems/binary-tree-preorder-traversal/
 */
public class _144_二叉树的前序遍历 {


    /**
     * 递归的本质也是用stack这种结构来模拟，所有递归都可以，但是复杂的递归不建议用栈，递归本身会更简单
     * @param root
     * @return
     */
    private TreeNode preorderTraversal1(TreeNode root) {
        // root-left-right
        // 使用递归
        if (root == null) return null;
        // 这里是处理逻辑
        System.out.println(root.val);
        preorderTraversal1(root.left);
        preorderTraversal1(root.right);
        return root;
    }


    /**
     * 前序遍历：使用栈来模拟递归的一个效果
     * 用非递归方式遍历二叉树，需要引入额外的数据结构栈(stack),其基本流程如下：
     * 1、申请一个栈stack，然后将头节点压入stack中。
     * 2、从stack中弹出栈顶节点，打印
     * 3、将其右孩子节点（不为空的话）先压入stack中
     * 4、将其左孩子节点（不为空的话）压入stack中。
     * 5、不断重复步骤2、3、4，直到stack为空，全部过程结束。
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        // root-left-right 先访问根节点,左子树,然后右子树
        // 使用迭代完成
        List<Integer> levelList = new ArrayList<>();
        if (root == null) return levelList;
        // 申请栈
        Stack<TreeNode> stack = new Stack<>();
        //假设一个树：
        //      5
        //   4      6
        // 2    1
        // 将头节点压入栈中
        // 比如树的前序遍历结果是：54216（root-left-right）也就是我们需要保存在list中的顺序
        // 使用栈，前进后出，push(5)->pop(5);
        // 我们应该先把6放进去，在4，然后弹出4，在弹出6这种才符合栈先进后出
        // 处理4的时候也是按照这种逻辑，先把4的右child放进去，在放左child,弹出左child

        stack.push(root);
        while (!stack.isEmpty()) {
            //从stack中弹出栈顶节点，为啥要弹出，因为我们需要处理这个元素，将其放入list
            //  栈先进后出，FILO
            TreeNode node = stack.pop();
            // System.out.println(node.val);
            // 处理逻辑
            // 处理左右child
            levelList.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }

        }

        return levelList;

    }


}
