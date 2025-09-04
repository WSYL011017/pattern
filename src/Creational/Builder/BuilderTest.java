package Creational.Builder;

/**
 * Builder Design Pattern测试类
 */
public class BuilderTest {
    public static void main(String[] args) {
        // 创建素食餐点
        MealBuilder vegMealBuilder = new VegMealBuilder();
        MealDirector vegMealDirector = new MealDirector(vegMealBuilder);
        Meal vegMeal = vegMealDirector.construct();
        System.out.println("Veg Meal");
        vegMeal.showItems();

        System.out.println();

        // 创建非素食餐点
        MealBuilder nonVegMealBuilder = new NonVegMealBuilder();
        MealDirector nonVegMealDirector = new MealDirector(nonVegMealBuilder);
        Meal nonVegMeal = nonVegMealDirector.construct();
        System.out.println("Non-Veg Meal");
        nonVegMeal.showItems();
    }
}