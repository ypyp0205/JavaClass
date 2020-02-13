package kr.or.ddit.basic;

import java.net.Socket;

public class T07_TcpClient {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 7777);
			System.out.println("서버에 연결되었습니다.");
			
			Sender sender = new Sender(socket);
			Receiver receiver = new Receiver(socket);
			
			sender.start();
			receiver.start();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
