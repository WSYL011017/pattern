package Behavioral.Mediator;

/**
 * 中介者接口
 * 
 * 定义一个中介对象接口，用于同事对象之间的通信
 */
public interface ChatMediator {
    /**
     * 发送消息
     * 
     * @param msg 消息内容
     * @param user 发送消息的用户
     */
    void sendMessage(String msg, User user);
    
    /**
     * 添加用户
     * 
     * @param user 要添加的用户
     */
    void addUser(User user);
}