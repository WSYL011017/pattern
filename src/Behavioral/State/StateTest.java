package Behavioral.State;

/**
 * State模式测试类
 * 
 * 用于演示State模式的使用方法
 */
public class StateTest {
    public static void main(String[] args) {
        Context context = new Context();

        StartState startState = new StartState();
        startState.doAction(context);

        System.out.println(context.getState().toString());

        StopState stopState = new StopState();
        stopState.doAction(context);

        System.out.println(context.getState().toString());
    }
}