package Structural.Composite;

/**
 * 叶子节点 - 开发者
 */
public class Developer implements Employee {
    private String name;
    private String position;

    public Developer(String name, String position) {
        this.name = name;
        this.position = position;
    }

    @Override
    public void showDetails() {
        System.out.println("Developer: " + name + ", Position: " + position);
    }
}