package com.wrm;

/**
 * @author wrmeng
 * @create 2023-10-12:16
 **/

/**
 * 复杂度分析：
 * 最好情况复杂度
 * 最坏情况复杂度
 * 平均情况复杂度
 * 均摊复杂度
 *
 * 动态数据的扩容和缩容
 * 缩容：有较多的剩余空间，可以进行缩容
 * 都是申请一块新的空间，将之前的数据拿过来
 *
 * 有动态缩容
 *
 * 扩容倍数和缩容时间不合理，会造成复杂度震荡
 * 扩容倍数和缩容倍数相乘不等于1
 * 主要就是增加删除的那个临界的设计
 *
 */

public class ArrayList2 {


    private int size;
    private int[] elements;

    private static final int DEFAULT_CAPACITY = 10;
    private static final int ELEMENT_NOT_FOUND = -1;

    public ArrayList2(int capacity) {
        capacity = (capacity < DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : capacity;
        elements = new int[capacity];

    }

    public ArrayList2() {
//        elements = new int[DEFAULT_CAPACITY];
        this(DEFAULT_CAPACITY);
    }

    /**
     * 清除元素
     */
    public void clear() {
//        for (int i = 0; i < this.size; ++i) {
//            this.elements[i] = null;
//        }

        this.size = 0;
    }

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
    public boolean contains(int element) {
        return this.indexOf(element) != ELEMENT_NOT_FOUND;
    }

    /**
     * 添加元素到末尾
     *
     * @param element
     */
    public void add(int element) {
        this.add(this.size, element);
    }

    /**
     * 获取指定索引位置的元素
     *
     * @param index
     * @return
     */
    public int get(int index) {
        this.rangeCheck(index);
        //index *4+首地址 这种
        return this.elements[index];//直接根据索引获取，复杂度为O(1)
        //数组随机访问速度非常快
    }

    /**
     * 设置指定位置的元素
     *
     * @param index
     * @param element
     * @return
     */
    public int set(int index, int element) { //O(1)
        this.rangeCheck(index);
        int old = this.elements[index];
        this.elements[index] = element;
        return old;
    }

    public void add(int index, int element) {
        /**
         * 最好就是在最后添加元素：不需要挪动O(1);
         * 最坏就是在第一个，O(n);
         * 平均：O(n/2)->省略系数：O(n)
         */
        this.rangeCheckForAdd(index);
        this.ensureCapacity(this.size + 1);

        for (int i = this.size; i > index; --i) {
            this.elements[i] = this.elements[i - 1];
        }

        this.elements[index] = element;
        ++this.size;
    } //O(n)n是数据规模，也就是目前存放的数据容量，也就是size,

    public int remove(int index) {
        this.rangeCheck(index);
        int old = this.elements[index];

        for (int i = index + 1; i < this.size; ++i) {
            this.elements[i - 1] = this.elements[i];
        }

        trim();
        //this.elements[--this.size] = null;
        return old;
    }

    /**
     * 剩余空间还剩有一半
     */
    private void trim(){
        int capacity=elements.length;
        //>>右移等于除以2
        //缩容不要小于我们最小容量
        int newCapacity=capacity>>1;
        if(size>=(newCapacity) || newCapacity<=DEFAULT_CAPACITY) return;;

        //说明剩余容量还有很多
        int[] newElements =new int[newCapacity];
        for (int i = 0; i < this.size; ++i) {
            newElements[i] = this.elements[i];
        }

        elements=newElements;
        System.out.println(capacity + "缩容为" + newCapacity);

    }

    /**
     * 查看元素的索引
     *
     * @param element
     * @return
     */
    public int indexOf(int element) {
        int i;
        for (i = 0; i < this.size; ++i) {
            if (element == (this.elements[i])) {
                return i;
            }
        }

        return -1;
    }

    private void ensureCapacity(int capacity) {
        int oldCapacity = this.elements.length;
        if (oldCapacity < capacity) {
            // 新的容量是旧容量的1。5倍数。右移运算 位运算/2，左*2
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            int[] newElements = new int[newCapacity];

            for (int i = 0; i < this.size; ++i) {
                newElements[i] = this.elements[i];
            }
            //指向新申请的地址
           this.elements = newElements;
            System.out.println(oldCapacity + "扩容为" + newCapacity);
        }
    }

    private void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + this.size);
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= this.size) {
            this.outOfBounds(index);
        }

    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > this.size) {
            this.outOfBounds(index);
        }

    }

    /**
     * java中默认的toString是返回类名的
     * 需要进行覆盖
     * @return
     */
    public String toString() {
        //效率比较高，线程不安全
        StringBuilder string = new StringBuilder();
        string.append("size=").append(this.size).append(", [");

        for (int i = 0; i < this.size; ++i) {
            if (i != 0) {
                string.append(", ");
            }

            string.append(this.elements[i]);
        }

        string.append("]");
        return string.toString();
    }

}
