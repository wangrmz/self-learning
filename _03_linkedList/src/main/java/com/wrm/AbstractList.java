package com.wrm;
/**
@author wrmeng
@create 2023-21-18:30
**/

// 抽象类，接口中有些方法不用实现，交给子类去实现
// 用来抽取公共方法。不对外公开，
public abstract class AbstractList<E> implements List<E> {

    protected int size;
    /**
     * 元素的数量
     *
     * @return
     */
    public int size() {
        return this.size;
    }

    /**
     * 是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * 是否包含某个元素
     *
     * @param element
     * @return
     */
    public boolean contains(E element) {
        return this.indexOf(element) != ELEMENT_NOT_FOUND;
    }

    /**
     * 添加元素到末尾
     *
     * @param element
     */
    public void add(E element) {
        this.add(this.size, element);
    }


    public void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + this.size);
    }

    public void rangeCheck(int index) {
        if (index < 0 || index >= this.size) {
            this.outOfBounds(index);
        }

    }

    public void rangeCheckForAdd(int index) {
        if (index < 0 || index > this.size) {
            this.outOfBounds(index);
        }

    }


}
