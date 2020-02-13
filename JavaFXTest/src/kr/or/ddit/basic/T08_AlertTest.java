package kr.or.ddit.basic;

import java.util.Optional;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

public class T08_AlertTest extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Alert alertInformation = new Alert(AlertType.INFORMATION);
		alertInformation.setTitle("INFORMATION");
		alertInformation.setContentText("INFORMATION창 입니다.");
		alertInformation.showAndWait(); // Alert창 보이기
		//=============================================================
		Alert alertError = new Alert(AlertType.ERROR);
		alertError.setTitle("ERROR");
		alertError.setContentText("ERROR Alert창 입니다.");
		alertError.showAndWait();
		//=============================================================
		Alert alertWarn = new Alert(AlertType.WARNING);
		alertWarn.setTitle("WARNING");
		alertWarn.setContentText("WARNING Alert창 입니다.");
		alertWarn.showAndWait();
		//=============================================================
		Alert alertConfirm = new Alert(AlertType.CONFIRMATION);
		alertConfirm.setTitle("CONFIRMATION");
		alertConfirm.setContentText("CONFIRMATION Alert창 입니다.");
		
		// Alert창을 보여주고 사용자가 누른 버튼 값 읽어오기
		ButtonType confirmResult = alertConfirm.showAndWait().get();
		
		if (confirmResult == ButtonType.OK) {
			System.out.println("OK 버튼을 눌렀습니다.");
		}else if(confirmResult == ButtonType.CANCEL) {
			System.out.println("취소 버튼을 눌렀습니다.");
		}
		//=============================================================
		
		// Javascript의 Prompt창과 같은 기능
		// '기본값'은 생략 가능
		TextInputDialog inputDialog = new TextInputDialog("기본값");
		inputDialog.setTitle("Prompt창"); // 창제목
		inputDialog.setHeaderText("TextInputDialog창 입니다."); // 출력메세지
		
		// 창을 보이고 입력한 값을 읽어오기
		Optional<String> result = inputDialog.showAndWait();
		String strResult = null; // 입력한 값이 저장될 변수 선언
		
		// 입력한 값이 있는지 검사(값 입력 후 'OK'버튼 눌렀는지 검사
		if (result.isPresent()) {
			strResult = result.get(); // 값 읽어오기
		}
		System.out.println("읽어온 값 : " + strResult);
	}
	

	public static void main(String[] args) {
		launch(args);
	}
}
