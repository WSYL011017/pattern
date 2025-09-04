package Behavioral.ChainOfResponsibility;

/**
 * Chain of Responsibility Design Pattern测试类
 */
public class ChainOfResponsibilityTest {
    private static Logger getChainOfLoggers() {
        Logger errorLogger = new ErrorLogger(Logger.ERROR);
        Logger fileLogger = new FileLogger(Logger.DEBUG);
        Logger consoleLogger = new ConsoleLogger(Logger.INFO);

        errorLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(consoleLogger);

        return errorLogger;
    }

    public static void main(String[] args) {
        Logger loggerChain = getChainOfLoggers();

        loggerChain.logMessage(Logger.INFO, "This is an information.");
        System.out.println();

        loggerChain.logMessage(Logger.DEBUG, "This is a debug level information.");
        System.out.println();

        loggerChain.logMessage(Logger.ERROR, "This is an error information.");
    }
}