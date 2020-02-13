package kr.or.ddit.creational.builder;

public class MealBuilder {
	
	
	// 채식주의자 식사 준비
	public Meal prepareVegMeal() {
		Meal meal = new Meal();
		meal.addItem(new VegBurger());
		meal.addItem(new Coke());
		
		return meal;
	}
	
	// 비채식주의자 식사 준비
	public Meal prepareNonVegMeal() {
		Meal meal = new Meal();
		meal.addItem(new ChickenBurger());
		meal.addItem(new Pepsi());
		
		return meal;
	}
	
}
