package Structural.Flyweight;

import java.util.Random;

/**
 * Flyweight Design Pattern测试类
 */
public class FlyweightTest {
    private static final String[] colors = {"Red", "Green", "Blue", "White", "Black"};
    private static final Random random = new Random();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Circle circle = (Circle) ShapeFactory.getCircle(getRandomColor());
            circle.draw(getRandomX(), getRandomY());
        }
    }

    private static String getRandomColor() {
        return colors[random.nextInt(colors.length)];
    }

    private static int getRandomX() {
        return random.nextInt(100);
    }

    private static int getRandomY() {
        return random.nextInt(100);
    }
}