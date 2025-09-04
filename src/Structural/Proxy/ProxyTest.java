package Structural.Proxy;

/**
 * Proxy Design Pattern测试类
 */
public class ProxyTest {
    public static void main(String[] args) {
        Image image1 = new ProxyImage("test_10mb.jpg");
        Image image2 = new ProxyImage("test_20mb.jpg");

        // 第一次显示图片，需要从磁盘加载
        System.out.println("First call to display image1:");
        image1.display();
        System.out.println("");

        // 第二次显示图片，不需要从磁盘加载
        System.out.println("Second call to display image1:");
        image1.display();
        System.out.println("");

        // 显示第二张图片
        System.out.println("First call to display image2:");
        image2.display();
    }
}