package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import vo.ScoreVO;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class ScoreAddController implements Initializable {

	@FXML
	private TextField name;
	@FXML
	private TextField kor;
	@FXML
	private TextField math;
	@FXML
	private TextField eng;
	@FXML
	private Button saveBtn;
	@FXML
	private Button cancelBtn;

	private ObservableList<ScoreVO> list;

	// get을 이용해서 외부Class및 메소드에서 데이터를 가져온다.
	// 여기서는 직접적인 DB와 관계가없이 데이터를 가져와서 보여주기만하는 부분이므로
	// get을 굳이 사용하지 않아도 된다.
//	public ObservableList<ScoreVO> getList() {
//		return list;
//	}

	public void setList(ObservableList<ScoreVO> list) {
		this.list = list;
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	@FXML
	public void saveClicked(ActionEvent event) {
		if (name.getText().isEmpty() || kor.getText().isEmpty() || math.getText().isEmpty()
				|| eng.getText().isEmpty()) {
			errMsg("작업 오류", "빈 항목이 있습니다.");
			return;
		}

		// vo에 textField값 저장해서 list에 담기.
		String txtName = name.getText();
		int txtKor = Integer.parseInt(kor.getText());
		int txtMath = Integer.parseInt(math.getText());
		int txtEng = Integer.parseInt(eng.getText());

		ScoreVO vo = new ScoreVO(txtName, txtKor, txtMath, txtEng);
		
		// table에 데이터를 추가한다.
		list.add(vo);
		System.out.println("데이터 추가");

		infoMsg("작업 결과", txtName + "님 정보를 추가했습니다.");

		// 저장버튼 누르면 자동으로 창 닫기
		Stage main = (Stage) saveBtn.getScene().getWindow();
		main.close();
	}

	@FXML
	public void cancelClicked(ActionEvent event) {
		Stage main = (Stage) saveBtn.getScene().getWindow();
		main.close();
	}

	public void errMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("오류");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
	}

	public void infoMsg(String headerText, String msg) {
		Alert infoAlert = new Alert(AlertType.INFORMATION);
		infoAlert.setTitle("정보 확인");
		infoAlert.setHeaderText(headerText);
		infoAlert.setContentText(msg);
		infoAlert.showAndWait();
	}

}
