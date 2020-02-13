package 컨트롤객체연습;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControllObject_pr_Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("ControllObject_pr.fxml"));
		// parent 대신 borderpain 으로 해도 됨. 더 많이 담을수있는 상위클래스로 적은것뿐이다.
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("컨트롤객체 연습");
		primaryStage.setScene(scene);
		primaryStage.show();

		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
