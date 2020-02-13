package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProdInfoMain extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ProdInfo.fxml"));
		Parent root = loader.load();
		
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("상품관리(ProdInfo)");
		primaryStage.setScene(scene); //Scene 추가
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
