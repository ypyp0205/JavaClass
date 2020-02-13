package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HorseRaceExam {
	public static int rank = 1; // 현재 말의 순위
	public static List<Horse> hList = new ArrayList<Horse>();

	public static void main(String[] args) {
		
		hList.add(new Horse("01번말"));
		hList.add(new Horse("02번말"));
		hList.add(new Horse("03번말"));
		hList.add(new Horse("04번말"));
		hList.add(new Horse("05번말"));
		hList.add(new Horse("06번말"));
		hList.add(new Horse("07번말"));
		hList.add(new Horse("08번말"));
		hList.add(new Horse("09번말"));
		hList.add(new Horse("10번말"));

		HorsePositionDisplay hpd = new HorsePositionDisplay();
		hpd.start();

		for (int i = 0; i < hList.size(); i++) {
			hList.get(i).start();
		}

		try {
			hpd.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		System.out.println();
		System.out.println("지금 경마가 종료되었습니다.!!!");
		System.out.println();

		Collections.sort(hList); // 리스트를 순위 오름차순으로 정렬하기
		
		System.out.println("================");
		System.out.println("   경마 순위    ");
		System.out.println("================");
		for (int i = 0; i < hList.size(); i++) {
			System.out.println(hList.get(i).getHorseRank() + "등" + " == >" + hList.get(i).getHorseName());
		}

	}
}

class Horse extends Thread implements Comparable<Horse> {
	private String horseName; // 말이름
	private int horseRank; // 순위
	private int horsePosi; // 위치

	/**
	 * 생성자
	 * 
	 * @param horseName 경주말 이름
	 */
	public Horse(String horseName) {
		this.horseName = horseName;
	}

	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public int getHorseRank() {
		return horseRank;
	}

	public void setHorseRank(int horseRank) {
		this.horseRank = horseRank;
	}

	public int getHorsePosi() {
		return horsePosi;
	}

	private void setHorsePosi(int horsePosi) {
		this.horsePosi = horsePosi;
	}
	
	@Override
	public int compareTo(Horse o) {
		return Integer.compare(horseRank, o.getHorseRank()); // 순위 오름차순으로 정렬하도록 함.
	}

	@Override
	public void run() {
		for (int i = 0; i < 50; i++) { 
			try {
				Thread.sleep((int) (Math.random() * 500)); // 0~49 까지의 난수 발생(구간 사이의 딜레이를 주기 위함)
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			setHorsePosi(i); // 각 구간으로 말의 위치 이동
		}
		this.horseRank = HorseRaceExam.rank;
		HorseRaceExam.rank++;
	}
}


/**
 * 
 * @author sem
 *
 */
class HorsePositionDisplay extends Thread {
	
	/**
	 * 화면 출력 정리를 위한 메서드
	 */
	public void clear() {
		for (int i = 0; i < 27; i++) {
			System.out.println();
		}
	}

	@Override
	public void run() {
		String horseCourse = "--------------------------------------------------"; // 50구간
		
		while (true) {
			clear(); // 화면지우기
			int finishedCnt = 0; // 도착한 말의 수
			System.out.println("지금 경마가 시작되었습니다.!!!");
			System.out.println("==========================================================");
			System.out.println();
			for (int i = 0; i < HorseRaceExam.hList.size(); i++) {
				if (HorseRaceExam.hList.get(i).getHorsePosi() != 49) {
					System.out.print(HorseRaceExam.hList.get(i).getHorseName() + " : ");
					System.out.print(horseCourse.substring(0, HorseRaceExam.hList.get(i).getHorsePosi()) + ">");
					System.out.println(horseCourse.substring(HorseRaceExam.hList.get(i).getHorsePosi(), 49));
				} else {
					System.out.print(HorseRaceExam.hList.get(i).getHorseName() + " : ");
					System.out.print(horseCourse.substring(0, HorseRaceExam.hList.get(i).getHorsePosi()) + "도착");
					System.out.println();
					finishedCnt++;
				}
			}

			if (finishedCnt == 10) { // 모든 경주말이 도착한 경우 ...
				return; // 쓰레드 종료
			}
			
			try {
				Thread.sleep(1000); // 1초마다 화면 출력
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}
