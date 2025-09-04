package Behavioral.Strategy;

/**
 * 具体策略实现C - 乘法运算
 */
public class OperationMultiply implements Strategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 * num2;
    }
}