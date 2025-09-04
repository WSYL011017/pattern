package Behavioral.Mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体中介者
 * 
 * 实现中介者接口，协调各个同事对象的通信
 */
public class ChatMediatorImpl implements ChatMediator {
    private List<User> users;

    /**
     * 构造函数
     */
    public ChatMediatorImpl() {
        this.users = new ArrayList<>();
    }

    @Override
    public void addUser(User user) {
        this.users.add(user);
    }

    @Override
    public void sendMessage(String msg, User user) {
        for (User u : this.users) {
            // 不向发送者发送消息
            if (u != user) {
                u.receive(msg);
            }
        }
    }
}