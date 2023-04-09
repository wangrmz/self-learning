package com.mj.set;

/**
 * 特点：不允许元素重复
 * 可以用来去除重复；
 *
 * 性能对比：
 * 一般使用LinkedList,或者红黑树来实现
 * LinkedList->ListSet效率较低
 * RBTree->TreeSet效率较高（不需要额外判断重复，本身的设计就是新添加的元素重复则直接覆盖）
 * RBTree本身是平衡二叉搜索树，具有天然的去重功能
 *
 * TreeSet的缺陷：
 * 元素必须具备可比较性；
 *
 * 如果存储不需要可比较性的元素使用哈希表
 *
 * @param <E>
 */
public interface Set<E> {
	int size();
	boolean isEmpty();
	void clear();
	boolean contains(E element);
	void add(E element);
	void remove(E element);

	/**
	 * 动态数组、链表具有有索引的概念，所以不需要提供遍历的接口
	 *
	 * @param visitor
	 */
	void traversal(Visitor<E> visitor);


	public static abstract class Visitor<E> {
		boolean stop;
		public abstract boolean visit(E element);
	}
}
