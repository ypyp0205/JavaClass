package kr.or.ddit.basic;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class T18_BarChartTest extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		// X축, Y축 이용한 Chart는 해당 축 객체를 생성한다.
		
		// 축의 값이 주로 문자열일 때 사용하는 객체
		CategoryAxis xAxis = new CategoryAxis();
		
		// 축의 값이 숫자일 때 사용하는 객체
		NumberAxis yAxis = new NumberAxis();
		
		//	위에서 만든 축 정보를 이용한 BarChart객체 생성
		BarChart<String, Number> bc = new BarChart<>(xAxis, yAxis);
		
		bc.setTitle("차트 Title");
		xAxis.setLabel("나라");
		yAxis.setLabel("가격");
		
		//BarChart에 나타날 데이터 구성하기
		XYChart.Series<String , Number> ser1 = new XYChart.Series<>();
		ser1.setName("2015년");
		ser1.getData().add(new XYChart.Data<String, Number>("호주",26000));
		ser1.getData().add(new XYChart.Data<String, Number>("브라질",20000));
		ser1.getData().add(new XYChart.Data<String, Number>("프랑스",10000));
		ser1.getData().add(new XYChart.Data<String, Number>("이탈리아",35000));
		ser1.getData().add(new XYChart.Data<String, Number>("미국",15000));
		
		XYChart.Series<String, Number> ser2 = new XYChart.Series<>();
		ObservableList<XYChart.Data<String, Number>> ser2List = FXCollections.observableArrayList();
		ser2List.addAll(new XYChart.Data<>("호주",50000),
						  new XYChart.Data<>("브라질" ,41000),
						  new XYChart.Data<>("프랑스" ,45000),
						  new XYChart.Data<>("이탈리아" ,11700),
						  new XYChart.Data<>("미국" ,14000));
						  
						  
		ser2.setName("2016년");
		ser2.setData(ser2List);
		
		XYChart.Series<String, Number> ser3 = new XYChart.Series<>();
		ser3.setName("2017년");
		ser3.getData().add(new XYChart.Data<>("호주", 45000));
		ser3.getData().add(new XYChart.Data<>("브라질", 44000));
		ser3.getData().add(new XYChart.Data<>("프랑스", 18000));
		ser3.getData().add(new XYChart.Data<>("이탈리아", 17000));
		ser3.getData().add(new XYChart.Data<>("미국", 87000));
		
		bc.getData().addAll(ser1, ser2, ser3);
		
		Scene scene = new Scene(bc, 800, 600);
		primaryStage.setTitle("BarChart연습");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}	
}
