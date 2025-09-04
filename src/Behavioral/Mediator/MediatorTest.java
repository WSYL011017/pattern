package Behavioral.Mediator;

/**
 * Mediator模式测试类
 * 
 * 用于演示Mediator模式的使用方法
 */
public class MediatorTest {
    public static void main(String[] args) {
        ChatMediator mediator = new ChatMediatorImpl();
        
        User user1 = new UserImpl(mediator, "Alice");
        User user2 = new UserImpl(mediator, "Bob");
        User user3 = new UserImpl(mediator, "Charlie");
        User user4 = new UserImpl(mediator, "David");
        
        mediator.addUser(user1);
        mediator.addUser(user2);
        mediator.addUser(user3);
        mediator.addUser(user4);
        
        user1.send("Hi All");
    }
}