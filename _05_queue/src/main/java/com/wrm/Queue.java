package com.wrm;
/**
@author wrmeng
@create 2023-28-15:09
**/


import com.wrm.list.LinkedList;
import com.wrm.list.List;

/**
 * 优先使用双向链表，因为队列主要是往头尾操作元素
 *
 * @param <E>
 */

public class Queue<E> {

    private List<E> list=new LinkedList<>();

    // 元素的数量
    public int size() {

        return list.size();
    }

    // 是否为空
    public  boolean isEmpty(){

        return list.isEmpty();
    }

    // 入队 是添加到尾部
    public void enQueue(E element){
      list.add(element);
    }

    // 出队 是队头的元素出队
    public E deQueue(){
        return list.remove(0);
    }

    // 获取队头元素
    public E front(){
        return list.get(0);
    }


    public void clear(){

    }
}
