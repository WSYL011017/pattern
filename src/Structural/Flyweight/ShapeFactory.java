package Structural.Flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元工厂 - 形状工厂
 */
public class ShapeFactory {
    private static final Map<String, ShapeFlyweight> circleMap = new HashMap<>();

    public static ShapeFlyweight getCircle(String color) {
        Circle circle = (Circle) circleMap.get(color);

        if (circle == null) {
            circle = new Circle(color);
            circleMap.put(color, circle);
            System.out.println("Creating circle of color : " + color);
        }
        return circle;
    }
}