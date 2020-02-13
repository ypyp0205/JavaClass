package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import vo.ScoreVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class PiController implements Initializable{
	@FXML PieChart pieChart;
	@FXML Button canBtn;
	
	ScoreVO mem;
	
	public void setMem(ScoreVO mem) {
		this.mem = mem;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		

//		System.out.println(mem.toString());
		
//		ScoreVO sv = tableView.getSelectionModel().getSelectedItem();
		
		
/*		//차트에 나타날 데이터 구성하기
		try {
		ObservableList<PieChart.Data> pieChartData = 
				FXCollections.observableArrayList(
						new PieChart.Data("국어", sv.getKor()),
						new PieChart.Data("수학", sv.getMath()),
						new PieChart.Data("영어", sv.getEng())
						);
		
		pieChart.setLabelLineLength(50);
		pieChart.setLegendSide(Side.BOTTOM);//범례가 나타날 위치
		pieChart.setData(pieChartData); //데이터 설정
		
		}
		catch (Exception ex) {
			
		}*/
		
	}
	
	public void piChart() {
		System.out.println(mem);
		try {
			ObservableList<PieChart.Data> pieChartData = 
					FXCollections.observableArrayList(
							new PieChart.Data("국어", mem.getKor()),
							new PieChart.Data("수학", mem.getMath()),
							new PieChart.Data("영어", mem.getEng())
							);
			
			pieChart.setLabelLineLength(50);
			pieChart.setLegendSide(Side.BOTTOM);//범례가 나타날 위치
			pieChart.setData(pieChartData); //데이터 설정
			
			}
			catch (Exception ex) {
				
			}
	}

	@FXML public void canBtn(ActionEvent event) {
		Stage main = (Stage) canBtn.getScene().getWindow();
		main.close();
	}

}
