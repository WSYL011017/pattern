package Creational.Prototype;

/**
 * 具体原型类 - 羊
 */
public class Sheep implements Prototype {
    private String name;
    private String category;

    public Sheep(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public Prototype clone() {
        return new Sheep(name, category);
    }

    @Override
    public String toString() {
        return "Sheep{name='" + name + "', category='" + category + "'}";
    }
}