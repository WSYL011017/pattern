package Behavioral.State;

/**
 * 具体状态A
 * 
 * 实现State接口，表示开始状态
 */
public class StartState implements StateInterface {
    @Override
    public void doAction(Context context) {
        System.out.println("Player is in start state");
        context.setState(this);
    }

    public String toString() {
        return "Start State";
    }
}