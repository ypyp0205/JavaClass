package vo;

public class ScoreVO {
//	private String mem_id;
	private String name;
	private int kor;
	private int math;
	private int eng;
	
	public ScoreVO() {
		super();
	}

	public ScoreVO(String name, int kor, int math, int eng) {
		this.name = name;
		this.kor = kor;
		this.math = math;
		this.eng = eng;
	}
	
//	public String getMem_id() {
//		return mem_id;
//	}
//
//	public void setMem_id(String mem_id) {
//		this.mem_id = mem_id;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	@Override
	public String toString() {
		return "ScoreVO [name=" + name + ", kor=" + kor + ", math=" + math + ", eng=" + eng + ", getName()=" + getName()
				+ ", getKor()=" + getKor() + ", getMath()=" + getMath() + ", getEng()=" + getEng() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}


	
}
