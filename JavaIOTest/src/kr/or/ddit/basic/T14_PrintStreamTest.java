package kr.or.ddit.basic;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * 프린터 기능 제공 보조 스트림
 * @author PC-21
 *
 */
public class T14_PrintStreamTest {
	public static void main(String[] args) throws IOException {
		FileOutputStream fout = new FileOutputStream("e:/D_Other/print.txt");
		FileOutputStream fout2 = new FileOutputStream("e:/D_Other/print2.txt");
		
		PrintStream out = new PrintStream(fout);
		out.print("안녕하세요. PrintStream입니다. \r\n");
		out.println("안녕하세요. PrintStream입니다. 2");
		out.println("안녕하세요. PrintStream입니다. 3");
		
		out.close();
		
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(fout2, "UTF-8"));
		writer.print("안녕하세요. PrintWriter입니다. \r\n");
		writer.println("안녕하세요. PrintWriter입니다. 2");
		writer.println("안녕하세요. PrintWriter입니다. 3");
		
		writer.close();
	}
	
}
