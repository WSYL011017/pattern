package Behavioral.Memento;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理者类
 * 
 * 负责保存备忘录，但不能对备忘录的内容进行操作或检查
 */
public class CareTaker {
    private List<MementoClass> mementoList = new ArrayList<>();

    /**
     * 添加备忘录到列表
     * 
     * @param state 备忘录对象
     */
    public void add(MementoClass state) {
        mementoList.add(state);
    }

    /**
     * 从列表中获取备忘录
     * 
     * @param index 备忘录索引
     * @return 备忘录对象
     */
    public MementoClass get(int index) {
        return mementoList.get(index);
    }
}