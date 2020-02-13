package FX;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.regex.Pattern;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class FX_회원목록관리_Controller implements Initializable {

	@FXML private TextField id;
	@FXML private TextField name;
	@FXML private TextField tel;
	@FXML private TextField addr;
	@FXML private Button add;
	@FXML private Button edit;
	@FXML private Button del;
	@FXML private Button ok;
	@FXML private Button no;
	@FXML private TableColumn culId;
	@FXML private TableColumn culName;
	@FXML private TableColumn culTel;
	@FXML private TableColumn culAddr;
	@FXML private TableView tv; 

	ObservableList<Member> data = FXCollections.observableArrayList(new Member("홍길동", "gildong", "2222-2222", "대전"));
	TableView<Member> table = new TableView<>(data);
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		tv.setItems(data);
		culId.setCellValueFactory(new PropertyValueFactory<>("id"));
		culName.setCellValueFactory(new PropertyValueFactory<>("name"));
		culTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
		culAddr.setCellValueFactory(new PropertyValueFactory<>("addr"));
		
		
		// TableView를 클릭했을때 처리
		table.setOnMouseClicked(e->{
			// TableView에서 선택한 줄의 데이터를 가져온다.
			Member mem = table.getSelectionModel().getSelectedItem();
			
			id.setText(mem.getId());
			name.setText(mem.getName());
			
			tel.setText(mem.getTel());
			addr.setText(mem.getAddr());

		});
		
	}

	@FXML public void AddClicked(ActionEvent event) {
		
		
		add.setOnAction(e->{
			if(id.getText().isEmpty() 
					|| name.getText().isEmpty()
					||  tel.getText().isEmpty()
					||  addr.getText().isEmpty()
					){
				System.out.println("빈 항목이 있습니다.");
				
				return;
			}
			
			
			data.add(new Member(id.getText(), name.getText(), tel.getText(), addr.getText()));
			System.out.println("정보 추가 성공...");
			
			id.clear();
			name.clear();
			
			tel.clear();
			addr.clear();
		});
		
	}

	@FXML public void EditClicked(ActionEvent event) {
	
	edit.setOnAction(e->{
		if(name.getText().isEmpty() 
				|| name.getText().isEmpty()
				
				||  tel.getText().isEmpty()
				||  addr.getText().isEmpty()) {
			System.out.println("빈 항목이 있습니다.");
			
			return;
		}
		
		
		
		// 인덱스위치 (table.getSelectionModel().getSelectedIndex())
		data.set(table.getSelectionModel().getSelectedIndex(), 
				new Member(id.getText(), 
							name.getText(), 
						
							tel.getText(), 
							addr.getText()));
		
		infoMsg("작업 결과", name.getText() + "님 정보를 수정했습니다.");
		
		System.out.println("정보 수정 성공...");
		
	
		name.clear();
		id.clear();
		tel.clear();
		addr.clear();
	});
	}
	
	
	@FXML public void DelClicked(ActionEvent event) {
		
		del.setOnAction(e->{
			if (table.getSelectionModel().isEmpty()) {
				errMsg("작업 오류", "삭제할 자료를 선택한 후 삭제하세요.");
				return;
			}
			
			data.remove(table.getSelectionModel().getSelectedIndex());
			
			infoMsg("작업 결과", name.getText() + "님 정보를 삭제했습니다.");

			System.out.println("정보 수정 성공...");
			
			id.clear();
			name.clear();
			
			tel.clear();
			addr.clear();
		});
	}

	
	
	@FXML public void OkClicked(ActionEvent event) {
		
		
	}

	@FXML public void NoClicked(ActionEvent event) {
		
		
	}

	

	
	
	
	
	

	public void errMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("오류");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
	}
	
	public void infoMsg(String headerText, String msg) {
		Alert infoAlert = new Alert(AlertType.ERROR);
		infoAlert.setTitle("오류");
		infoAlert.setHeaderText(headerText);
		infoAlert.setContentText(msg);
		infoAlert.showAndWait();
	}
	
	
	public class Member {

		private String id;
		private String name;
		private String tel;
		private String addr;
		public Member(String id, String name, String tel, String addr) {
			super();
			this.id = id;
			this.name = name;
			this.tel = tel;
			this.addr = addr;
		}
		public String getId() {
			return id;
		}
		public String getName() {
			return name;
		}
		public String getTel() {
			return tel;
		}
		public String getAddr() {
			return addr;
		}
		public void setId(String id) {
			this.id = id;
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setTel(String tel) {
			this.tel = tel;
		}
		public void setAddr(String addr) {
			this.addr = addr;
		}
	
		
	
		
	
		
		
		
	}
	 
	
}
