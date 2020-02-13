package kr.or.ddit.basic;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.scene.control.TableColumn;
import javafx.scene.Node;
import javafx.scene.control.Pagination;

public class PagingController implements Initializable {

//	@FXML TableView tableView; // 타입안넣으면 제너릭인데 왜 타입설정안했냐고 노랑줄 띄워줌. 아래처럼 타입넣어줘야한다.
	@FXML TableView<MemberVO> tableView;
	@FXML TableColumn<MemberVO, String> id;
	@FXML TableColumn<MemberVO, String> name;
	@FXML TableColumn<MemberVO, String> address;
	@FXML Pagination pagination;
	// 사용할 변수 추가합니다.
	private int from, to, itemsForPage; // 한페이지에 몇개씩보여줄건지랑, 범위?
	
	//정보담는 그릇
	private ObservableList<MemberVO> allTableData, currentPageData;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 멤버VO랑 컬럼이랑 매칭시킨다.
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		address.setCellValueFactory(new PropertyValueFactory<>("address"));
		
		// 전체 테이블 데이터 설정
		allTableData = FXCollections.observableArrayList();
		
		// 데이터 넣기
		allTableData.add(new MemberVO("1", "홍길동1", "대전시 중구 대흥동 대덕인재개발원"));
		allTableData.add(new MemberVO("2", "홍길동2", "대전시 중구 대흥동 대덕인재개발원"));
		allTableData.add(new MemberVO("3", "홍길동3", "대전시 중구 대흥동 대덕인재개발원"));
		allTableData.add(new MemberVO("4", "홍길동4", "대전시 중구 대흥동 대덕인재개발원"));
		allTableData.add(new MemberVO("5", "홍길동5", "대전시 중구 대흥동 대덕인재개발원"));
		allTableData.add(new MemberVO("6", "홍길동6", "대전시 중구 대흥동 대덕인재개발원"));
		allTableData.add(new MemberVO("7", "홍길동7", "대전시 중구 대흥동 대덕인재개발원"));
		allTableData.add(new MemberVO("8", "홍길동8", "대전시 중구 대흥동 대덕인재개발원"));
		allTableData.add(new MemberVO("9", "홍길동9", "대전시 중구 대흥동 대덕인재개발원"));
		allTableData.add(new MemberVO("10", "홍길동10", "대전시 중구 대흥동 대덕인재개발원"));
		allTableData.add(new MemberVO("11", "홍길동11", "대전시 중구 대흥동 대덕인재개발원"));
		allTableData.add(new MemberVO("12", "홍길동12", "대전시 중구 대흥동 대덕인재개발원"));
		allTableData.add(new MemberVO("13", "홍길동13", "대전시 중구 대흥동 대덕인재개발원"));
		allTableData.add(new MemberVO("14", "홍길동14", "대전시 중구 대흥동 대덕인재개발원"));
		allTableData.add(new MemberVO("15", "홍길동15", "대전시 중구 대흥동 대덕인재개발원"));
		allTableData.add(new MemberVO("16", "홍길동16", "대전시 중구 대흥동 대덕인재개발원"));
		allTableData.add(new MemberVO("17", "홍길동17", "대전시 중구 대흥동 대덕인재개발원"));
		allTableData.add(new MemberVO("18", "홍길동18", "대전시 중구 대흥동 대덕인재개발원"));
		
		itemsForPage = 5; // 한 페이지에 보여줄 항목 수 설정
		int totPageCount = allTableData.size()%itemsForPage == 0 ? allTableData.size()/itemsForPage : allTableData.size()/itemsForPage +1;
		//                   어레이리스트 전체사이즈%내가설정한 보여줄항목수 == 0.    
		pagination.setPageCount(totPageCount); // 전체페이지 수 설정
		
		// 페이징처리의 버튼을 누를때마다 이벤트처리를 어떻게 해줄지
		pagination.setPageFactory(new Callback<Integer, Node>() { //셋페이지팩토리 파라미터는 콜백객체
			
			@Override
			public Node call(Integer pageindex) { //???
				from = pageindex * itemsForPage;
				to = from + itemsForPage -1;
				tableView.setItems(getTableViewData(from, to));
				
				return tableView;
						
				
			}

		});
	
	}
	
	/**
	 * TableView에 재워줄 데이터를 가져오는 메서드
	 * @param from
	 * @param to
	 * @return
	 */
	private ObservableList<MemberVO> getTableViewData(int from, int to) {
		
		// 현재 페이지의 데이터 초기화
		currentPageData = FXCollections.observableArrayList();
		
		int totSize = allTableData.size();
		for(int i = from; i <=to && i < totSize; i++) {
			currentPageData.add(allTableData.get(i));
		}
		return currentPageData;
	}
	
	
	
	// 디비접근을 한다 가정하고 만든다.?
	

}



// 멤버VO랑 생성자, 게터세터 만들었다.

