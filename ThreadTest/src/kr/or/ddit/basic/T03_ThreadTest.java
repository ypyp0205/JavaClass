package kr.or.ddit.basic;

public class T03_ThreadTest {
	public static void main(String[] args) {
		// 쓰레드의 수행시간 체크해 보기
		Thread th = new Thread(new MyRunner());

		// 1970년 1월 1일 0시 0초(표준시)로부터 경과한 시간을
		// 밀리세컨드(1/1000초)단위로 나타낸다.
		long startTime = System.currentTimeMillis();

		th.start(); // 쓰레드 작업 시작

		try {
			th.join(); // 현재 실행중인 쓰레드에서 작업중인 쓰레드 (지금은 th쓰레드)
					   // 가 종료될 때까지 기다린다.
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		long endTime = System.currentTimeMillis();

		System.out.println("경과 시간 : 0." + (endTime - startTime) + "초");

	}
}

/**
 * 1~1000000000 까지의 합계를 구하는 쓰레드
 * 
 * @author PC-21
 * 
 */
class MyRunner implements Runnable {
	@Override
	public void run() {
		long sum = 0;
		for (long i = 0; i <= 1000000000; i++) {
			sum += i;
		}
		System.out.println("합계 : " + sum);
	}
}
