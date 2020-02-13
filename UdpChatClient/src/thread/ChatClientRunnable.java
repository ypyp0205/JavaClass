package thread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketTimeoutException;

import javafx.scene.control.TextArea;
import application.MainController;

public class ChatClientRunnable implements Runnable{
	
	private TextArea taChatList;
	private DatagramSocket socket = null;
	private byte[] msg = new byte[1000];
	
	public ChatClientRunnable(TextArea textArea_chatList, DatagramSocket socket){
		this.taChatList = textArea_chatList;
		this.socket = socket;
	}
	
	public void run(){
		while(true){
			DatagramPacket inPacket = new DatagramPacket(msg, msg.length);
			try {
				socket.receive(inPacket); // 이 메서드는 패킷을 수신할때까지 BLOCK 됨. 받은 데이터는 inPacket 안에..
				System.out.println(new String(inPacket.getData()));
				taChatList.appendText(new String(inPacket.getData())+"\n");
			}catch (SocketTimeoutException e){
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
