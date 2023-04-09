package com.wrm.链表;
/**
@author wrmeng
@create 2023-27-08:57
**/
import java.util.ArrayList;

/**
 * 题目地址：
 * https://leetcode-cn.com/problems/middle-of-the-linked-list/solution/
 */
public class _876_链表的中间结点 {

    /**
     * 给你单链表的头结点 head ，请你找出并返回链表的中间结点。
     * // 偶数个节点--如果有两个中间结点，则返回第二个中间结点。
     * 输入：head = [1,2,3,4,5,6]
     * 输出：[4,5,6]
     * // 奇数个节点
     * 输入：head = [1,2,3,4,5]
     * 输出：[3,4,5]
     * 解释：链表只有一个中间结点，值为 3 。
     * @param head
     * @return
     */
    public ListNode middleNode1(ListNode head) {
        // 先遍历一遍计算节点的个数
        if(head==null || head.next==null) return head;
        int count=0;
        while(head!=null){
            head=head.next;
            ++count;
        }
        //记录节点的个数
        System.out.println(count);
        ListNode cur=null;
        int i=0;
        if(count%2==0){//偶数
            while(head!=null){
                i++;
                head=head.next;
                if(i==count/2){
                    cur=head;
                }
            }
        }else{//奇数
            while(head!=null){
                i++;
                head=head.next;
                if(i==count/2+1){
                    cur=head;
                }
            }
        }
        return cur;
    }

    public ListNode middleNode2(ListNode head) {
        // 快慢指针
        ListNode slow=head;
        ListNode fast=head;

        while(fast!=null && fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }

        return slow;
    }


}
