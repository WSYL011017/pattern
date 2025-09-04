package Creational.AbstractFactory;

/**
 * 具体产品A2 - 正方形
 */
public class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}