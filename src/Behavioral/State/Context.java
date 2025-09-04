package Behavioral.State;

/**
 * 上下文类
 * 
 * 定义客户感兴趣的接口，维护一个ConcreteState子类的实例，这个实例定义当前状态
 */
public class Context {
    private StateInterface state;

    /**
     * 构造函数
     */
    public Context() {
        state = null;
    }

    /**
     * 设置状态
     * 
     * @param state 状态对象
     */
    public void setState(StateInterface state) {
        this.state = state;
    }

    /**
     * 获取状态
     * 
     * @return 状态对象
     */
    public StateInterface getState() {
        return state;
    }
}