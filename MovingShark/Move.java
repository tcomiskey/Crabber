import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;  
import java.util.*;

public class Move extends JPanel{
	private int xloc;
	private int yloc;
	private int deltax;
	private String picName;
	private BufferedImage img;
	private static ArrayList<Animal> sharks= new ArrayList<Animal>();
	
	final static int frameWidth =800;
    	final static int frameHeight = 500;
   	
    	
    	public Move(Animal character){
    		xloc = character.getXloc();
		yloc = character.getYloc();
		deltax = character.getDeltax();
		picName = character.getpicName();
    		img = createImage();
    	}
    	public void paint(Graphics g) {
    		if (xloc < -100)
    			xloc = 900;
    		g.drawImage(img, xloc+=deltax, yloc, Color.gray, this);
    	}
    	
	public static void main(String args[]){
		JFrame frame = new JFrame();
		Animal s1 = new Shark(900,200,-10,"shark");
		Animal s2 = new Shark(950,200,-10,"shark");
		sharks.add(s1);
		sharks.add(s2);
		frame.getContentPane().add(new Move(s1));
		frame.getContentPane().add(new Move(s2));
	    	frame.setBackground(Color.gray);
	    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	frame.setSize(frameWidth, frameHeight);
	    	frame.setVisible(true);
	    	
	    	for(int i = 0; i < 1000; i++){
			frame.repaint();
	    		try {
	    			Thread.sleep(100);
	    		} catch (InterruptedException e) {
	    			e.printStackTrace();
	    		}
	    		   		
	    	}
	}
	private BufferedImage createImage(){
	    	BufferedImage bufferedImage;
	    	try {
	    		bufferedImage = ImageIO.read(new File("images/" + picName + ".png"));
	    		return bufferedImage;
	    	} catch (IOException e) {
	    		e.printStackTrace();
	    	}
	    	return null;
	    	
    	}
}
