package Behavioral.Mediator;

/**
 * 抽象同事类
 * 
 * 定义同事对象的通用接口和属性
 */
public abstract class User {
    protected ChatMediator mediator;
    protected String name;

    /**
     * 构造函数
     * 
     * @param mediator 中介者对象
     * @param name 用户名
     */
    public User(ChatMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    /**
     * 发送消息
     * 
     * @param msg 消息内容
     */
    public abstract void send(String msg);
    
    /**
     * 接收消息
     * 
     * @param msg 消息内容
     */
    public abstract void receive(String msg);
}