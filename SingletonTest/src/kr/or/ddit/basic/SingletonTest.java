package kr.or.ddit.basic;

public class SingletonTest {
	public static void main(String[] args) {
		//		MySingleton test1 = new MySingleton(); <-이건 에러
		
		//	getInstance() 인스턴스 메서드를 사용하여 객체 생성
		
	MySingleton test2 = MySingleton.getInstance();
	test2.displayText();
	
	MySingleton test3 = MySingleton.getInstance();
	
	System.out.println("test2 =>"+ test2);
	System.out.println("test3 =>"+ test3);
	}
}
