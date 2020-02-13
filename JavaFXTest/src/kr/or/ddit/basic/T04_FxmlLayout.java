package kr.or.ddit.basic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class T04_FxmlLayout extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		// fxml을 읽어와 현재 Stage에 적용하기
		
		// Parent객체는 모든 컨테이너의 조상 객체
		// 방법1 - 스태틱메서드를 직접받는 방법
		//Parent root = FXMLLoader.load(getClass().getResource("FxmlLayout.fxml"));
		
		// 방법2 - 인스턴스객체를 호출해서 받는방법
		FXMLLoader loader = new FXMLLoader(getClass().getResource("FxmlLayout.fxml"));
		
		Parent root = loader.load();
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("Fxml문서를 이용한 레이아웃 연습");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}

}

