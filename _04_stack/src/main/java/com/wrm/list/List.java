package com.wrm.list;

/**
 * @author wrmeng
 * @create 2023-21-18:20
 **/

public interface List<E> {

    // 接口中默认 public
     static final int ELEMENT_NOT_FOUND = -1;

    /**
     * 清除元素
     */
    public void clear() ;
    /**
     * 元素的数量
     *
     * @return
     */
    public int size() ;

    /**
     * 是否为空
     *
     * @return
     */
    public boolean isEmpty() ;

    /**
     * 是否包含某个元素
     *
     * @param element
     * @return
     */
    public boolean contains(E element) ;

    /**
     * 添加元素到末尾
     *
     * @param element
     */
    public void add(E element) ;

    /**
     * 获取指定索引位置的元素
     *
     * @param index
     * @return
     */
    public E get(int index) ;
    /**
     * 设置指定位置的元素
     *
     * @param index
     * @param element
     * @return
     */
    public E set(int index, E element) ;

    public void add(int index, E element) ;

    public E remove(int index) ;

    /**
     * 查看元素的索引
     *
     * @param element
     * @return
     */
    public int indexOf(E element) ;



}
