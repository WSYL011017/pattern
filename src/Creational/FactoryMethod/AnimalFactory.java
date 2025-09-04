package Creational.FactoryMethod;

/**
 * 创建者抽象类 - 动物工厂抽象类
 */
public abstract class AnimalFactory {
    // 工厂方法
    public abstract Animal createAnimal();
    
    // 使用工厂方法创建动物并让它说话
    public void makeAnimalSpeak() {
        Animal animal = createAnimal();
        animal.speak();
    }
}