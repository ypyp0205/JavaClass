package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import vo.ScoreVO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class BarController implements Initializable{

	@FXML BarChart<String, Number> barchart;
	@FXML CategoryAxis xAxis;
	@FXML NumberAxis yAxis;
	@FXML Button canBtn;

	private ObservableList<ScoreVO> list;
	
	// 이부분이 왜필요한거지??
	 	public ObservableList<ScoreVO> getList() {
		return list;
	}

	public void setList(ObservableList<ScoreVO> list) {
		this.list = list;
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// BarChart에 나타날 데이터 구성하기
		
		
	}

	@FXML public void canBtn(ActionEvent event) {
		Stage main = (Stage) canBtn.getScene().getWindow();
		main.close();
	}
	
	public void chartCreate() {
		XYChart.Series<String, Number> ser1 = new XYChart.Series<>();//내부클래스
		XYChart.Series<String, Number> ser2 = new XYChart.Series<>();//내부클래스
		XYChart.Series<String, Number> ser3 = new XYChart.Series<>();//내부클래스
		
		ser1.setName("국어");
		ser2.setName("수학");
		ser3.setName("영어");
		for(int i =0; i < list.size();i++) {
			ser1.getData().add(new XYChart.Data<String, Number>(list.get(i).getName(),list.get(i).getKor()));
			ser2.getData().add(new XYChart.Data<String, Number>(list.get(i).getName(),list.get(i).getMath()));
			ser3.getData().add(new XYChart.Data<String, Number>(list.get(i).getName(),list.get(i).getEng()));
		}

		barchart.getData().addAll(ser1, ser2, ser3);
	}

}
