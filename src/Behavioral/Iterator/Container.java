package Behavioral.Iterator;

/**
 * 集合接口
 * 
 * 这个接口定义了获取迭代器的方法，是迭代器模式中的聚合对象接口
 * 
 * @param <T> 集合中元素的类型
 */
public interface Container<T> {
    /**
     * 获取集合的迭代器
     * 
     * @return 集合的迭代器实例
     */
    IteratorInterface<T> getIterator();
}