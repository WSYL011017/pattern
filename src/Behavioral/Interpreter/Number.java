package Behavioral.Interpreter;

import java.util.Map;

/**
 * 终结符表达式 - 数字
 */
public class Number implements Expression {
    private int number;

    public Number(int number) {
        this.number = number;
    }

    @Override
    public int interpret(Map<String, Integer> context) {
        return number;
    }
}