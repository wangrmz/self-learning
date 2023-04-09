package com.mj;

import com.mj.printer.BinaryTrees;
import com.mj.tree.AVLTree;
import com.mj.tree.BST;

@SuppressWarnings("unused")
public class Main {

	static void test1() {
		Integer data[] = new Integer[] {
				7, 4, 9, 2, 5, 8, 11, 3, 12, 1
		};
		
		BST<Integer> bst = new BST<>();
		for (int i = 0; i < data.length; i++) {
			bst.add(data[i]);
		}
		
		BinaryTrees.println(bst);
		
		bst.remove(7);
		
		BinaryTrees.println(bst);
	}

	static void test3() {
		Integer data[] = new Integer[] {
				85, 19, 69, 3, 7, 99, 95, 2, 1, 70,44,58,11,21,14,93,57,4,56
		};

		AVLTree<Integer> avl = new AVLTree<>();
		for (int i = 0; i < data.length; i++) {
			avl.add(data[i]);
		}

		BinaryTrees.println(avl);

//		avl.remove(7);
//
//		BinaryTrees.println(avl);
	}
	
	static void test2() {
		Integer data[] = new Integer[] {
				// 7, 4
				// 7, 4, 9
				// 7, 4, 9, 5
				// 7, 4, 9, 2
				// 7, 4, 9, 2, 8
				7, 4, 9, 2, 1
		};
		
		BST<Integer> bst = new BST<>();
		for (int i = 0; i < data.length; i++) {
			bst.add(data[i]);
		}
		
		BinaryTrees.println(bst);
		System.out.println("----------------------------------");
		System.out.println(bst.isComplete());
	}
	
	public static void main(String[] args) {

		test3();
	}
}
