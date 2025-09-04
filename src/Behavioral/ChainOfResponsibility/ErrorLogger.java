package Behavioral.ChainOfResponsibility;

/**
 * 具体处理者2 - 错误日志记录器
 */
public class ErrorLogger extends Logger {
    public ErrorLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Error Console::Logger: " + message);
    }
}