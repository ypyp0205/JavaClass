package ko.or.ddit.basic;

public class Service {
	
	@PrintAnnotation()
	public void method1() {
		System.out.println("메서드1에서 출력되었습니다.");
	}
	
	@PrintAnnotation(value="%")   // => ("%")로 대체 가능 : value한가지만 들어갈떄는 생략가능하다
	public void method2() {
		System.out.println("메서드1에서 출력되었습니다.");
	}
	
	@PrintAnnotation(value="#", count=25)
	public void method3() {
		System.out.println("메서드1에서 출력되었습니다.");
	}
		
}
