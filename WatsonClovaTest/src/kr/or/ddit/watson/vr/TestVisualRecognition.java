package kr.or.ddit.watson.vr;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyOptions;

public class TestVisualRecognition {
	
	public void test(){
		
	   IamOptions options = new IamOptions.Builder().apiKey("LFStNxQAVwktlmG7WvDnkYs-QAq5A3LmmEI9L24c28CD").build();
	   
	   VisualRecognition service = new VisualRecognition("2018-03-19", options);
	   
	   InputStream imagesStream;
	   
	   try {
		   
		   //imagesStream = new FileInputStream("src/basic/watson/actrees.jpg");
		   //imagesStream = new FileInputStream("src/kr/or/ddit/watson/visualRecognition/test.jpg");
		   imagesStream = new FileInputStream(new File(getClass().getResource("11.png").getPath()));
		   
		   ClassifyOptions classifyOptions
		   	= new ClassifyOptions
		   	.Builder()
		   	.imagesFile(imagesStream)
		   	.imagesFilename("11.png")
		   	.build();
		   
		   ClassifiedImages result = service.classify(classifyOptions).execute();
		   System.out.println(result);
		   
	   }catch(FileNotFoundException e) {
		   e.printStackTrace();
	   }
	   
	}
	
	public static void main(String[] args) {
		TestVisualRecognition obj = new TestVisualRecognition();
		obj.test();
	}
}
