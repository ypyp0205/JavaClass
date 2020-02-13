package kr.or.ddit.creational.simplefactory;
// main이 있는 실행하는 곳.
public class FactoryPatternDemo {
	// 
	
	public static void main(String[] args) {
		
		// 팩토리 객체 생성.
		ShapeFactory shapeFactory = new ShapeFactory();
		
		// 심플팩토리 패턴 사용전에 객체생성하던 방법
		Shape shape0 = new Circle();
		shape0.draw();
		
		
		// 이 밑이 심플팩토리 패턴 으로 객체생성하던 방법
		
		Shape shape1 = shapeFactory.getShape("CIRCLE");
		shape1.draw();
		
		
		
		
		Shape shape2 = shapeFactory.getShape("RECTANGLE");
		shape2.draw();
		
		Shape shape3 = shapeFactory.getShape("SQUARE");
		shape3.draw();
		
	}
}
