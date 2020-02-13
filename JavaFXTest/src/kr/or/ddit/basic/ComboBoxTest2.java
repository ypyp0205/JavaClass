package kr.or.ddit.basic;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ComboBoxTest2 extends Application {

	@Override
	public void start(Stage primaryStage) {
		VBox vbox = new VBox(10);
		ComboBox<MyFriend> combo = new ComboBox<>();
		TextArea result = new TextArea();
		
		ObservableList<MyFriend> list = FXCollections.observableArrayList(
			new MyFriend("a001", "홍길동", "010-1111-1111", "대전"),
			new MyFriend("b001", "일지매", "010-2222-2222", "광주"),
			new MyFriend("c001", "성춘향", "010-3333-3333", "제주"),
			new MyFriend("d001", "이몽룡", "010-4444-4444", "포항"),
			new MyFriend("e001", "변학도", "010-5555-5555", "인천"),
			new MyFriend("f001", "이순신", "010-6666-6666", "부산")
		);
		combo.setItems(list);
		
		// ComboBox의 목록이 보여지는 곳의 내용 변경하기
		// 화면에 나타나는 셀의 내용을 변경하는 부분으로 
		// 이 부분의 변경 내용은 셀부분만 변경된다.
		combo.setCellFactory(
			new Callback<ListView<MyFriend>, ListCell<MyFriend>>() {
				@Override
				public ListCell<MyFriend> call(ListView<MyFriend> param) {
					return new ListCell<MyFriend>() {
						protected void updateItem(MyFriend item, boolean empty) {
							super.updateItem(item, empty);
							if(item==null || empty) {
								setText(null);
							}else {
								// 변경된 값이 문자열이면 setText()
								// 객체이면 setGraphic() 메서드로 변경한다.
								setText(item.getName());  // 이름으로 셋팅
								
//								setGraphic(
//									new ImageView(
//										new Image( getClass().getResourceAsStream("images/ok.png"))));
							}
						}
					};
				}
			}
		);
		
		// ComboBox에서 리스트 항목을 선택하면 선택된 내용이
		// ComboBox의 '버튼영역'에 나타나는데
		// 이부분의 내용도 변경해 주어야 한다.
		combo.setButtonCell(
				new ListCell<MyFriend>() {
					protected void updateItem(MyFriend item, boolean empty) {
						super.updateItem(item, empty);
						if(empty) {
							setText(null);
						}else {
							setText(item.getName());
						}
					}
				}
		);
		
		combo.setValue(list.get(0));
		
		// ComboBox를 setOnAction 처리하기
		combo.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// 현재 선택한 데이터 구하기
				MyFriend data = 
					combo.getSelectionModel().getSelectedItem();
				
				result.setText(data.getId() + "\n");
				result.appendText(data.getName() + "\n");
				result.appendText(data.getTel() + "\n");
				result.appendText(data.getAddr() + "\n");
			}
		});
		
		
		vbox.getChildren().addAll(combo, result);
		vbox.setPadding(new Insets(10));
		
		Scene scene = new Scene(vbox, 400, 300);
		primaryStage.setTitle("콤보박스에 객체 데이터 설정");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	// 데이터를 나타내는 클래스를 Inner클래스로 선언한다.
	class MyFriend {
		private String id;
		private String name;
		private String tel;
		private String addr;
		
		public MyFriend() {	}
		
		public MyFriend(String id, String name, String tel, String addr) {
			super();
			this.id = id;
			this.name = name;
			this.tel = tel;
			this.addr = addr;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getTel() {
			return tel;
		}

		public void setTel(String tel) {
			this.tel = tel;
		}

		public String getAddr() {
			return addr;
		}

		public void setAddr(String addr) {
			this.addr = addr;
		}
		
	}
	
}
