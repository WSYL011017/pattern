package Behavioral.Visitor;

/**
 * 具体访问者实现
 * 
 * 实现每个由Visitor声明的操作，为每个具体元素类提供对应的访问逻辑
 */
public class ComputerPartDisplayVisitor implements ComputerPartVisitor {
    @Override
    public void visit(Computer computer) {
        System.out.println("Displaying Computer.");
    }

    @Override
    public void visit(Mouse mouse) {
        System.out.println("Displaying Mouse.");
    }

    @Override
    public void visit(Keyboard keyboard) {
        System.out.println("Displaying Keyboard.");
    }

    @Override
    public void visit(Monitor monitor) {
        System.out.println("Displaying Monitor.");
    }

    @Override
    public void visit(ComputerPart computerPart) {
        System.out.println("Displaying ComputerPart.");
    }
}