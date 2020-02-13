package kr.or.ddit.behavioral.iterator;

public class NameRepository implements Container {

	
	public String names[] = {"이누리", "구한나", "강현지", "JYP"};
	
	
	@Override
	public Iterator getIterator() {
		return new NameIterator();
	}

	private class NameIterator implements Iterator{

		int index;
		
		@Override
		public boolean hasNext() {
			if (index < names.length) {
				return true;
			}
			
			return false;
		}

		@Override
		public Object next() {
			if (this.hasNext()) {
				return names[index++];
			}
			return null;
		}
		
	}
}
