package Creational.FactoryMethod;

/**
 * 具体产品2 - 猫
 */
public class Cat implements Animal {
    @Override
    public void speak() {
        System.out.println("Cat says: Meow!");
    }
}