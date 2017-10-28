import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class BITCHEZZ extends JPanel{


	public BITCHEZZ(){
		setLayout(new BorderLayout());
		JButton start = new JButton ("Start");
		start.setBounds(300,300,100,50);
		try{
		JLabel background = new JLabel(new ImageIcon(ImageIO.read(new File("Background_01.png"))));
		add(background);
		background.add(start);
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
	}

	public static void createAndShowGUI(){
		JFrame frame = new JFrame("THICC");
		
		frame.setSize(600,600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JComponent newContentPane = new BITCHEZZ();
		
		
		frame.setContentPane(newContentPane);
		frame.setVisible(true);
	}

	public static void main (String [] args){
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				createAndShowGUI();
			}
		});
	}
}
