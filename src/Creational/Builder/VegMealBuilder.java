package Creational.Builder;

/**
 * 具体建造者1 - 素食餐点构建器
 */
public class VegMealBuilder implements MealBuilder {
    private Meal meal;

    public VegMealBuilder() {
        meal = new Meal();
    }

    @Override
    public void buildDrink() {
        meal.setDrink("Orange Juice");
    }

    @Override
    public void buildMainCourse() {
        meal.setMainCourse("Vegetable Burger");
    }

    @Override
    public void buildSide() {
        meal.setSide("Fries");
    }

    @Override
    public Meal getMeal() {
        return meal;
    }
}