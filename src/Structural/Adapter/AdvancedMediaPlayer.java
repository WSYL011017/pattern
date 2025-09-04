package Structural.Adapter;

/**
 * 被适配者 - 高级媒体播放器
 */
public interface AdvancedMediaPlayer {
    void playVlc(String fileName);
    void playMp4(String fileName);
}