package Behavioral.Memento;

/**
 * 备忘录类
 * 
 * 负责存储Originator对象的内部状态
 */
public class MementoClass {
    private String state;

    /**
     * 构造函数
     * 
     * @param state 需要保存的状态
     */
    public MementoClass(String state) {
        this.state = state;
    }

    /**
     * 获取保存的状态
     * 
     * @return 状态字符串
     */
    public String getState() {
        return state;
    }
}