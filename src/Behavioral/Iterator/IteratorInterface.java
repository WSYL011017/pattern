package Behavioral.Iterator;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.Optional;
import java.util.Iterator;
import java.util.function.BiPredicate;
import java.util.function.BiFunction;

/**
 * 迭代器接口
 * <p>
 * 这个接口提供了基本的迭代功能，允许按顺序遍历集合中的元素，
 * 同时提供可选的元素操作功能如移除和批量处理。
 * <p>
 * 迭代器模式支持在不暴露集合内部结构的情况下访问集合元素，
 * 同时保证集合遍历过程中的稳定性。
 * 
 * @param <T> 遍历的元素类型
 */
public interface IteratorInterface<T> {
    /**
     * 检查是否还有更多元素可以遍历
     * 
     * @return 如果还有元素返回true，否则返回false
     */
    boolean hasNext();

    /**
     * 获取下一个元素
     * 
     * @return 下一个元素
     * @throws NoSuchElementException 如果没有更多元素时调用此方法
     */
    T next();

    /**
     * 移除当前元素（可选操作）
     * <p>
     * 默认实现抛出UnsupportedOperationException
     * 子类可以根据需要覆盖此方法实现元素移除功能
     * 
     * @throws UnsupportedOperationException 如果迭代器不支持移除操作
     * @throws IllegalStateException 如果在没有调用next()之前调用remove()
     */
    default void remove() {
        throw new UnsupportedOperationException("Remove operation is not supported by this iterator implementation.");
    }

    /**
     * 对剩余的所有元素执行指定操作
     * 
     * @param action 要执行的操作
     * @throws NullPointerException 如果action为null
     */
    default void forEachRemaining(Consumer<? super T> action) {
        if (action == null) {
            throw new NullPointerException("Cannot perform null action on remaining elements.");
        }
        
        while (hasNext()) {
            action.accept(next());
        }
    }
    
    /**
     * 跳过指定数量的元素
     * 
     * @param n 要跳过的元素数量
     * @return 实际跳过的元素数量
     * @throws IllegalArgumentException 如果n为负数
     */
    default int skip(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Cannot skip negative number of elements: " + n);
        }
        
