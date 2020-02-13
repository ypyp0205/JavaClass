package kr.or.ddit.creational.builder;

public class VegBurger extends Burger{

	@Override
	public String name() {
		return "야채버거";
	}

	@Override
	public float price() {
		return 25.0f;
	}
	
}


