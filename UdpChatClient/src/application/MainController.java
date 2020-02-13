package application;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import thread.ChatClientRunnable;

public class MainController implements Initializable{

	@FXML private TextArea taChatList; 
	@FXML private TextField tfChat;
	
	private DatagramSocket socket = null;
	private byte[] msg = new byte[100];
	
	InetAddress serverAddress = null;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			socket = new DatagramSocket(); 
			socket.setSoTimeout(500);
			serverAddress = InetAddress.getByName("127.0.0.1"); // 서버주소 설정
			String chatName = showPromptWindow(); // 대화명을 입력받는다.
			
			// 대화명 전송
			DatagramPacket outPacket = 
					new DatagramPacket(chatName.getBytes(), chatName.getBytes().length, serverAddress, 7777);
			socket.send(outPacket);
			
			taChatList.setEditable(false); // 읽기 전용 속성설정
			
			
			ChatClientRunnable chatClientRunnable = new ChatClientRunnable(taChatList, socket);
			Thread thread = new Thread(chatClientRunnable);
			thread.start();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 대화명 입력창
	 * @return
	 */
	private String showPromptWindow(){
		
		String strResult = "";  // 대화가 저장될 변수 선언
		
		do{
			// '기본값'은 생략 가능
			TextInputDialog javaPrompt = new TextInputDialog("대화명"); 
			
			javaPrompt.setTitle("대화명 입력창"); // 창 제목
			javaPrompt.setHeaderText("대화명을 입력해 주세요."); // 출력 메시지
			
			// 창을 보이고 입력한 값을 읽어오기
			Optional<String> result = javaPrompt.showAndWait();
			
			// 입력한 값이 있는지 검사 (값 입력후 'OK'버튼 눌렀는지 검사)
			if(result.isPresent()) { 
				strResult = result.get(); // 값 읽어오기
			}
		}while(strResult.isEmpty());
		
		return strResult;
	}

	@FXML
	public void sendMessage(ActionEvent event){
		String message = tfChat.getText();
		DatagramPacket outPacket = new DatagramPacket(message.getBytes(), message.getBytes().length, serverAddress, 7777);
		try {
			socket.send(outPacket);
		} catch (IOException e) {
			e.printStackTrace();
		}
		tfChat.setText("");
	}
	
}
