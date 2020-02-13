package kr.or.ddit.behavioral.iterator;

public class IteratorPatternDemo {
	public static void main(String[] args) {
		NameRepository nameRepository = new NameRepository();
		
		Iterator it = nameRepository.getIterator();
		
		while(it.hasNext()) {
			String name = (String) it.next();
			System.out.println("이름 : " + name);
		}
		
	}
}
