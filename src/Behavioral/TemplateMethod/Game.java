package Behavioral.TemplateMethod;

/**
 * 抽象类 - 游戏抽象类
 */
public abstract class Game {
    public abstract void initialize();
    public abstract void startPlay();
    public abstract void endPlay();

    // 模板方法
    public final void play() {
        // 初始化游戏
        initialize();

        // 开始游戏
        startPlay();

        // 结束游戏
        endPlay();
    }
}