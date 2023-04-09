package com.wrm.circle;

import com.wrm.AbstractList;

/**
 * @author wrmeng
 * @create 2023-21-18:10
 **/
@SuppressWarnings("unchecked")
public class SingleCircleLinkedList<E> extends AbstractList<E> {

//    private static final int ELEMENT_NOT_FOUND = -1;
//    private int size;


    //ArrayList有构造函数因为允许指定默认的size容量
    //默认为16
    //也就是ArrayList容易造成内存浪费的原因

    //LinkedList 不需要预先分配内存，避免内存的浪费，

    //头指针
    private Node first;

    /**
     * 静态内部类
     */
    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder sb=new StringBuilder();
            // 因为环形链表，不会为空
            sb.append(element).append("_").append(next.element);
            return "Node{}";
        }
    }

    @Override
    public void clear() {
        size = 0;
        first = null;
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
    @Override
    public void add(int index, E element) {

        rangeCheckForAdd(index);
        // 链表需要注意边界测试，index为0，sie-1，size
        //为0的时候进行处理
        if (index == 0) {
            Node<E>  newFirst = new Node<>(element, first);
            // 最后一个节点需要链接到第一个
            // 获取最后一个节点一定要在修改first之前否则就会报错的
            // 如果插入的节点为唯一一个，需要进行特殊处理
            Node<E> last = (size == 0) ? newFirst : node(size - 1);
            // 当链表中只有一个节点的时候需要指向自身进行闭环
            last.next = newFirst;
            first=newFirst;

        } else {
            //index的前一个节点对象
            Node<E> prev = node(index - 1);
            //新的对象节点的指向原来的index位置的对象
            Node<E> eNode = new Node<>(element, prev.next);
            //前一节点对象指向当前创建的新对象
            prev.next = eNode;
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
        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        // 默认等于first
        Node<E> node = first;
        //获取指定索引的前一个元素
        if (index == 0) {
            //当前链表中就一个节点，直接删除该节点，则first指向为null
            if(size == 1){
              first = null;
            }else{
                // 一定要先获取最后一个节点
                Node<E> last = (size - 1) == 0 ? first : node(size - 1);
                first = first.next;
                last.next=first;
            }

        } else {
            //一般情况
            //获取指定索引的前一个元素
            //前一个节点对象指向删除索引节点指向的下一个对象
            Node<E> prev = node(index - 1);
            //保存一下当前指定index的节点对象
            //node = node(index);
            node = prev.next;
            prev.next = prev.next.next;
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


