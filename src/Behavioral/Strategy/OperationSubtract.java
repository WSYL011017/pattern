package Behavioral.Strategy;

/**
 * 具体策略实现B - 减法运算
 */
public class OperationSubtract implements Strategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 - num2;
    }
}