package kr.or.ddit.basic;





import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class T11_CheckBoxTest extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		Rectangle rect = new Rectangle(90, 30); // 4각형 그리기. width = 90 height = 30
		rect.setArcHeight(10); // 둥근 4각형을 만들기 위한 꼭지점 모따기
		rect.setArcWidth(10);
		rect.setFill(Color.rgb(41, 41, 41)); // 4각형 내부 색칠하기.
		
		String[] names = new String[] {"Security", "Project", "Chart"};
		// image 랑 imageview 는 세트로사용. imageview 가 컨트롤이야. imageview 안에는 image객체가 들어감.
		
		Image[] images = new Image[names.length]; //이미지갯수만큼으로 만듬.
		ImageView[] icons = new ImageView[names.length];
		
		CheckBox[] chkboxs = new CheckBox[names.length];
		
		for(int i = 0; i < names.length; i ++) { // 3번돌면서 초기화시킴.
			// 출력할 이미지 읽어오기
			final Image img = images[i] = new Image(getClass().getResourceAsStream("images/" + names[i] + ".png"));
			// final변수로해야 밑에서 사용할수있기 때문.
			
			
			// 이미지를 출력하는 객체 생성
			final ImageView icon = icons[i] = new ImageView();
			final CheckBox cb = chkboxs[i] = new CheckBox(names[i]);
			
			//checkbox 를 클릭해서 값이 변경되었을 때의 이벤트 처리 - 전체 람다식으로 바꿀 수 있다.
			cb.selectedProperty().addListener(new ChangeListener<Boolean>() {

				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					//체크하면 oldValue : false / newValue : true 로시작.
					//다시 풀면 oldValue : true / newValue : false
					
					// ImagesView 의 setImage()메서드는 보여줄 이미지 세팅해줌.-여기람다식으로 바꿀수인써. 람다식안의 변수는 final이어야해.
					if(newValue == true) {
						 icon.setImage(img);
					}else {
						icon.setImage(null); // imageview 에서 이미지 삭제
					}
					
				}
				
				
			});
		}// for문 완료
		
		Button btnOk = new Button("확인");
		
		btnOk.setOnAction(e->{
			// checkBox의 check여부를 확인하는 방법
			// if(두번째 체크박스에 체크가되면)
			if(chkboxs[1].isSelected()) {
				showInfo(chkboxs[1].getText() + "체크");
			}else {
				showInfo(chkboxs[1].getText() + "체크 해제");
			}
			// chkboxs[0].setselected(true) => 1번체크박스에 체크한다.
			// chkboxs[0].setselected(false) => 1번체크박스에 체크해제한다.
			// isSelected 는 체크되있는지 여부에대한 상태만 말해줌.
			chkboxs[0].setSelected(!chkboxs[1].isSelected());
		});
		
		
		VBox vbox = new VBox(5); // 스페이싱역할.vbox.spacing = 5 준거랑 똑같다.
		vbox.getChildren().addAll(chkboxs);
		vbox.getChildren().add(btnOk);
		
		HBox hbox = new HBox();
		hbox.getChildren().addAll(icons);
		hbox.setPadding(new Insets(0,0,0,5));
		
		// stackPane 은 컨트롤들을 쌓아놓는 방식으로 배치하는 컨테이너다.
		
		StackPane stack = new StackPane(); // 켜켜쌓음 z좌표 +로.
		stack.getChildren().addAll(rect, hbox); // 사각형넣고
		StackPane.setAlignment(rect, Pos.TOP_CENTER); // 사각형위에 hbox 넣음.(imageview3개)
		
		HBox root = new HBox();
		root.setSpacing(40);
		root.setPadding(new Insets(20,10,10,20));
		root.getChildren().addAll(vbox, stack);// vbox : 체크박스, stackpane : 이미지뷰+사각형
		
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("ChechBox 연습");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		
	}
	
	private void showInfo(String msg) {
		Alert alertInfo = new Alert(AlertType.INFORMATION);
		alertInfo.setTitle("INFORMATION");
		alertInfo.setContentText(msg);
		alertInfo.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
