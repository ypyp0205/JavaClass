package kr.or.ddit.board.vo;

public class BoVo {
	private int nums;
	private String titles;
	private String names;
	private String dateymd;
	private String datehms;
	private String cont;
	private int cnt;
	
	public BoVo() {}
	
	
	public BoVo(int nums, String titles, String names, String dateymd, String datehms, int cnt, String cont) {
		super();
		this.nums = nums;
		this.titles = titles;
		this.names = names;
		this.dateymd = dateymd;
		this.datehms = datehms;
		this.cnt = cnt;
		this.cont = cont;
	}


	public int getNums() {
		return nums;
	}


	public String getTitles() {
		return titles;
	}


	public String getNames() {
		return names;
	}


	public String getDateymd() {
		return dateymd;
	}


	public String getDatehms() {
		return datehms;
	}


	public int getCnt() {
		return cnt;
	}


	public String getCont() {
		return cont;
	}


	public void setNums(int nums) {
		this.nums = nums;
	}


	public void setTitles(String titles) {
		this.titles = titles;
	}


	public void setNames(String names) {
		this.names = names;
	}


	public void setDateymd(String dateymd) {
		this.dateymd = dateymd;
	}


	public void setDatehms(String datehms) {
		this.datehms = datehms;
	}


	public void setCnt(int cnt) {
		this.cnt = cnt;
	}


	public void setCont(String cont) {
		this.cont = cont;
	}
	
	
	
	
}
