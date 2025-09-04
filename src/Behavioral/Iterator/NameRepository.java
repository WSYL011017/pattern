package Behavioral.Iterator;

import java.util.List;
import java.util.ArrayList;

/**
 * 具体集合实现
 * 
 * 这个类实现了Container接口，用于存储和管理名称列表
 */
public class NameRepository implements Container<String> {
    private List<String> names = new ArrayList<>();

    /**
     * 添加名称到集合中
     * 
     * @param name 要添加的名称
     */
    public void addName(String name) {
        names.add(name);
    }

    /**
     * 获取集合的迭代器
     * 
     * @return 集合的迭代器实例
     */
    @Override
    public IteratorInterface<String> getIterator() {
        return new NameIterator<>(names);
    }
}