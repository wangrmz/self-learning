package com.wrm.队列;
/**
@author wrmeng
@create 2023-28-15:24
**/

import java.util.Stack;

/**
 * 题目地址：
 * https://leetcode.cn/problems/implement-queue-using-stacks/
 */
public class _232_用栈实现队列 {

    //都可以直接看源码
    //java.util.Queue<Integer>

    private Stack<Integer> inStack;
    private Stack<Integer> outStack;

    public _232_用栈实现队列() {
        inStack=new Stack<>();
        outStack=new Stack<>();
    }

    // 入队
    public void push(int x) {
        //只要是入队，全都压进inStack
        inStack.push(x);
    }


    /**
     * 出队
     * 需要判断outStack是否为空
     *
     * @return
     */
    public int pop() {

        checkOutStack();
        //出都从outStack中出
        return outStack.pop();
    }

    //获取队头
    public int peek() {
        checkOutStack();
        return outStack.peek();
    }

    public boolean empty() {

        return inStack.isEmpty() && outStack.isEmpty();
    }

    private  void checkOutStack(){
        if(outStack.isEmpty()){
            //需要将instack弹出，全部押入outStack
            while(!inStack.isEmpty()){
                outStack.push(inStack.pop());

            }
        }
    }

}
