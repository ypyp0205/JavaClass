package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class T03_ByteArrayIOTest {
	public static void main(String[] args) {
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		
		//	스트림 선언 및 객체 생성
		ByteArrayInputStream input = null; // 스트림 선언
		input = new ByteArrayInputStream(inSrc); // 객체 생성
		
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		int data; // 읽어온 자료를 저장할 함수
		
		//	read()메서드 => byte단위로 자료를 읽어와 int형으로 반환한다.
		//						 => 더 이상 읽어올 자료가 없으면 -1을 반환한다.
		while((data = input.read()) != -1) {
			output.write(data); //출력하기
		}
		
		//	출력된 스트림 값들을 배열로 변환해서 반환하는 메서드
		outSrc = output.toByteArray();
		
		System.out.println("inSrc =>"+ Arrays.toString(inSrc));
		System.out.println("outSrc =>"+ Arrays.toString(outSrc));
		
		try {
			input.close();
			output.close();
		}catch(IOException e) {
			e.printStackTrace();
		}		
	}
}
