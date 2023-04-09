package com.mj.tree;

import java.util.Comparator;

/**
 * @author wrmeng
 * @create 2023-04-04 -11:49
 **/


/**
 *
 * 红黑树必须满足以下 5 条性质
 * 1. 节点是 RED 或者 BLACK
 * 2. 根节点是 BLACK
 * 3. 叶子节点（外部节点，空节点）都是 BLACK
 * 4. RED 节点的子节点都是 BLACK
 * ✓ RED 节点的 parent 都是 BLACK
 * ✓ 从根节点到叶子节点的所有路径上不能有 2 个连续的 RED 节点
 * 5. 从任一节点到叶子节点的所有路径都包含相同数目的 BLACK 节点
 *  -------红黑树的平衡是依靠5条性质保证，没有所谓平衡因子
 *
 *
 * 红黑树 类似 2-3-4树也就是4阶B树
 * B树：添加和删除总是叶子节点
 *
 *
 * 红黑树-》B树
 * 黑色节点和他的红色子节点合并成为一个节点；
 * 也就是说在B树里面永远是黑色节点是父节点
 *
 * 红黑树添加：
 * 1、默认添加为红色，尽快满足红黑树的性质；
 * 2、如果添加的为根节点，则染成黑色；
 *
 * 4阶B树所有节点的元素个数 x 都符合 1 ≤ x ≤ 3
 * ◼ 建议新添加的节点默认为 RED，这样能够让红黑树的性质尽快满足（性质 1、2、3、5 都满足，性质 4 不一定）
 * ◼ 如果添加的是根节点，染成 BLACK 即可
 *
 * 红黑树叶子节点的情况4种：
 * 红黑红；黑红；红黑；黑
 * 一共12种情况：
 * 4种情况是满足红黑树性质4的：parent为BLACK，同时也满足4阶B树性质，不需要额外处理
 *
 * 8种情况不满足红黑树的性质4：parent为RED,（出现了double red）-》需要进行处理
 *
 * 判断条件是：uncle 不是RED 4种情况
 *           是RED的 4种情况
 *
 *  LL/RR: 把父节点然成黑色。祖父节点然成红色
 *  上溢：LL---->只需要染色
 *  判断条件是RED:
 *  1、parent,uncle然成黑色；
 *  2、grand 向上合并
 *     染成RED,当作是新添加的节点处理
 *     grand向上合并，可能继续发生上溢。若上溢到根节点，只需要把根节点然成BLACK
 *  B 树-》取出中间节点向上合并
 *
 *  RR:
 *
 *  LR/RL:不一样的地方是把自身染成黑色，自身成为根节点
 *
 *
 *
 *
 * 红黑树的性质：
 * 1、节点必须是黑色或者红色
 * 2、根节点必须是BLACk;
 * 3、叶子节点（外部节点，空节点）必须是黑色；
 * 4、RED节点的子节点必须是BLACK
 *     从根节点到叶子节点不能经过两个连续的RED;
 *     RED节点的parent节点必须是黑色；
 * 5、从任意一个节点到叶子节点，BLACK节点的数量是一样的：
 *
 *
 *

 * @param <E>
 */

public class RBTree<E> extends BBST {

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public RBTree() {
    }

    public RBTree(Comparator comparator) {
        super(comparator);
    }



    /**
     * 新添加的节点
     *
     * @param node
     */
    @Override
    protected void afterAdd(Node node) {

        Node<E> parent = node.parent;

        // 添加的是根节点
        if(parent == null){
           isBlack(node);
           return;
        }

        // 如果添加的节点的父节点是黑色，不需要处理的4种情况，直接返回
        if(isBlack(parent)) return;

        // uncle节点
        Node<E> uncle = parent.sibling();
        // 祖父节点
        Node<E> grand = parent.parent;
        if(isRed(uncle)) {// 叔父节点是红色
            // 染色
            black(parent);
            black(uncle);

            // grand当作是新添加的节点，向上合并
            afterAdd(red(grand));//递归调用
            return;

        }

        //要考虑到重复代码，代码抽取
        // 叔父节点不是红色
        // 旋转
        if(parent.isLeftChild()){ // L
            red(grand);
            if(node.isLeftChild()){ //LL

                // parent染黑色 ，grand染成红色
                // grand 右旋
                black(parent);
               // red(grand);
               // rotateRight(grand);

            }else { // LR
                // 自己染成黑色； grand 染成红色
                // parent左旋转，grand右旋转
                black(node);
                //red(grand);
                rotateLeft(parent);
                //rotateRight(grand);
            }
            rotateRight(grand);

        }else { //R
            red(grand);
            if(node.isLeftChild()){ //RL
                // 自己染成黑色； grand 染成红色
                // parent右旋转，grand左旋转
                black(node);
                //red(grand);
                rotateRight(parent);
                //rotateLeft(grand);

            }else { // RR

                // parent染黑色 ，grand染成红色
                // grand 左旋
                black(parent);
                //red(grand);
                //rotateLeft(grand);

            }
            rotateLeft(grand);
        }


    }

    @Override
    protected void afterRemove(Node node) {

    }

    /**
     * 辅助函数:染色
     *
     * @param node
     */

    private Node<E> color(Node<E> node, boolean color) {

        if (node == null) return node;
        ((RBNode<E>) node).color = color;
        return node;
    }

    /**
     * 染成红色
     *
     * @param node
     * @return
     */
    private Node<E> red(Node<E> node) {
        return color(node, RED);
    }

    /**
     * 染成黑色
     *
     * @param node
     * @return
     */
    private Node<E> black(Node<E> node) {
        return color(node, BLACK);
    }


    /**
     * 判断节点是什么颜色
     *
     * @param node
     * @return
     */
    private boolean colorOf(Node<E> node) {
        return node == null ? BLACK : ((RBNode<E>) node).color;
    }


    /**
     * 判断节点是否为黑色
     *
     * @param node
     * @return
     */
    private boolean isBlack(Node<E> node) {
        return colorOf(node) == BLACK;
    }

    /**
     * 判断节点是否为红色
     *
     * @param node
     * @return
     */
    private boolean isRed(Node<E> node) {
        return colorOf(node) == RED;
    }

    @Override
    protected Node createNode(Object element, Node parent) {
        return new RBNode(element, parent);
    }

    /**
     * 需要重新定义，符合红黑树的定义
     * 红黑树的每一个节点都必须是红或者黑
     *
     * @param <E>
     */
    private static class RBNode<E> extends Node<E> {

        boolean color =RED;//新添加的节点默认是红色，能尽快满足红黑树的性质

        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }

        @Override
        public String toString() {
            String str="";
            if(color == RED){
                str ="R_";
            }
            return str + element.toString();
        }
    }


}
