package Structural.Flyweight;

/**
 * 具体享元类 - 圆形
 */
public class Circle implements ShapeFlyweight {
    private String color;
    
    public Circle(String color) {
        this.color = color;
        System.out.println("Creating circle of color : " + color);
    }

    @Override
    public void draw(int x, int y) {
        System.out.println("Circle: Draw() [Color : " + color + ", x : " + x + ", y :" + y);
    }
}