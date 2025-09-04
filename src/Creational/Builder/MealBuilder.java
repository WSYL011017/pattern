package Creational.Builder;

/**
 * 抽象建造者 - 餐点构建器接口
 */
public interface MealBuilder {
    void buildDrink();
    void buildMainCourse();
    void buildSide();
    Meal getMeal();
}