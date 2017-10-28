public class MainClass{

	public static void main(String[] args){
		Controller c = new Controller();
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				createAndShowGUI();
			}
		});
	}//main

	private static void createAndShowGUI(){

	}//createAndShowGUI

}//MainClass


