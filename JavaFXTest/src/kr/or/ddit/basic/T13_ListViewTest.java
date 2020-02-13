package kr.or.ddit.basic;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

public class T13_ListViewTest extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		ListView<String> list = new ListView<>();
		
		Label label = new Label();
		label.setFont(new Font(20));
		
		// ListView에 들어갈 데이터 구성하기
		ObservableList<String> data =
			FXCollections.observableArrayList(
			"green", "gold", "red", "blue","black",	
			"brown", "blueviolet", "pink", "yellow","chocolate"	
			);
		
		list.setItems(data); 
		
		/*// ListView에서 값이 선택되었을때 처리
		list.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<String>() {

			@Override
			public void changed(
					ObservableValue<? extends String> observable, 
					String oldValue, 
					String newValue) {
				
				label.setText(newValue);
				label.setTextFill(Color.web(newValue)); // 글자색변경  글자색 넣어주는부분
			}
		});*/

		
		// 위에 주석을 람다식으로 표현 
		list.getSelectionModel().selectedItemProperty()
		.addListener((observable, oldValue, newValue)->{
			
			label.setText(newValue);
			label.setTextFill(Color.web(newValue)); // 글자색변경  글자색 넣어주는부분
			
		});



		
		
		// ListView의 내용은 변경하지 않고 화면에 보이는 부분만 변경하는 방법
		list.setCellFactory(
				new Callback<ListView<String>, 
							ListCell<String>>() {
			
			@Override
			public ListCell<String> call(ListView<String> param) {
				
				return new ListCell<String>() {
					protected void updateItem(String item, 
												boolean empty) {
						super.updateItem(item, empty);
		//--------------------------------------------------------------------------	요부분을 고민해야할 부분			
						// 변경되는 데이터가 문자열이면 setText()를 사용함.
						// setText(item);
						
						Rectangle rect = new Rectangle(100, 20);
						if(item != null) {// 또는 !empty
							rect.setFill(Color.web(item)); // 색칠하기 
							// setGraphic(rect); // 값 변경하기
							
							Label lbTxt = new Label(item);
							lbTxt.setTextFill(Color.web(item));
							
							HBox hb2 = new HBox(10);
							
							hb2.getChildren().addAll(rect, lbTxt);
							
							setGraphic(hb2);
						}
		//--------------------------------------------------------------------------	
					}
				};
			}
		});
		
		VBox vbox = new VBox(10);
		
		vbox.getChildren().addAll(list, label);
		
		Scene scene = new Scene(vbox);
		
		primaryStage.setTitle("ListView 연습");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
