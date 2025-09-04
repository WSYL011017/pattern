package Creational.Builder;

/**
 * 具体建造者2 - 非素食餐点构建器
 */
public class NonVegMealBuilder implements MealBuilder {
    private Meal meal;

    public NonVegMealBuilder() {
        meal = new Meal();
    }

    @Override
    public void buildDrink() {
        meal.setDrink("Coke");
    }

    @Override
    public void buildMainCourse() {
        meal.setMainCourse("Chicken Burger");
    }

    @Override
    public void buildSide() {
        meal.setSide("Onion Rings");
    }

    @Override
    public Meal getMeal() {
        return meal;
    }
}