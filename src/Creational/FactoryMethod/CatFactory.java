package Creational.FactoryMethod;

/**
 * 具体创建者2 - 猫工厂
 */
public class CatFactory extends AnimalFactory {
    @Override
    public Animal createAnimal() {
        return new Cat();
    }
}