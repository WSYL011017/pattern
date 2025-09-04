package Creational.FactoryMethod;

/**
 * 具体产品1 - 狗
 */
public class Dog implements Animal {
    @Override
    public void speak() {
        System.out.println("Dog says: Woof!");
    }
}