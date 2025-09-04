package Behavioral.ChainOfResponsibility;

/**
 * 具体处理者3 - 文件日志记录器
 */
public class FileLogger extends Logger {
    public FileLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("File::Logger: " + message);
    }
}