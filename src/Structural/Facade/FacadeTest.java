package Structural.Facade;

/**
 * Facade Design Pattern测试类
 */
public class FacadeTest {
    public static void main(String[] args) {
        ComputerFacade computer = new ComputerFacade();
        computer.start();
    }
}