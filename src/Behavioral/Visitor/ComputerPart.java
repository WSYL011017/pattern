package Behavioral.Visitor;

/**
 * 元素接口
 * 
 * 定义一个accept操作，它以一个访问者为参数
 */
public interface ComputerPart {
    /**
     * 接受访问者
     * 
     * @param computerPartVisitor 访问者对象
     */
    void accept(ComputerPartVisitor computerPartVisitor);
}