package com.wrm.链表;
/**
 * @author wrmeng
 * @create 2023-27-08:54
 **/

/**
 * 题目地址
 * https://leetcode-cn.com/problems/remove-linked-list-elements/
 */

public class _203_移除链表元素 {


    /**
     * * Definition for singly-linked list.
     */
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 给你一个链表的头节点 head 和一个整数 val ，
     * 请你删除链表中所有满足 Node.val == val 的节点，
     * 并返回 新的头节点 。
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {

        ListNode newHead = head;
        //1、如果链表为空
        if (head == null ) return null;
        // 遍历链表，查找node.val=val的节点并删除
        // 有两个以及以上的节点
        while (head != null && head.next != null) {
            // 第一个节点就需要删除
            // tmp 保存head.next
            ListNode tmp = head.next;
            if (head.val == val) {
                newHead = head.next;
            } else if (head.val != val && tmp.val == val) {
                head.next = head.next.next;
            }
            head = head.next;
        }
        return newHead;
    }

    public ListNode removeElements2(ListNode head, int val) {
        //1、如果链表为空
//        if (head == null ) return head;
        //头节点==val
        //[7,7,7,7]
        while(head!=null && head.val==val){
            head=head.next;
        }
        // 遍历链表，查找node.val=val的节点并删除
        // 有两个以及以上的节点
        // [1,2,2,1]
        // [1,2,6,3,4,5,6]
        ListNode newHead = head;
        while (newHead != null && newHead.next!=null) {
            if(newHead.next.val==val){
                newHead.next=newHead.next.next;
            }else{
                newHead=newHead.next;
            }
        }
        //没有匹配
        return head;
    }


}
