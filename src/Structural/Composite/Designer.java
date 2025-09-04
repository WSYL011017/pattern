package Structural.Composite;

/**
 * 叶子节点 - 设计师
 */
public class Designer implements Employee {
    private String name;
    private String position;

    public Designer(String name, String position) {
        this.name = name;
        this.position = position;
    }

    @Override
    public void showDetails() {
        System.out.println("Designer: " + name + ", Position: " + position);
    }
}