import java.awt.*;
import java.awt.event.*;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

//import components.LayeredPaneDemo2;

public class CrabTest1 extends JPanel implements MouseListener{
	
	private JLayeredPane layeredPane;
    	private JLabel crabLabel;
    
    	private static String ON_TOP_COMMAND = "ontop";
    	private static String LAYER_COMMAND = "layer";
    
    	public CrabTest1(){
    		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    	
	    	final ImageIcon icon = null;
	    	
		    layeredPane = new JLayeredPane();
		    layeredPane.setPreferredSize(new Dimension(300,620));
		    layeredPane.setBorder(BorderFactory.createTitledBorder("Click to move the crab."));
		    layeredPane.addMouseListener(this);
		    
		    layeredPane.setLayout(new GridLayout(13,6));
		    JLabel label;
		    for (int i = 0; i < 72; i++){
		    	if (i % 2 == 0 && (i / 6) % 2 == 0){
		    		label = createColoredLabel("",Color.gray);
		    	}
		    	else if((i / 6) % 2 == 1 && i % 2 == 1){
		    		label = createColoredLabel("",Color.gray);
		    	}
		    	else{
		    		label = createColoredLabel("",Color.white);
		    	}
		    	
		    	layeredPane.add(label, new Integer(i),-1);
		    }
		    
		    crabLabel = new JLabel(icon);
		    if (icon == null) {
		    //System.err.println("Duke icon not found; using black rectangle instead.");
		    crabLabel.setOpaque(true);
		    crabLabel.setBackground(Color.BLACK);
		}
		layeredPane.add(crabLabel,new Integer(-1));
		
		add(Box.createRigidArea(new Dimension(0,10)));
		add(layeredPane);
	}
    
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = CrabTest1.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    
    private JLabel createColoredLabel(String text, Color color) {
		JLabel label = new JLabel(text);
		label.setVerticalAlignment(JLabel.TOP);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setOpaque(true);
		label.setBackground(color);
		label.setForeground(Color.black);
		label.setBorder(BorderFactory.createLineBorder(Color.black));
		label.setPreferredSize(new Dimension(140, 140));
		return label;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getY() < layeredPane.getHeight()/2){
			layeredPane.setLayer(crabLabel, 72,0);
			crabLabel.move(crabLabel.getX(), crabLabel.getY()-(layeredPane.getHeight()/14)-1);
		}
		else if(e.getX() > layeredPane.getWidth()/2 && crabLabel.getX()+(layeredPane.getWidth()/6)-2 < layeredPane.getWidth()-layeredPane.getWidth()/6){
			layeredPane.setLayer(crabLabel, 72,0);
			crabLabel.move(crabLabel.getX()+(layeredPane.getWidth()/6)-2, crabLabel.getY());
		}
		else if(e.getX() < layeredPane.getWidth()/2 && crabLabel.getX()-(layeredPane.getWidth()/6)+2 > 0){
			layeredPane.setLayer(crabLabel, 72,0);
			crabLabel.move(crabLabel.getX()-(layeredPane.getWidth()/6)+2, crabLabel.getY());
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Crabber");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new CrabTest1();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
	public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        createAndShowGUI();
    }

}

