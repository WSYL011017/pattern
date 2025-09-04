package Creational.AbstractFactory;

/**
 * 具体产品A3 - 圆形
 */
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}