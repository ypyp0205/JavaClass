package Controller;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import Service.ProdInfoService;
import Service.ProdInfoServiceImpl;
import Vo.LprodVO;
import Vo.ProdVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;


public class ProdInfoController {

	ProdInfoService service = ProdInfoServiceImpl.getInstance();
	Map<String, String> map = new HashMap<String, String>();
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TableView<ProdVO> prodInfoView;

	@FXML
	private TableColumn<ProdVO, String> p_outline;

	@FXML
	private TableColumn<ProdVO, Integer> p_cost;

	@FXML
	private TableColumn<ProdVO, String> p_name;

	@FXML
	private TableColumn<ProdVO, String> p_buyer;

	@FXML
	private ComboBox<ProdVO> p_name_box;

	@FXML
	private TableColumn<ProdVO, Integer> p_sale;

	@FXML
	private TableColumn<ProdVO, String> p_lgu;

	@FXML
	private TableColumn<ProdVO, Integer> p_price;

	@FXML
	private TableColumn<ProdVO, String> p_detail;

	@FXML
	private TableColumn<ProdVO, String> p_id;

	@FXML
	private ComboBox<LprodVO> lp_name_box;

	@FXML
	void lpSelect(ActionEvent event) {
		String lpName = lp_name_box.getValue().getLprod_nm();
		map.put("lpGu", lp_name_box.getValue().getLprod_gu());
		ObservableList<ProdVO> list = FXCollections.observableArrayList(service.getPlist(lpName));
		p_name_box.setItems(list);
	}

	@FXML
	void pSelect(ActionEvent event) {
		if (p_name_box.getValue() == null) {
			return;
		}
		map.put("pName", p_name_box.getValue().getProd_name());
		ObservableList<ProdVO> list = FXCollections.observableArrayList(service.getSearchData(map));

		// 테이블 뷰에 데이터 셋팅
		prodInfoView.setItems(list);

		p_id.setCellValueFactory(new PropertyValueFactory<>("prod_id"));
		p_name.setCellValueFactory(new PropertyValueFactory<>("prod_name"));
		p_lgu.setCellValueFactory(new PropertyValueFactory<>("prod_lgu"));
		p_buyer.setCellValueFactory(new PropertyValueFactory<>("prod_buyer"));
		p_cost.setCellValueFactory(new PropertyValueFactory<>("prod_cost"));
		p_price.setCellValueFactory(new PropertyValueFactory<>("prod_price"));
		p_sale.setCellValueFactory(new PropertyValueFactory<>("prod_sale"));
		p_outline.setCellValueFactory(new PropertyValueFactory<>("prod_outline"));
		p_detail.setCellValueFactory(new PropertyValueFactory<>("prod_detail"));
	}

	@FXML
	void initialize() {
		// 1번째 콤보박스 세팅
		// 콤보박스의 리스트
		lp_name_box.setCellFactory(new Callback<ListView<LprodVO>, ListCell<LprodVO>>() {
			@Override
			public ListCell<LprodVO> call(ListView<LprodVO> param) {
				return new ListCell<LprodVO>() {
					@Override
					protected void updateItem(LprodVO item, boolean empty) {
						super.updateItem(item, empty);
						if (!empty) {
							setText(item.getLprod_nm());
						}
					}
				};
			}
		});

		// 콤보박스의 보여지는 부분
		lp_name_box.setButtonCell(new ListCell<LprodVO>() {
			@Override
			protected void updateItem(LprodVO item, boolean empty) {
				super.updateItem(item, empty);
				if (!empty) {
					setText(item.getLprod_nm());
				}
			}
		});

		// 2번째 콤보박스 셋팅
		// 콤보박스 리스트

		p_name_box.setCellFactory(new Callback<ListView<ProdVO>, ListCell<ProdVO>>() {
			@Override
			public ListCell<ProdVO> call(ListView<ProdVO> param) {
				return new ListCell<ProdVO>() {
					@Override
					protected void updateItem(ProdVO item, boolean empty) {
						super.updateItem(item, empty);
						if (!empty) {
							setText(item.getProd_name());
						}
					}
				};
			}
		});

		// 콤보박스의 보여지는 부분
		p_name_box.setButtonCell(new ListCell<ProdVO>() {
			@Override
			protected void updateItem(ProdVO item, boolean empty) {
				super.updateItem(item, empty);
				if (!empty) {
					setText(item.getProd_name());
				}
			}
		});

		// 1번째 콤보박스에 셋팅
		List<LprodVO> Lplist = service.getLpName();
		ObservableList<LprodVO> list = FXCollections.observableArrayList(Lplist);
		lp_name_box.setItems(list);
	}
}
