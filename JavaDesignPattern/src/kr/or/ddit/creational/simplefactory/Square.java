package kr.or.ddit.creational.simplefactory;

public class Square implements Shape{

	@Override
	public void draw() {
		System.out.println("정사각형 그리기 호출됨.");
		System.out.println();
	}

}
