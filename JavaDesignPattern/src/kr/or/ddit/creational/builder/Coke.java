package kr.or.ddit.creational.builder;

public class Coke extends ColdDrink{

	@Override
	public String name() {
		
		return "코카콜라";
	}

	@Override
	public float price() {
		return 0.5f;
	}

}

