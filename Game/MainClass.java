/**
Contains the methods necessary to run the game
*/
public class MainClass{
    private static View v;

	public static void main(String[] args){
        	v = new View();
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


