public class MainClass{

	public static void main(String[] args){
		View v = new View();
		v.setVisible(true);
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				createAndShowGUI();
			}
		});
	}//main

	private static void createAndShowGUI(){

	}//createAndShowGUI

}//MainClass


