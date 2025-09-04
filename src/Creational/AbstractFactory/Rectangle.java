package Creational.AbstractFactory;

/**
 * 具体产品A1 - 矩形
 */
public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}