package Structural.Decorator;

/**
 * Decorator Design Pattern测试类
 */
public class DecoratorTest {
    public static void main(String[] args) {
        // 简单咖啡
        Coffee coffee = new SimpleCoffee();
        System.out.println("Cost: $" + coffee.getCost() + ", Description: " + coffee.getDescription());

        // 加牛奶的咖啡
        coffee = new MilkDecorator(coffee);
        System.out.println("Cost: $" + coffee.getCost() + ", Description: " + coffee.getDescription());

        // 加糖的咖啡
        coffee = new SugarDecorator(coffee);
        System.out.println("Cost: $" + coffee.getCost() + ", Description: " + coffee.getDescription());

        // 加香草的咖啡
        coffee = new VanillaDecorator(coffee);
        System.out.println("Cost: $" + coffee.getCost() + ", Description: " + coffee.getDescription());
    }
}