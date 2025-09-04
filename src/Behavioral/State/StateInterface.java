package Behavioral.State;

/**
 * 状态接口
 * 
 * 定义一个接口以封装与Context的一个特定状态相关的行为
 */
public interface StateInterface {
    /**
     * 执行与状态相关的行为
     * 
     * @param context 上下文对象
     */
    void doAction(Context context);
}