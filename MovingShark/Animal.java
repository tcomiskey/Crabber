class Animal{
	private int xloc;
	private int yloc;
	private int deltax;
	private String picName;
	
	public int getXloc(){
		return xloc;
	}
	public int getYloc(){
		return yloc;
	}
	public int getDeltax(){
		return deltax;
	}
	public String getpicName(){
		return picName;
	}
	public void setXloc(int x){
		xloc = x;
	}
	public void setYloc(int y){
		yloc = y;
	}
	public void setDeltax(int delta){
		deltax = delta;
	}
	public void setPicName(String n){
		picName = n;
	}
}
