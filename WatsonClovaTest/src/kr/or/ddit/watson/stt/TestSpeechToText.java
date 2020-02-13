package kr.or.ddit.watson.stt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibm.watson.developer_cloud.http.ServiceCall;
import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechModel;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechModels;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechRecognitionResult;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechRecognitionResults;
import com.ibm.watson.developer_cloud.speech_to_text.v1.websocket.BaseRecognizeCallback;
/**
 * IBM Watson Speech to Text 서비스는 IBM의 음성 인식기능을
 * 응용 프로그램에 추가할 수 있는 API를 제공한다.
 * 이 서비스는 다양한 언어 및 오디오 형식의 음성을 빠르게 텍스트로
 * 변환한다.
 * 모든 응답내용은 UTF-8 인코딩의 JSON 형식으로 반환한다.
 * @author HelloJava
 *
 */
public class TestSpeechToText {
	private static final String API_KEY = "ykgEqoip04MvUsDAnBZh7w0PpbtPIRTJs-92rE_74OUp";
	private static final String URL = "https://api.us-south.speech-to-text.watson.cloud.ibm.com/instances/c8e4ce27-7959-45c9-8293-e1a6e3688835";
	
	private SpeechToText service; // SpeechToText 서비스
	
	private RecognizeOptions options; // 서비스 옵션
	
	private BaseRecognizeCallback callback; //서비스 콜백
	
	
	/**
	 * 서비스 설정
	 * IBM Watson에 등록한 사용자계정으로 서비스에 접속한다.
	 */
	private void setService() {
		
		IamOptions options = new IamOptions.Builder()
								 .apiKey(API_KEY)
								 .build();

		service = new SpeechToText(options);

		service.setEndPoint(URL);

	}
	
	/**
	 * 서비스 헤더 설정
	 * - watson은 기본적으로 서비스 사용에 대한 로그를 남겨
	 *   서비스를 개선하는데 사용하고 있다.
	 *   만약 watson에서 서비스의 내용을 바꾸길 원하지 않는다면
	 *   헤더에 내용을 명시해 주어야 한다.
	 */
	private void setHeader() {
		Map<String, String> headers = new HashMap<String, String>();
		
		// true는 허용, false는 불허
		headers.put("X-Watson-Learning-Opt-Out", "false");
		
		service.setDefaultHeaders(headers);
	}

	/**
	 * 서비스 모델 검색
	 * 서비스에서 사용할 수 있는 언어 모델을 검색한다.
	 */
	private void getModel() {
		// 서비스 요청 인터페이스 ServiceCall
		ServiceCall<SpeechModels> serviceCall = service.listModels();
		
		// 서비스 요청을 실행해서 얻어온 모델 리스트
		SpeechModels speechModels = serviceCall.execute();
		
		// watson에서 제공하는 모든 언어 모델
		System.out.println(speechModels);
		
		
		// 선택한 언어에 해당하는 모델을 검색
		// en-US_NarrowbandModel (영어)
		//GetLanguageModelOptions languageModelOptions = GetLang
		//ServiceCall<SpeechModel> serviceCall_eng = service.getModel(getModelOptions)("en-US_NarrowbandModel");
		
		// 한국어
		//ServiceCall<SpeechModel> serviceCall_kor = service.getLanguageModel("ko-KR_BroadbandModel");
		
		//SpeechModel model = serviceCall_eng.execute();
		//System.out.println(model);
	}

	/**
	 * 서비스 옵션 설정
	 */
	private void setOption() {
		/*
		 * model - 언어모델
		 * contentType - 컨텐츠 타입
		 * 	  audio/basic
		 * 	  audio/flac
		 * 	  audio/l16
		 * 	  audio/mp3
		 * 	  audio/mpeg
		 * 	  audio/mulaw
		 * 	  audio/ogg
		 * 	  audio/ogg;codecs=opus
		 * 	  audio/ogg;codecs=vorbis
		 * 	  audio/wav
		 * 	  audio/webm
		 * 	  audio/webm;codecs=opus
		 * 	  audio/webm;codecs=vorbis
		 * 	interimResults - 중간결과를 반환할지 여부
		 *  true인 경우 임시결과는
		 *  JSON SpeechRecognitionResults 객체의
		 *  스트림으로 반환된다.
		 *  false(기본값)인 경우 응답은 최종결과만 있는
		 *  단일 SpeechRecognitionResults 객체
		 *  
		 *  maxAlternatives -
		 *  반환 될 대체 성적 증명서의 최대수
		 *  기본적으로 한개가 반환된다.
		 *  
		 *  keywords
		 *  오디오에서 발견할 수 있는 키워드 목록
		 *  키워드는 최종결과에서만 발견되며 최대 1000개의
		 *  키워드를 발견할 수 있다.
		 *  키워드를 알아볼 필요가 없을때는 매개변수를 생략하거나
		 *  빈 배열을 지정한다.
		 *  
		 *  keywordsThreshold
		 *  키워드 검색을 위한 신뢰값
		 *  신뢰도가 임계값보다 크거나 같으면
		 *  단어가 키워드와 일치하는 것으로 판단
		 *  0과 1사이로 확률을 지정한다. 
		 */
		
		List<String> kewords = new ArrayList<>();
		kewords.add("colorado");
		kewords.add("tornado");
		kewords.add("tornadoes");
		
		try {
			FileInputStream fis = 
					new FileInputStream(
							new File(getClass().getResource("obama_speech.flac").getPath()));
			
			
			options = new RecognizeOptions.Builder()
					  .model("en-US_BroadbandModel")
					  .contentType("audio/flac")
					  .audio(fis) // 음성파일
					  .interimResults(true).maxAlternatives(3)
					  .keywords(kewords)
					  .keywordsThreshold(0.5f)
					  .build();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 서비스 실행 후 콜백 지정
	 * 서비스 실행 후에 처리할 내용을 지정해 준다.
	 */
	private void setCallback() {
		callback = new BaseRecognizeCallback() {
			// 문자변환시 처리할 내용
			@Override
			public void onTranscription(SpeechRecognitionResults speechResults) {
				for(SpeechRecognitionResult srResult:speechResults.getResults()){
					String text = srResult.getAlternatives().get(0).getTranscript();
					System.out.println(text);
//					for(SpeechAlternative alternative:transcript.getAlternatives()){
//						String text = alternative.getTranscript();
//						System.out.println(text);
//					}
				}
			}
			// 연결 종료시 처리할 내용
			@Override
			public void onDisconnected() {
				System.exit(0);
			}
		};
		
	}
	
	/**
	 * 서비스 실행
	 * (설정해 놓은 옵션과 서비스 실행 후 처리할 콜백을
	 * 지정하여 서비스를 실행한다.)
	 */
	private void executeService() {
		
		service.recognizeUsingWebSocket(options, callback);
	}

	public static void main(String[] args) {
		TestSpeechToText speechToText = new TestSpeechToText();
		speechToText.setService();
		speechToText.setHeader();
		//speechToText.getModel();
		speechToText.setOption();
		speechToText.setCallback();
		speechToText.executeService();
	}
	
}
