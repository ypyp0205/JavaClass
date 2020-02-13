package kr.or.ddit.basic;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class T09_ComboBoxTest extends Application{
	//코딩한번해보세요.
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		HBox hbox = new HBox();
		TextArea txtArea = new TextArea();
		
		ComboBox<String> combo = new ComboBox<>(); // 콤보박스자체를 재너릭타입클래스로 했음.
		combo.getItems().addAll("한강", "금강", "영산강", "낙동강"); // 한강금강 얘네가 item 이다.
		combo.setValue("한강"); // 처음에 보이는 부분의 데이터 셋팅
		
		
		// 방법 1.
/*		combo.setOnAction(e->{
			txtArea.setText(combo.getValue());
		});*/
		//
		
		
		// 방법 2.
		combo.valueProperty().addListener(new ChangeListener<String>() {
			// addListener 도 함수적 인터페이스. ChangeListener 타입은 내가 바꿔주는것.'new ChangeListener<String>()' 이부분의 String.
			// listener : 변화가 있는지 듣는애. changelistener : 바뀐것을 듣는 애.
			// changelistener 사용위해서는 changed 메서드를 오버라이드해야함. 
			
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				System.out.println("oldValue : " + oldValue);
				System.out.println("newValue : " + newValue);
				txtArea.setText(newValue);
			}
		});
		//
		// 방법 2가 방법 1보다  좋은 이유 : (eventhandler 보다 listner사용이 좋은 이유) : newvalue, oldvalue 를 모두 꺼내올 수 있음.! 
		
		
		
		
		//observable list 랑 list 랑 거의 비슷한데 observable 에 obsevable기능이 들어가있다.....
		ObservableList<String> fruitList = FXCollections.observableArrayList(
				"사과", "배", "복숭아", "포도", "감" 
				);
		
		// 객체 생성 및 데이터 초기화를 동시에...
		ComboBox<String> combo2 = new ComboBox<>(fruitList);
		
		// 데이터 초기화 후에 추가하기
		combo2.getItems().addAll("대추", "호두");
		//삭제
		//combo2.getItems().remove("");
		
		combo2.setValue("포도");
		
		Button btn = new Button("확인");
		
		
		btn.setOnAction(new EventHandler<ActionEvent>() {
			//
			@Override
			public void handle(ActionEvent event) {
				if(combo.getValue() != null && combo2.getValue() != null) {
					txtArea.setText(combo.getValue() + "지역에는 " + combo2.getValue() + "가 유명합니다. ");
				}
				
			}
			// 이부분 람다식으로 바꿔보렴.
		});
		
		hbox.setSpacing(10);
		hbox.setPadding(new Insets(10));
		hbox.getChildren().addAll(combo, combo2, btn);
		
		root.setTop(hbox);
		root.setCenter(txtArea);
		// 여기까지가 컨트롤 세팅 끝!
		
		
		//여기부터는 화면에 보여지도록 scene을 만든다.
		Scene scene = new Scene(root, 500, 400); //이렇게 신크기조정도가능.
		primaryStage.setTitle("ComboBox 연습");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
