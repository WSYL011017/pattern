package Behavioral.Memento;

/**
 * 发起人类
 * 
 * 创建并在Memento对象中存储状态
 */
public class Originator {
    private String state;

    /**
     * 设置状态
     * 
     * @param state 状态值
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 获取状态
     * 
     * @return 当前状态
     */
    public String getState() {
        return state;
    }

    /**
     * 保存状态到备忘录
     * 
     * @return 包含当前状态的备忘录对象
     */
    public MementoClass saveStateToMemento() {
        return new MementoClass(state);
    }

    /**
     * 从备忘录恢复状态
     * 
     * @param memento 包含状态的备忘录对象
     */
    public void getStateFromMemento(MementoClass memento) {
        state = memento.getState();
    }
}