package thread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import vo.ClientVO;

public class ChatServerRunnable implements Runnable{

	//UDP연결을 담당할 소켓
	private DatagramSocket socket = null;
	//클라이언트의 ip주소 리스트 - MainController의 clientList와 같은 주소를 바라보고 있음
	private ObservableList<String> clientList; //사용자목록
	private Map<String, ClientVO> clientMap; //사용자정보
	//서버 실행 여부
	private boolean isServerOn = true;
	
	//생성자
	public ChatServerRunnable(ObservableList<String> clientList, Map<String, ClientVO> clientMap){
		try {
			//객체가 생성될 때 소켓을 7777포트로 초기화
			this.socket = new DatagramSocket(7777);
			//소켓의 타임아웃을 0.5초로 설정 - 클라이언트로부터 데이터가 들어오지 않은채로
			//0.5초가 지나면 타임아웃 익셉션을 발생시키고 넘어가게 됨
			this.socket.setSoTimeout(500);
			//MainController로부터 받은 클라이언트 정보 맵
			this.clientList = clientList;
			this.clientMap = clientMap;
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	public void run(){
		while(isServerOn){ // true무한루프
			byte[] inMsg = new byte[100];
			DatagramPacket inPacket = new DatagramPacket(inMsg, inMsg.length); // 패킷생성
			try {
				socket.receive(inPacket); // 기다리고 있다가 전송받으면 inPacket에 정보를 담는다.
				final InetAddress address = inPacket.getAddress();
				final int port = inPacket.getPort();
				//isEcist가 있으면 true 없으면 false
				boolean isExist = clientList.contains(address.getHostName());
			
				if(!isExist){ // 목록에 존재하지 않는 사용자라면..등록해준다. send해줬을때
					Platform.runLater(new Runnable() {//화면 ui관련된 작업은 javafx Application 쓰레드만 할수있다. 
						
						@Override
						public void run() {
							clientList.add(address.getHostName()); // JavaFx Application 쓰레드 관련 작업임.
						}
					});
					
					ClientVO vo = new ClientVO(address.getHostAddress(),   
												port, 
												new String(inPacket.getData()).trim() // 대화명에서 불필요한 공백(trim) 제거.
												);
					clientMap.put(address.getHostName(), vo); // 맵에 담아줌 
					
				}else{ // 목록에 존재하는 사용자인 경우...(이미 채팅중인 사용자인 경우...)
				
					System.out.println(new String(inPacket.getData())); // 보내온 메세지를 받음
					
					Iterator<String> it = clientMap.keySet().iterator(); //맵을 뒤져본다.
					
					ClientVO senderVO =  clientMap.get(address.getHostName()); // vo객체를 꺼내온다.
					
					while(it.hasNext()) {  // 받아온내용을 하나하나 꺼내서 전송한다?
						String ipAddr = it.next();
						ClientVO vo = clientMap.get(ipAddr); 
						InetAddress ipAddress = InetAddress.getByName(vo.getIpAddr()); //패킷에 상대방파라미터로 넣은거 (메세지 바이트, 사이즈, 아이피, 포트번호)를 준비
						DatagramPacket outPacket = null;
						if(address.getHostName().equals(vo.getIpAddr()) 
							&& (port != vo.getPortNum())) { // 아이피주소는 동일한데 포트번호가 다른경우...
							vo.setPortNum(port);
							clientMap.put(ipAddr, vo); // 기존 정보 갱신
						}
						
						String sendMsg = "[" + senderVO.getChatName() + "] " + new String(inPacket.getData()); // 메세지
						
						System.out.println("메시지 : " + sendMsg);
						outPacket = new DatagramPacket(sendMsg.getBytes(),  	//바이트	
												sendMsg.getBytes().length, 		//사이즈
												ipAddress, 						//아이피
												vo.getPortNum());				//포트번호
						socket.send(outPacket); // 접속한 클라이언트에게 메시지 전송
					}
				}
				
				//Thread.sleep(500);
			} catch(SocketTimeoutException e){	
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void turnOffServer(){
		isServerOn = false;
	}
}
