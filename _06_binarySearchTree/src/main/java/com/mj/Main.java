package com.mj;


/**
 * @author wrmeng
 * @create 2023-29-12:45
 **/

public class Main {

    private static class PersonComparator implements java.util.Comparator<Person> {

        @Override
        public int compare(Person e1, Person e2) {
            return e1.getAge() - e2.getAge();
        }


    }

    private static class PersonComparator2 implements java.util.Comparator<Person> {

        @Override
        public int compare(Person e1, Person e2) {
            return e2.getAge() - e1.getAge();
        }

    }

    public void test1() {
        Integer data[] = new Integer[]{7, 4, 9, 2, 5, 8, 11, 3};


        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }

        //打印这个树
        //BinaryTrees.println(bst);
    }

    public void test2() {
        Integer data[] = new Integer[]{7, 4, 9, 2, 5, 8, 11, 3};


        BinarySearchTree<Person> bst1 = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst1.add(new Person(data[i]));
        }

        //打印这个树
        //BinaryTrees.println(bst);
    }

    public static void main(String[] args) {

        //Integer data[] = new Integer[]{7, 4, 9, 2, 5, 8, 11, 3};
        Integer data[] = new Integer[]{7, 4, 9, 2, 5};


        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }
        //中序遍历
        //bst.inorderTraversal();

        //层序
//        bst.levelOrder(new BinarySearchTree.Visitor<Integer>() {
//            @Override
//            public boolean visit(Integer element) {
//                //自己实现访问逻辑
//                System.out.print("_" + element + "_");
//                return element == 9 ? true : false;
//            }
//        });

        System.out.println("=====================");
        //高度
        System.out.println(bst.height());
        System.out.println(bst.isComplete());

        //打印这个树
        //BinaryTrees.println(bst);

//        BinarySearchTree<Person> bst1 = new BinarySearchTree<>(new Comparator<Person>() {
//
//            @Override
//            public int compare(Person e1, Person e2) {
//                return e1.getAge()-e2.getAge();
//            }
//        });
//
//        bst1.add(new Person(12));
//        bst1.add(new Person(15));


//        BinarySearchTree<Person> bst2 = new BinarySearchTree<>(new PersonComparator());
//        bst2.add(new Person(12));
//        bst2.add(new Person(15));

        //使用java内置比较器
//        BinarySearchTree<Person> bst3 = new BinarySearchTree<>();
//        bst2.add(new Person(12));
//        bst2.add(new Person(15));


    }
}
