package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class T04_ByteArrayIOTest {
	public static void main(String[] args) {
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		
		byte[] temp = new byte[4]; //	자료를 읽을 때 사용할 배열
		
		ByteArrayInputStream input = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		try {
			//	available() = > 읽어올 수 있는 byte수를 반환
			while(input.available() > 0) {

				/*
				//	temp 배열 크기만큼 자료를 읽어와 temp배열에 저장함.
				input.read(temp);
				
				output.write(temp);	//	temp배열의 내용을 출력한다.
				*/
				
				
				// 실제 읽어온 byte수를 반환한다.
				int len = input.read(temp);
				
				// temp배열의 내용 중에서 0번째 부터 len개수만큼 출력한다.
				output.write(temp, 0, len);
				
				
				
				
				System.out.println("temp : " + Arrays.toString(temp));
			}
			System.out.println();
			
			outSrc = output.toByteArray();
		
			System.out.println("inSrc =>"+ Arrays.toString(inSrc));
			System.out.println("outSrc =>"+ Arrays.toString(outSrc));
			
			input.close();
			output.close();
		
		}catch(IOException e) {
			e.printStackTrace();
		}		
	}
}
