package kr.or.ddit.board;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kr.or.ddit.board.controller.BoardController;

public class BoardMain extends Application{
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("xboard.fxml"));
		Parent root = loader.load();
		BoardController controller = loader.getController();
		controller.setPrimaryStage(primaryStage);
		
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("샘플게시판");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
