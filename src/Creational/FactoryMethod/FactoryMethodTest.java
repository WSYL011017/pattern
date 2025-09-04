package Creational.FactoryMethod;

/**
 * Factory Method Design Pattern测试类
 */
public class FactoryMethodTest {
    public static void main(String[] args) {
        // 创建狗工厂并制造狗
        AnimalFactory dogFactory = new DogFactory();
        dogFactory.makeAnimalSpeak();

        // 创建猫工厂并制造猫
        AnimalFactory catFactory = new CatFactory();
        catFactory.makeAnimalSpeak();

        // 创建狮子工厂并制造狮子
        AnimalFactory lionFactory = new LionFactory();
        lionFactory.makeAnimalSpeak();
    }
}