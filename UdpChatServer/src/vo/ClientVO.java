package vo;
/**
 * 클라이언트 정보를 담기 위한 VO
 * @author HelloJava
 *
 */
public class ClientVO {
	private String ipAddr; // IP주소
	private String chatName; // 대화명
	private int portNum; 		// 포트번호
	public ClientVO(String ipAddr, int portNum, String chatName) {
		super();
		this.ipAddr = ipAddr;
		this.portNum = portNum;
		this.chatName = chatName;
	}
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	public int getPortNum() {
		return portNum;
	}
	public void setPortNum(int portNum) {
		this.portNum = portNum;
	}
	public String getChatName() {
		return chatName;
	}
	public void setChatName(String chatName) {
		this.chatName = chatName;
	}
	
}
