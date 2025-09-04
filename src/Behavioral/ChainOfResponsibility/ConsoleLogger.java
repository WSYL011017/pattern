package Behavioral.ChainOfResponsibility;

/**
 * 具体处理者1 - 控制台日志记录器
 */
public class ConsoleLogger extends Logger {
    public ConsoleLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Standard Console::Logger: " + message);
    }
}