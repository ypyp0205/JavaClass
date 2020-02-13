package kr.or.ddit.board;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import kr.or.ddit.service.BoardServiceImpl;
import kr.or.ddit.vo.BoardVO;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextArea;

public class BoardFXMainController implements Initializable {
	private int from, to ,itemsForpage;
	private BoardServiceImpl bsi = BoardServiceImpl.getInstance();
	@FXML TableView<BoardVO> tableView;
	@FXML TableColumn<BoardVO, String> board_no;
	@FXML TableColumn<BoardVO, String> board_title;
	@FXML TableColumn<BoardVO, String> board_writer;
	@FXML TableColumn<BoardVO, String> board_date;
	@FXML TableColumn<BoardVO, String> board_content;
	@FXML Button mainUpdate;
	@FXML Button mainDelte;
	@FXML Button mainWrite;
	@FXML Button mainSearch;
	@FXML Button mainExit;
	@FXML ComboBox<String> comboMenu;
	@FXML TextField searchText;
	@FXML Pagination page;
	List<BoardVO> boardList;
	private ObservableList<BoardVO> tableData , currentPageData;
	private ObservableList<String> comboData;
	List <BoardVO> bv = new ArrayList<BoardVO>();
	Parent parent = null;
	Stage dialog = new Stage(StageStyle.UTILITY);
	@FXML TextField writeTitle;
	@FXML TextArea writeContents;
	@FXML Button writeBtn;
	@FXML Button writeCancel;
	@FXML TextField writer;
	@FXML Button display;

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		display();

}

	public void info(String headerText, String msg) {
		
		Alert infoAlert = new Alert(AlertType.ERROR);
		infoAlert.setTitle("알림");
		infoAlert.setHeaderText(headerText);
		infoAlert.setContentText(msg);
		infoAlert.showAndWait();
	}

	@FXML public void write(ActionEvent event) {
		
		

		Stage dialog = new Stage(StageStyle.UTILITY);
		AnchorPane root = new AnchorPane();
		root.setPrefSize(600, 430);
		
		Label label = new Label("작성할 제목 : ");
		label.setLayoutX(14);
		label.setLayoutY(10);
		label.setFont(new Font(14));
		
		Label label2 = new Label("작성할 작성자 : ");
		label2.setLayoutX(14);
		label2.setLayoutY(56);
		label2.setFont(new Font(14));
		
		Label label3 = new Label("작성할 내용 : ");
		label3.setLayoutX(14);
		label3.setLayoutY(98);
		label3.setFont(new Font(14));
		
		TextField txtField1 = new TextField();
		txtField1.setLayoutX(115);
		txtField1.setLayoutY(8);
		txtField1.setPrefHeight(16);
		txtField1.setPrefWidth(400);
		
		TextField txtField2 = new TextField();
		txtField2.setLayoutX(115);
		txtField2.setLayoutY(54);
		txtField1.setPrefHeight(16);
		txtField2.setPrefWidth(400);
		
		TextArea txtArea = new TextArea();
		txtArea.setPrefWidth(400);
		txtArea.setPrefHeight(279);
		txtArea.setLayoutX(115);
		txtArea.setLayoutY(98);
		
		Button btn1 = new Button("작성");
		btn1.setLayoutX(130);
		btn1.setLayoutY(386);
		
		Button btn2 = new Button("취소");
		btn2.setLayoutX(430);
		btn2.setLayoutY(386);
		
		root.getChildren().addAll(label,label2,label3,txtField1,txtField2,txtArea,btn1,btn2);
		
		Scene scene = new Scene(root);
		dialog.setScene(scene);
		dialog.show();
		dialog.setX(150);
		dialog.setY(100);
		dialog.setTitle("작성하기");
	
		btn1.setOnAction(e->{
		try {	
		
		String bdTitle = txtField1.getText();
		String bdWriter = txtField2.getText();
		String bdContent = txtArea.getText();
		BoardVO bv = new BoardVO();
		if(bdTitle.isEmpty() || bdWriter.isEmpty() || bdContent.isEmpty()) {
			info("빈칸 발견", "빈칸을 확인해주세요");
			return;
		}else {
		bv.setBoard_title(bdTitle);
		bv.setBoard_writer(bdWriter);
		bv.setBoard_content(bdContent);
		bsi.insertContent(bv);
		info("작성완료 ","게시글을 작성했습니다");
		display();
		dialog.close();
		}
		}catch (Exception ex) {
			info("오류!", "빈칸을 확인해주세요");
			return;
		}

		
		});
		btn2.setOnAction(e2->{
			dialog.close();
		});
		
		
	}

	@FXML public void update(ActionEvent event) {
		
		Stage dialog = new Stage(StageStyle.UTILITY);
		AnchorPane root = new AnchorPane();
		root.setPrefSize(600, 430);
		
		Label label = new Label("수정할 제목 : ");
		label.setLayoutX(14);
		label.setLayoutY(10);
		label.setFont(new Font(14));
		
		Label label2 = new Label("수정할 작성자 : ");
		label2.setLayoutX(14);
		label2.setLayoutY(56);
		label2.setFont(new Font(14));
		
		Label label3 = new Label("수정할 내용 : ");
		label3.setLayoutX(14);
		label3.setLayoutY(98);
		label3.setFont(new Font(14));
		
		TextField txtField1 = new TextField();
		txtField1.setLayoutX(115);
		txtField1.setLayoutY(8);
		txtField1.setPrefHeight(16);
		txtField1.setPrefWidth(400);
		
		TextField txtField2 = new TextField();
		txtField2.setLayoutX(115);
		txtField2.setLayoutY(54);
		txtField1.setPrefHeight(16);
		txtField2.setPrefWidth(400);
		
		TextArea txtArea = new TextArea();
		txtArea.setPrefWidth(400);
		txtArea.setPrefHeight(279);
		txtArea.setLayoutX(115);
		txtArea.setLayoutY(98);
		
		Button btn1 = new Button("수정");
		btn1.setLayoutX(130);
		btn1.setLayoutY(386);
		
		Button btn2 = new Button("취소");
		btn2.setLayoutX(430);
		btn2.setLayoutY(386);
		
		root.getChildren().addAll(label,label2,label3,txtField1,txtField2,txtArea,btn1,btn2);
		
		Scene scene = new Scene(root);
		dialog.setScene(scene);
		dialog.show();
		dialog.setX(150);
		dialog.setY(100);
		dialog.setTitle("수정하기");
	
		btn1.setOnAction(e->{
		try {	
		String bdno = tableView.getSelectionModel().getSelectedItem().getBoard_no();
		String bdTitle = tableView.getSelectionModel().getSelectedItem().getBoard_title();
		String bdWriter = tableView.getSelectionModel().getSelectedItem().getBoard_writer();
		String bdContent = tableView.getSelectionModel().getSelectedItem().getBoard_content();
		
		
		String bdTitle1 = txtField1.getText();
		String bdWriter1 = txtField2.getText();
		String bdContent1 = txtArea.getText();

		
		if(bdno.isEmpty() || bdTitle.isEmpty() || bdWriter.isEmpty() || bdContent.isEmpty()) {
			info("빈칸 발견", "빈칸을 확인해주세요");
			return;
		}else {
		Map<String,String> bmap = new HashMap<String,String>();
		bmap.put("bdNo", bdno);
		bmap.put("bdTitle", bdTitle);
		bmap.put("bdWriter", bdWriter);
		bmap.put("bdContent", bdContent);
		
		bmap.put("board_title", bdTitle1);
		bmap.put("board_writer", bdWriter1);
		bmap.put("board_content", bdContent1);
		bsi.updateBoard(bmap);
		info("수정완료 ","게시글을 수정했습니다");
		display();
		dialog.close();
		}
		}catch (Exception ex) {
			info("오류!", "수정할 게시글을 선택해주세요");
			return;
		}

		
		});
		btn2.setOnAction(e2->{
			Stage cancel = (Stage)btn2.getScene().getWindow();
			cancel.close();
			dialog.close();
		});
		
		
		
	}

	@FXML public void delte(ActionEvent event) {
		try {
			String bdno = tableView.getSelectionModel().getSelectedItem().getBoard_no();
			bsi.deleteBoard(bdno);

			info("삭제완료.","게시글을 지웠습니다");
			
			boardList = bsi.displayBoardAll();
			tableData = FXCollections.observableArrayList(boardList);
			tableView.setItems(tableData);
			}catch(Exception ex) {
				info("삭제 실패.","삭제할 게시글을 선택해 주세요");
				return;
			}
	}

	@FXML public void exit(ActionEvent event) {
		Stage ext = (Stage)mainExit.getScene().getWindow();
		ext.close();
	}


	@FXML public void combo(ActionEvent event) {
		String s = comboMenu.getSelectionModel().getSelectedItem();
		display();
		searchText.clear();
			try {
				mainSearch.setOnAction(e->{
					BoardVO bv = new BoardVO();
					if(s.equals("No")) {
					String bdno = searchText.getText();
					bv.setBoard_no(bdno);
					}else if(s.equals("제목")) {
					String bdtitle = searchText.getText();
					bv.setBoard_title(bdtitle);
					}else if(s.equals("작성자")) {
					String bdwriter = searchText.getText();
					bv.setBoard_writer(bdwriter);
					}
					
					List<BoardVO> bdlist = bsi.searchBoard(bv);

					if (bdlist.size() == 0) {
						info("검색끝", "검색할 회원 정보가 없습니다");
					}else {
						info("검색 끝","검색을 완료하였습니다");
						tableData = FXCollections.observableArrayList(bdlist);
						tableView.setItems(tableData);
					}			
				});
				
			} catch (Exception e) {
				info("오류 발생","다시 확인해 주세요");
			}
			
		
	}

	@FXML public void display(ActionEvent event) {

		display();
	}
	public void display() {
		comboData = FXCollections.observableArrayList(
		"No", "제목", "작성자"
					);
		
		comboMenu.setItems(comboData);
		board_no.setCellValueFactory(new PropertyValueFactory<>("board_no"));
		board_title.setCellValueFactory(new PropertyValueFactory<>("board_title"));
		board_writer.setCellValueFactory(new PropertyValueFactory<>("board_writer"));
		board_date.setCellValueFactory(new PropertyValueFactory<>("board_date"));
		board_content.setCellValueFactory(new PropertyValueFactory<>("board_content"));
		boardList = bsi.displayBoardAll();
		tableData = FXCollections.observableArrayList(boardList);
		tableView.setItems(tableData);
		itemsForpage = 18; //한페이지에 보여줄 항목 수 설정
		int totPageCount = tableData.size()%itemsForpage == 0 ?
				tableData.size()/itemsForpage : tableData.size()/itemsForpage + 1;
		
		page.setPageCount(totPageCount);// 전체페이지 수 설정
		
		page.setPageFactory(new Callback<Integer, Node>() {
			
			@Override
			public Node call(Integer pageIndex) {
				from = pageIndex * itemsForpage;
				to = from + itemsForpage -1;
				
				tableView.setItems(getTableViewData(from,to));
				
				return tableView;
			}
			/**
			 * TableView에 채워줄 데이터를 가져오는 메서드 
			 * @param from
			 * @param to
			 * @return
			 */
			private ObservableList<BoardVO> getTableViewData(int from, int to) {
				//현재페이지의 데이터 초기화
				currentPageData = FXCollections.observableArrayList();
				
				int totSize = tableData.size();
				for(int i = from; i<= to && i < totSize; i++) {
					currentPageData.add(tableData.get(i));
				}
				
				return currentPageData;
			}
		});
	}
}
