package Behavioral.TemplateMethod;

/**
 * Template Method Design Pattern测试类
 */
public class TemplateMethodTest {
    public static void main(String[] args) {
        Game game = new Cricket();
        game.play();
        System.out.println();
        game = new Football();
        game.play();
    }
}