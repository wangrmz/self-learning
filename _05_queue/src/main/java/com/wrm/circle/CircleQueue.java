package com.wrm.circle;
/**
@author wrmeng
@create 2023-28-16:29
**/

import org.omg.CORBA.Object;

/**
 * 循环队列
 */
public class CircleQueue<E> {

    // 记录队头
    private int front;

    private  int size;
    private E[] elements;

    private static final int DEFAULT_CAPACITY = 10;

    public CircleQueue() {
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    // 元素的数量
    public int size() {
        return size;
    }

    // 是否为空
    public  boolean isEmpty(){
        return size==0;
    }

    // 入队 是添加到尾部
    public void enQueue(E element){
        // 这里需要特殊处理
       elements[(front + size ) % elements.length] = element;
       size++;

    }

    // 出队 是队头的元素出队
    public E deQueue(){
        E frongElement = elements[front];
        // 清空队头
        elements[front]=null;
        // 这里必须保证指向队头元素
        front=(front+1) % elements.length;
        size--;
        return frongElement;
    }

    // 获取队头元素
    public E front(){
        return elements[front];
    }

    public void clear(){

    }

    private void ensureCapacity(int capacity) {
        int oldCapacity = this.elements.length;
        if (oldCapacity < capacity) {
            // 新的容量是旧容量的1。5倍数。右移运算 位运算/2，左*2
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            E[] newElements = (E[]) new Object[newCapacity];

            for (int i = 0; i < this.size; ++i) {
                // 必须获取到真实的索引
                newElements[i] = this.elements[(i+front) % elements.length];
            }
            //指向新申请的地址
            this.elements = newElements;

            // 重置front
            // 1、先将原来队列中的数据逐一出队，放入新的
            // 2、再将front==0;

            System.out.println(oldCapacity + "扩容为" + newCapacity);
        }
    }

}
