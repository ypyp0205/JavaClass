package Tel_CV;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

import java.util.Map;
import java.util.Scanner;


public class TelCV {
	private Scanner scan;
	private Map<String, Phone> phoneBookMap;

	FileOutputStream fos = null;
	BufferedOutputStream bos = null;
	static ObjectOutputStream oos = null;

	FileInputStream fis = null;
	BufferedInputStream bis = null;
	ObjectInputStream ois = null;

	public TelCV() {
		scan = new Scanner(System.in);
		phoneBookMap = new HashMap<>();
	}

	// 메뉴를 출력하는 메서드
	public void displayMenu() {
		System.out.println();
		System.out.println("메뉴를 선택하세요.");
		System.out.println(" 1. 전화번호 등록");
		// System.out.println(" 2. 전화번호 수정");
		// System.out.println(" 3. 전화번호 삭제");
		// System.out.println(" 4. 전화번호 검색");
		System.out.println(" 5. 전화번호 전체 출력");
		System.out.println(" 0. 프로그램 종료");
		System.out.print(" 번호입력 >> ");
	}

	// 프로그램을 시작하는 메서드
	public void phoneBookStart() {
		System.out.println("===============================================");
		System.out.println("   전화번호 관리 프로그램(파일로 저장되지 않음)");
		System.out.println("===============================================");

		while (true) {

			displayMenu(); // 메뉴 출력

			int menuNum = scan.nextInt(); // 메뉴 번호 입력

			switch (menuNum) {
			case 1:
				insert(); // 등록
				break;
			case 2:
				update(); // 수정
				break;
			case 3:
				delete(); // 삭제
				break;
			case 4:
				search(); // 검색
				break;
			case 5:
				displayAll(); // 전체 출력
				break;
			case 0:
				System.out.println("프로그램을 종료합니다...");
				return;
			default:
				System.out.println("잘못 입력했습니다. 다시입력하세요.");
			} // switch문
		} // while문
	}

	/*
	 * 새로운 전화번호 정보를 등록하는 메소드 이미 등록된 사람은 등록되지 않는다.
	 */
	private void insert() {
		System.out.println("새롭게 등록할 전화번호 정보를 입력하세요.");
		System.out.print("이름 >> ");
		String name = scan.next();
		if (phoneBookMap.get(name) != null) {
			System.out.println(name + "씨는 이미 등록된 사람입니다.");
			return; // 메소드 종료
		}
		System.out.print("전화번호 >> ");
		String tel = scan.next();
		System.out.print("주소 >> ");
		scan.nextLine(); // 버퍼쓰레기값 삭제

		String addr = scan.nextLine();

		phoneBookMap.put(name, new Phone(name, tel, addr));
		System.out.println(name + "씨 등록 완료.");

		// ============================ 파일 생성 ====================================
		try {
			oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("e:/D_Other/TelBook/TelBook.txt")));
			
			oos.writeObject(phoneBookMap);
			
			oos.close();

		} catch (IOException e) {
			System.out.println("IO 발생");
			e.printStackTrace();
		} 

		// ===========================================================================
	}

	/*
	 * 전체 자료를 출력하는 메소드
	 */
	private void displayAll() {
		// ============================ 파일 생성 ====================================
		try {
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("e:/D_Other/TelBook/TelBook.txt")));

			Object obj = null;

			while ((obj = ois.readObject()) != null) {
				// 읽어온 데이터를 원래의 객체형으로 변환후 사용한다.
				Map<String, Phone> tel = (Map<String, Phone>) obj;
				System.out.println("==========================");
				for (String key : tel.keySet()) {
					Phone value = tel.get(key);
					System.out.println("이름 : " + value.getName());
					System.out.println("전화번호 : " + value.getTel());
					System.out.println("주소 : " + value.getAddr());
					System.out.println("==========================");
				}
				
			}
			ois.close();

		} catch (IOException e) {
			System.out.println("전체 출력 완료");
			// e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// ===========================================================================
	}

	private void update() {
		System.out.println();
		System.out.println("수정할 전화번호 정보를 입력하세요.");
		System.out.println("이름 >> ");
		String name = scan.next();

		if (phoneBookMap.get(name) == null) {
			System.out.println(name + "씨의 전화번호 정보가 없습니다.");
			return; // 메소드 종료
		}
		System.out.print("전화번호 >> ");
		String tel = scan.next();
		System.out.print("주소 >> ");
		scan.nextLine(); // 버퍼에 남아있을지 모르는 엔터키값 제거

		String addr = scan.nextLine();

		Phone p = new Phone(name, tel, addr);
		phoneBookMap.put(name, p); // 같은 key값에 데이터를 저장하면 value값이 변경된다.
		System.out.println(name + "씨의 정보 수정 완료...");

	}

	private void delete() {
		System.out.println();
		System.out.println("삭제할 전화번호 정보를 입력하세요");
		System.out.println("이름 >> ");
		String name = scan.next();

		// remove(key) => 삭제 성공하면 삭제된 value값을 반환하고 실패하면 null을 반환한다.
		if (phoneBookMap.remove(name) == null) {
			System.out.println(name + "씨는 등록된 사람이 아닙니다.");
			return;
		} else {
			System.out.println(name + "씨 정보를 삭제했습니다.");
		}
		System.out.println("삭제 작업 완료....");
	}

	/*
	 * 이름을 이용한 전화번호 정보를 검색하는 메소드
	 */
	private void search() {
		System.out.println();
		System.out.println("검색할 전화번호 정보를 입력세요.");
		System.out.println("이름 >> ");
		String name = scan.next();

		Phone p = phoneBookMap.get(name);
		if (p == null) {
			System.out.println(name + "씨 전화번호 정보가 없습니다.");
			return;
		} else {
			System.out.println(name + "씨의 전화번호 정보");
			System.out.println("이    름 : " + p.getName());
			System.out.println("전화번호 : " + p.getTel());
			System.out.println("주    소 : " + p.getAddr());
		}
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		new TelCV().phoneBookStart();
	}
}

class Phone implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1950313354647528617L;

	private String name;
	private String tel;
	private String addr;

	public Phone(String name, String tel, String addr) {
		super();
		this.name = name;
		this.tel = tel;
		this.addr = addr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Override
	public String toString() {
		return "Phone [name=" + name + ", tel=" + tel + ", addr=" + addr + "]";
	}

}
