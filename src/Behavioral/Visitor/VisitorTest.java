package Behavioral.Visitor;

/**
 * Visitor模式测试类
 * <p>
 * 用于演示Visitor模式的使用方法
 */
public class VisitorTest {
    public static void main(String[] args) {
        ComputerPart computer = new Computer();
        computer.accept(new ComputerPartDisplayVisitor());
    }
}