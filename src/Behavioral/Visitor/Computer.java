package Behavioral.Visitor;

/**
 * 具体元素实现 - 计算机
 * 
 * 实现accept操作，以访问者为参数调用访问者的visit方法
 */
public class Computer implements ComputerPart {
    ComputerPart[] parts;

    /**
     * 构造函数
     */
    public Computer() {
        parts = new ComputerPart[]{new Mouse(), new Keyboard(), new Monitor()};
    }

    @Override
    public void accept(ComputerPartVisitor computerPartVisitor) {
        for (int i = 0; i < parts.length; i++) {
            parts[i].accept(computerPartVisitor);
        }
        computerPartVisitor.visit(this);
    }
}