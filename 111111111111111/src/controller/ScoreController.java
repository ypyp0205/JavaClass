package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import vo.ScoreVO;
import javafx.scene.control.TableColumn;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class ScoreController implements Initializable{

	@FXML private TableView<ScoreVO> tbView;
	@FXML private TableColumn<ScoreVO, String> name;
	@FXML private TableColumn<ScoreVO, Integer> kor;
	@FXML private TableColumn<ScoreVO, Integer> math;
	@FXML private TableColumn<ScoreVO, Integer> eng;
	@FXML private Button addBtn;
	@FXML private Button graphBtn;
	
	BarChart<String, Number> barchart;
	CategoryAxis xAxis;
	NumberAxis yAxis;
	
	PieChart pieChart;
	
	private ObservableList<ScoreVO> list = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		kor.setCellValueFactory(new PropertyValueFactory<>("kor"));
		math.setCellValueFactory(new PropertyValueFactory<>("math"));
		eng.setCellValueFactory(new PropertyValueFactory<>("eng"));
		
		tbView.setItems(list);
		
		ScoreVO hj = new ScoreVO();
//		hj.setMem_id("h001");
		hj.setName("강현지");
		hj.setKor(80);
		hj.setEng(70);
		hj.setMath(60);
		list.add(hj);
		
		
	}

	@FXML public void addBtnClicked(ActionEvent event) {
		// 새 창띄우기
		Stage dialog = new Stage(StageStyle.UTILITY);
		
		// 모달창 설정
		dialog.initModality(Modality.APPLICATION_MODAL);
		
		dialog.setTitle("추가");
		
		Parent parent = null;
		try {
			// 다른 컨트롤러를 사용하기위해 호출
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ScoreAdd.fxml"));
			// fxml 로드
			parent = loader.load();
			
			// 해당 fxml에 연결된 컨트롤러 호출
			ScoreAddController addCont = loader.getController();
			
			// 다른 컨트롤러에있는 List주소를 보내서 연결시키는것.
			addCont.setList(list);

		}catch(IOException e) {
			e.printStackTrace();
		}
	
		Scene scene = new Scene(parent);
		dialog.setScene(scene);
		dialog.show();
	}

	
	
	@FXML
	/**
	 * 
	 * @param event
	 */
	public void graphBtnClicked(ActionEvent event) {
		// 새 창띄우기
		Stage dialog = new Stage(StageStyle.UTILITY);
		
		// 모달창 설정
		dialog.initModality(Modality.APPLICATION_MODAL);
		
		dialog.setTitle("추가");
		
		Parent parent = null;
		try {
			// 다른 컨트롤러를 사용하기위해 호출
			FXMLLoader loader = new FXMLLoader(getClass().getResource("barChart.fxml"));
			// fxml 로드
			parent = loader.load();
			
			// 해당 fxml에 연결된 컨트롤러 호출
			BarController barcont = loader.getController();
			
	// 다른 controller에 만들어둔 세터 사용. 이게뭔소리지!?
			barcont.setList(list);
			barcont.chartCreate();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
//		barchart = (BarChart<String, Number>) parent.lookup("#barchart");
//		xAxis = (CategoryAxis) parent.lookup("#xAxis");
//		yAxis =  (NumberAxis) parent.lookup("#yAxis");
		
		
		Scene scene = new Scene(parent);
		dialog.setScene(scene);
		dialog.show();
		
		
		
		/*// 새 창 띄우기
		Stage dialog = new Stage(StageStyle.UTILITY);
		
		// 모달창 설정
		dialog.initModality(Modality.APPLICATION_MODAL);
		
		
		// x,y축 생성
		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis yAxis = new NumberAxis();
		
		// 위의 축정보를 이용한 BarChart 객체 생성
		BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
		
		// 데이터 구성
		XYChart.Series<String, Number> ser1 = new XYChart.Series<>();
		XYChart.Series<String, Number> ser2 = new XYChart.Series<>();
		XYChart.Series<String, Number> ser3 = new XYChart.Series<>();
 	
		ser1.setName("국어");
		ser2.setName("수학");
		ser3.setName("영어");
		
		for(int i= 0; i <list.size(); i++) {
			String getName = list.get(i).getName(); 
			int getKor = list.get(i).getKor();
			int getMath = list.get(i).getMath();
			int getEng = list.get(i).getEng();
			ser1.getData().add(new XYChart.Data<String, Number>(getName, getKor));
			ser2.getData().add(new XYChart.Data<String, Number>(getName, getMath));
			ser3.getData().add(new XYChart.Data<String, Number>(getName, getEng));
		}
		barChart.getData().addAll(ser1, ser2, ser3);
		
		
		
		
		Button btn = new Button("닫기");
		btn.setOnAction(e->{
			dialog.close();
		});
		
		VBox vb = new VBox();
		vb.getChildren().addAll(barChart, btn);
		vb.setAlignment(Pos.CENTER);
		vb.setSpacing(20);
		
		Scene scene = new Scene(vb, 800, 600); 
		dialog.setTitle("막대 그래프");
		dialog.setScene(scene);
		dialog.show();*/
		
	}
	
	@FXML public void selected(MouseEvent event) {
		
		if(tbView.getSelectionModel().isEmpty()) {
			return;
		}
		
		// 새 창띄우기
		Stage dialog = new Stage(StageStyle.UTILITY);
		
		// 모달창 설정
		dialog.initModality(Modality.APPLICATION_MODAL);
		
		dialog.setTitle("추가");
		
		Parent parent = null;
		try {
			// 다른 컨트롤러를 사용하기위해 호출
			FXMLLoader loader = new FXMLLoader(getClass().getResource("pieChart.fxml"));
			
			// fxml 로드
			parent = loader.load();
			
			// 해당 fxml에 연결된 컨트롤러 호출
			PiController picont = loader.getController();
			
			//클릭된 한명의 정보를 가져와서 모달창의 데이터에 넣어준다.
			ScoreVO sv = tbView.getSelectionModel().getSelectedItem();
			
			// load를 할때 initailize가 무조건 먼저 실행되기때문에
			// initiallize속에 내용을 빼고 밖에 새로운 메서드를 만들어 작성.
			// 이후 컨트롤러의 새 메서드호출한다.
			// picont.piChart(); 이거가 fxml이 로드된 이후에 실행되어야한다는 뜻.
			// 이전에 initialize가 먼저실행되고 fxml이 로드되어서 예외발생했던것임.
			picont.setMem(sv);
			picont.piChart();
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}

		dialog.setTitle(tbView.getSelectionModel().getSelectedItem().getName() +"님의 파이그래프");
	
		
		
		
		Scene scene = new Scene(parent);
		dialog.setScene(scene);
		dialog.setResizable(false);// 크기고정
		dialog.show();
/*		dialog.setX(1250);
		dialog.setY(100);*/
		
		
		
		
		
		
		
		/*Stage dialog = new Stage(StageStyle.DECORATED);
		
		dialog.initModality(Modality.APPLICATION_MODAL);
		
		ScoreVO vo = tbView.getSelectionModel().getSelectedItem();
		
		PieChart pc = new PieChart();
		
		ObservableList<PieChart.Data> pcData = FXCollections.observableArrayList(
				new PieChart.Data("국어", vo.getKor()),
				new PieChart.Data("수학", vo.getMath()),
				new PieChart.Data("영어", vo.getEng())
			);
		
		pc.setLabelLineLength(30);
		pc.setLegendSide(Side.BOTTOM);
		pc.setData(pcData);
		
		Button btn = new Button("닫기");
		btn.setOnAction(e->{
			dialog.close();
		});
		
		VBox vb = new VBox();
		vb.getChildren().addAll(pc, btn);
		vb.setAlignment(Pos.CENTER);
		vb.setSpacing(20);
		
		Scene scene = new Scene(vb, 500, 500);
		dialog.setTitle("파이 그래프");
		dialog.setScene(scene);
		dialog.show();
	*/	
	}
	

}
