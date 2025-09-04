package Creational.Singleton;

/**
 * Singleton Design Pattern测试类
 */
public class SingletonTest {
    public static void main(String[] args) {
        // 获取唯一实例
        Singleton singleton1 = Singleton.getInstance();
        singleton1.showMessage();

        // 再次获取实例
        Singleton singleton2 = Singleton.getInstance();
        
        // 验证两个引用是否指向同一个对象
        System.out.println("singleton1 == singleton2: " + (singleton1 == singleton2));
        
        // 使用线程安全的方式获取实例
        Singleton singleton3 = Singleton.getInstanceThreadSafe();
        System.out.println("singleton1 == singleton3: " + (singleton1 == singleton3));
    }
}