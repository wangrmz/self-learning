package com.mj.tree;

import java.util.Comparator;

/**
 * @author wrmeng
 * @create 2023-01-12:07
 **/

/**
 * 总结：
 *   添加：可能会导致左右祖先节点失衡
 *           只要让高度最低的失衡节点恢复，整棵树就恢复平衡【仅需要O(1)次调整】
 *
 *    删除：是可能导致父节点或者祖先节点失衡（只有一个节点失衡）；就是删除节点导致了高度变化，平衡因子变化
 *    删除短的路径，长的路径没有变化，就意味着高度不变，往上走，父节点的高度没有变化，父节点就不会失衡
 *    删除
 *         让父节点恢复平衡后，可能会导致更高层的祖先节点失衡【最多需要O(logn)次调整】
 *
 *    平均时间复杂度
 *    搜索：O(logn)
 *    添加：O(logn)，仅需要O(1)次旋转，不是1次，而是常数级哈
 *    删除：O(logn)，最多需要O(logn)次旋转
 *    整个树高度维持在O(logn)，二叉搜索树的复杂度和高度有关
 *
 *    关于a,g:
 *    统一操作中：影响的是中间5，也就是b,c,d,e,f;针对avl来说，确实可以不处理a,g
 *    可以将rotate方法改造，删除a,g的处理
 *    如果为了通用，最好都处理
 *
 * @param <E>
 */
public class AVLTree<E> extends BST<E> {


    public AVLTree() {
        this(null);
    }

    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * 找到失衡的节点
     * 最差复杂度：O(logn) 也就是导致每一层都需要调整，
     * @param node 被删除的节点
     *             这里被删除节点的parent一直都在，并没有删除
     */
    @Override
    protected void afterRemove(Node<E> node) {

        while ((node = node.parent) != null) {
            // 是否平衡，平衡因子
            if (isBalanced(node)) {
                // 更新高度
                // 每一个新添加的节点都是叶子节点，叶子节点的高度为1
                updateHeight(node);

            } else {// 第一个不平衡的节点
                // 恢复平衡
                rebalance(node);
            }
        }

    }


    /**
     * 调整失衡应该在添加之后

     *
     * 4种失衡：
     * 单旋
     * 双旋
     * LL:
     * RR:
     * LR:
     * RL:
     */

