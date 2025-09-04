package Creational.FactoryMethod;

/**
 * 具体创建者3 - 狮子工厂
 */
public class LionFactory extends AnimalFactory {
    @Override
    public Animal createAnimal() {
        return new Lion();
    }
}