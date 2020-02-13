package kr.or.ddit.basic;

import java.util.List;
import java.util.Scanner;

public class BoardMain {

/*

위의 테이블을 작성하고 게시판을 관리하는
다음 기능들을 구현하시오.

기능 구현하기 ==> 전체 목록 출력, 새글작성, 수정, 삭제, 검색 
 
------------------------------------------------------------

게시판 테이블 구조 및 시퀀스

create table jdbc_board(
    board_no number not null,  -- 번호(자동증가)
    board_title varchar2(100) not null, -- 제목
    board_writer varchar2(50) not null, -- 작성자
    board_date date not null,   -- 작성날짜
    board_content clob,     -- 내용
    constraint pk_jdbc_board primary key (board_no)
);
create sequence board_seq
    start with 1   -- 시작번호
    increment by 1; -- 증가값
		
----------------------------------------------------------

// 시퀀스의 다음 값 구하기
//  시퀀스이름.nextVal

 */

	private Scanner sc = new Scanner(System.in); 
	private IBoardService boaService = new BoardServiceImpl();
	
	public static void main(String[] args) {
		BoardMain board = new BoardMain();
		board.start();
	}
	
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 전체 목록 출력");
		System.out.println("  2. 새 글 작성");
		System.out.println("  3. 글 수정");
		System.out.println("  4. 글 삭제");
		System.out.println("  5. 글 검색");
		System.out.println("  6. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	public void start(){
		int choice;
		do{
			displayMenu(); //메뉴 출력
			choice = sc.nextInt(); // 메뉴번호 입력받기
			sc.nextLine();
			switch(choice){
				case 1 :  // 목록 출력
					boardList();
					break;
				case 2 :  // 글 작성
					writePost();
					break;
				case 3 :  // 글 수정
					modifyPost();
					break;
				case 4 :  // 글 삭제
					deletePost();
					break;
				case 5 :  // 글 검색
					searchPost();
					break;
				case 6 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=6);
	}
	
	private void boardList() {
		
		System.out.println();
		System.out.println("----------------------------------------------------");
		System.out.println("번호\t제목\t작성자\t작성날짜\t\t내용"); 
		System.out.println("----------------------------------------------------");
		
		List<BoardVO> bList = boaService.getAllBoardList();
		
		if(bList.size() == 0) {
			System.out.println("출력할 게시글 정보가 없습니다");}
		else {
			for(BoardVO bv : bList) {
				System.out.println(bv.getBoard_no()+"\t"+
									bv.getBoard_title()+"\t"+
									bv.getBoard_writer()+"\t"+
									bv.getBoard_date()+"\t\t"+
									bv.getBoard_content());
			}
		}
		System.out.println("----------------------------------------------------");
		System.out.println("출력 작업 완료");
	}
	
	
	private void writePost() {
		System.out.println();
		System.out.println("글 제목을 적어주세요 ");
		String title = sc.nextLine();
		System.out.println("글 작성자를 적어주세요 ");
		String writer = sc.nextLine();
		System.out.println("글 내용을 적어주세요 ");
		String content = sc.nextLine();
		
		BoardVO bv = new BoardVO();
		bv.setBoard_title(title);
		bv.setBoard_writer(writer);
		bv.setBoard_content(content);

		int check = boaService.insertBoard(bv);
		
		if(check > 0) {System.out.println("게시글 추가 성공...");}
		else {System.out.println("게시글 추가 실패!!!");}
	}
	
	
	private void modifyPost() {
		System.out.println();
		System.out.print("수정할 글의 게시번호를 적어주세요 : ");
		int boardno = sc.nextInt();
		
		boolean check = boaService.getBoard(boardno);
		if(!check) {System.out.println("게시글이 존재하지 않습니다!!!"); return;}
		
		System.out.println();
		sc.nextLine();
		System.out.println("수정할 글의 제목을 적어주세요 ");
		String title = sc.nextLine();
		System.out.println("수정할 글의 작성자를 적어주세요 ");
		String writer = sc.nextLine();
		System.out.println("수정할 글의 내용을 적어주세요 ");
		String content = sc.nextLine();
		
		BoardVO bv = new BoardVO();
		bv.setBoard_title(title);
		bv.setBoard_writer(writer);
		bv.setBoard_content(content);
		bv.setBoard_no(boardno);
		
		int check2 = boaService.updateBoard(bv);
		
		if(check2 > 0) {System.out.println("게시글 수정 성공...");}
		else {System.out.println("게시글 수정 실패!!!");}
		
	}
	
	
	private void deletePost() {
		System.out.println();
		System.out.print("삭제할 글의 게시번호를 적어주세요 : ");
		int boardno = sc.nextInt();
		
		int check = boaService.deletBoard(boardno);
		
		if(check > 0) {System.out.println("게시글 삭제 성공...");}
		else {System.out.println("게시글 삭제 실패!!!");}
	}
	
	
	public void searchPost() {
		
		System.out.println();
		System.out.println("검색할 게시물 정보를 입력하세요");
		System.out.println("정보를 모를 경우 엔터로 칸을 비워주세요");
		System.out.print("게시글 번호 : ");
		String bno = sc.nextLine().trim();
		System.out.print("게시글 제목 : ");
		String title = sc.nextLine().trim();
		System.out.print("게시글 작성자 : ");
		String writer = sc.nextLine().trim();
		System.out.print("게시글 내용 일부 : ");
		String content = sc.nextLine().trim();
		
		int boardno;
		if(bno==null||bno.equals("")) {boardno = 0;}
		else{boardno = Integer.parseInt(bno);}
		
		BoardVO bv = new BoardVO();
		bv.setBoard_no(boardno);
		bv.setBoard_title(title);
		bv.setBoard_writer(writer);
		bv.setBoard_content(content);
		
		List<BoardVO> boaList = boaService.getSearchBoard(bv);
		
		System.out.println();
		System.out.println("----------------------------------------------------");
		System.out.println("번호\t제목\t작성자\t작성날짜\t\t내용"); 
		System.out.println("----------------------------------------------------");
		
		if(boaList.size() == 0) {
			System.out.println("출력할 게시글 정보가 없습니다");
		}else {
			for(BoardVO bv2 : boaList) {
				System.out.println(bv2.getBoard_no()+"\t"+
									bv2.getBoard_title()+"\t"+
									bv2.getBoard_writer()+"\t"+
									bv2.getBoard_date()+"\t\t"+
									bv2.getBoard_content());
			}
		}
		System.out.println("----------------------------------------------------");
		System.out.println("출력 작업 완료");
	}
	
	
}
