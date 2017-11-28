import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.*;


//import components.LayeredPaneDemo2;


/**
 * This class contains all necessary fields and methods to create the
 * user interface for the Crabber game. It handles all of the Swing painting
 * and is passed data and images from a controller.
 *
 * @author John Malone
 * @author Maura Swift
 */
public class View extends JFrame implements MouseListener{
	private int difficulty;
	private JPanel menu;
	private JPanel diffScreen;
	private JPanel gameScreen;
	private JPanel gameOverScreen;
	private JLabel playerLabel;
	private ArrayList<JLabel> enemyLabels;
    private JLabel bonusLabel;
	private JButton start;
	private JButton easy;
	private JButton medium;
	private JButton hard;
	private JButton playAgain;
	private Controller control;
	private int playerX;
    private int playerY;
    private int bonusX;
    private int bonusY;
    private ArrayList<int[]> enemyAtt;

	/**
	* This is the main View constructor that creates a new JPanel onto which a start button
	* is placed. This start button is able to begin the game and 
	* create all other necessary JPanels.
	*/
	public View(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setLocationRelativeTo(null);
		setSize(new Dimension(800,700));
		menu = new JPanel();
		getContentPane().add(menu);
		start = new JButton("Start");
		start.setFont(new Font(start.getName(),Font.PLAIN, 72));
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
	/**
	* Method to create the difficulty screen.
	* This method can either be called by the player clicking the
	* start button or by the player clicking any "Play again"
	* button. Once a difficulty is selected, a new controller is 
	* created and the startGameWindow() method is called.
	*/
	private void createDiffScreen(){
		//this clears old screen
		getContentPane().removeAll();
		diffScreen = new JPanel();
		easy = new JButton("Easy");
		easy.setFont(new Font(start.getName(),Font.PLAIN, 72));
		easy.addActionListener(
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
		medium.setFont(new Font(start.getName(),Font.PLAIN, 72));
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
		hard.setFont(new Font(start.getName(),Font.PLAIN, 72));
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
	/**
	* Method to relocate the enemy images across the gameboard. Takes 
	* no inputs or outputs and repaints using Swing repaint.
	*/
	public void updateLocations(){

		control.getBoard().getTheOneAndOnlyShark().increasePicNum();
		int tempPicNum = control.getBoard().getTheOneAndOnlyShark().getPicNum();
		Iterator<int[]> enemyIterator = control.getBoard().getTheOneAndOnlyShark().getEnemyAtt().iterator();
		int counter = 0;
		while (enemyIterator.hasNext()) {
		    int [] currentEnemy = enemyIterator.next();
		    if((currentEnemy[1]/control.getBoard().getPlayer().getPlayerHeight())%2 == 0){
		      		if(currentEnemy[2] == -1){
		                enemyLabels.get(counter).setIcon(new ImageIcon(control.getBoard().getTheOneAndOnlyShark().getLeftImageArray()[tempPicNum]));
		            }
		            else{
		                enemyLabels.get(counter).setIcon(new ImageIcon(control.getBoard().getTheOneAndOnlyShark().getRightImageArray()[tempPicNum]));
		            }
		    }
		    else{
		      		if(currentEnemy[2] == -1){
		                enemyLabels.get(counter).setIcon(new ImageIcon(control.getBoard().getTheOneAndOnlyTrash().getLeftImageArray()[tempPicNum]));
		            }
		            else{
		                enemyLabels.get(counter).setIcon(new ImageIcon(control.getBoard().getTheOneAndOnlyTrash().getRightImageArray()[tempPicNum]));
		            }
		    }
		    counter++;
		}
        	
		for(int i = 0; i < enemyAtt.size(); i++){
			enemyLabels.get(i).setLocation(enemyAtt.get(i)[0], enemyAtt.get(i)[1]);
		}
		repaint();
		
		
	}
    
    public void updateBonusLocation(){
        bonusLabel.setLocation(bonusX, bonusY);
        repaint();
    }
    
    /**
	* Method that returns that the difficulty that is selected by
	* the player. Required so that the controller knows which difficulty
	* the user selects.
	*
	* @return	the difficulty selected by the user
	*/
	public int getDifficulty(){
		return difficulty;
	}

	private void startGameWindow(){
		setEnemiesSea();
		gameScreen = new JPanel();
		gameScreen.setLayout(null);
		for(int i = 0; i < enemyAtt.size(); i++){
			gameScreen.add(enemyLabels.get(i));
			enemyLabels.get(i).setBounds(new Rectangle(new Point(enemyAtt.get(i)[0], enemyAtt.get(i)[1]), new Dimension(enemyAtt.get(i)[3], enemyAtt.get(i)[4])));			
		}
		gameScreen.add(playerLabel);
        gameScreen.add(bonusLabel);
		playerLabel.setBounds(new Rectangle(new Point(playerX, playerY), new Dimension(playerLabel.getIcon().getIconWidth(), playerLabel.getIcon().getIconHeight())));
        bonusLabel.setBounds(new Rectangle(new Point(bonusX, bonusY), new Dimension(bonusLabel.getIcon().getIconWidth(), bonusLabel.getIcon().getIconHeight())));
		gameScreen.addMouseListener(this);
		gameScreen.setBackground(Color.blue);
		getContentPane().removeAll();
		getContentPane().add(gameScreen);
		//diffScreen.setOpaque(true);
		setVisible(true);
		repaint();
	}

	public void startLandWindow(){
		gameScreen.removeAll();
		setEnemiesSea();
		gameScreen.setLayout(null);
		for(int i = 0; i < enemyAtt.size(); i++){
			gameScreen.add(enemyLabels.get(i));
			enemyLabels.get(i).setBounds(new Rectangle(new Point(enemyAtt.get(i)[0], enemyAtt.get(i)[1]), new Dimension(enemyAtt.get(i)[3], enemyAtt.get(i)[4])));			
		}
		playerLabel.setLocation(playerX, playerY);
		gameScreen.add(playerLabel);
		playerLabel.setBounds(new Rectangle(new Point(playerX, playerY), new Dimension(playerLabel.getIcon().getIconWidth(), playerLabel.getIcon().getIconHeight())));
		gameScreen.setBackground(new Color(239,211,110));
		//diffScreen.setOpaque(true);
		setVisible(true);
		repaint();
	}

	public void makePlayerLabel(BufferedImage img){
		playerLabel = new JLabel(new ImageIcon(img));
	}
	
    public void makeBonusLabel(BufferedImage img){
        bonusLabel = new JLabel(new ImageIcon(img));
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
    
    public void setBonusX(int x){
        bonusX = x;
    }
    
    public void setBonusY(int y){
        bonusY = y;
    }
    
    public void setEnemiesSea() {
    	enemyAtt = new ArrayList<int[]>();
        enemyLabels = new ArrayList<JLabel>();
        int tempPicNum = control.getBoard().getTheOneAndOnlyShark().getPicNum();
        Iterator<int[]> enemyIterator = control.getBoard().getTheOneAndOnlyShark().getEnemyAtt().iterator();
        while (enemyIterator.hasNext()) {
        	int [] currentEnemy = enemyIterator.next();
              	enemyAtt.add(currentEnemy);
              	if((currentEnemy[1]/control.getBoard().getPlayer().getPlayerHeight())%2 == 0){
              		if(currentEnemy[2] == -1){
                        enemyLabels.add(new JLabel(new ImageIcon(control.getBoard().getTheOneAndOnlyShark().getLeftImageArray()[tempPicNum])));
                    }
                    else{
                        enemyLabels.add(new JLabel(new ImageIcon(control.getBoard().getTheOneAndOnlyShark().getRightImageArray()[tempPicNum])));
                    }
                }
                else{
                    if(currentEnemy[2] == -1){
                        enemyLabels.add(new JLabel(new ImageIcon(control.getBoard().getTheOneAndOnlyTrash().getLeftImageArray()[tempPicNum])));
                    }
                    else{
                        enemyLabels.add(new JLabel(new ImageIcon(control.getBoard().getTheOneAndOnlyTrash().getRightImageArray()[tempPicNum])));
                    }
            }
        }
    }
    
    public ArrayList<int[]> getEnemyAtt(){
        return enemyAtt;
    }
    public Controller getControl() {
        return control;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        control.getMouseClick(e);
        playerLabel.setLocation(playerX, playerY);
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
            control.resetPlayer();
            playerLabel.setLocation(playerX, playerY);
            System.out.println(playerLabel.getX());
            System.out.println(playerLabel.getY());
            repaint();
            
        }
        else {
            startGameOverWindow();
        }
    }
    
    public void startGameOverWindow(){
        gameOverScreen = new JPanel();
        gameOverScreen.setBackground(Color.red);
        gameOverScreen.setLayout(new BoxLayout(gameOverScreen, BoxLayout.Y_AXIS));
        JLabel gameOverText = new JLabel("Oh no! You lost.");
        gameOverText.setFont(new Font(gameOverText.getName(),Font.PLAIN, 72));
        gameOverText.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameOverScreen.add(gameOverText);
        playAgain = new JButton("Play again?");
        playAgain.addActionListener(
                                    new ActionListener(){
            public void actionPerformed(ActionEvent e){
                clearStaticEnemies();
                createDiffScreen();
            }
        }
                                    );
        playAgain.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameOverScreen.add(playAgain);
        getContentPane().removeAll();
        getContentPane().add(gameOverScreen);
        //diffScreen.setOpaque(true);
        setVisible(true);
        repaint();
    }
    
    public void startWinWindow(){
        JPanel winScreen = new JPanel();
        winScreen.setBackground(Color.yellow);
        winScreen.setLayout(new BoxLayout(winScreen, BoxLayout.Y_AXIS));
        JLabel winText = new JLabel("Congrats!!! You won!");
        winText.setFont(new Font(winText.getName(),Font.PLAIN, 72));
        winText.setAlignmentX(Component.CENTER_ALIGNMENT);
        winScreen.add(winText);
        playAgain = new JButton("Play again?");
        playAgain.addActionListener(
                                    
                                    new ActionListener(){
            public void actionPerformed(ActionEvent e){
                clearStaticEnemies();
                createDiffScreen();
            }
        }
                                    );
        playAgain.setAlignmentX(Component.CENTER_ALIGNMENT);
        winScreen.add(playAgain);
        getContentPane().removeAll();
        getContentPane().add(winScreen);
        //diffScreen.setOpaque(true);
        setVisible(true);
        repaint();
    }
    
    public void clearStaticEnemies() {
        // SET NOTRASH AND NOSHARK TO TRUE AGAIN BEFORE DELETING EVERYTHING
        control.getBoard().getTheOneAndOnlyShark().setNoSharks(true);
        control.getBoard().getTheOneAndOnlyTrash().setNoTrash(true);
        control.getBoard().getTheOneAndOnlyShark().clearEnemyAtt();
    }
    
}



