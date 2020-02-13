package kr.or.ddit.member;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

/*
	회원정보를 관리하는 프로그램을 작성하는데 
	아래의 메뉴를 모두 구현하시오. (CRUD기능 구현하기)
	(DB의 MYMEMBER테이블을 이용하여 작업한다.)
	
	* 자료 삭제는 회원ID를 입력 받아서 삭제한다.
	
	예시메뉴)
	----------------------
		== 작업 선택 ==
		1. 자료 입력			---> insert
		2. 자료 삭제			---> delete
		3. 자료 수정			---> update
		4. 전체 자료 출력		---> select
		5. 작업 끝
	----------------------
	 
	   
// 회원관리 프로그램 테이블 생성 스크립트 
create table mymember(
    mem_id varchar2(8) not null,  -- 회원ID
    mem_name varchar2(100) not null, -- 이름
    mem_tel varchar2(50) not null, -- 전화번호
    mem_addr varchar2(128)    -- 주소
);

*/
public class MemberMain {
	
	private IMemberService memberService;

	public MemberMain() {
		memberService = new MemberServiceImpl();
	}
	
	
	private Scanner scan = new Scanner(System.in); 
	
	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 자료 입력");
		System.out.println("  2. 자료 삭제");
		System.out.println("  3. 자료 수정");
		System.out.println("  4. 전체 자료 출력");
		System.out.println("  5. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	/**
	 * 프로그램 시작메서드
	 */
	public void start(){
		int choice;
		do{
			displayMenu(); //메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch(choice){
				case 1 :  // 자료 입력
					insertMember();
					break;
				case 2 :  // 자료 삭제
					deleteMember();
					break;
				case 3 :  // 자료 수정
					updateMember();
					break;
				case 4 :  // 전체 자료 출력
					displayMemberAll();			
					break;
					
				case 5 :  // 전체 자료 출력
					searchMember();			
					break;
				case 6 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=6);
	}
	/**
	 	회원정보를 삭제하는 메서드(입력받은 회원 ID를 이용하여 삭제한다.
	 */
	
	private void deleteMember() {
		System.out.println();
		System.out.println("삭제할 회원 ID를 입력하세요 >> ");
		String memId = scan.next();
		
		int cnt = memberService.deleteMember(memId);
		
		if(cnt > 0) {
			System.out.println(memId +"회원 삭제 성공...");
		}else {
			System.out.println(memId +"회원 삭제 실패!!!");
		}
	}

	/**
	 	회원 자료를 수정하는 메서드
	 */
	
	private void updateMember() {
		System.out.println();
		String memId = "";
		boolean chk = true;
		
		do {
			System.out.print("수정할 회원 ID를 입력하세요.>> ");
			memId = scan.next();
		
			chk = getMember(memId);
			
			if(chk == false) {
				System.out.println(memId + "회원은 없는 회원입니다.");
				System.out.println(" 수정할 자료가 없으니 다시 입력하세요.");
			}
		}while(chk == false);
		System.out.println("수정할 내용을 입력하세요.");
		System.out.println("새로운 회원 이름 >> ");
		String memName = scan.next();
		
		System.out.println("새로운 회원 번호 >> ");
		String memTel = scan.next();
		
		scan.nextLine();//버퍼 비우기
		System.out.println("새로운 회원 주소>> ");
		String memAddr = scan.nextLine();
		
		MemberVO mv = new MemberVO();
		mv.setMem_id(memId);
		mv.setMem_name(memName);
		mv.setMem_tel(memTel);
		mv.setMem_addr(memAddr);
		
		int cnt = memberService.updateMember(mv);
		
		if(cnt > 0) {
			System.out.println(memId +"회원 수정 작업 성공...");
		}else {
			System.out.println(memId +"회원 수정 작업 실패!!!");
		}
		
	}

	/**
	 * 전체 회원을 출력하는 메서드
	 */
	
	private void displayMemberAll() {
		System.out.println();
		System.out.println("-----------------------------------------------");
		System.out.println(" ID\t이름\t전화번호\t주소");
		System.out.println("-----------------------------------------------");
				
		List<MemberVO>memList = memberService.getAllMemberList();
		if(memList.size() == 0) {
			System.out.println("출력할 회원정보가 없습니다.");
		}else {
			for(MemberVO mv : memList) {
				System.out.println(mv.getMem_id()+"\t"+mv.getMem_name()
				+"\t"+mv.getMem_tel()+"\t"+mv.getMem_addr());
			}			
		}
	}

	/**
	 	회원 추가하는 메서드
	 */
	
	private void insertMember() {
		boolean chk = false;	// 중복체크의 목적을 가진다. check==chk
		String memId = "";
		do {
			System.out.println();
			System.out.println("추가할 회원 정보를 입력하세요");
			System.out.println("회원 ID >>");
			memId = scan.next();
			
			chk = getMember(memId);	
			if(chk) {
				System.out.println("회원 ID가 "+memId+"인 회원은 이미 존재합니다");
				System.out.println("다시 입력하세요.");
			}
			
		}while(chk == true);
		
		System.out.println("회원 이름>> ");
		String memName = scan.next();
		
		System.out.println("회원 전화번호>> ");
		String memTel = scan.next();
		
		scan.nextLine(); // 버퍼 비우기
		System.out.print("회원 주소>> ");
		String memAddr = scan.nextLine();
		
		// 입력받은 정보를 VO객체에 넣는다.
		MemberVO mv = new MemberVO();
		mv.setMem_id(memId);
		mv.setMem_name(memName);
		mv.setMem_tel(memTel);
		mv.setMem_addr(memAddr);
		
		int cnt = memberService.insertMember(mv);
		
		if(cnt > 0) {
			System.out.println(memId +"회원 추가 작업 성공...");
		}else {
			System.out.println(memId +"회원 추가 작업 실패!!!");
		}
	}
	
	/**
	 * 회원 ID를 이용하여 회원이 있는지 알려주는 메서드
	 * @param memId
	 * @return true : 이미 존재함, false: 신규회원
	 */
	private boolean getMember(String memId) {
		
		boolean chk= false;				
		
		memberService.getMember(memId);
		
		return chk;
	}

	public void searchMember() {
		// 검색할 회원 ID, 회원 이름, 전화번호, 주소등을 입력하면
		// 입력한 정보만 사용하여 검색하는 기능을 구현하시오.
		// 주소는 입력한 값이 포함만 되어도 검색되도록 한다.
		// 입력을 하지 않을 자료는 엔터키로 다음 입력으로 넘긴다.
		scan.nextLine(); // 입력버퍼 지우기
		System.out.println();
		System.out.println("검색할 정보를 입력하세요.");
		System.out.println("회원 ID >>");
		String memId = scan.nextLine().trim();
		
		System.out.println("새로운 회원 이름 >> ");
		String memName = scan.nextLine().trim();
		
		System.out.println("새로운 회원 번호 >> ");
		String memTel = scan.nextLine().trim();
		
		scan.nextLine();//버퍼 비우기
		System.out.println("새로운 회원 주소>> ");
		String memAddr = scan.nextLine().trim();
		
		MemberVO mv = new MemberVO();
		mv.setMem_id(memId);
		mv.setMem_name(memName);
		mv.setMem_tel(memTel);
		mv.setMem_addr(memAddr);
		
		List<MemberVO> memList =
				memberService.getSearchMember(mv);
		System.out.println();
		System.out.println("-----------------------------------------------");
		System.out.println(" ID\t이름\t전화번호\t주소");
		System.out.println("-----------------------------------------------");
	
		if(memList.size() == 0) {
			System.out.println("출력할 회원정보가 없습니다.");
		}else {
			for(MemberVO mv2 : memList) {
				System.out.println(mv2.getMem_id()+"\t"+mv2.getMem_name()
				+"\t"+mv2.getMem_tel()+"\t"+mv2.getMem_addr());
			}			
		}
	}
	

	public static void main(String[] args) {
		MemberMain memObj = new MemberMain();
		memObj.start();
	}

}






