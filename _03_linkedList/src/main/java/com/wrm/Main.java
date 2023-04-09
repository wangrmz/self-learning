package com.wrm;

import com.wrm.single.LinkedList2;

import java.util.LinkedList;

/**
@author wrmeng
@create 2023-25-11:45
**/

public class Main {

    public static void main(String[] args) {
        List<Integer> list=new LinkedList2<>();
        list.add(20);
        list.add(0,10);
        list.add(30);
        list.add(list.size(),40);
        // [10,20,30,40]

        list.remove(1);//20 removed
        // [10,30,40]
        System.out.println(list);

        LinkedList<Integer> integers = new LinkedList<Integer>();

    }



}
