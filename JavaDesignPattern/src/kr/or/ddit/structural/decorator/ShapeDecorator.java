package kr.or.ddit.structural.decorator;

public abstract class ShapeDecorator implements Shape{
	
	protected Shape decoratedSgape;
	
	public ShapeDecorator(Shape decoratedShape) {
		this.decoratedSgape = decoratedShape;
	}
	
}

