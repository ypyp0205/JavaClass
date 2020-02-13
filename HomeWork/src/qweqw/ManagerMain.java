package qweqw;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ManagerMain extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("manager.fxml"));
		
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("회원관리 프로그램");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	
	public static void main(String[] args) {
		
		launch(args);
		
	}
	
}
