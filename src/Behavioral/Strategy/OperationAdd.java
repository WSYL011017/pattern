package Behavioral.Strategy;

/**
 * 具体策略实现A - 加法运算
 */
public class OperationAdd implements Strategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 + num2;
    }
}