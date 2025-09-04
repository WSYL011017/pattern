package Behavioral.Memento;

/**
 * Memento模式测试类
 * 
 * 用于演示Memento模式的使用方法
 */
public class MementoTest {
    public static void main(String[] args) {
        Originator originator = new Originator();
        CareTaker careTaker = new CareTaker();

        // 设置状态并保存
        originator.setState("State #1");
        careTaker.add(originator.saveStateToMemento());
        
        originator.setState("State #2");
        careTaker.add(originator.saveStateToMemento());
        
        originator.setState("State #3");
        careTaker.add(originator.saveStateToMemento());
        
        originator.setState("State #4");
        
        // 输出当前状态
        System.out.println("Current State: " + originator.getState());
        
        // 恢复到之前的状态
        originator.getStateFromMemento(careTaker.get(0));
        System.out.println("First saved State: " + originator.getState());
        
        originator.getStateFromMemento(careTaker.get(1));
        System.out.println("Second saved State: " + originator.getState());
        
        originator.getStateFromMemento(careTaker.get(2));
        System.out.println("Third saved State: " + originator.getState());
    }
}