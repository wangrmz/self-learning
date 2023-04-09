package com.wrm.链表;
/**
@author wrmeng
@create 2023-25-13:38
**/

/**
 * 题目地址：
 * https://leetcode-cn.com/problems/linked-list-cycle/
 */
public class _141_环形链表 {

      class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
  }

    /**
     * 思路：
     * 快慢指针：指针相遇则表明是环形链表
     * 指针在java中叫做引用
     * 快的指针追上慢的
     *  每次一个外循环，距离就会缩小一步
     *  假设慢指针在前，每次一步，快指针在后每次两步 两者之间距离就是 n+1-2=n-1 也就是每次外循环减少一步；
     *  可以画图证明；
     *  如果走3步就会出现错过的情况，不安全
     *  快慢指针的应用还有获取中间节点
     * @param head
     * @return
     */
     public boolean hasCycle(ListNode head) {

         //slow走一步
         //fast走两步
         //走进环，会出现相遇的情况--》操场跑步类比
         //快指针先达到null,则没有环
         if(head==null || head.next==null) return false;

         // 为什么一开始不都指向头指针呢
         // 原因：一开始就相遇了，判断就会非常麻烦
         ListNode slow=head;
         ListNode fast=head.next;
         //复杂度，fast走完所有节点，也就是n/2,也就是O(n)
         //循环条件是fast到达尾节点了，还没相遇，表明没有环
         while(fast!=null && fast.next!=null){
             //调整代码执行顺序
             //因为一开始一定是不相等的，先走一步在进行判断即可
             slow=slow.next;
             fast=fast.next.next;
             //表明相遇了
             if(slow==fast) return  true;
         }
         return true;
    }

    /**
     * 没有思路是因为没有足够的积累
     *
     */


    /**
     * 复杂度complexity：数据规模
     * 链表中指的是节点的数量，the number of the node
     *
     */
}
