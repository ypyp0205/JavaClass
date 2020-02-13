package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import service.FX_Post_Service;
import service.FX_Post_ServiceImpl;
import vo.FX_Post_VO;


public class FX_Post_Controller implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<FX_Post_VO> tableView;
    @FXML
    private TableColumn<FX_Post_VO, String> zipcode;
    @FXML
    private TableColumn<FX_Post_VO, String> sido;
    @FXML
    private TableColumn<FX_Post_VO, String> gugun;
    @FXML
    private TableColumn<FX_Post_VO, String> dong;
    @FXML
    private TableColumn<FX_Post_VO, String> bunji;
    @FXML
    private ComboBox<String> combobox;
    @FXML
    private TextField tfid;
    @FXML
    private Button btn;

    FX_Post_Service  Postnumservice = FX_Post_ServiceImpl.getInstance();
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	//컬럼 연결하기
	zipcode.setCellValueFactory(new PropertyValueFactory<>("zipcode"));
	sido.setCellValueFactory(new PropertyValueFactory<>("sido"));
	gugun.setCellValueFactory(new PropertyValueFactory<>("gugun"));
	dong.setCellValueFactory(new PropertyValueFactory<>("dong"));
	bunji.setCellValueFactory(new PropertyValueFactory<>("bunji"));
		
	combobox.getItems().addAll("동이름", "우편번호");
	
	btn.setOnAction(e ->{
//		String search = null;
//		Map<String, String> map = new HashMap<>();
		if (combobox.getValue() == null || combobox.getValue().equals("")) {
		} else if (combobox.getValue().equals("동이름")) {
			String dong = tfid.getText();
			
			ObservableList<FX_Post_VO> list = FXCollections.observableArrayList(Postnumservice.getSearchdong(dong));
			
			tableView.setItems(list);
		} else if (combobox.getValue().equals("우편번호")) {
			String zipcode = tfid.getText();
			
			ObservableList<FX_Post_VO> list = FXCollections.observableArrayList(Postnumservice.getSearchzipcode(zipcode));
			
			tableView.setItems(list);
		} else {
			System.out.println("오류발생");
			return;
		}

	
		});
	}
	
	
	
}
