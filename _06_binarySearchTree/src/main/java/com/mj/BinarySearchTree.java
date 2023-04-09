package com.mj;
/**
 * @author wrmeng
 * @create 2023-29-12:40
 **/


import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉搜索树，二叉排序树，二叉查找树
 * 添加删除修改复杂度O(logn)
 * 大大提高了数据查询的效率
 * 联系二分查找的算法
 * 需要特别注意的是
 * 二叉树没有索引的概念
 * 添加的节点在哪一层不好判断
 * 二叉搜索树要求数据必须具有可比性
 */
public class BinarySearchTree<E> {


    //节点数量
    private int size;

    // 根节点 因为都是从根节点开始查找
    private Node<E> root;


    //    private Comparator<E> comparator;
    private java.util.Comparator<E> comparator;

    public BinarySearchTree() {
        this((java.util.Comparator) null);
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public BinarySearchTree(com.mj.Comparator<Person> personComparator) {
    }

    // 元素的数量
    int size() {

        return size;
    }

    // 是否为空
    boolean isEmpty() {

        return false;
    }

    // 清空所有元素
    void clear() {

    }

    // 添加元素
    void add(E element) {
        //先检查元素是否为null
        elementNOtNullCheck(element);

        //1、根节点为空，添加为跟节点
        if (root == null) {
            root = new Node<>(element, null);
            size++;
            return;
        }

        // 添加的是后面的节点，不是根节点
        // 步骤：
        //1、找到父节点parent
        //2、创建新的节点node
        //3、parent.left=node or parent.right=node;

        // 遇到值相同的元素如何处理
        // 1、啥都不做，直接return
        // 2、直接覆盖

        Node<E> node = root;
        //新元素的父节点
        Node<E> parent = null;
        int cmp = 0;
        // element 和 node.element进行比较
        while (node != null) {
            cmp = compare(element, node.element);
            // 保存为新元素的父节点
            parent = node;
            // 因为下方进行比较后会根据返回值进行节点的移动
            if (cmp > 0) {//新的元素大
                // 需要在右
                node = node.right;
            } else if (cmp < 0) {//新的元素小
                // 左边
                node = node.left;
            } else {//相等
                // 建议进行覆盖
                // 自定义对象
                node.element = element;
            }
        }

        // 找到父节点了,也就是拿到比较的结果，看插入的位置
        // 这里是最后一次比较的返回
        // 创建节点
        Node<E> newNode = new Node<>(element, parent);
        if (cmp > 0) {//插入到父节点的右边
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;

    }


    /**
     * 设计的优点：java官方会抛出错误
     * java官方接口
     *
     * @param e1
     * @param e2
     * @return 返回值等于0，e1=e2;大于0，e1>e2;小于0，e1<e2;
     */
    private int compare(E e1, E e2) {
        //如果有比较器，优先使用比较器
        // 没有比较器强制转换成Comparable接口，强制认为可以比
        return this.comparator != null ? this.comparator.compare(e1, e2) : ((java.lang.Comparable) e1).compareTo(e2);
    }

    // 删除元素 --画图理清思路
    void remove(E element) {
        //先根据元素找到节点，在删除节点
        remove(node(element));

        // 分析删除节点的情况

        // 删除叶子节点 度为0的节点
        // 叶子1；root --> child.parent==null(严谨);root=child

        // 叶子2：是其parent.left
        // 叶子3：是其parent.right


        // 删除度为1的节点也就是只有一个子树
        // 不能直接删除，只向销毁这一个节点
        // 结局方案：使用子节点进行替换
        // child=node.left || child=node.right
        // 情况1，只有左子树，
        // child.parent=node.parent
        // node.parent.left=child

        // 情况2 ，只有右子树
        // child.parent=node.parent
        // node.parent.right=child


        // 删除度为2的节点：采取从左右子树中寻找节点来取代需要删除的节点
        // 这里应该找该节点的前驱或者后继节点，然后覆盖原节点的值，然后删除前驱或者后继节点
        // 如果一个节点的度为2，则前驱或者后继节点的度只可能是1和0；这里删除也就是相当于在删除度为1的节点或者叶子节点
        // 这里真正被销毁内存的是该节点的前驱或者后继


    }


    /**
     * 删除节点
     *
     * @param node
     */
    private void remove(Node<E> node) {

        if (node == null) return;
        size--;
        // 判断度为2，1，0
        // 首先判断度为2，具有重叠关系
        if (node.hasTwoChildren()) {
            //找到该node的前驱或者后继
            //前驱：先找左子树，然后一直往右找
            //或者左子树为null,则找到parent,祖父node 节点一直位于右子树

            //先找后继
            Node<E> s = successor(node);
            //用后继节点的值覆盖度为2的节点的值
            node.element = s.element;
            //删除后继节点
            node = s;
        }

        // 删除node节点（node的度为1或者0）
        Node<E> replacement = node.left != null ? node.left : node.right;

        if (replacement != null) { //node的度为1

            // 维护parent属性
            replacement.parent = node.parent;
            //特殊情况可能是空
            if (node.parent == null) { //node是度为1 的节点，且是根节点
                root = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else {//node == node.parent.right
                node.parent.right = replacement;
            }

        } else if (node.parent == null) { //叶子节点且没有根节点，也就是root
            root = null;
        } else {  //node 是叶子节点，但不是根节点
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {// node == node.parent.left
                node.parent.right = null;
            }

        }


    }

    /**
     * 根据元素找到节点
     *
     * @param element
     * @return
     */
    private Node<E> node(E element) {
        //从根节点开始找
        Node<E> node = root;
        while (node != null) {
            // 比较元素
            int cmp = compare(element, node.element);
            //元素相等
            if (cmp == 0) return node;
            if (cmp > 0) { // 大于0 ，表明该元素的节点在右子树
                node = node.right;

            } else { //小于0，表明该元素的节点在左子树
                node = node.left;
            }
        }

        // 结束循环，表明未找到
        return null;
    }

    /**
     * BST不允许为空，
     */
    public void elementNOtNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

    // 是否包含某元素
    boolean contains(E element) {

        return node(element) != null;
    }

    //以下方法用于遍历打印
    public Object root() {
        return this.root;
    }

    public Object left(Object node) {
        return ((Node) node).left;
    }

    public Object right(Object node) {
        return ((Node) node).right;
    }

    public Object string(Object node) {
        Node<E> myNode = (Node) node;
        String parentString = "null";
        if (myNode.parent != null) {
            parentString = myNode.parent.element.toString();
        }

        return myNode.element + "_p(" + parentString + ")";
    }


    /**
     * 前序遍历：这里的前是指根节点
     * root -left -right
     * 明显采用递归
     */

    public void preorderTraversal() {
        preorderTraversal(root);

    }

    private void preorderTraversal(Node<E> node) {

        if (node == null) return;
        System.out.println(node.element);

        preorderTraversal(node.left);
        preorderTraversal(node.right);

    }

    /**
     * 中序遍历：升序或者降序
     * left -root-right
     */

    public void inorderTraversal() {
        inorderTraversal(root);

    }

    public void inorderTraversal(Node<E> node) {

        if (node == null) return;
        inorderTraversal(node.left);

        System.out.println(node.element);

        inorderTraversal(node.right);

    }

    /**
     * 后序遍历：主要的是root在最后就行，其他先访问左子树还是右子树可自行定义
     * left -right-root
     * 也可以right-left-root
     */
    public void postorderTraversal() {
        postorderTraversal(root);

    }

    private void postorderTraversal(Node<E> node) {

        if (node == null) return;

        postorderTraversal(node.left);
        postorderTraversal(node.right);
        System.out.println(node.element);

    }


    /**
     * 层序遍历：必须要会，可以默写出来，可以手撕代码，后面会不断用到这块代码
     * 从上到下，从左到右访问每一个节点
     * 实现思路：使用队列
     * 1. 将根节点入队
     * 2. 循环执行以下操作，直到队列为空
     * 将队头节点 A 出队，进行访问
     * 将 A 的左子节点入队
     * 将 A 的右子节点入队
     */
    public void levelorderTraversal() {
        if (root == null) return;

        //使用java的队列
        Queue<Node<E>> queue = new LinkedList<>();

        //根节点入队
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            System.out.println(node.element);

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }

        }

    }


    /**
     * 获取前驱节点：指的是中序遍历的前一个节点
     * 不对外提供
     * 可以借助二叉搜索树来理解，因为二叉搜索树中序遍历是按照胜序或者降序
     * 比如：1，2，3，4，5，6，7，8，9，10，11，12，13
     * 求8的前驱节点：绝对是左子树最大的一个节点，也就是前一个比他小的节点
     * 先找到左子树，在一直往右找，才能找到距离最近的那个
     *
     * @param node
     * @return
     */
    private Node<E> prodesessor(Node<E> node) {

        if (node == null) return null;
        //1、这里意味着前驱节点在做子树中（left.right.righ.right.right....）
        // 找左子树
        // 获取当前节点的左子树
        Node<E> p = node.left;
        if (p != null) {
            //不断往右,找到最后一个
            while (p.right != null) {
                p = p.right;
            }
            // 已经不能在往右边了，也就是已经到最后一个了
            return p;
        }

        // 2、从父节点，祖父节点中寻找前驱节点：说明左子树为空
        // 且 节点应该位于左子树
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }

        // node.parent ==null
        // node == node.parent.right
        return node.parent;
    }


