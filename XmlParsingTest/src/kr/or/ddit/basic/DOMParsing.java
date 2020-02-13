package kr.or.ddit.basic;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMParsing {
	public void parse() {
		
		try {
			// DOM Document객체 생성하기 위한 메서드
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			
			// DOM파서로 부터 입력받은 파일을 파싱하도록 요청
			DocumentBuilder builder = dbf.newDocumentBuilder();
			
			// XML파일 지정
			String url = getClass().getResource("/kr/or/ddit/basic/book.xml").toExternalForm();
			
			Document xmlDoc = null;
			
			// DOM 파서로부터 입력받은 파일을 파싱하도록 요청
			xmlDoc = builder.parse(url);
			
			// 루트 엘리먼트 접근
			Element root = xmlDoc.getDocumentElement();
			System.out.println("root.getTagName() => " + root.getTagName());
			
			// 하위 엘리먼트 접근
			NodeList bookNodeList = root.getElementsByTagName("book");
			Node firstBookNode = bookNodeList.item(0); // 첫번째 아이템
			Element bookElement = (Element)firstBookNode;
			
			// 속성 접근방법1 : 엘리먼트 객체의 getAttribute()메서드 이용
			String isbn = bookElement.getAttribute("isbn");
			System.out.println("bookElement.getAttrbute(\"isbn\") => " + isbn);
			
			// 속성 접근방법2 : 노드객체의  getAttributes()메서드 이용(속성노드 접근)
			NamedNodeMap nodeMap = firstBookNode.getAttributes();
			System.out.println("노드객체의 getAttribyutes()메서드이용 : " + nodeMap.getNamedItem("isbn").getNodeValue());
			
			// 텍스트(노드) 접근
			NodeList bookN1 = firstBookNode.getChildNodes();
			
			// 인덱스 사용시 주의할 것.
			// 엔터기에 해당되는 부분이 읽힐 수 있다.
			// (getChildNodes() 보다는, getElementsByTagName()을 이용하는게 좋다.)
			Node titleNode = bookN1.item(1); // 공백문자 때문에 인덱스 값을 1로 설정
			Element titleElement = (Element)titleNode;
			System.out.println("titleElement.getTageName() => " + titleElement.getTagName()); // title
			System.out.println("titleElement.getTextContent() => " + titleElement.getTextContent()); // 자바초급
			
			// 전체 출력하기
			// 속성값 : isbn, kind
			// 엘리먼트 덱스트값 : title, author, price
			System.out.println("-------------------------------");
			for (int i = 0; i < bookNodeList.getLength(); i++) {
				
				Node bookNode = bookNodeList.item(i);
				Element element = (Element) bookNode;
				String is= element.getAttribute("isbn");
				String ki= element.getAttribute("kind");
				String title = element.getElementsByTagName("title").item(0).getTextContent();
				String author = element.getElementsByTagName("author").item(0).getTextContent();
				String price = element.getElementsByTagName("price").item(0).getTextContent();
				String str = String.format("%8s %10s %20s %10s %8s", is, ki, title, author, price);
				System.out.println(str);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
public static void main(String[] args) {
	DOMParsing parse = new DOMParsing();
	parse.parse();
}		
		
}
