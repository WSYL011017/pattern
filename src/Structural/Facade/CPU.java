package Structural.Facade;

/**
 * 子系统1 - CPU类
 */
public class CPU {
    public void freeze() {
        System.out.println("CPU freeze");
    }

    public void jump(long position) {
        System.out.println("CPU jump to position: " + position);
    }

    public void execute() {
        System.out.println("CPU execute");
    }
}