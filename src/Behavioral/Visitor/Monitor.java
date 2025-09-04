package Behavioral.Visitor;

/**
 * 具体元素实现 - 显示器
 * 
 * 实现accept操作，以访问者为参数调用访问者的visit方法
 */
public class Monitor implements ComputerPart {
    @Override
    public void accept(ComputerPartVisitor computerPartVisitor) {
        computerPartVisitor.visit(this);
    }
}