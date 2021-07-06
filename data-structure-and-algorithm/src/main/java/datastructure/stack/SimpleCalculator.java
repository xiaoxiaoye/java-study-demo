package datastructure.stack;

import java.util.Stack;

/**
 * @program: data-structure-and-algorithm
 * 基本计算器 对应LeetCode 224题   https://leetcode-cn.com/problems/basic-calculator/description/
 * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 *
 * 字符串表达式可以包含左括号 ( ，右括号 )，加号 + ，减号 -，非负整数和空格  。
 *
 * 示例 1:
 * 输入: "1 + 1"
 * 输出: 2
 *
 *
 * 示例 2:
 * 输入: " 2-1 + 2 "
 * 输出: 3
 *
 * 示例 3:
 * 输入: "(1+(4+5+2)-3)+(6+8)"
 * 输出: 23
 *
 * @author: Yejiaxin
 * @create: 2020-08-06 10:18
 */
public class SimpleCalculator {
    public int calculate(String s) {
        Stack<Integer> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (Character c : s.toCharArray()) {
            if (c.equals(' ') || c.equals('(') || c.equals(')')) {
                continue;
            }

            if (Character.isDigit(c)) {
                numbers.add(Character.getNumericValue(c));
            } else {
                if (operators.isEmpty()) {
                    operators.add(c);
                } else {
                    int l = numbers.pop();
                    int r = numbers.pop();
                    char ops = operators.pop();
                    if (ops == '+') {
                        numbers.add(l + r);
                    } else {
                        numbers.add(l - r);
                    }

                    operators.add(c);
                }
            }
        }

        return numbers.pop();
    }
}
