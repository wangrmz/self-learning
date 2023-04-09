package com.wrm.circle;

import com.sun.tools.javac.tree.JCTree;
import com.wrm.AbstractList;

/**
 * @author wrmeng
 * @create 2023-21-18:10
 * <p>
 * 双向循环链表
 **/

@SuppressWarnings("unchecked")
public class CircleLinkedList<E> extends AbstractList<E> {

//    private static final int ELEMENT_NOT_FOUND = -1;
//    private int size;


    //ArrayList有构造函数因为允许指定默认的size容量
    //默认为16
    //也就是ArrayList容易造成内存浪费的原因

    //LinkedList 不需要预先分配内存，避免内存的浪费，

    //头指针
    private Node first;
    private Node last;
    private Node current;


    /**
     * 静态内部类
     */
    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;

        public Node(E element, Node<E> next, Node<E> prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }

    /**
     * current 指向头节点
     */
    public void reset() {
        current = first;
    }

    public E next() {
        if (current == null) return null;

        current = current.next;
        return (E) current.element;
    }

    /**
     * 约瑟夫问题
     */
    public static void josephus(){
        CircleLinkedList<Integer> list = new CircleLinkedList<>();
        for (int i=1;i<=8;i++){
            list.add(i);
        }
        // 指向头节点1
        list.reset();
        while(!list.isEmpty()){
            list.next();
            list.next();
            System.out.println(list.remove());
        }
    }

    public static void main(String[] args) {
        josephus();
    }
    public E remove() {
        if (current == null) return null;
        //只剩一个node的情况，自己指向自己，导致删除掉
        //先获取需要删除节点的下一个node
        Node<E> next = current.next;
        E element = (E) remove(current);
        if (size == 0) {
            current = null;
        } else {
            current = next;
        }

        return element;
    }

    @Override
    public void clear() {
        size = 0;
        first = null;
        last = null;

        /**
         * gc root对象 jvm的底层设计，会进行判断
         * 1》被栈指针（局部变量）指向的对象
         *
         */
    }


    @Override
    public E get(int index) {
        return node(index).element;
    }

    /**
     * 覆盖指定位置原来的值
     * 返回旧值
     *
     * @param index
     * @param element
     * @return
     */
    @Override
    public E set(int index, E element) {
        //覆盖
        //取出原来节点的内容
        Node<E> node = node(index);
        E old = node.element;
        node.element = element;
        return old;
    }

    //添加指定位置的对象节点

    /**
     * 双向链表的添加
     * 直接找到index位置的节点就可以，因为有next和prev，可以直接
     *
     * @param index
     * @param element
     */
    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);

        if (index == size) {//如果链表一开始啥都没，就是index==0,size==0
            // 保存一下
            Node<E> oldLast = last;
            // 新添加到最后的节点需要指向第一个
            last = new Node(element, first, oldLast);
            if (oldLast == null) {//表明当前添加的是链表的第一个元素
                first = last;
                //循环链表第一个节点需要指向自己，
                //多了两条线
                first.next = first;
                first.prev = first;
            } else {
                //添加的不是第一个元素
                oldLast.next = last;
                first.prev = last;
            }

        } else {
            //先考虑通用情况
            Node<E> next = node(index);
            Node<E> prev = next.prev;
            Node node = new Node(element, next, prev);
            next.prev = node;
            prev.next = node;
            //插入到最前面的一个
            if (prev == null) {   //index==0
                // first指向最新的节点
                first = node;
            }
        }

        size++;//节点的数量++
    }

    /**
     * 获取指定位置index的节点对象
     *
     * @param index
     * @return
     */
    private Node<E> node(int index) {
        rangeCheck(index);

        //可以使用first和last,区分索引的位置
        //左边
        if (index < (size >> 1)) {
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        } else {
            Node<E> node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
            return node;
        }

    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        Node<E> node = first;
        //链表中只有一个节点的时候
        if (size == 1) {
            first = null;
            last = null;
        } else {
            //首先获取要删除的节点以及前后节点
            //中间的通用情况
            node = node(index);
            Node<E> next = node.next;
            Node<E> prev = node.prev;

            prev.next = next;
            next.prev = prev;

            //也就是头节点
            if (node == first) {//表明删除的是第一个节点index==0
                first = next;
            }
            //最后一个节点
            if (next == last) {//index==size-1;
                last = prev;
            }
        }


        size--;
        return node.element;
    }


    private E remove(Node<E> node) {

        //链表中只有一个节点的时候
        if (size == 1) {
            first = null;
            last = null;
        } else {
            //首先获取要删除的节点以及前后节点
            //中间的通用情况
            Node<E> next = node.next;
            Node<E> prev = node.prev;

            prev.next = next;
            next.prev = prev;

            //也就是头节点
            if (node == first) {//表明删除的是第一个节点index==0
                first = next;
            }
            //最后一个节点
            if (next == last) {//index==size-1;
                last = prev;
            }
        }

        size--;
        return node.element;
    }

    @Override
    public int indexOf(E element) {

        if (element == null) {
            //遍历链表
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (node.element == null) return i;
                node = node.next;
            }
        } else {
            //遍历链表
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)) return i;
                node = node.next;
            }
        }
        return ELEMENT_NOT_FOUND;
    }


    // 接口设计
    // 链表也是线性结构

    /**
     * java中默认的toString是返回类名的
     * 需要进行覆盖
     *
     * @return
     */
    public String toString() {
        //效率比较高，线程不安全
        StringBuilder string = new StringBuilder();
        string.append("size=").append(this.size).append(", [");
        Node<E> node = first;
        for (int i = 0; i < this.size; ++i) {
            if (i != 0) {
                string.append(", ");
            }
            string.append(node.element);

            node = node.next;
        }

        //遍历的方式2
//        Node<E> node1=first;
//        while(node1!=null){
//            //
//            node1=node1.next;
//        }
        string.append("]");
        return string.toString();
    }


}


