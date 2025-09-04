package Behavioral.State;

/**
 * 具体状态B
 * 
 * 实现State接口，表示停止状态
 */
public class StopState implements StateInterface {
    @Override
    public void doAction(Context context) {
        System.out.println("Player is in stop state");
        context.setState(this);
    }

    public String toString() {
        return "Stop State";
    }
}