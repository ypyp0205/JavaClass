package 컨트롤객체연습;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;
import javafx.scene.control.CheckBox;

public class ControllObject_pr_Controller implements Initializable {

	@FXML 
	private Button btnSee;
	
	@FXML 
	private TextArea txtResult;

	@FXML 
	private TextField txtNmResult;

	@FXML 
	private RadioButton rbmale;

	@FXML 
	private RadioButton rbfemale;

	@FXML 
	private CheckBox chktrav;

	@FXML 
	private CheckBox chkclim;

	@FXML 
	private CheckBox chkbook;

	@FXML 
	private CheckBox chkgo;

	@FXML 
	private CheckBox chkjang;

	@FXML 
	private CheckBox chkgam;

	@FXML 
	private CheckBox chkten;

	@FXML 
	private CheckBox chkbad;

	//토글그룹 객체생성(성별)
	private ToggleGroup togGroup = new ToggleGroup();
	
	// 체크박스배열선언(취미)
	private CheckBox[] chkbox;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		rbmale.setToggleGroup(togGroup);
		rbmale.setUserData("남자");
		rbmale.setSelected(true);
		
		rbfemale.setToggleGroup(togGroup);
		rbfemale.setUserData("여자");
		
		// 체크박스배열에 체크박스들 넣기.
		chkbox = new CheckBox[]{chktrav, chkclim, chkbook, chkgo, 
				chkjang, chkgam, chkten, chkbad};
	}

	@FXML 
	public void btnSeeClicked(ActionEvent event) {
		
		// 유저데이터 취미
		String hobby = "";
		for(int i = 0; i < chkbox.length; i++) {
			if(chkbox[i].isSelected()) { 
				hobby += chkbox[i].getText() + " ";
			}
		}
		
		// 유저데이터 이름
		String name = txtNmResult.getText();
		
		// 유저데이터 성별
		if(togGroup.getSelectedToggle().getUserData() != null) {
			String mfm = togGroup.getSelectedToggle().getUserData().toString();
			
		txtResult.setText("이름 : " + name + "\n");
		txtResult.appendText("성별 : " + mfm + "\n");
		txtResult.appendText("취미 : " + hobby + "\n");
		
		}
	}
}
