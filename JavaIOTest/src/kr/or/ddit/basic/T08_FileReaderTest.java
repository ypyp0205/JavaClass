package kr.or.ddit.basic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class T08_FileReaderTest {
	public static void main(String[] args) throws IOException {
		// 문자 기반 스트림을 이용한 파일 내용 읽기
		FileReader fr = null;
		
		// 문자단위의 입력을 담당하는 Reader형 객체 생성
		fr = new FileReader("e:/D_Other/testChar.txt");
		
		int c;
		
		while((c=fr.read()) != -1) {
			System.out.print((char)c);
		}
		
		fr.close();
	}
}
