package Creational.Prototype;

/**
 * Prototype Design Pattern测试类
 */
public class PrototypeTest {
    public static void main(String[] args) {
        Sheep original = new Sheep("Dolly", "Mountain Sheep");
        System.out.println("Original: " + original);

        Sheep cloned = (Sheep) original.clone();
        cloned.setName("Molly");
        System.out.println("Cloned: " + cloned);
    }
}