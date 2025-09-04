package Creational.Singleton;

public class Singleton {

    private static Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello World from Singleton!");
    }
    
    // 双重检查锁定实现方式
    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }
    
    public static Singleton getInstanceThreadSafe() {
        return SingletonHolder.INSTANCE;
    }
}