    /**
     * 找到所有失衡节点最低的一个就可以
     *
     * @param node
     */
    @Override
    protected void afterAdd(Node<E> node) {
        // 也可能出现添加节点后不失衡
        // 进入循环的是新添加节点parent
        while ((node = node.parent) != null) {
            // 是否平衡，平衡因子
            if (isBalanced(node)) {
                // 更新高度
                // 每一个新添加的节点都是叶子节点，叶子节点的高度为1
                updateHeight(node);

            } else {// 第一个不平衡的节点
                // 恢复平衡
                rebalance(node);
                // 整颗树恢复平衡
                break;
            }

        }
    }


    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element, parent);


    }


    /**
     * 核心：恢复平衡
     * 传递进来的是：高度最低的那个不平衡的节点
     * <p>
     * 统一操作
     *
     * @return
     */
    private void rebalance(Node<E> grand) {

        // 父
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        // 子
        Node<E> node = ((AVLNode<E>) parent).tallerChild();

        //重点：旋转类型
        // 如果parent和node都是 不平衡节点的左边就是LL
        if (((AVLNode<E>) parent).isLeftChild()) {//L
            if (((AVLNode<E>) node).isLeftChild()) { //LL
                rotate(grand,node.left,node,node.right,parent,parent.right,grand,grand.right);
            } else { //LR
                // parent首先左旋转，使得node成为根节点
                rotate(grand,parent.left,parent,node.left,node,node.right,grand,grand.right);
            }
        } else { // R
            if (((AVLNode<E>) node).isLeftChild()) { //RL
                //双旋的情况先处理parent,子树先平衡
                rotate(grand,grand.left,grand,node.left,node,node.right,parent,parent.right);
            } else { //RR
                rotate(grand,grand.left,grand,parent.left,parent,node.left,node,node.right);
            }
        }
    }

    /**
     * @param r 原先子树的root
     * @param a
     * @param b
     * @param c
     * @param d 恢复平衡的树的根节点
     * @param e
     * @param f
     * @param g
     */
    private void rotate(
            Node<E> r,
            Node<E> a, Node<E> b, Node<E> c,
            Node<E> d,
            Node<E> e, Node<E> f, Node<E> g) {

        // 让d成为这个子树的根节点
        d.parent = r.parent;
        if (((AVLNode<E>) r).isLeftChild()) {
            r.parent.left = d;
        } else if (((AVLNode<E>) r).isRightChild()) {
            r.parent.right = d;
        } else { //根节点root
            root = d;
        }

        // 处理 a-b-c
        b.left=a;
        if(a !=null){
            a.parent=b;
        }
        b.right=c;
        if( c!=null){
            c.parent=b;
        }

        // b 的左右子树都发生了变化，更新高度
        updateHeight(b);

        // 处理 e-f-g

        f.left=e;
        if(e !=null){
            e.parent=f;
        }
        f.right=g;
        if( g!=null){
            g.parent=f;
        }

        updateHeight(f);

        // 串联 b-d-f
        d.left=b;
        d.right=f;
        f.parent=d;
        b.parent=d;
        updateHeight(d);


    }


    /**
     * 核心：恢复平衡
     * 传递进来的是：高度最低的那个不平衡的节点
     *
     * @return
     */
    private void rebalance2(Node<E> grand) {

        // 父
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        // 子
        Node<E> node = ((AVLNode<E>) parent).tallerChild();

        //重点：旋转类型
        // 如果parent和node都是 不平衡节点的左边就是LL
        if (((AVLNode<E>) parent).isLeftChild()) {//L
            if (((AVLNode<E>) node).isLeftChild()) { //LL
                rotateRight(grand);
            } else { //LR
                // parent首先左旋转，使得node成为根节点
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else { // R
            if (((AVLNode<E>) node).isLeftChild()) { //RL
                //双旋的情况先处理parent,子树先平衡
                rotateRight(parent);
                rotateLeft(grand);
            } else { //RR
                rotateLeft(grand);
            }
        }

    }


    /**
     * 左旋转 RR 添加导致失衡的节点在右子树
     *
     * @param grand
     */
    public void rotateLeft(Node<E> grand) {
        //传递过来的就是失去高度最低的失去平衡的节点

        // g.right=p.left;
        // p.left=g;
        // 此时p成为根节点
        // 需要维护 t1(p.left) ,p ,g 的parent属性
        // 先后更新g,p的高度

        //不能更改顺序哦hhh~~~~
        // AVLNode<E> grandAvl = (AVLNode<E>) grand;

        Node<E> parent = ((AVLNode<E>) grand).right;
        // 原先parent的左子树t1
        Node<E> child = parent.left;
        ((AVLNode<E>) grand).right = parent.left;
        parent.left = ((AVLNode<E>) grand);

        afterRotate(grand, parent, child);

        //子树的根节点
//        parent.parent = ((AVLNode<E>) grand).parent;
//
//        // 让parent成为子树的根节点
//        // 判断grand是左右
//        if (((AVLNode<E>) grand).isLeftChild()) {//左子树
//            ((AVLNode<E>) grand).parent.left = parent;
//        } else if (((AVLNode<E>) grand).isRightChild()) {//右子树
//            ((AVLNode<E>) grand).parent.right = parent;
//        } else { //表明grand == root是根节点,既不是左子树也不是右子树
//            root = parent;
//        }
//
//        // 更新child的parent属性
//        if(child != null){
//            child.parent=grand;
//        }
//
//        // 更新grand的parent属性
//        grand.parent=parent;
//
//        //  维护高度
//        //  先更新矮的也就是现在的子树，然后再更新p
//
//        updateHeight(grand);
//        updateHeight(parent);


    }

    /**
     * LL:右旋转，添加导致失衡的节点在左子树
     *
     * @param grand
     */
    public void rotateRight(Node<E> grand) {
        // 传递过来的就是失去高度最低的失去平衡的节点
        // grand向右旋转，grand.left 也就是parent成为根节点
        if (grand == null) return;

        // g.left=p.right;
        // p.right=g;

        grand.left = grand.left.right;
        grand.left.right = grand;

        // 首先确认好三个角色，grand,parent,t2
        Node<E> parent = ((AVLNode<E>) grand).left;
        // 原先parent的右子树t2
        Node<E> child = parent.right;
        ((AVLNode<E>) grand).left = child;
        parent.right = grand;

        // p成为根节点

        // 更新三个节点的高度；

        afterRotate(grand, parent, child);

        //子树的根节点
//        parent.parent = ((AVLNode<E>) grand).parent;

        // 让parent成为子树的根节点
        // 判断grand是左右
//        if (((AVLNode<E>) grand).isLeftChild()) {//左子树
//            ((AVLNode<E>) grand).parent.left = parent;
//        } else if (((AVLNode<E>) grand).isRightChild()) {//右子树
//            ((AVLNode<E>) grand).parent.right = parent;
//        } else { //表明grand == root是根节点,既不是左子树也不是右子树
//            root = parent;
//        }
//
//        // 更新child的parent属性
//        if(child != null){
//            child.parent=grand;
//        }
//
//        // 更新grand的parent属性
//        grand.parent=parent;
//
//        //  维护高度
//        //  先更新矮的也就是现在的子树，然后再更新p
//
//        updateHeight(grand);
//        updateHeight(parent);

    }

    /**
     * 1、更新三个节点的parent
     * 2、更新g,p的高度
     */
    public void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {

        //子树的根节点
        parent.parent = ((AVLNode<E>) grand).parent;

        // 让parent成为子树的根节点
        // 判断grand是左右
        if (((AVLNode<E>) grand).isLeftChild()) {//左子树
            ((AVLNode<E>) grand).parent.left = parent;
        } else if (((AVLNode<E>) grand).isRightChild()) {//右子树
            ((AVLNode<E>) grand).parent.right = parent;
        } else { //表明grand == root是根节点,既不是左子树也不是右子树
            root = parent;
        }

        // 更新child的parent属性
        if (child != null) {
            child.parent = grand;
        }

        // 更新grand的parent属性
        grand.parent = parent;

        //  维护高度
        //  先更新矮的也就是现在的子树，然后再更新p

        updateHeight(grand);
        updateHeight(parent);
    }


    /**
     * 判断是否平衡左右子树高度相差绝对值小于等于1
     *
     * @return
     */
    private boolean isBalanced(Node<E> node) {
        return Math.abs(((AVLNode<E>) node).balanceFactor()) <= 1;
    }

    /**
     * 更新高度
     */
    private void updateHeight(Node<E> node) {
        AVLNode<E> avlNode = ((AVLNode<E>) node);

        avlNode.updateHeight();
    }


    private static class AVLNode<E> extends Node<E> {

        int height = 1;

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        /**
         * 获取平衡因子 = 左子树的高度 - 右子树的高度
         *
         * @return
         */
        public int balanceFactor() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            return leftHeight - rightHeight;

        }


        /**
         * 更新自己的高度
         */
        public void updateHeight() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;

            height = Math.max(leftHeight, rightHeight) + 1;
        }

        public Node<E> tallerChild() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            if (leftHeight > rightHeight) return left;
            if (rightHeight > leftHeight) return right;
            // 相等就返回同方向
            return isLeftChild() ? left : right;
        }

        /**
         * 判断自己是左子树还是右子树
         *
         * @return
         */
        public boolean isLeftChild() {

            return parent != null && this == parent.left;
        }

        /**
         * 判断自己是左子树还是右子树
         *
         * @return
         */
        public boolean isRightChild() {

            return parent != null && this == parent.right;
        }


        @Override
        public String toString() {

            String parentString = "null";
            if (parent != null) {
                parentString = parent.element.toString();
            }
            return parent.element + "_p(" + parentString + ")_h(" + height + ")";
        }


    }
}
