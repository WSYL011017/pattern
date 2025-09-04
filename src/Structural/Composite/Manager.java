package Structural.Composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合节点 - 经理
 */
public class Manager implements Employee {
    private String name;
    private String position;
    private List<Employee> subordinateList = new ArrayList<>();

    public Manager(String name, String position) {
        this.name = name;
        this.position = position;
    }

    public void addSubordinate(Employee employee) {
        subordinateList.add(employee);
    }

    public void removeSubordinate(Employee employee) {
        subordinateList.remove(employee);
    }

    @Override
    public void showDetails() {
        System.out.println("Manager: " + name + ", Position: " + position);
        for (Employee employee : subordinateList) {
            employee.showDetails();
        }
    }
}