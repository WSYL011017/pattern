package Creational.AbstractFactory;

public abstract class AbstractFactory {


    public abstract Shape getShape(String shapeType);


    public abstract Color getColor(String color);
}
