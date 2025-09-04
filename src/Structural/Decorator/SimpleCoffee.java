package Structural.Decorator;

/**
 * 具体组件 - 简单咖啡
 */
public class SimpleCoffee implements Coffee {
    @Override
    public double getCost() {
        return 2.0;
    }

    @Override
    public String getDescription() {
        return "Simple coffee";
    }
}