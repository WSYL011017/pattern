package Creational.AbstractFactory;

/**
 * 具体产品B2 - 绿色
 */
public class Green implements Color {
    @Override
    public void fill() {
        System.out.println("Inside Green::fill() method.");
    }
}