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

public class Deque<E> {

    private List<E> list=new LinkedList<>();

    // 元素的数量
    public int size() {
        return list.size();
    }

    // 是否为空
    public  boolean isEmpty(){

        return list.isEmpty();
    }

    // 入队 添加到尾部
    public void enQueueRear(E element){
      list.add(element);
    }

    // 入队 添加到头部
    public void enQueueFront(E element){
        list.add(0,element);
    }

    // 出队 队头的元素出队
    public E deQueueFront(){
        return list.remove(0);
    }

    // 出队 队尾的元素出队
    public E deQueueRear(){
        return list.remove(list.size()-1);
    }

    // 获取队头元素
    public E front(){
        return list.get(0);
    }

    // 获取队尾元素
    public E rear(){
        return list.get(list.size()-1);
    }



    public void clear(){

    }
}
