package com.wrm.链表;
/**
@author wrmeng
@create 2023-25-13:09
**/

/**
 * 题目地址：
 * https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
 */
public class _237_删除链表中的节点 {


    /**
     * Definition for singly-linked list.
     * */
      public class ListNode {
         int val;
         ListNode next;
         ListNode(int x) { val = x; }
     }

    /**
     * 传递的是指定的节点。
     * 思路：传统的删除节点是需要获取到被删除节点的前一个，
     * 问题：拿不到前面一个节点
     * 解决：值覆盖，删除后面一个
     * 说明：看letcode要求，是否需要进行边界测试
     * @param node
     */
    public void deleteNode(ListNode node) {
        node.val=node.next.val;
        node.next=node.next.next;
    }

}
