package Structural.Decorator;

/**
 * 具体装饰器 - 香草
 */
public class VanillaDecorator extends CoffeeDecorator {
    public VanillaDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.8;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", vanilla";
    }
}