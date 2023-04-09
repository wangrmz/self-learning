package com.wrm.链表;
/**
@author wrmeng
@create 2023-27-08:56
**/

/**
 * 题目地址：
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
 */
public class _83_删除排序链表中的重复元素 {

      public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    /**
     * 思路：
     * 因为给定的链表是排好序的，元素如果重复则是连续出现的
     * head.val和head.next.val进行比较
     * 一次遍历即可删除重复的节点
     * 只需要遍历到倒数第二个节点就可以
     * 注意最后一个节点的处理
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        //新的链表指向原来head
        ListNode newHead=head;
        if(head==null|| head.next==null) return newHead;
        while(head.next!=null){
            if(head.next.val==head.val){
                head.next=head.next.next;
            }else{
                head=head.next;
            }
        }
        return newHead;

    }


}
