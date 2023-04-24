package com.wrm.二叉树;
/**
 * @author wrmeng
 * @create 2023-30-16:19
 **/


/**
 * 题目地址：https://leetcode.cn/problems/binary-tree-level-order-traversal/
 */
public class _102_二叉树的层序遍历 {


    /**
     * 输入：root = [3,9,20,null,null,15,7]
     * 输出：[[3],[9,20],[15,7]]
     * 需要返回每一层的节点值，每一层都是一个list
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> list = new ArrayList<>();
        if (root == null) return list;
        // 记录层序遍历的每一层节点个数
        int levelSize = 1;
        // 首先写出层序遍历，使用队列,队列：FIFO，
        // 从上到下，从左到右
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        //用来存储每一层的节点数据
        List<Integer> levelList = new ArrayList<>();
        while (!queue.isEmpty()) {
            //出队
            TreeNode node = queue.poll();
            levelSize--;
            //这里进行处理每一层的数据
            levelList.add(node.val);
            //左子树入队
            if (node.left != null) {
                queue.offer(node.left);
            }
            //右子树入队
            if (node.right != null) {
                queue.offer(node.right);
            }

            //判断一层结束，也就是该层的节点
            //即将访问一下层
            if (levelSize == 0) {
                levelSize = queue.size();
                //将这一层的节点数据存储
                if (!levelList.isEmpty()) {
                    list.add(levelList);
                }
                //再将下一层存储数据的list重新生成
                levelList = new ArrayList<>();
            }
        }

        return list;
    }
}