    /**
     * 二叉树，前驱和后继代码通用，建议和层序遍历一样记住，理解
     * 正好和前驱相反的过程
     * 获取后继节点：指的是中序遍历的后一个节点
     * 中序：left-root-right
     * 所以从以上可以看出来，left离的最近一个就是前驱，right离的最近就是后一个比他大的后继
     * 不对外提供
     * 可以借助二叉搜索树来理解，因为二叉搜索树中序遍历是按照胜序或者降序
     * 比如：1，2，3，4，5，6，7，8，9，10，11，12，13
     * 求8的后继节点：绝对是右子树最小的一个节点，也就是后一个比他大的节点
     * 先找到右子树，在一直往左找，才能找到距离最近的那个
     * 这里体验了树的parent属性很重要，方便往上找
     *
     * @param node
     * @return
     */
    private Node<E> successor(Node<E> node) {

        if (node == null) return null;
        //1、这里意味着前驱节点在左子树中（left.right.righ.right.right....）
        // 找左子树
        // 获取当前节点的右子树
        Node<E> p = node.right;
        if (p != null) {
            //不断往左,找到最后一个
            while (p.left != null) {
                p = p.left;
            }
            // 已经不能在往左边了，也就是已经到最后一个了
            return p;
        }

        // 2、从父节点，祖父节点中寻找前驱节点：说明右子树为空
        // 且 节点应该位于右子树
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }

