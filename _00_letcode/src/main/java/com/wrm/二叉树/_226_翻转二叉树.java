package com.wrm.二叉树;
/**
@author wrmeng
@create 2023-30-15:11
**/

import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目地址：https://leetcode.cn/problems/invert-binary-tree/
 */
public class  _226_翻转二叉树 {


    /**
     * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
     * 翻转的含义也就是说，这个树的左右子树全部翻转
     *
     * 思路：节点需要全部遍历
     * 4种遍历方式都可以实现
     * @param root
     * @return
     */

    public TreeNode invertTree(TreeNode root) {


        // root-left-right 前序遍历
        if(root == null) return root;
        //交换
        TreeNode tmp=root.left;
        root.left=root.right;
        root.right=tmp;


        invertTree(root.left);
        invertTree(root.right);

        return root;
    }

    /**
     * 后序遍历
     * @param root
     * @return
     */
    public TreeNode invertTree2(TreeNode root) {

        // 思路：节点需要全部遍历

        // left-right -root
        if(root == null) return root;
        invertTree(root.left);
        invertTree(root.right);
        //交换
        TreeNode tmp=root.left;
        root.left=root.right;
        root.right=tmp;

        return root;
    }

    /**
     * 中序遍历
     * @param root
     * @return
     */
    public TreeNode invertTree3(TreeNode root) {

        // 思路：节点需要全部遍历
        // left-root-right
        if(root == null) return root;
        invertTree(root.left);
        //交换
        TreeNode tmp=root.left;
        root.left=root.right;
        root.right=tmp;


        //因为此时已经翻转过了，
        invertTree(root.left);
        return root;
    }

    /**
     * 层序遍历
     * @param root
     * @return
     */
    public TreeNode invertTree4(TreeNode root) {

        // 思路：节点需要全部遍历
        if(root == null) return root;

        Queue<TreeNode> queue=new LinkedList<>();

        queue.offer(root);

        while(!queue.isEmpty()){

            TreeNode node=queue.poll();

            //交换左右节点
            TreeNode tmp=node.left;
            node.left=node.right;
            node.right=tmp;

            //左子树入队
            if(node.left!=null){
                queue.offer(node.left);
            }

            //右子树入队
            if(node.right!=null){
                queue.offer(node.right);
            }

        }

        return root;
    }

}
