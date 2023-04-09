package com.wrm.栈;
/**
 * @author wrmeng
 * @create 2023-28-13:26
 **/

import java.util.HashMap;
import java.util.Stack;

/**
 * 题目地址：
 * https://leetcode.cn/problems/valid-parentheses/
 */
public class _20_有效的括号 {

    private HashMap<Character, Character> map = new HashMap<>();

    public _20_有效的括号() {
        // key value
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
    }

    /**
     * "{[()]}"
     * 出现的括号三种，可以进行匹配则消除的思想
     * @param s
     * @return
     */
    public boolean isValid(String s) {

        //效率很低
        while (s.contains("{}") || s.contains("[]") || s.contains("()")) {
            s = s.replace("{}", "");
            s = s.replace("[]", "");
            s = s.replace("()", "");
        }
        return s.isEmpty();
    }

    /**
     * 使用栈进行处理
     * @param s
     * @return
     */
    public boolean isValid2(String s) {

        Stack<Character> stack = new Stack<>();

        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            //左字符则入栈
            if (c == '{' || c == '[' || c == '(') {
                stack.push(c);
            } else { //右括号
                //先判断栈是否为空
                if (stack.isEmpty()) return false;
                char left = stack.pop();
                if (left == '(' && c != ')') return false;
                if (left == '{' && c != '}') return false;
                if (left == '[' && c != ']') return false;
            }
        }
        return stack.isEmpty();
    }


    /**
     * 使用栈进行处理
     * @param s
     * @return
     */
    public boolean isValid3(String s) {

        Stack<Character> stack = new Stack<>();

        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            //左字符则入栈
            if (map.containsKey(c)) {
                stack.push(c);
            } else { //右括号
                //先判断栈是否为空
                if (stack.isEmpty()) return false;
                //左边的括号
                char left = stack.pop();
                //直接根据key获取map的值
//                map.get(c)
                if (c != map.get(left))return false;
            }
        }
        return stack.isEmpty();
    }

}
