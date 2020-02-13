package kr.or.ddit.creational.builder;

public class Pepsi extends ColdDrink{

	@Override
	public String name() {
		return "펩시";
	}
	

	@Override
	public float price() {
		return 0.3f;
	}

}

