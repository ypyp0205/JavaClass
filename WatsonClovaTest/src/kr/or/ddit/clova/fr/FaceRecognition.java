package kr.or.ddit.clova.fr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class FaceRecognition {

	// 애플리케이션 클라이언트 아이디 값
	//private static final String CLIENT_ID = "HPhMS5z3Rlc1P1bvVauw";
	private static final String CLIENT_ID = "m63qHEiV7IUpvuugNyTb";
	// 애플리케이션 클라이언트 시크릿값
	//private static final String CLIENT_SECRET = "60fJyesyCs";
	private static final String CLIENT_SECRET = "_cwFyKUoM3";
	
	// 서비스 연결 커넷션
	private HttpURLConnection con;
	
	/**
	 * 얼굴 인식 URL 연결 설정
	 */
	public void setConnection() {
		String apiURL = "https://openapi.naver.com/v1/vision/celebrity"; // 유명인 얼굴 인식

		//String apiURL = "https://openapi.naver.com/v1/vision/face"; // 얼굴 감지
		
		URL url;
		try {
			url = new URL(apiURL);
			con = (HttpURLConnection) url.openConnection();
			con.setUseCaches(false);
			con.setDoOutput(true);
			con.setDoInput(true);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 파일 전송 설정
	 */
	public void setFileTransfer() {
		// multipart request
		String boundary = "---" + System.currentTimeMillis() + "---";
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
		con.setRequestProperty("X-Naver-Client-Id", CLIENT_ID);
		con.setRequestProperty("X-Naver-Client-Secret", CLIENT_SECRET);
		try {
			OutputStream outputStream = con.getOutputStream();
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true);
			String LINE_FEED = "\r\n";
			// file 추가
			String imgFile = FaceRecognition.class.getResource("888.png").getPath();
			//String imgFile = FaceRecognition.class.getResource("Actress.jpg").getPath();
			
			File uploadFile = new File(imgFile);
			String paramName = "image"; // 파라미터명은 image로 지정
			String fileName = uploadFile.getName();
			writer.append("--" + boundary).append(LINE_FEED);
			writer.append("Content-Disposition: form-data; name=\"" + paramName + "\"; filename=\"" + fileName + "\"")
			.append(LINE_FEED);
			writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(fileName)).append(LINE_FEED);
			writer.append(LINE_FEED);
			writer.flush();
			FileInputStream inputStream = new FileInputStream(uploadFile);
			byte[] buffer = new byte[4096];
			int bytesRead = -1;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
			outputStream.flush();
			inputStream.close();
			writer.append(LINE_FEED).flush();
			writer.append("--" + boundary + "--").append(LINE_FEED);
			writer.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 응답수신
	 */
	public void receiveResponse() {
		try {
			BufferedReader br = null;
			int responseCode = con.getResponseCode();
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
				System.out.println("error!!!!!!! responseCode= " + responseCode);
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			}
			String inputLine;
			if (br != null) {
				StringBuffer response = new StringBuffer();
				while ((inputLine = br.readLine()) != null) {
					response.append(inputLine);
				}
				br.close();
				System.out.println(response.toString());
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		FaceRecognition faceRecognition = new FaceRecognition();
		faceRecognition.setConnection();
		faceRecognition.setFileTransfer();
		faceRecognition.receiveResponse();
	}
}
