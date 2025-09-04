package Behavioral.Mediator;

/**
 * 具体同事类
 * 
 * 实现User抽象类，提供具体的消息发送和接收功能
 */
public class UserImpl extends User {
    /**
     * 构造函数
     * 
     * @param mediator 中介者对象
     * @param name 用户名
     */
    public UserImpl(ChatMediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void send(String msg) {
        System.out.println(this.name + ": Sending Message=" + msg);
        mediator.sendMessage(msg, this);
    }

    @Override
    public void receive(String msg) {
        System.out.println(this.name + ": Received Message:" + msg);
    }
}