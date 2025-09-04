package Behavioral.Iterator;

import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * 具体迭代器实现
 * 
 * 这个类实现了Iterator接口，用于遍历名称列表
 */
public class NameIterator<T> implements IteratorInterface<T> {
    private List<T> names;
    private int index;
    private boolean canRemove = false;

    /**
     * 构造函数
     * 
     * @param names 要遍历的名称列表
     */
    public NameIterator(List<T> names) {
        this.names = names;
    }

    @Override
    public boolean hasNext() {
        return index < names.size();
    }

    @Override
    public T next() {
        if (this.hasNext()) {
            canRemove = true;
            return names.get(index++);
        }
        throw new NoSuchElementException("No more elements in the iterator");
    }

    /**
     * 从集合中移除最近返回的元素
     * 
     * @throws IllegalStateException 如果在调用next()之前调用remove()
     */
    @Override
    public void remove() {
        if (!canRemove) {
            throw new IllegalStateException("Can't remove before calling next()");
        }
        names.remove(--index);
        canRemove = false;
    }
}