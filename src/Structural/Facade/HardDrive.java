package Structural.Facade;

/**
 * 子系统3 - 硬盘类
 */
public class HardDrive {
    public byte[] read(long lba, int size) {
        System.out.println("HardDrive read " + size + " bytes from LBA " + lba);
        return new byte[size];
    }
}