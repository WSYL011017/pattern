package Creational.AbstractFactory;

/**
 * 具体产品B1 - 红色
 */
public class Red implements Color {
    @Override
    public void fill() {
        System.out.println("Inside Red::fill() method.");
    }
}