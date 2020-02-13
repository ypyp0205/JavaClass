package kr.or.ddit.board.controller;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.xml.ws.handler.Handler;

import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.vo.BoVo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.TextFlow;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Pagination;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;

public class BoardController implements Initializable{
	private Stage primaryStage;
	BoardServiceImpl boserv =BoardServiceImpl.getInstance();
	@FXML Label boardtitle;
	@FXML TableView<BoVo> tableview;
	@FXML TableColumn<BoVo, Integer> colnum;
	@FXML TableColumn<BoVo, String> coltitle;
	@FXML TableColumn<BoVo, String> colwrite;
	@FXML TableColumn<BoVo, String> colday;
	@FXML TableColumn<BoVo, Integer> colcnt;
	@FXML Pagination pagenum;
	@FXML Button mainbtn;
	@FXML Button repair;
	@FXML Button deletebtn;
	@FXML Button findbtn;
	@FXML TextField titlefield;
	@FXML TextField writerfield;
	@FXML TextArea contentarea;
	@FXML Label titlerepair, writerrepair, teatarearepair;
	@FXML TextField savetitle;
	@FXML TextField savewrite;
	@FXML TextArea savecont;
	@FXML TextField findfield;
	@FXML ComboBox<String> combo1;
	private int from, to, itemsForPage;
	private ObservableList<BoVo> listob, currentPageData;
	private ObservableList<String> comboboxlist, seletList;
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage =primaryStage;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tableLive();
		comboboxlist= FXCollections.observableArrayList(
				"번호","제목","작성자"
				);
		combo1.setItems(comboboxlist);									//comboBox set
		
