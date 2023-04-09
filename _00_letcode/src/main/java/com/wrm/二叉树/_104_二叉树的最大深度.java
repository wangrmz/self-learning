package com.wrm.二叉树;
/**
 * @author wrmeng
 * @create 2023-30-16:20
 **/

import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目地址：https://leetcode.cn/problems/maximum-depth-of-binary-tree/
 */
public class _104_二叉树的最大深度 {

    public int maxDepth(TreeNode root) {
        // 那就是求树的层数；
        // 使用层序遍历
        if (root == null) return 0;

        int height = 0;
        int levelSize = 1;

        // 层序遍历：从上到下，从左到右
        Queue<TreeNode> queue = new LinkedList<>();
        // root第一层的节点个数为1
        // root入队
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 出队
            TreeNode node = queue.poll();
            //levelSize也就是这一层的节点个数，--
            levelSize--;

            // 左子树入队
            if (node.left != null) {
                queue.offer(node.left);

            }
            // 右子树入队
            if (node.right != null) {
                queue.offer(node.right);
            }

            // 此时这一层的节点已经全部出队,也就是说这一层已经全部访问结束
            // 即将开始访问下一层的节点了，
            if (levelSize == 0) {
                levelSize = queue.size();
                height ++;
            }
        }
        //到达这里说明已经队列为空，全部节点已经访问结束
        return height;
    }

}
