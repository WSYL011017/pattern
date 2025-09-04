package Creational.FactoryMethod;

/**
 * 具体创建者1 - 狗工厂
 */
public class DogFactory extends AnimalFactory {
    @Override
    public Animal createAnimal() {
        return new Dog();
    }
}