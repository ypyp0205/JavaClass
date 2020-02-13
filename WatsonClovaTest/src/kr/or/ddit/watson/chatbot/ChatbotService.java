package kr.or.ddit.watson.chatbot;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.assistant.v1.Assistant;
import com.ibm.watson.developer_cloud.assistant.v1.model.InputData;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.service.security.IamOptions;

public class ChatbotService {

	private static final String URL = "https://gateway.watsonplatform.net/assistant/api";
	public static final String WORKSPACE_ID= "31310d54-e654-477f-b0b6-cd63b149e68a";
	public static final String API_KEY = "ySLwginkt2uvwntoTZ0j3AbKRLCYDXHHrPSuwXiyuAgR";
	public static final String VERSION = "2018-03-19";

	private Assistant assistant;
	
	public ChatbotService() {
		
		IamOptions options = new IamOptions.Builder()
			    .apiKey(API_KEY)
			    .build();

		assistant = new Assistant(VERSION, options);

		assistant.setEndPoint(URL);

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("X-Watson-Learning-Opt-Out", "true");
		assistant.setDefaultHeaders(headers);
		
	}
	
	public void message(String msg) {
		
		System.out.println("나 : " + msg);
		
		InputData input = new InputData.Builder(msg).build();

		MessageOptions options = new MessageOptions.Builder(WORKSPACE_ID)
		  .input(input)
		  .build();

		MessageResponse response = assistant.message(options).execute();
		
		JsonObject jsonObject=new JsonObject();
		JsonParser jsonParser=new JsonParser();
		
		Object obj = jsonParser.parse(response.toString());
		
		jsonObject = (JsonObject) obj;
		
		obj = jsonParser.parse(jsonObject.get("output").toString());
		
		jsonObject = (JsonObject) obj;
		
		JsonArray list = (JsonArray)jsonObject.get("text");
		
		Iterator<JsonElement> it = list.iterator();
		System.out.println("WATSON : ");
		
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
	
	public static void main(String[] args) {
		ChatbotService service = new ChatbotService();
		service.message("뭐 먹을거 이");
		//service.message("질문있어요");
	}
	
}
