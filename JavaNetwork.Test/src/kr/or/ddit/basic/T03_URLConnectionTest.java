package kr.or.ddit.basic;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class T03_URLConnectionTest {
	public static void main(String[] args) throws IOException{
		// URLCOnnection => 애플리케이션과 URL간의 통신연결을 위한 추상클래스
		
		// 특정 서버(예: 네이버)의 정보와 파일 내용을 출력하는 예제
		URL url = new URL("https://www.naver.com/index.html");
		
		// Header정보 가져오기
		
		// URLConnection객체 구하기
		URLConnection urlCon = url.openConnection();
		
		System.out.println("Content-Type : " + urlCon.getContentType());
		System.out.println("Content Encoding : " + urlCon.getContentEncoding());
		System.out.println("Content : " + urlCon.getContent());
		
		// 전체 Header정보 출력하기
		Map<String, List<String>> headerMap = urlCon.getHeaderFields();
		
		// Header의 key값 구하기
		Iterator<String> iterator = headerMap.keySet().iterator();
		while(iterator.hasNext()) {
			String key = iterator.next();
			System.out.println(key + " : " + headerMap.get(key));
		}
		System.out.println("-------------------------------------------------");
		
		// 해당 호스트의 페이지 내용 가져오기
		
		// 파일을 읽어오기 위한 스트림 생성
		// 방법1 => URLConnection의 getInputStream메서드 이용하기
		// InputStream is = urlCon.getInputStream();
		// 방법2 => URL객체의 openStream()메서드 이용하기
		// InputStream is = url.openStream();
		InputStream is = url.openConnection().getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "utf-8");
		BufferedReader br = new BufferedReader(isr);
				
		// 내용 출력하기
		while(true) {
			String str = br.readLine();
			if (str == null) break;
			System.out.println(str);	
		}
		// 스트림 닫기
		br.close();
	}
}

