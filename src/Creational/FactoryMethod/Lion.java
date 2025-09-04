package Creational.FactoryMethod;

/**
 * 具体产品3 - 狮子
 */
public class Lion implements Animal {
    @Override
    public void speak() {
        System.out.println("Lion says: Roar!");
    }
}