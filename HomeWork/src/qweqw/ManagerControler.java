package qweqw;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;


public class ManagerControler<id,name, tel, addr> implements Initializable{

	@FXML TableColumn<String, id> idCol;
	@FXML TableColumn<String, name> nameCol;
	@FXML TableColumn<String, tel> telCol;
	@FXML TableColumn<String, addr> addrCol;
	@FXML Button addBtn;
	@FXML TextField txtId;
	@FXML TextField txtName;
	@FXML TextField txtTel;
	@FXML TextField txtAddr;
	@FXML TableView<Member> tableView;
	@FXML Button setBtn;
	@FXML Button delBtn;
	@FXML Button confirm;
	@FXML Button cancel;
	int a = 0;
	//데이터 구성
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		ObservableList<Member> data = FXCollections.observableArrayList();
		
		tableView.setItems(data);
		
		//컬럼 연결하기
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		telCol.setCellValueFactory(new PropertyValueFactory<>("tel"));
		addrCol.setCellValueFactory(new PropertyValueFactory<>("addr"));

		//추가 버튼 작업시
		addBtn.setOnAction(e->{ //수정과 삭제 비활성화 및 확인과 취소버튼 활성화
			setBtn.setDisable(true);
			delBtn.setDisable(true);
			confirm.setDisable(false);
			cancel.setDisable(false);
		a = 1;	
		});
		
		//수정 버튼 작업시
		setBtn.setOnAction(e->{//추가와 삭제 비활성및 확인과 취소버튼 활성화
			addBtn.setDisable(true);
			delBtn.setDisable(true);
			confirm.setDisable(false);
			cancel.setDisable(false);
		a = 2;	
		});
		
		//삭제 버튼 작업시
		delBtn.setOnAction(e->{//추가와 수정버튼 비활성화 및 확인과 취소버튼 활성화

			addBtn.setDisable(true);
			setBtn.setDisable(true);
			confirm.setDisable(false);
			cancel.setDisable(false);
		a = 3;
		});
		
		//취소 버튼 작업시
		cancel.setOnAction(e->{ //초기상태로 돌아감
			addBtn.setDisable(false);
			setBtn.setDisable(false);
			delBtn.setDisable(false);
			confirm.setDisable(true);
			cancel.setDisable(true);
			
		});
		
		//확인 버튼 작업시 	
		
				
			confirm.setOnAction(e->{ //작업을 진행한다.
				
			if(a == 1) {if(txtId.getText().isEmpty() 
					|| txtName.getText().isEmpty()	
					|| txtTel.getText().isEmpty()	
					|| txtAddr.getText().isEmpty()) {

					errmsg("작업오류", "빈 항목이 있습니다.");
					
					return;
				}
				
				data.add(new Member(txtId.getText(), 
									txtName.getText(), 
									txtTel.getText(), 
									txtAddr.getText()));
				info("작업 결과", txtName.getText() + " 님 정보를 추가했습니다.");
				a = 0;
			}
				
			if(a == 2) {if(txtId.getText().isEmpty() 
					|| txtName.getText().isEmpty()	
					|| txtTel.getText().isEmpty()	
					|| txtAddr.getText().isEmpty()) {

					errmsg("작업오류", "빈 항목이 있습니다.");
					
					return;
				}
				
			data.set(tableView.getSelectionModel().getSelectedIndex(),new Member(txtId.getText(), 
									txtName.getText(), 
									txtTel.getText(), 
									txtAddr.getText()));
				info("작업 결과", txtName.getText() + " 님 정보를 수정했습니다.");
				a = 0;
			}
			
			if(a == 3) {if(tableView.getSelectionModel().isEmpty()) {
				errmsg("작업 오류", "삭제할 자료를 선택한 후 삭제하세요.");
				return;
			}
			
			data.remove(tableView.getSelectionModel().getSelectedIndex());
			
			info("작업결과", nameCol.getText() + "님 정보를 삭제했습니다.");
			a = 0;
			}
				
				
				txtId.clear();
				txtName.clear();
				txtTel.clear();
				txtAddr.clear();	
		});
			
			
			// TableView를 클릭했을때의 처리
			tableView.setOnMouseClicked(e -> {
				//TableView에서 선택한 줄의 데이터를 가져온다.
				Member mem = tableView.getSelectionModel().getSelectedItem();
				
				if(mem == null)
					return;
				
				txtId.setText(mem.getId());
				txtName.setText(mem.getName());
				txtTel.setText(mem.getTel());
				txtAddr.setText(mem.getAddr());
				
			});	
		
	}
	
	public void errmsg(String headerText, String msg) {
		
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("오류");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
	}
	public void info(String headerText, String msg) {
		
		Alert infoAlert = new Alert(AlertType.ERROR);
		infoAlert.setTitle("정보 확인");
		infoAlert.setHeaderText(headerText);
		infoAlert.setContentText(msg);
		infoAlert.showAndWait();
	}
	
	public class Member{
		
	private String id;
	private String name;
	private String tel;
	private String addr;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public Member(String id, String name, String tel, String addr) {
		super();
		this.id = id;
		this.name = name;
		this.tel = tel;
		this.addr = addr;
	}
	
	
	}
	
}
