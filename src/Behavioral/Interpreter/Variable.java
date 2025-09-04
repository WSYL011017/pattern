package Behavioral.Interpreter;

import java.util.Map;

/**
 * 终结符表达式 - 变量
 */
public class Variable implements Expression {
    private String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public int interpret(Map<String, Integer> context) {
        if (context.containsKey(name)) {
            return context.get(name);
        }
        return 0;
    }
}