package Behavioral.Interpreter;

import java.util.Map;

/**
 * 表达式接口
 */
public interface Expression {
    int interpret(Map<String, Integer> context);
}