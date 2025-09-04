package Behavioral.Interpreter;

import java.util.HashMap;
import java.util.Map;

/**
 * Interpreter Design Pattern测试类
 */
public class InterpreterTest {
    public static void main(String[] args) {
        // 创建变量映射
        Map<String, Integer> variables = new HashMap<>();
        variables.put("x", 10);
        variables.put("y", 5);
        variables.put("z", 2);

        // 创建表达式: x + y - z = 10 + 5 - 2 = 13
        Expression expression = new Subtract(
            new Add(new Variable("x"), new Variable("y")),
            new Variable("z")
        );

        // 解释并计算表达式
        int result = expression.interpret(variables);
        System.out.println("Result of x + y - z: " + result);

        // 创建表达式: x + 10 = 10 + 10 = 20
        Expression expression2 = new Add(new Variable("x"), new Number(10));
        int result2 = expression2.interpret(variables);
        System.out.println("Result of x + 10: " + result2);
    }
}