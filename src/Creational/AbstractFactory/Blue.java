package Creational.AbstractFactory;

/**
 * 具体产品B3 - 蓝色
 */
public class Blue implements Color {
    @Override
    public void fill() {
        System.out.println("Inside Blue::fill() method.");
    }
}