package kr.or.ddit.structural.decorator;

public class RedShapeDecorator extends ShapeDecorator{

	public RedShapeDecorator(Shape decoratedShape) {
		super(decoratedShape);
	}
	
	@Override
	public void draw() {
			decoratedSgape.draw();  // 기본기능
			setRedBorder(decoratedSgape); // 경계선 빨간색으로 칠하기 기능 추가
		
	}
	
	private void setRedBorder(Shape decoratedShape) {
		System.out.println("경계선 색을 빨간색으로 칠하기");
		
	}

}
