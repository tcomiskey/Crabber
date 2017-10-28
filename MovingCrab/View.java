import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.*;


//import components.LayeredPaneDemo2;

public class View extends JFrame{ //implements ActionListener,MouseListener{

	private int difficulty;
	private JPanel menu;
	private JPanel diffScreen;
	private JPanel gameScreen;
	private JLabel playerLabel;
	private ArrayList<JLabel> enemyLabels;
	private JButton start;
	private JButton easy;
	private JButton medium;
	private JButton hard;
	
	View(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(new Dimension(400,400));
		menu = new JPanel();
		getContentPane().add(menu);
		start = new JButton("Start");
		start.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					createDiffScreen();
				}
			}
			);
		start.setVisible(true);
		menu.add(start);
		menu.setBackground(Color.black);
	}

	public static void main (String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new View().setVisible(true);
			}
		});
	}

	private void createDiffScreen(){
		//this clears old screen
		getContentPane().removeAll();
		diffScreen = new JPanel();
		easy = new JButton("Easy");
		easy.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					difficulty = 1;
					System.out.println(difficulty);
				}
			}
			);
		medium = new JButton("Medium");
		medium.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					difficulty = 2;
					System.out.println(difficulty);
				}
			}
			);
		hard = new JButton("Hard");
		hard.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					difficulty = 3;
					System.out.println(difficulty);
				}
			}
			);
		diffScreen.setVisible(true);
		diffScreen.add(easy);
		diffScreen.add(medium);
		diffScreen.add(hard);
		getContentPane().add(diffScreen);
		//diffScreen.setOpaque(true);
		setVisible(true);
		repaint();
	}
		
	public int getDifficulty(){
		return difficulty;
	}

	public void startGameWindow(){
		gameScreen = new JPanel();
		gameScreen.setBackground(Color.blue);
		getContentPane().removeAll();
		getContentPane().add(gameScreen);
		//diffScreen.setOpaque(true);
		setVisible(true);
		repaint();
	}
}
		

	
	

