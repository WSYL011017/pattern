package Structural.Composite;

/**
 * Composite Design Pattern测试类
 */
public class CompositeTest {
    public static void main(String[] args) {
        Employee developer1 = new Developer("John", "Java Developer");
        Employee developer2 = new Developer("Mike", "Python Developer");
        Employee designer = new Designer("Emily", "UI Designer");

        Manager engineeringManager = new Manager("Robert", "Engineering Manager");
        engineeringManager.addSubordinate(developer1);
        engineeringManager.addSubordinate(developer2);

        Manager generalManager = new Manager("Alice", "General Manager");
        generalManager.addSubordinate(engineeringManager);
        generalManager.addSubordinate(designer);

        generalManager.showDetails();
    }
}