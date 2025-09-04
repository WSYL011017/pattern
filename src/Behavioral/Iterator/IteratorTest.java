package Behavioral.Iterator;

/**
 * Iterator模式测试类
 * 
 * 用于演示Iterator模式的使用方法
 */
public class IteratorTest {
    public static void main(String[] args) {
        // 创建名称仓库
        NameRepository namesRepository = new NameRepository();

        // 添加一些测试数据
        namesRepository.addName("Robert");
        namesRepository.addName("John");
        namesRepository.addName("Julie");
        namesRepository.addName("Lora");

        // 使用迭代器遍历名称
        System.out.println("遍历所有名称:");
        IteratorInterface<String> iter = namesRepository.getIterator();
        while (iter.hasNext()) {
            String name = iter.next();
            System.out.println("Name: " + name);
        }

        // 测试过滤功能
        System.out.println("\n筛选以'J'开头的名称:");
        namesRepository.addName("Jack");
        namesRepository.addName("Jane");
        
        IteratorInterface<String> filteredIter = namesRepository.getIterator().filter(name -> name.startsWith("J"));
        while (filteredIter.hasNext()) {
            String name = filteredIter.next();
            System.out.println("Filtered Name: " + name);
        }

        // 测试映射功能
        System.out.println("\n将名称转换为大写:");
        IteratorInterface<String> upperCaseIter = namesRepository.getIterator().map(String::toUpperCase);
        while (upperCaseIter.hasNext()) {
            String name = upperCaseIter.next();
            System.out.println("Uppercase Name: " + name);
        }

        // 测试限制功能
        System.out.println("\n只显示前3个名称:");
        IteratorInterface<String> limitedIter = namesRepository.getIterator().limit(3);
        while (limitedIter.hasNext()) {
            String name = limitedIter.next();
            System.out.println("Limited Name: " + name);
        }

        // 测试计数功能
        System.out.println("\n总名称数: " + namesRepository.getIterator().count());

        // 测试去重功能
        System.out.println("\n添加重复名称并去重:");
        namesRepository.addName("Robert");
        namesRepository.addName("John");
        System.out.println("去重前数量: " + namesRepository.getIterator().count());
        System.out.println("去重后数量: " + namesRepository.getIterator().distinct().count());
    }
}