package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import thread.ChatServerRunnable;
import vo.ClientVO;

public class MainController implements Initializable{

	//접속한 클라이언트의 ip주소를 표시해 줄 리스트뷰
	@FXML private ListView<String> lvClient;
	//리스트뷰의 데이터와 바인드 될 리스트
	private ObservableList<String> clientList;

	// 접속한 클라이언트 정보를 담기위한 Map
	private Map<String, ClientVO> clientMap;
	
	//채팅 참여하는 클라이언트를 리스트에 추가해 줄 쓰레드의 Runnable 구현 클래스
	private ChatServerRunnable chatServerRunnable = null;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//처음 실행시 클라이언트 목록이 없으므로 리스트를 새로 생성
		clientList = FXCollections.observableArrayList();
		//리스트뷰에 데이터를 바인드 - 후에 리스트에 데이터를 추가하면 자동으로 리스트뷰에 반영됨
		lvClient.setItems(clientList);
		//동기화처리
		clientMap = Collections.synchronizedMap(new HashMap<>());
		
	}

	/**
	 * 서버실행 버튼을 눌렀을 경우 동작할 이벤트 메서드
	 * @param event
	 */
	@FXML
	public void turnOnServer(ActionEvent event){
		System.out.println("서버가 시작되었음...");
		//서버실행 버튼을 누르면 클라이언트의 ip주소를 추가해주는 쓰레드를 시작
		chatServerRunnable = new ChatServerRunnable(clientList, clientMap);
		Thread thread = new Thread(chatServerRunnable);
		thread.setDaemon(true);
		thread.start();
	}
	
	/**
	 * 서버 종료 버튼을 눌렀을 경우 실행될 이벤트 메서드
	 * @param event
	 */
	@FXML
	public void turnOffServer(ActionEvent event){
		//서버 실행 쓰레드의 작동여부를 false로 바꿔주면 쓰레드가 종료됨
		chatServerRunnable.turnOffServer();
		//서버가 종료되었으므로 클라이언트 리스트를 초기화
		clientList.clear();
	}
}
