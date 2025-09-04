package Behavioral.Interpreter;

import java.util.Map;

/**
 * 非终结符表达式 - 减法
 */
public class Subtract implements Expression {
    private Expression leftExpression;
    private Expression rightExpression;

    public Subtract(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public int interpret(Map<String, Integer> context) {
        return leftExpression.interpret(context) - rightExpression.interpret(context);
    }
}