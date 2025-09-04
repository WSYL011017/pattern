package Structural.Facade;

/**
 * 子系统2 - 内存类
 */
public class Memory {
    public void load(long position, byte[] data) {
        System.out.println("Memory load data at position: " + position);
    }
}