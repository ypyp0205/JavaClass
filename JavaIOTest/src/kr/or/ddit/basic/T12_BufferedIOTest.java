package kr.or.ddit.basic;

import java.io.FileReader;
import java.io.IOException;

/**
 * 문자기반 Buffered스트림 예제
 * @author PC-21
 *
 */
public class T12_BufferedIOTest {
	public static void main(String[] args) {
		// 문자기반 Buffered스트림 사용 예제
		try {
			// 이클립스에서 만든 자바 프로그램이 실행되는 기본 위치는
			// 해당 '프로젝트폴더'가 기본위치가 된다.
			FileReader fr = new FileReader("./src/kr/or/ddit/basic/T11_BufferedIOTest.java");
			
			int c;
			while((c=fr.read()) != -1) {
				System.err.print((char)c);
			}
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
