package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import Vo.FX_Vo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FX_Controller implements Initializable {
	ObservableList<FX_Vo> data =
			FXCollections.observableArrayList();

	@FXML TableColumn<FX_Vo,String> nameCol;
	@FXML TableColumn<FX_Vo,Integer> korCol;
	@FXML TableColumn<FX_Vo,Integer> matCol;
	@FXML TableColumn<FX_Vo,Integer> engCol;
	@FXML Button addBtn;
	@FXML Button barchartBtn;
	TextField name;
	TextField korScore;
	TextField matScore;
	TextField engScore;
	@FXML Button saveBtn;
	@FXML Button canBtn;
	@FXML TableView<FX_Vo> tableView= new TableView<>(data);
	private ObservableList<FX_Vo> tableData;
	List<FX_Vo> tableList = new ArrayList<FX_Vo>();
	
	BarChart<String, Number> barchart;
	CategoryAxis xAxis;
	NumberAxis yAxis;
	
	PieChart pieChart;


	
	Parent parent = null;



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Stage dialog = new Stage(StageStyle.UTILITY);
		tableView.setOnMouseClicked(e->{
			
			dialog.initModality(Modality.APPLICATION_MODAL);
			
			// 4. 자식창이 나타날 컨테이너 객체 생성

			try {
				parent = FXMLLoader.load(getClass().getResource("/pieChart.fxml"));
			}catch (IOException ex) {
				ex.printStackTrace();
			}
			// 5. Scene객체 생성해서 컨테이너 객체 추가
			Scene scene = new Scene(parent);
			
			pieChart = (PieChart) parent.lookup("#pieChart");
			
			FX_Vo sv = tableView.getSelectionModel().getSelectedItem();
			
			
			//차트에 나타날 데이터 구성하기
			try {
			ObservableList<PieChart.Data> pieChartData = 
					FXCollections.observableArrayList(
							new PieChart.Data("국어", sv.getKorScore()),
							new PieChart.Data("수학", sv.getMatScore()),
							new PieChart.Data("영어", sv.getEngScore())
							);
			
			pieChart.setLabelLineLength(50);
			pieChart.setLegendSide(Side.BOTTOM);//범례가 나타날 위치
			pieChart.setData(pieChartData); //데이터 설정
			dialog.setTitle(tableView.getSelectionModel().getSelectedItem().getName() +"님의 파이그래프");
			}
			catch (Exception ex) {
				
			}
			
			
			// 6. Stage객체에 Scene객체 추가
			dialog.setScene(scene);
			dialog.setResizable(false);//크기고정
			dialog.show();
			dialog.setX(1250);
			dialog.setY(100);
			//TableView에서 선택한 줄의 데이터를 가져온다.
			
		});

		
	}

	
	@FXML public void addBtnClicked() {
		Stage dialog = new Stage(StageStyle.UTILITY);
		dialog.setTitle("추가");
		dialog.initModality(Modality.APPLICATION_MODAL);
		// 4. 자식창이 나타날 컨테이너 객체 생성

		try {
			parent = FXMLLoader.load(getClass().getResource("/addScore.fxml"));
		}catch (IOException ex) {
			ex.printStackTrace();
		}
		// 5. Scene객체 생성해서 컨테이너 객체 추가
		Scene scene = new Scene(parent);
		
		// 6. Stage객체에 Scene객체 추가
		dialog.setScene(scene);
		dialog.setResizable(false);//크기고정
		dialog.show();
		dialog.setX(460);
		dialog.setY(230);
		
		name = (TextField) parent.lookup("#name");
		korScore = (TextField) parent.lookup("#korScore");
		matScore = (TextField) parent.lookup("#matScore");
		engScore = (TextField) parent.lookup("#engScore");
		
		Button sbtn = (Button) parent.lookup("#saveBtn");
		sbtn.setOnAction(e->saveBtn(e));

	}
	
	public void saveBtn(ActionEvent e) {
		Stage dialog = new Stage(StageStyle.UTILITY);

		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		korCol.setCellValueFactory(new PropertyValueFactory<>("korScore"));
		matCol.setCellValueFactory(new PropertyValueFactory<>("matScore"));
		engCol.setCellValueFactory(new PropertyValueFactory<>("engScore"));
		


		// ResultSet객체에 저장되어 있는 자료를 반복문과 next()메서드를 이용하여 차례로 읽어와 처리한다.
			 
		try {
			FX_Vo scvo = new FX_Vo();

			if(name.getText().isEmpty() ||korScore.getText().isEmpty() || matScore.getText().isEmpty() || engScore.getText().isEmpty()){
				info("데이터 오류", "빈칸을 확인해주세요");
				return;
			}
		
			else if(!Pattern.matches("^[0-9]+$", korScore.getText()) || Integer.parseInt(korScore.getText()) > 100 ) {
				info("데이터오류", "국어점수는 '정수형'이나 '100점 이하'로 입력 주세요.");
				korScore.requestFocus(); // 해당 객체에 포커스 주기
				return;
			}
			else if(!Pattern.matches("^[0-9]+$", matScore.getText()) || Integer.parseInt(matScore.getText()) > 100 ) {
				info("데이터오류", "수학점수는 '정수형'이나 '100점 이하'로 입력해주세요.");
				matScore.requestFocus(); // 해당 객체에 포커스 주기
				return;
			}
			else if(!Pattern.matches("^[0-9]+$", engScore.getText()) || Integer.parseInt(engScore.getText()) > 100) {
				info("데이터오류", "영어점수는 '정수형'이나 '100점 이하'로 입력해주세요.");
				engScore.requestFocus(); // 해당 객체에 포커스 주기
				return;
			}

			else {
			 scvo.setName(name.getText());	
			 scvo.setKorScore(Integer.parseInt(korScore.getText()));
			 scvo.setMatScore(Integer.parseInt(matScore.getText()));
			 scvo.setEngScore(Integer.parseInt(engScore.getText()));
			 
			 tableList.add(scvo);//VO 객체에 담아서 어레이리스트에 담음.
			 info("입력완료",name.getText()+"님 점수를 입력하였습니다");
			 
			}
		}catch (Exception ex) {
			
		}
		
			 
		 tableData = FXCollections.observableArrayList(tableList); //어레이리스트에 담은 데이터를 observableArrayList에 담음
		 
		 tableView.setItems(tableData); //observableArrayList에 담은 데이터를 테이블뷰에 세팅.

		 dialog.close();
	
		
	}

	@FXML public void barchartBtnClicked() {
		Stage dialog = new Stage(StageStyle.UTILITY);
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.setTitle("막대 그래프");
		
		// 4. 자식창이 나타날 컨테이너 객체 생성

		try {
			parent = FXMLLoader.load(getClass().getResource("/barChart.fxml"));
		}catch (IOException ex) {
			ex.printStackTrace();
		}
		// 5. Scene객체 생성해서 컨테이너 객체 추가
		Scene scene = new Scene(parent);
		
		
		barchart = (BarChart<String, Number>) parent.lookup("#barchart");
		xAxis = (CategoryAxis) parent.lookup("#xAxis");
		yAxis =  (NumberAxis) parent.lookup("#yAxis");
		
		
		
		// BarChart에 나타날 데이터 구성하기
		XYChart.Series<String, Number> ser1 = new XYChart.Series<>();//내부클래스
		XYChart.Series<String, Number> ser2 = new XYChart.Series<>();//내부클래스
		XYChart.Series<String, Number> ser3 = new XYChart.Series<>();//내부클래스
		
		ser1.setName("국어");
		ser2.setName("수학");
		ser3.setName("영어");
		for(int i =0; i < tableList.size();i++) {
			
		ser1.getData().add(new XYChart.Data<String, Number>(tableList.get(i).getName(),tableList.get(i).getKorScore()));
		ser2.getData().add(new XYChart.Data<String, Number>(tableList.get(i).getName(),tableList.get(i).getMatScore()));
		ser3.getData().add(new XYChart.Data<String, Number>(tableList.get(i).getName(),tableList.get(i).getEngScore()));
		}

		barchart.getData().addAll(ser1, ser2, ser3);

		// 6. Stage객체에 Scene객체 추가
		dialog.setScene(scene);
		dialog.setResizable(false);//크기고정
		dialog.show();
		dialog.setX(1250);
		dialog.setY(550);
		


		
	}




	@FXML public void canBtn() {
		
	    Stage stage = (Stage) canBtn.getScene().getWindow();
	    stage.close();
	}
	
	public void info(String headerText, String msg) {
		
		Alert infoAlert = new Alert(AlertType.ERROR);
		infoAlert.setTitle("알림");
		infoAlert.setHeaderText(headerText);
		infoAlert.setContentText(msg);
		infoAlert.showAndWait();
	}

}

