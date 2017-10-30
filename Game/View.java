import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.*;


//import components.LayeredPaneDemo2;

public class View extends JFrame implements MouseListener{

	private int difficulty;
	private JPanel menu;
	private JPanel diffScreen;
	private JPanel gameScreen;
	private JPanel gameOverScreen;
	private JLabel playerLabel;
	private ArrayList<JLabel> enemyLabels;
	private JButton start;
	public JButton easy;
	private JButton medium;
	private JButton hard;
	private JButton playAgain;
	private Controller control;
	private int playerX;
	private int playerY;
	private ArrayList<GameCharacter> enemies;
	
	View(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setLocationRelativeTo(null);
		setSize(new Dimension(800,800));
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

	/*public static void main (String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new View().setVisible(true);
			}
		});
	}*/

	private void createDiffScreen(){
		//this clears old screen
		getContentPane().removeAll();
		diffScreen = new JPanel();
		easy = new JButton("Easy");
		easy.addActionListener(control.getMouseClick(e);
		playerLabel.setLocation(playerX, playerY);
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					difficulty = 1;
					System.out.println(difficulty);
					setupController();
					startGameWindow();
				}
			}
			);
		medium = new JButton("Medium");
		medium.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					difficulty = 2;
					System.out.println(difficulty);
					setupController();
					startGameWindow();
				}
			}
			);
		hard = new JButton("Hard");
		hard.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					difficulty = 3;
					System.out.println(difficulty);
					setupController();
					startGameWindow();
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
	public void updateLocations(){
		for(int i = 0; i < enemies.size(); i++){
			enemyLabels.get(i).setLocation(enemies.get(i).getX(), enemies.get(i).getY());
		}
		repaint();
		
	}
		
	public int getDifficulty(){
		return difficulty;
	}

	private void startGameWindow(){
		setEnemies();
		gameScreen = new JPanel();
		gameScreen.setLayout(null);
		for(int i = 0; i < enemies.size(); i++){
			gameScreen.add(enemyLabels.get(i));
			enemyLabels.get(i).setBounds(new Rectangle(new Point(enemies.get(i).getX(), enemies.get(i).getY()), new Dimension(enemies.get(i).getImgWidth(), enemies.get(i).getImgHeight())));			
		}
		gameScreen.add(playerLabel);
		playerLabel.setBounds(new Rectangle(new Point(playerX, playerY), new Dimension(playerLabel.getIcon().getIconWidth(), playerLabel.getIcon().getIconHeight())));
		gameScreen.addMouseListener(this);
		gameScreen.setBackground(Color.blue);
		getContentPane().removeAll();
		getContentPane().add(gameScreen);
		//diffScreen.setOpaque(true);
		setVisible(true);
		repaint();
	}

	public void makePlayerLabel(ImageIcon img){
		playerLabel = new JLabel(img);
	}
	

	public JButton getEasyButton(){
		return easy;
	}

	public void setupController(){
		control = new Controller(this);
	}

	public void setPlayerX(int x){
		playerX = x;
	}

	public void setPlayerY(int y){
		playerY = y;
	}
    
    public void setEnemies() {
    	enemies = new ArrayList<GameCharacter>();
   	enemyLabels = new ArrayList<JLabel>();
        Iterator<GameCharacter> enemyIterator = control.getBoard().getEnemies().iterator();
        while (enemyIterator.hasNext()) {
        	GameCharacter gc = enemyIterator.next();
            	enemies.add(gc);
            	enemyLabels.add(new JLabel(gc.getImage()));
        }
    }

	public ArrayList<GameCharacter> getEnemies(){
		return enemies;
	}
    public Controller getControl() {
        return control;
    }

    @Override
	public void mouseClicked(MouseEvent e) {
		control.getMouseClick(e);
		playerLabel.setLocation(playerX, playerY);
		/*
		if(e.getY() < playerLabel.getY()){
			playerLabel.setLocation(playerLabel.getX(),playerLabel.getY()-playerLabel.getIcon().getIconHeight());
		}
		else if(e.getX() > playerLabel.getX()){
			playerLabel.setLocation(playerLabel.getX()+playerLabel.getIcon().getIconWidth(), playerLabel.getY());
		}
		else if(e.getX() < playerLabel.getX()){
			playerLabel.setLocation(playerLabel.getX()-playerLabel.getIcon().getIconWidth(), playerLabel.getY());
		}
		*/
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
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
    
    public void throwQuestion(Message currentMessage) {
        String[] buttons = {currentMessage.getRightAnswer(), currentMessage.getWrongAnswer1(), currentMessage.getWrongAnswer2()};
        int rc = JOptionPane.showOptionDialog(null,currentMessage.getQuestion(),"Quiz Question", JOptionPane.WARNING_MESSAGE, 0, null, buttons, null);
        if (rc==0) {
            System.out.println("RIGHT!");
            control.resetPlayer();
            playerLabel.setLocation(playerX, playerY);
            System.out.println(playerLabel.getX());
            System.out.println(playerLabel.getY());
            repaint();
            
        }
        else {
            System.out.println("W R O N G heck u");
            startGameOverWindow();
        }
    }

    	private void startGameOverWindow(){
		gameOverScreen = new JPanel();
		gameOverScreen.setBackground(Color.red);
		//gameOverScreen.setLayout(new FlowLayout());
		
		playAgain = new JButton("Play again?");
		playAgain.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					createDiffScreen();
				}
			}
			);
		gameOverScreen.add(playAgain);
		getContentPane().removeAll();
		getContentPane().add(gameOverScreen);
		//diffScreen.setOpaque(true);
		setVisible(true);
		repaint();
	}

}
		


