package datastructure.stack;

import java.util.*;

/**
 * @program: data-structure-and-algorithm
 * 括号检测问题, 对应Leetcode 20题 https://leetcode-cn.com/problems/valid-parentheses/
 * @author: Yejiaxin
 * @create: 2020-08-06 09:32
 */
public class ValidParentheses {
    private final static Map<Character, Character> cacheMap = new HashMap<>();
    static {
        cacheMap.put('{', '}');
        cacheMap.put('[', ']');
        cacheMap.put('(', ')');
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (Character c : s.toCharArray()) {
            if (cacheMap.containsKey(c)) {
                stack.push(c);
            } else {
                // 这里需要判断栈是否已经为空， 为空，然后下个字符是右括号，说明括号不匹配
                if (stack.isEmpty()) {
                    return false;
                }
                Character l = stack.pop();
                if (!cacheMap.get(l).equals(c)) {
                    return false;
                }
            }
        }

        // 栈不为空，说明没有右括号与之对应
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ValidParentheses s = new ValidParentheses();

        boolean r1 = s.isValid("{[]}");
        System.out.println(r1);
    }
}