        int skipped = 0;
        while (skipped < n && hasNext()) {
            next();
            skipped++;
        }
        return skipped;
    }
    
    /**
     * 检查迭代器是否为空
     * 
     * @return 如果迭代器不包含任何元素返回true，否则返回false
     */
    default boolean isEmpty() {
        return !hasNext();
    }

    /**
     * 创建一个新的迭代器，只包含满足给定谓词的元素
     * 
     * @param predicate 元素筛选条件
     * @return 过滤后的迭代器
     * @throws NullPointerException 如果predicate为null
     */
    default IteratorInterface<T> filter(Predicate<? super T> predicate) {
        if (predicate == null) {
            throw new NullPointerException("Cannot apply null predicate for filtering.");
        }
        
        IteratorInterface<T> iterator = this;
        return new IteratorInterface<T>() {
            private T nextItem;
            private boolean hasNextItem = false;
            
            @Override
            public boolean hasNext() {
                if (hasNextItem) {
                    return true;
                }
                
                while (iterator.hasNext()) {
                    T item = iterator.next();
                    if (predicate.test(item)) {
                        nextItem = item;
                        hasNextItem = true;
                        return true;
                    }
                }
                
                return false;
            }
            
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more matching elements in the iterator");
                }
                
                hasNextItem = false;
                return nextItem;
            }
            
            @Override
            public void remove() {
                iterator.remove();
            }
        };
    }

    /**
     * 创建一个新的迭代器，将元素映射为另一种类型
     * 
     * @param <R> 转换后的元素类型
     * @param mapper 元素转换函数
     * @return 转换后的迭代器
     * @throws NullPointerException 如果mapper为null
     */
    default <R> IteratorInterface<R> map(Function<? super T, ? extends R> mapper) {
        if (mapper == null) {
            throw new NullPointerException("Cannot apply null mapper for element mapping.");
        }
        
        IteratorInterface<T> iterator = this;
        return new IteratorInterface<R>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }
            
            @Override
            public R next() {
                return mapper.apply(iterator.next());
            }
            
            @Override
            public void remove() {
                iterator.remove();
            }
        };
    }

    /**
     * 创建一个新的迭代器，最多返回指定数量的元素
     * 
     * @param n 限制的元素数量
     * @return 限制后的迭代器
     * @throws IllegalArgumentException 如果n为负数
     */
    default IteratorInterface<T> limit(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Cannot limit to negative number of elements: " + n);
        }
        
        IteratorInterface<T> iterator = this;
        return new IteratorInterface<T>() {
            private int count = 0;
            
            @Override
            public boolean hasNext() {
                return count < n && iterator.hasNext();
            }
            
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements in limited iterator");
                }
                
                count++;
                return iterator.next();
            }
            
            @Override
            public void remove() {
                iterator.remove();
            }
        };
    }

    /**
     * 创建一个新的迭代器，包含满足谓词的连续元素，直到遇到第一个不满足条件的元素
     * 
     * @param predicate 元素筛选条件
     * @return 条件截取后的迭代器
     * @throws NullPointerException 如果predicate为null
     */
    default IteratorInterface<T> takeWhile(Predicate<? super T> predicate) {
        if (predicate == null) {
            throw new NullPointerException("Cannot apply null predicate for takeWhile operation.");
        }
        
        IteratorInterface<T> iterator = this;
        return new IteratorInterface<T>() {
            private T nextItem;
            private boolean hasNextItem = false;
            private boolean stopped = false;
            
            @Override
            public boolean hasNext() {
                if (hasNextItem) {
                    return true;
                }
                
                if (stopped || !iterator.hasNext()) {
                    return false;
                }
                
                T item = iterator.next();
                if (predicate.test(item)) {
                    nextItem = item;
                    hasNextItem = true;
                    return true;
                } else {
                    stopped = true;
                    return false;
                }
            }
            
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more matching elements in the iterator");
                }
                
                hasNextItem = false;
                return nextItem;
            }
            
            @Override
            public void remove() {
                iterator.remove();
            }
        };
    }
    
    /**
     * 将迭代器中的元素收集到列表中
     * 
     * @return 包含迭代器所有元素的列表
     */
    default List<T> toList() {
        List<T> list = new ArrayList<>();
        forEachRemaining(list::add);
        return list;
    }
    
    /**
     * 将迭代器中的元素添加到指定的集合中
     * 
     * @param collection 目标集合
     * @return 目标集合
     * @throws NullPointerException 如果collection为null
     */
    default Collection<T> toCollection(Collection<T> collection) {
        if (collection == null) {
            throw new NullPointerException("Cannot add elements to null collection.");
        }
        
        forEachRemaining(collection::add);
        return collection;
    }
    
    /**
     * 检查迭代器是否包含指定元素
     * 
     * @param element 要检查的元素
     * @return 如果包含元素返回true，否则返回false
     */
    default boolean contains(Object element) {
        while (hasNext()) {
            T current = next();
            if (Objects.equals(current, element)) {
                // 找到元素后，跳过剩余元素
                return true;
            }
        }
        return false;
    }
    
    /**
     * 返回迭代器中元素的数量
     * 
     * @return 元素数量
     */
    default int count() {
        int count = 0;
        while (hasNext()) {
            next();
            count++;
        }
        return count;
    }
    
    /**
     * 创建一个新的迭代器，将多个迭代器连接在一起
     * 
     * @param iterators 要连接的其他迭代器
     * @return 连接后的迭代器
     * @throws NullPointerException 如果iterators为null
     */
    default IteratorInterface<T> concat(IteratorInterface<? extends T>... iterators) {
        if (iterators == null) {
            throw new NullPointerException("Cannot concatenate null array of iterators.");
        }
        
        return new IteratorInterface<T>() {
            private int currentIteratorIndex = 0;
            
            @Override
            public boolean hasNext() {
                while (currentIteratorIndex < iterators.length) {
                    if (iterators[currentIteratorIndex].hasNext()) {
                        return true;
                    }
                    currentIteratorIndex++;
                }
                return false;
            }
            
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements in concatenated iterator");
                }
                
                return iterators[currentIteratorIndex].next();
            }
            
            @Override
            public void remove() {
                iterators[currentIteratorIndex].remove();
            }
        };
    }
    
    /**
     * 创建一个新的迭代器，将每个元素映射为一个迭代器，并按顺序连接所有结果
     * 
     * @param <R> 转换后的元素类型
     * @param mapper 元素到迭代器的转换函数
     * @return 扁平化转换后的迭代器
     * @throws NullPointerException 如果mapper为null
     */
    default <R> IteratorInterface<R> flatMap(Function<? super T, ? extends IteratorInterface<? extends R>> mapper) {
        if (mapper == null) {
            throw new NullPointerException("Cannot apply null mapper for flatMap operation.");
        }
        
        IteratorInterface<T> iterator = this;
        return new IteratorInterface<R>() {
            private IteratorInterface<? extends R> currentIterator = null;
            
            @Override
            public boolean hasNext() {
                while (currentIterator == null || !currentIterator.hasNext()) {
                    if (!iterator.hasNext()) {
                        return false;
                    }
                    currentIterator = mapper.apply(iterator.next());
                }
                return true;
            }
            
            @Override
            public R next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements in flatMapped iterator");
                }
                return currentIterator.next();
            }
            
            @Override
            public void remove() {
                currentIterator.remove();
            }
        };
    }
    
    /**
     * 对迭代器进行分组，根据分类函数的返回值将元素分组
     *
     * @param <K> 分组的键类型
     * @param classifier 分组函数
     * @return 分组后的结果
     * @throws NullPointerException 如果classifier为null
     */
    default <K> Map<K, List<T>> groupBy(Function<? super T, ? extends K> classifier) {
        if (classifier == null) {
            throw new NullPointerException("Cannot group by null classifier.");
        }
        
        Map<K, List<T>> groups = new HashMap<>();
        forEachRemaining(item -> {
            K key = classifier.apply(item);
            groups.computeIfAbsent(key, k -> new ArrayList<>()).add(item);
        });
        return groups;
    }
    
    /**
     * 获取迭代器中的第一个元素（如果存在）
     * 
     * @return 第一个元素的Optional包装
     */
    default Optional<T> first() {
        if (hasNext()) {
            return Optional.of(next());
        }
        return Optional.empty();
    }
    
    /**
     * 获取迭代器中的最后一个元素（如果存在）
     * 
     * @return 最后一个元素的Optional包装
     */
    default Optional<T> last() {
        T lastElement = null;
        while (hasNext()) {
            lastElement = next();
        }
        return lastElement != null ? Optional.of(lastElement) : Optional.empty();
    }
    
    /**
     * 在迭代器开始处添加一个元素
     * 
     * @param element 要添加的元素
     * @return 新的迭代器
     */
    default IteratorInterface<T> prepend(T element) {
        IteratorInterface<T> iterator = this;
        return new IteratorInterface<T>() {
            private boolean hasPrepended = false;
            
            @Override
            public boolean hasNext() {
                return !hasPrepended || iterator.hasNext();
            }
            
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements in prepended iterator");
                }
                
                if (!hasPrepended) {
                    hasPrepended = true;
                    return element;
                }
                
                return iterator.next();
            }
            
            @Override
            public void remove() {
                if (!hasPrepended) {
                    iterator.remove();
                } else {
                    throw new IllegalStateException("Cannot remove element before next() call");
                }
            }
        };
    }
    
    /**
     * 在迭代器结束处添加一个元素
     * 
     * @param element 要添加的元素
     * @return 新的迭代器
     */
    default IteratorInterface<T> append(T element) {
        IteratorInterface<T> iterator = this;
        return new IteratorInterface<T>() {
            private boolean hasAppended = false;
            
            @Override
            public boolean hasNext() {
                if (iterator.hasNext()) {
                    return true;
                }
                
                return !hasAppended;
            }
            
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements in appended iterator");
                }
                
                if (iterator.hasNext()) {
                    return iterator.next();
                }
                
                hasAppended = false;
                return element;
            }
            
            @Override
            public void remove() {
                if (hasAppended) {
                    throw new IllegalStateException("Cannot remove element after last element");
                }
                
                if (iterator.hasNext()) {
                    iterator.remove();
                } else {
                    throw new NoSuchElementException("No element to remove");
                }
            }
        };
    }
    
    /**
     * 创建一个新的迭代器，跳过指定数量的元素后返回剩余元素
     * 
     * @param n 要跳过的元素数量
     * @return 跳过后的迭代器
     * @throws IllegalArgumentException 如果n为负数
     */
    default IteratorInterface<T> skipIterator(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Cannot skip negative number of elements: " + n);
        }
        
        // 先跳过n个元素
        skip(n);
        
        IteratorInterface<T> iterator = this;
        return new IteratorInterface<T>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }
            
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements after skipped elements");
                }
                
                return iterator.next();
            }
            
            @Override
            public void remove() {
                iterator.remove();
            }
        };
    }
    
    /**
     * 对迭代器中的元素进行排序
     * 
     * @param comparator 比较器
     * @return 排序后的迭代器
     * @throws NullPointerException 如果comparator为null
     */
    default IteratorInterface<T> sorted(java.util.Comparator<? super T> comparator) {
        if (comparator == null) {
            throw new NullPointerException("Cannot sort with null comparator.");
        }
        
        List<T> sortedList = toList();
        Collections.sort(sortedList, comparator);
        return (IteratorInterface<T>) new NameIterator(sortedList);
    }
    
    /**
     * 对迭代器中的元素进行去重
     * 
     * @return 去重后的迭代器
     */
    default IteratorInterface<T> distinct() {
        IteratorInterface<T> iterator = this;
        return new IteratorInterface<T>() {
            private Set<T> seen = new HashSet<>();
            private T nextItem;
            private boolean hasNextItem = false;
            
            @Override
            public boolean hasNext() {
                if (hasNextItem) {
                    return true;
                }
                
                while (iterator.hasNext()) {
                    T item = iterator.next();
                    if (seen.add(item)) {
                        nextItem = item;
                        hasNextItem = true;
                        return true;
                    }
                }
                
                return false;
            }
            
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more distinct elements");
                }
                
                hasNextItem = false;
                return nextItem;
            }
            
            @Override
            public void remove() {
                iterator.remove();
            }
        };
    }
    
    /**
     * 将迭代器转换为spliterator
     * 
     * @return 对应的spliterator
     */
    default Spliterator<T> spliterator() {
        return Spliterators.spliteratorUnknownSize(new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return IteratorInterface.this.hasNext();
            }

            @Override
            public T next() {
                return IteratorInterface.this.next();
            }
        }, Spliterator.ORDERED);
    }

    /**
     * 重启迭代器（可选操作）
     * 
     * 默认实现抛出 UnsupportedOperationException
     * 可由支持重置的迭代器实现覆盖
     * 
     * @throws UnsupportedOperationException 如果迭代器不支持重置操作
     */
    default void reset() {
        throw new UnsupportedOperationException("Reset operation is not supported by this iterator implementation.");
    }

    /**
     * 查看下一个元素而不移动迭代器位置（可选操作）
     * 
     * @return 下一个元素的Optional包装
     * @throws UnsupportedOperationException 如果迭代器不支持peek操作
     */
    default Optional<T> peek() {
        throw new UnsupportedOperationException("Peek operation is not supported by this iterator implementation.");
    }

    /**
     * 按指定大小批量处理元素
     * 
     * @param batchSize 批量大小
     * @return 批量处理的迭代器
     * @throws IllegalArgumentException 如果batchSize小于等于0
     */
    default IteratorInterface<List<T>> batch(int batchSize) {
        if (batchSize <= 0) {
            throw new IllegalArgumentException("Batch size must be positive: " + batchSize);
        }

        IteratorInterface<T> iterator = this;
        return new IteratorInterface<List<T>>() {
            private boolean hasCachedBatch = false;
            private List<T> currentBatch = null;

            @Override
            public boolean hasNext() {
                if (hasCachedBatch) {
                    return true;
                }

                currentBatch = new ArrayList<>();
                int count = 0;
                while (count++ < batchSize && iterator.hasNext()) {
                    currentBatch.add(iterator.next());
                }

                hasCachedBatch = !currentBatch.isEmpty();
                return hasCachedBatch;
            }

            @Override
            public List<T> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more batches available");
                }

                hasCachedBatch = false;
                return currentBatch;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Remove operation is not supported in batched iterator");
            }
        };
    }

    /**
     * 创建一个新的迭代器，提供基于索引的访问
     * 
     * @return 带索引信息的迭代器
     */
    default IteratorInterface<IndexedValue<T>> indexed() {
        IteratorInterface<T> iterator = this;
        return new IteratorInterface<IndexedValue<T>>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public IndexedValue<T> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements in indexed iterator");
                }

                return new IndexedValue<>(index++, iterator.next());
            }

            @Override
            public void remove() {
                iterator.remove();
                // 注意：移除元素后，索引可能需要调整
            }
        };
    }

    /**
     * 创建一个新的迭代器，按逆序遍历元素
     * 
     * @return 逆序迭代器
     */
    default IteratorInterface<T> reverse() {
        List<T> reversedList = toList();
        Collections.reverse(reversedList);
        return (IteratorInterface<T>) new NameIterator(reversedList);
    }

    /**
     * 对迭代器中的元素进行自定义累积操作
     * 
     * @param identity 初始值
     * @param accumulator 累积函数
     * @return 累积结果
     */
    default T accumulate(T identity, java.util.function.BinaryOperator<T> accumulator) {
        if (accumulator == null) {
            throw new NullPointerException("Cannot use null accumulator function");
        }

        T result = identity;
        while (hasNext()) {
            result = accumulator.apply(result, next());
        }
        return result;
    }

    /**
     * 统计匹配指定条件的元素数量
     * 
     * @param predicate 匹配条件
     * @return 匹配的元素数量
     * @throws NullPointerException 如果predicate为null
     */
    default int countIf(Predicate<? super T> predicate) {
        if (predicate == null) {
            throw new NullPointerException("Cannot apply null predicate for counting");
        }

        int count = 0;
        while (hasNext()) {
            if (predicate.test(next())) {
                count++;
            }
        }
        return count;
    }

    /**
         * 带索引信息的元素类
         *
         * @param <T> 元素类型
         */
        record IndexedValue<T>(int index, T value) {

        @Override
            public String toString() {
                return "Index: " + index + ", Value: " + value;
            }
        }

    /**
     * 将迭代器包装为Iterable以便在增强的for循环中使用
     * 
     * @return 一个Iterable对象
     */
    default Iterable<T> asIterable() {
        return () -> new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return IteratorInterface.this.hasNext();
            }

            @Override
            public T next() {
                return IteratorInterface.this.next();
            }
        };
    }
    
    /**
     * 将迭代器转换为字符串，显示前n个元素
     * 
     * @param n 要显示的元素数量
     * @return 迭代器的字符串表示
     * @throws IllegalArgumentException 如果n为负数
     */
    default String toString(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Cannot show negative number of elements: " + n);
        }
        
        // 创建一个副本列表以避免修改原始迭代器状态
        List<T> elements = new ArrayList<>();
        int count = 0;
        while (count < n && hasNext()) {
            elements.add(next());
            count++;
        }
        
        // 保存是否有更多元素
        boolean hasMore = hasNext();
        
        // 重置迭代器（注意：这个功能在当前实现中不完整）
        // 实际应用中可能需要更复杂的实现来支持真正的重置功能
        // 这里只是为了演示目的
        // 重置迭代器位置
        // ...
        
        StringBuilder sb = new StringBuilder();
        sb.append("Iterator[");
        
        for (int i = 0; i < elements.size(); i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(elements.get(i));
        }
        
        // 如果还有更多元素，添加省略号
        if (hasMore) {
            sb.append(", ...");
        }
        
        sb.append("]");
        
        return sb.toString();
    }
    
    /**
     * 检查两个迭代器是否包含相同的元素序列
     * 
     * @param other 另一个迭代器
     * @param equalityChecker 元素相等性检查器
     * @return 如果两个迭代器包含相同的元素序列返回true
     * @throws NullPointerException 如果other或equalityChecker为null
     */
    default boolean sequenceEqual(IteratorInterface<? extends T> other, BiPredicate<? super T, ? super T> equalityChecker) {
        if (other == null) {
            throw new NullPointerException("Cannot compare with null iterator.");
        }
        if (equalityChecker == null) {
            throw new NullPointerException("Cannot use null equality checker.");
        }
        
        IteratorInterface<T> iterator = this;
        while (true) {
            boolean thisHasNext = iterator.hasNext();
            boolean otherHasNext = other.hasNext();
            
            if (!thisHasNext && !otherHasNext) {
                return true; // Both ended
            }
            
            if (thisHasNext != otherHasNext) {
                return false; // One ended, the other didn't
            }
            
            T thisNext = iterator.next();
            T otherNext = other.next();
            
            if (!equalityChecker.test(thisNext, otherNext)) {
                return false;
            }
        }
    }
    
    /**
     * 创建一个新的迭代器，其元素是当前迭代器和另一个迭代器的配对元素
     * 
     * @param <U> 另一个迭代器的元素类型
     * @param other 另一个迭代器
     * @param combiner 元素组合函数
     * @return 配对后的迭代器
     * @throws NullPointerException 如果other或combiner为null
     */
    default <U, R> IteratorInterface<R> zip(IteratorInterface<? extends T> other, BiFunction<? super T, ? super T, ? extends R> combiner) {
        if (other == null) {
            throw new NullPointerException("Cannot zip with null iterator.");
        }
        if (combiner == null) {
            throw new NullPointerException("Cannot use null combiner function.");
        }
        
        IteratorInterface<T> iterator = this;
        return new IteratorInterface<R>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext() && other.hasNext();
            }
            
            @Override
            public R next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements in zipped iterator");
                }
                
                return combiner.apply(iterator.next(), other.next());
            }
        };
    }
    
    /**
     * 创建一个新的迭代器，包含当前迭代器和另一个迭代器的所有元素
     * 
     * @param other 另一个迭代器
     * @return 合并后的迭代器
     * @throws NullPointerException 如果other为null
     */
    default IteratorInterface<T> merge(IteratorInterface<? extends T> other) {
        if (other == null) {
            throw new NullPointerException("Cannot merge with null iterator.");
        }
        
        IteratorInterface<T> iterator = this;
        return new IteratorInterface<T>() {
            private boolean useCurrent = true;
            
            @Override
            public boolean hasNext() {
                return useCurrent ? iterator.hasNext() : other.hasNext();
            }
            
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements in merged iterator");
                }
                
                if (useCurrent) {
                    T nextItem = iterator.next();
                    // 如果当前迭代器没有更多元素，切换到另一个迭代器
                    if (!iterator.hasNext()) {
                        useCurrent = false;
                    }
                    return nextItem;
                } else {
                    return other.next();
                }
            }
            
            @Override
            public void remove() {
                if (useCurrent) {
                    iterator.remove();
                } else {
                    other.remove();
                }
            }
        };
    }
    
    /**
     * 对迭代器中的元素进行分区，根据条件分成两组
     * 
     * @param predicate 分区条件
     * @return 包含两个列表的数组，第一个列表满足条件，第二个列表不满足条件
     * @throws NullPointerException 如果predicate为null
     */
    default List<T>[] partition(Predicate<? super T> predicate) {
        if (predicate == null) {
            throw new NullPointerException("Cannot apply null predicate for partition operation.");
        }
        
        List<T> trueList = new ArrayList<>();
        List<T> falseList = new ArrayList<>();
        
        forEachRemaining(item -> {
            if (predicate.test(item)) {
                trueList.add(item);
            } else {
                falseList.add(item);
            }
        });
        
        // 创建一个泛型列表数组
        List<T>[] result = new List[2];
        result[0] = trueList;
        result[1] = falseList;
        return result;
    }
    
    /**
     * 创建一个新的迭代器，其元素是当前迭代器元素和索引的组合
     * 
     * @param combiner 元素和索引的组合函数
     * @return 组合后的迭代器
     * @throws NullPointerException 如果combiner为null
     */
    default <U> IteratorInterface<U> zipWithIndex(BiFunction<? super T, Integer, ? extends U> combiner) {
        if (combiner == null) {
            throw new NullPointerException("Cannot use null combiner function.");
        }
        
        IteratorInterface<T> iterator = this;
        return new IteratorInterface<U>() {
            private int index = 0;
            
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }
            
            @Override
            public U next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements in zipped iterator");
                }
                
                return combiner.apply(iterator.next(), index++);
            }
        };
    }
    
    /**
     * 返回一个只包含唯一元素的迭代器（根据equals方法）
     * 
     * @return 去重后的迭代器
     */
    default IteratorInterface<T> unique() {
        IteratorInterface<T> iterator = this;
        return new IteratorInterface<T>() {
            private Set<T> seen = new HashSet<>();
            private T nextItem;
            private boolean hasNextItem = false;
            
            @Override
            public boolean hasNext() {
                if (hasNextItem) {
                    return true;
                }
                
                while (iterator.hasNext()) {
                    T item = iterator.next();
                    if (!seen.contains(item)) {
                        seen.add(item);
                        nextItem = item;
                        hasNextItem = true;
                        return true;
                    }
                }
                
                return false;
            }
            
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more unique elements");
                }
                
                hasNextItem = false;
                return nextItem;
            }
        };
    }
    
    /**
     * 返回一个迭代器，其中包含当前迭代器中满足条件的元素和不满足条件的元素
     * 
     * @param predicate 筛选条件
     * @return 一个包含两个迭代器的数组，第一个迭代器包含满足条件的元素，第二个迭代器包含不满足条件的元素
     * @throws NullPointerException 如果predicate为null
     */
    default IteratorInterface<T>[] partitionIterator(Predicate<? super T> predicate) {
        if (predicate == null) {
            throw new NullPointerException("Cannot apply null predicate for partition operation.");
        }
        
        // 创建两个缓冲区来存储分区结果
        List<T> trueList = new ArrayList<>();
        List<T> falseList = new ArrayList<>();
        
        // 将元素分区
        forEachRemaining(item -> {
            if (predicate.test(item)) {
                trueList.add(item);
            } else {
                falseList.add(item);
            }
        });
        
        // 创建两个新的迭代器
        IteratorInterface<T> trueIterator = new NameIterator(trueList);
        IteratorInterface<T> falseIterator = new NameIterator(falseList);
        
        // 创建迭代器数组
        IteratorInterface<T>[] result = new IteratorInterface[2];
        result[0] = trueIterator;
        result[1] = falseIterator;
        return result;
    }
    
    /**
     * 将迭代器转换为指定类型的数组
     * 
     * @param arrayFactory 用于创建数组的函数
     * @return 包含迭代器所有元素的数组
     * @throws NullPointerException 如果arrayFactory为null
     */
    default T[] toArray(Function<Integer, T[]> arrayFactory) {
        if (arrayFactory == null) {
            throw new NullPointerException("Cannot use null array factory.");
        }
        
        // 收集所有元素到列表
        List<T> list = new ArrayList<>();
        forEachRemaining(list::add);
        
        // 转换为指定类型的数组
        T[] array = arrayFactory.apply(list.size());
        return list.toArray(array);
    }
    
    /**
     * 创建一个新的迭代器，其中包含当前迭代器元素和另一个值的组合
     * 
     * @param value 要组合的值
     * @param combiner 元素和值的组合函数
     * @return 组合后的迭代器
     * @throws NullPointerException 如果value或combiner为null
     */
    default <U, R> IteratorInterface<R> combineWith(U value, BiFunction<? super T, ? super U, ? extends R> combiner) {
        if (value == null) {
            throw new NullPointerException("Cannot use null value for combination.");
        }
        if (combiner == null) {
            throw new NullPointerException("Cannot use null combiner function.");
        }
        
        IteratorInterface<T> iterator = this;
        return new IteratorInterface<R>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }
            
            @Override
            public R next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements in combined iterator");
                }
                
                return combiner.apply(iterator.next(), value);
            }
        };
    }
    
    /**
     * 对迭代器进行分页，返回指定页码的子集
     * 
     * @param pageSize 页面大小
     * @param pageNumber 页码（从1开始）
     * @return 分页后的迭代器
     * @throws IllegalArgumentException 如果pageSize或pageNumber为负数或零
     */
    default IteratorInterface<T> paginate(int pageSize, int pageNumber) {
        if (pageSize <= 0) {
            throw new IllegalArgumentException("Page size must be positive: " + pageSize);
        }
        if (pageNumber <= 0) {
            throw new IllegalArgumentException("Page number must be positive: " + pageNumber);
        }
        
        // 计算要跳过的元素数量
        int skipElements = pageSize * (pageNumber - 1);
        
        IteratorInterface<T> iterator = this;
        return new IteratorInterface<T>() {
            private int count = 0;
            
            @Override
            public boolean hasNext() {
                // 跳过前面的元素
                while (count < skipElements && iterator.hasNext()) {
                    iterator.next();
                    count++;
                }
                
                // 在跳过足够的元素后，检查是否有剩余元素
                return iterator.hasNext();
            }
            
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements on page " + pageNumber);
                }
                
                return iterator.next();
            }
        };
    }
}