		tableview.setOnMouseClicked(new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		          if(event.getClickCount()>1) {
		        	  tableview.setItems(listob);
		        	  
		        	  picktview(event);
		          }
		     }
		});
	}
	
	public void tableLive() {
		List<BoVo> allcell = new ArrayList<>();
		allcell= boserv.mainViewList();
		
		colnum.setCellValueFactory(new PropertyValueFactory<>("nums"));
		coltitle.setCellValueFactory(new PropertyValueFactory<>("titles"));
		colwrite.setCellValueFactory(new PropertyValueFactory<>("names"));
		colday.setCellValueFactory(new PropertyValueFactory<>("dateymd"));
		colcnt.setCellValueFactory(new PropertyValueFactory<>("cnt"));
		
		listob=FXCollections.observableArrayList(allcell);				//comboBox List	
		itemsForPage = 13; //한페이지에 보여줄 항목 수 설정 
		int totPageCount = listob.size() % itemsForPage == 0 ? listob.size() / itemsForPage : listob.size() / itemsForPage +1;
		pagenum.setPageCount(totPageCount);// 전체 페이지 수 설정
		
		pagenum.setPageFactory(new Callback<Integer, Node>(){

			@Override
			public Node call(Integer pageIndex) {
				from = pageIndex * itemsForPage;
				to = from + itemsForPage -1;
				tableview.setItems(getTableViewData(from,to));
				return tableview;
			}

		});
	}
	private void picktview(MouseEvent e) {										//테이블뷰 클릭Action
		Stage dialog3 = new Stage(StageStyle.UTILITY);
		dialog3.initOwner(primaryStage);
		Parent prt = null;
		
		try {
			prt = FXMLLoader.load(getClass().getResource("repairDialog.fxml"));
			titlerepair = (Label) prt.lookup("#titlerepair");
			writerrepair = (Label) prt.lookup("#writerrepair");
			teatarearepair = (Label) prt.lookup("#teatarearepair");
			Button repairbtn = (Button) prt.lookup("#repairbtn");
			Button deletebtn = (Button) prt.lookup("#deletebtn");		//삭제버튼ID 
			Scene scene = new Scene(prt);
			dialog3.setTitle("님 글");
			dialog3.setScene(scene);
			primaryStage.hide();
			dialog3.show();
			
			dialog3.setOnCloseRequest(e5->{
				primaryStage.show();
			});
		//TabaleView에서 선택한 줄의 데이터를 가져온다.
		BoVo re = tableview.getSelectionModel().getSelectedItem();
		System.out.println(tableview.getSelectionModel().getSelectedItems().get(0).getNames());
		System.out.println(re.getTitles());
		titlerepair.setText(re.getTitles());
		writerrepair.setText(re.getNames());
		teatarearepair.setText(re.getCont());
		//----------------------------------------------------------------------------------
		re.setCnt(re.getCnt()+1);
		int cnt= re.getCnt();
		
		BoVo vo2 = new BoVo();
		vo2.setCnt(cnt);
		vo2.setNums(re.getNums());
		boserv.viewsCount(vo2);												//조회수 증가 쿼리
		tableview.refresh();// 			<----------테이블 한번 새로고침!!!!!
		
		deletebtn.setOnAction(new EventHandler<ActionEvent>() {		//삭제버튼 Action	(람다식안쓰고)

			@Override
			public void handle(ActionEvent event) {
				TextInputDialog	 inputDialog = new TextInputDialog();
				inputDialog.setTitle("삭제");
				inputDialog.setHeaderText("삭제 하실 작성자의 이름을 적어주세요.");
				inputDialog.setContentText("작성자이름:");
				
				Optional<String> result = inputDialog.showAndWait();
				if(re.getNames().equals(result.get())) {
					System.out.println("삭제되었습니다.");
					BoVo vo = new BoVo();
					vo.setTitles(re.getTitles());
					vo.setNames(re.getNames());
					vo.setNums(re.getNums());
					boserv.drop(vo);
//					for(int i=0; i<tableview.getColumns().size(); i++) {
//						tableview.getColumns().get(i).setVisible(false);
//						tableview.getColumns().get(i).setVisible(true);
//						System.out.println("!!!!!");
//					}
				}else {
					
				}
				
				primaryStage.show();
				tableLive();
				dialog3.close();
			}
});
		
		
		
	//---------------------------------------------------------------------------------------
		repairbtn.setOnAction(e3->{											//<---수정 버튼		
			Stage dialog4 = new Stage(StageStyle.UTILITY);
			dialog4.initOwner(primaryStage);
			Parent prt2 = null;
			
			try {
				prt2 = FXMLLoader.load(getClass().getResource("saverepair.fxml"));	//글수정Stage
				savetitle = (TextField) prt2.lookup("#savetitle");
				savewrite = (TextField) prt2.lookup("#savewrite");
				savecont = (TextArea) prt2.lookup("#savecont");
				Button savebtn = (Button) prt2.lookup("#savebtn");
				
				Scene scene2 = new Scene(prt2);
				dialog4.setTitle("글수정 창");
				dialog4.setScene(scene2);
				dialog3.hide();
				dialog4.show();
				
				savetitle.setText(re.getTitles());
				savewrite.setText(re.getNames());
				savecont.setText(re.getCont());
				
	//---------------------------------------------------------------------------------------		
				savebtn.setOnAction(e5->{									//수정완료 버튼 Action
					Alert al = new Alert(AlertType.CONFIRMATION);
					al.setTitle("저장");
					al.setContentText("정말로 수정하시겠습니까?");
					
					ButtonType confirm = al.showAndWait().get();
					
					if(confirm == ButtonType.OK) {
						boolean flag = false;
						BoVo vo = new BoVo();
						vo.setTitles(savetitle.getText());
						vo.setNames(savewrite.getText());
						vo.setCont(savecont.getText());
						vo.setNums(re.getNums());
						
						flag=boserv.saveUp(vo);
						
						if(flag == true) {
							primaryStage.show();
							tableLive();
							dialog4.close();
							dialog3.close();
							tableview.refresh();// <----------테이블 한번 새로고침!!!!!
							
						}else {
							System.out.println("수정실패 다시입력하세요.");
						}
						
					}else if(confirm == ButtonType.CANCEL){
						System.out.println("취소되었습니다.");
					}
					
					
				});//savebtn.setOnAction 끝
			
			}catch(IOException e4){
				e4.printStackTrace();
			}
			
		});//repairbtn.setOnAction 끝
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		
		
		
	}//picktview 끝
	private ObservableList<BoVo> getTableViewData(int from, int to) {
		currentPageData = FXCollections.observableArrayList();
		
		int totSize = listob.size();
		for(int i = from; i<=to && i<totSize; i++) {
			currentPageData.add(listob.get(i));
		}
		return currentPageData;
	}
	
	@FXML public void mainAct(ActionEvent event) {								//메인에 글작성버튼 Action
		Stage dialog2 = new Stage(StageStyle.UTILITY);							//글추가 Stage
		dialog2.initOwner(primaryStage);
		Parent prt = null;
		
		try {
			prt = FXMLLoader.load(getClass().getResource("addwindow.fxml"));
		titlefield = (TextField) prt.lookup("#titlefield");
		writerfield = (TextField) prt.lookup("#writerfield");
		contentarea = (TextArea) prt.lookup("#contentarea");
		Button addbtn = (Button) prt.lookup("#addbtn");
		Scene scene = new Scene(prt);
		dialog2.setTitle("글쓰기 창");
		dialog2.setScene(scene);
		primaryStage.hide();
		dialog2.show();

		
		
		addbtn.setOnAction(evv->{												// 글작성 추가버튼 Action
			boolean flag= false;
			String ti = titlefield.getText();
			String nam = writerfield.getText();
			String con = contentarea.getText();
			if(ti.isEmpty() || nam.isEmpty() || con.isEmpty()) {
				return;
			}
			Date date = new Date();
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat format2 = new SimpleDateFormat(" HH:mm:ss");
			String time1 =format1.format(date);
			String time2 =format2.format(date);
			
			BoVo vo= new BoVo();
			vo.setTitles(ti);
			vo.setNames(nam);
			vo.setCont(con);
			vo.setDateymd(time1);
			vo.setDatehms(time2);
			flag=boserv.dialogwrite(vo);
			if(flag==false) {
				return;
			}
			listob.add(vo);
			Alert alertInfomatrion  = new Alert(AlertType.INFORMATION);
			alertInfomatrion.setTitle("글작성 ");
			alertInfomatrion.setContentText("글작성 완료");
			alertInfomatrion.showAndWait();	//Alert창 보이기
			primaryStage.show();
			tableLive();
			dialog2.close();
		});
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}	//mainAct 끝
	//--------------------------------------검색 버튼 -------------------------------------------------
	@FXML public void comboSelect(ActionEvent event) {				//콤보박스
		//글번호, 글 제목 ,작성자 기준 콤보리스트
		
		System.out.println(combo1.getValue());

	}			
	@FXML public void findbtn(ActionEvent event) {					//!!검색
		List<BoVo> selList = new ArrayList<>();
		BoVo search = new BoVo();
		HashMap<String, String> combotext = new HashMap<>();
		
		String comboitem=combo1.getSelectionModel().getSelectedItem();
		String txtstr=findfield.getText();
		
		if(!comboitem.isEmpty() && !txtstr.isEmpty()) {
			if(comboitem.equals("번호")) {
				combotext.put("nums", txtstr);
			}else if(comboitem.equals("제목")) {
				combotext.put("titles", txtstr);
			}else {
				combotext.put("names", txtstr);
			}
		}else {
				System.out.println("값을 입력해주세요");
			return;
		}
		selList = boserv.listSearch(combotext);
	}

}
