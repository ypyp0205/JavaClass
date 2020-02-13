package user_MemberList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FX_Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
		// parent 대신 borderpain 으로 해도 됨. 더 많이 담을수있는 상위클래스로 적은것뿐이다.
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("회원 목록 관리");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	public static void main(String[] args) {
		launch(args);
	}
}
