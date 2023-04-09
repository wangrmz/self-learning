package com.wrm.链表;
/**
@author wrmeng
@create 2023-25-13:25
**/

/**
 * 题目地址：
 * https://leetcode-cn.com/problems/reverse-linked-list/
 */
public class _206_反转链表 {

    /**
     *  Definition for singly-linked list.
     */
      public class ListNode {
          int val;
          ListNode next;
          //无参构造函数
          ListNode() {}
          //有参构造
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }

    /**
     * 给定单链表的头节点 head ，请你反转链表，并返回反转后的链表。
     *
     * 1、递归
     * 递归的问题是是否导致死循环
     * 2、迭代
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        // 边界的考虑
        // 头节点为空或者只有一个节点
        if(head==null ||head.next==null) return head;

        ListNode newHead = reverseList(head.next);
        head.next.next=head;
        head.next=null;
        return newHead;
    }

    /**
     * 非递归方式
     * 思考：只能从头节点开始
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        ListNode newHead = null;
        while (head!=null){
            // 中间变量，保存head.next
            ListNode tmpNode=head.next;
            head.next=newHead;
            newHead=head;
            head=tmpNode;
        }
        return newHead;
    }
}