        return node.parent;
    }

    /***
     * 层序遍历用处：计算树的高度
     *            判断是否为完全二叉树--通过判断节点的度
     *            完全二叉树：节点的度为2，1，0
     *            但是必须向左对其，也就是不允许node.left==null && node.right!=null出现；
     *            只要出现node.left!=null && node.right==null,说明接下来所有节点都是叶子节点 ：node.left==null && node.right==null;
     */

    /**
     * 优化代码，减少重复的判断
     *
     * @return
     */
    public boolean isComplete() {
        if (root == null) return false;

        //层序遍历，迭代的实现代码
        //从上到下，从左到右
        //左子树入队，右子树入队
        //使用队列实现

        Queue<Node<E>> queue = new LinkedList<>();

        //首先根节点入队
        queue.offer(root);

        boolean leaf = false;
        //遍历
        while (!queue.isEmpty()) {
            //出队
            Node<E> node = queue.poll();
            if (leaf && !node.isLeaf()) return false;

            //只要是层序遍历，首先保证的是节点入队；不管用层序遍历来干啥，这是首先要保证的

            if (node.left != null) {
                queue.offer(node.left);
            } else if (node.right != null) {
                return false;
            }

            if (node.right != null) {
                queue.offer(node.right);
            } else {
                // node.left==null && node.right==null
                // node.left!=null && node.right==null
                // 也就说后续的节点应该都是叶子节点
                leaf = true;
            }

        }
        return true;
    }

    public boolean isComplete2() {
        if (root == null) return false;

        //层序遍历，迭代的实现代码
        //从上到下，从左到右
        //左子树入队，右子树入队
        //使用队列实现

        Queue<Node<E>> queue = new LinkedList<>();

        //首先根节点入队
        queue.offer(root);

        boolean leaf = false;
        //遍历
        while (!queue.isEmpty()) {
            //出队
            Node<E> node = queue.poll();
            if (leaf && !node.isLeaf()) return false;

            // 对节点进行判断
            if (node.hasTwoChildren()) {
                queue.offer(node.left);
                queue.offer(node.right);

            } else if (node.left == null && node.right != null) {
                return false;
            } else { //后面遍历的节点都必须是叶子节点
                leaf = true;
                //修复bug
                //有个度为1的，left!=null 的没有入队导致的错误
                if (node.left != null) {
                    queue.offer(node.left);
                }

            }
        }
        return true;
    }

    /**
     * 使用层序遍历，树的高度就是层数
     *
     * @return
     */
    public int height() {
        if (root == null) return 0;
        //树的高度
        int height = 0;
        //存储着每一层的元素数量
        int levelSize = 1;
        Queue<Node<E>> queue = new LinkedList<>();

        //根节点入队
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            // 。。。。。。
            // 每访问一个元素，size--;
            levelSize--;

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }

            //一层循环走完，这一层的最后一个访问结束，也就是下一层的元素数，刚好是queue.size 入队的元素数量
            // levelSize==0,意味着即将要访问下一层
            if (levelSize == 0) {
                levelSize = queue.size();
                height++;//记录层树
            }
        }
        return height;
    }

    /**
     * 获取树的高度
     *
     * @return
     */
    public int height2() {
        return height2(root);
    }

    /**
     * 获取某一节点的高度
     *
     * @return
     */
    private int height2(Node<E> node) {

        if (node == null) return 0;
        // 使用递归
        return 1 + Math.max(height2(node.left), height2(node.right));
    }


    /**
     * 树的打印
     *
     * @return
     */

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(root, sb, "");
        return sb.toString();
    }

    private void toString(Node<E> node, StringBuilder sb, String prefix) {
        if (node == null) return;

        // 使用前序遍历 root-left-right
        sb.append(prefix).append(node.element).append("\n");
        toString(node.left, sb, prefix + "L---");
        toString(node.right, sb, prefix + "R---");


    }

    /**
     * #############################################################################################################
     * <p>
     * 设计遍历的接口
     * <p>
     * #############################################################################################################
     */

    public void levelOrder(Visitor<E> visitor) {
        if (root == null || visitor == null) return;

        //使用java的队列

        Queue<Node<E>> queue = new LinkedList<>();

        //根节点入队
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            //关键的访问逻辑
            //System.out.println(node.element);
            boolean visit = visitor.visit(node.element);
            if (visit == true) return;
            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }

        }
    }


    /**
     * 有接口的前序遍历
     *
     * @param visitor
     */

    public void preorderTraversal(Visitor<E> visitor) {
        if (visitor == null) return;
        preorderTraversal(root, visitor);

    }

    private void preorderTraversal(Node<E> node, Visitor<E> visitor) {

        if (node == null) return;
//        System.out.println(node.element);
        //将上次遍历的返回值保存
        if (visitor.stop == true) return;
        visitor.stop = visitor.visit(node.element);

        preorderTraversal(node.left);
        preorderTraversal(node.right);

    }

    /**
     * 有接口的中序遍历
     *
     * @param visitor
     */
    public void inorderTraversal(Visitor<E> visitor) {
        if (visitor == null) return;
        inorderTraversal(root, visitor);

    }

    public void inorderTraversal(Node<E> node, Visitor<E> visitor) {

        if (node == null || visitor.stop == true) return;
        inorderTraversal(node.left);

        //System.out.println(node.element);
        //将上次遍历的返回值保存
        if (visitor.stop == true) return;
        visitor.stop = visitor.visit(node.element);


        inorderTraversal(node.right);
    }

    /**
     * 有接口的后续遍历
     *
     * @param visitor
     */
    public void postorderTraversal(Visitor<E> visitor) {
        if (visitor == null) return;
        postorderTraversal(root, visitor);
    }

    private void postorderTraversal(Node<E> node, Visitor<E> visitor) {

        if (node == null || visitor.stop == true) return;

        postorderTraversal(node.left, visitor);
        postorderTraversal(node.right, visitor);// visitor.stop == true
        //将上次遍历的返回值保存
        if (visitor.stop == true) return;//防止右子树已经返回true
        visitor.stop = visitor.visit(node.element);


        //System.out.println(node.element);

    }


    // 设计遍历的接口--接口中只能定义方法
    // 抽象类：可以保存成员变量，方法的必须为abstract--继承他的类必须实现这个方法

    public static abstract class Visitor<E> {
        boolean stop;

        /**
         * 返回true代表停止遍历
         *
         * @param element
         * @return
         */
        abstract boolean visit(E element);
    }


    /**
     * 节点类
     *
     * @param <E>
     */
    private static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;// 红黑树相当有用

        //必要
        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }


        //判断是否为叶子节点
        public boolean isLeaf() {
            return left == null && right == null;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }
    }
}
