package Behavioral.Visitor;

/**
 * 访问者接口
 * 
 * 为对象结构中的每个具体元素类声明一个visit操作
 */
public interface ComputerPartVisitor {
    /**
     * 访问Computer对象
     * 
     * @param computer Computer对象
     */
    void visit(Computer computer);
    
    /**
     * 访问Mouse对象
     * 
     * @param mouse Mouse对象
     */
    void visit(Mouse mouse);
    
    /**
     * 访问Keyboard对象
     * 
     * @param keyboard Keyboard对象
     */
    void visit(Keyboard keyboard);
    
    /**
     * 访问Monitor对象
     * 
     * @param monitor Monitor对象
     */
    void visit(Monitor monitor);
    
    /**
     * 访问ComputerPart对象
     * 
     * @param computerPart ComputerPart对象
     */
    void visit(ComputerPart computerPart);
}