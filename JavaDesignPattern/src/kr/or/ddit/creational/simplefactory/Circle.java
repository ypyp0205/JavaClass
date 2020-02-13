package kr.or.ddit.creational.simplefactory;

public class Circle implements Shape{

	@Override
	public void draw() {
		System.out.println("원 그리기 호출됨.");
		System.out.println();
	}

}
