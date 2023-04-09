//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.mj.tree;

import java.util.Comparator;

public class BBST<E> extends BST<E> {
    public BBST() {
        this((Comparator)null);
    }

    public BBST(Comparator<E> comparator) {
        super(comparator);
    }

    protected void rotateLeft(BinaryTree.Node<E> grand) {
        BinaryTree.Node<E> parent = grand.right;
        BinaryTree.Node<E> child = parent.left;
        grand.right = child;
        parent.left = grand;
        this.afterRotate(grand, parent, child);
    }

    protected void rotateRight(BinaryTree.Node<E> grand) {
        BinaryTree.Node<E> parent = grand.left;
        BinaryTree.Node<E> child = parent.right;
        grand.left = child;
        parent.right = grand;
        this.afterRotate(grand, parent, child);
    }

    protected void afterRotate(BinaryTree.Node<E> grand, BinaryTree.Node<E> parent, BinaryTree.Node<E> child) {
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else {
            this.root = parent;
        }

        if (child != null) {
            child.parent = grand;
        }

        grand.parent = parent;
    }

    protected void rotate(BinaryTree.Node<E> r, BinaryTree.Node<E> b, BinaryTree.Node<E> c, BinaryTree.Node<E> d, BinaryTree.Node<E> e, BinaryTree.Node<E> f) {
        d.parent = r.parent;
        if (r.isLeftChild()) {
            r.parent.left = d;
        } else if (r.isRightChild()) {
            r.parent.right = d;
        } else {
            this.root = d;
        }

        b.right = c;
        if (c != null) {
            c.parent = b;
        }

        f.left = e;
        if (e != null) {
            e.parent = f;
        }

        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;
    }
}
