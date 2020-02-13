package kr.or.ddit.basic;

import java.io.FileWriter;
import java.io.IOException;

import java.io.InputStreamReader;

public class T07_FileWriterTest {
	public static void main(String[] args) {
		// 사용자가 입력한 내용을 그래도 파일로 저장하기
		
		// 콘솔(표준입출력장치)과 연결된 입력용 문자 스트림 생성
		// InputStreamReader스트림 => 바이트기반 스트림을 문자기반 스트림으로
		//							변환해 주는 보조 스트림이다.
		InputStreamReader isr = new InputStreamReader(System.in);
		
		FileWriter fw = null; // 파일 출력용 문자 기반 스트림
		
		try {
			// 파일 출력용 문자 스트림 객체 생성
			fw = new FileWriter("e:/D_Other/testChar.txt");
			int c;
			
			System.out.println("아무거나 입력하세요.");
			
			// 콘솔에서 입력할 때 입력의 끝 표시는 Ctrl + Z 키를 누르면 된다.
			while((c=isr.read()) != -1) {
				fw.write(c); // 콘솔에서 입력받은 값을 파일에 출력하기
			}
			System.out.println("출력끝...");
			
			isr.close();
			fw.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
