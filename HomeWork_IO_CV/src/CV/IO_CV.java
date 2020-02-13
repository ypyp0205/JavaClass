package CV;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
	'e:/D_Other/'에 있는 'pome.PNG'파일을
	'복사본_pome.PNG'로 복사하는 프로그램을 작성.
 */

public class IO_CV {
	public static void main(String[] args) throws IOException {
		
		FileInputStream fin = new FileInputStream("e:/D_Other/pome.PNG");
		FileOutputStream fout = new FileOutputStream("e:/D_Other/복사본_pome.PNG");
		BufferedOutputStream bout = new BufferedOutputStream(fout);		

		int c;
		
		while((c = fin.read()) != -1) {
			bout.write(c);
		}
		bout.close();
		System.out.println("복사완료");
		
		fin.close();
		fout.close();
	}
}

