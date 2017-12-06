import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.*;
import java.io.BufferedReader;
import java.io.*;


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
    private JPanel tutorialScreen;
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
    private JLabel tutorialCrab;
   	private JLabel tutorialShark;
   	private JLabel tutorialBonus;
   	private JLabel tutorialText;
   	private JLabel startScreenLabel;
    private boolean isTutorial;
    private int tutorialNum;
    private JLabel timerLabel;
    private String[][] textMatrix;
    private JTable leaderboard;
    private int score;
    private double aspectRatio = 580.0/930; // height/width of start screen image = 0.623
    private double scalingFactor;
    private int screenWidth;
    private int screenHeight;	//as long as these are the overall width and height of the frame everything should work
    

    /**
	* This is the main View constructor that creates a new JPanel onto which a start button
	* is placed. This start button is able to begin the game and 
	* create all other necessary JPanels.
	*/
	public View(){
        	setDefaultCloseOperation(EXIT_ON_CLOSE);
        	//setLocationRelativeTo(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        	if(dim.getHeight()/dim.getWidth() < aspectRatio){
                System.out.println("IF STATEMENT");
        		screenHeight = (int)dim.getHeight();
        		screenWidth = (int)(screenHeight/aspectRatio);
        	}
        	else{
                System.out.println("ELSE STATEMENT");
        		screenWidth = (int)dim.getWidth();
        		screenHeight = (int)(screenWidth*aspectRatio);
       		}
       
        	setExtendedState(JFrame.MAXIMIZED_BOTH);
        	menu = new JPanel();
        	menu.setLayout(new BorderLayout());
        	getContentPane().add(menu);
        	/*
        	start = new JButton("Start");
        	start.setBackground(Color.GREEN);
        	start.setOpaque(true);
        	start.setBorderPainted(false);
        	*/

        	try {
            		Image image = ImageIO.read(new File("images/Start_Screen.png"));
            		image = image.getScaledInstance(screenWidth, screenHeight, Image.SCALE_SMOOTH);
            		startScreenLabel = new JLabel(new ImageIcon(image));
           		 menu.add(startScreenLabel);
        	} catch (IOException e) {
            		e.printStackTrace();
        	}
        
/*
        	start.setFont(new Font(start.getName(),Font.PLAIN, 72));
        	start.addActionListener(
                	new ActionListener(){
           			public void actionPerformed(ActionEvent e){
                			runTutorial();
            			}
        		}
                );
                */
        	score = 0;
        	readLeaderboard();
        	leaderboard.setEnabled(false);
		//start.setVisible(true);
		//menu.add(start, BorderLayout.SOUTH);
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
        diffScreen.setLayout(new BorderLayout());
        diffScreen.add(startScreenLabel);
        screenWidth = getWidth();
	screenHeight = getHeight();

        JPanel buttons = new JPanel();
        
        easy = new JButton("Easy");
        easy.setBackground(Color.GREEN);
        easy.setOpaque(true);
        easy.setBorderPainted(false);
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
        medium.setBackground(Color.YELLOW);
        medium.setOpaque(true);
        medium.setBorderPainted(false);
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
        hard.setBackground(Color.RED);
        hard.setOpaque(true);
        hard.setBorderPainted(false);
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
        buttons.add(easy);
        buttons.add(medium);
        buttons.add(hard);
        buttons.setBackground(Color.BLACK);
        diffScreen.add(buttons, BorderLayout.SOUTH);
        diffScreen.setBackground(Color.BLACK);
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
    
    public void updateLandLocations(){
        
        control.getBoard().getTheOneAndOnlyHuman().increasePicNum();
        int tempPicNum = control.getBoard().getTheOneAndOnlyHuman().getPicNum();
        Iterator<int[]> enemyIterator = control.getBoard().getTheOneAndOnlyHuman().getEnemyAtt().iterator();
        int counter = 0;
        while (enemyIterator.hasNext()) {
            int [] currentEnemy = enemyIterator.next();
            if((currentEnemy[1]/control.getBoard().getPlayer().getPlayerHeight())%2 == 0){
                if(currentEnemy[2] == -1){
                    enemyLabels.get(counter).setIcon(new ImageIcon(control.getBoard().getTheOneAndOnlyHuman().getLeftImageArray()[tempPicNum]));
                }
                else{
                    enemyLabels.get(counter).setIcon(new ImageIcon(control.getBoard().getTheOneAndOnlyHuman().getRightImageArray()[tempPicNum]));
                }
            }
            else{
                if(currentEnemy[2] == -1){
                    enemyLabels.get(counter).setIcon(new ImageIcon(control.getBoard().getTheOneAndOnlyBird().getLeftImageArray()[tempPicNum]));
                }
                else{
                    enemyLabels.get(counter).setIcon(new ImageIcon(control.getBoard().getTheOneAndOnlyBird().getRightImageArray()[tempPicNum]));
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
        	gameScreen.add(timerLabel);
        	timerLabel.setBounds(new Rectangle(new Point(screenWidth-timerLabel.getWidth(), 0), timerLabel.getSize()));
		playerLabel.setBounds(new Rectangle(new Point(playerX, playerY), new Dimension(playerLabel.getIcon().getIconWidth(), playerLabel.getIcon().getIconHeight())));
       		bonusLabel.setBounds(new Rectangle(new Point(bonusX, bonusY), new Dimension(bonusLabel.getIcon().getIconWidth(), bonusLabel.getIcon().getIconHeight())));
        	
        	try {
            		Image image = ImageIO.read(new File("images/Ocean Background.png"));
           		image = image.getScaledInstance(screenWidth, screenHeight, Image.SCALE_SMOOTH);
           		JLabel background = new JLabel(new ImageIcon(image));
           		gameScreen.add(background);
           		background.setBounds(new Rectangle(new Point(0, 0), new Dimension(screenWidth, screenHeight)));
        	} catch (IOException e) {
           		e.printStackTrace();
        	}
        	
		gameScreen.addMouseListener(this);
		//gameScreen.setBackground(Color.blue);
		getContentPane().removeAll();
		getContentPane().add(gameScreen);
		//diffScreen.setOpaque(true);
		setVisible(true);
		repaint();
	}

	public void startLandWindow(){
        score += difficulty*control.getBoard().getRemainingTime()/10;
		gameScreen.removeAll();
		setEnemiesLand();
		gameScreen.setLayout(null);
		for(int i = 0; i < enemyAtt.size(); i++){
			gameScreen.add(enemyLabels.get(i));
			enemyLabels.get(i).setBounds(new Rectangle(new Point(enemyAtt.get(i)[0], enemyAtt.get(i)[1]), new Dimension(enemyAtt.get(i)[3], enemyAtt.get(i)[4])));			
		}
		playerLabel.setLocation(playerX, playerY);
		gameScreen.add(playerLabel);
		gameScreen.add(timerLabel);
        	timerLabel.setBounds(new Rectangle(new Point(screenWidth-timerLabel.getWidth(), 0), timerLabel.getSize()));
		playerLabel.setBounds(new Rectangle(new Point(playerX, playerY), new Dimension(playerLabel.getIcon().getIconWidth(), playerLabel.getIcon().getIconHeight())));

        	try {
            		Image image = ImageIO.read(new File("images/beach.png"));
           		image = image.getScaledInstance(screenWidth, screenHeight, Image.SCALE_SMOOTH);
           		JLabel background = new JLabel(new ImageIcon(image));
           		gameScreen.add(background);
           		background.setBounds(new Rectangle(new Point(0, 0), new Dimension(screenWidth, screenHeight)));
        	} catch (IOException e) {
           		e.printStackTrace();
        	}
		//gameScreen.setBackground(new Color(239,211,110));
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

    public void makeTimerLabel(String time){
    	timerLabel = new JLabel(time);
    	timerLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    	timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
    	timerLabel.setBackground(Color.WHITE);
    	timerLabel.setOpaque(true);
    	timerLabel.setFont(new Font(timerLabel.getFont().getName(), Font.PLAIN, 24));
    	timerLabel.setSize(100, 50);
    }

    public void updateTimerLabel(String time){
    	timerLabel.setText(time);
    }
    
    public JButton getEasyButton(){
		return easy;
	}
    
    public JLabel getBonusLabel(){
    	return bonusLabel;
    }
    
    public JLabel getTimerLabel(){
    	return timerLabel;
    }

    public int getScreenWidth(){
    	return screenWidth;
    }

    public int getScreenHeight(){
    	return screenHeight;
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
    public void setEnemiesLand() {
        enemyAtt = new ArrayList<int[]>();
        enemyLabels = new ArrayList<JLabel>();
        int tempPicNum = control.getBoard().getTheOneAndOnlyHuman().getPicNum();
        Iterator<int[]> enemyIterator = control.getBoard().getTheOneAndOnlyHuman().getEnemyAtt().iterator();
        while (enemyIterator.hasNext()) {
            int [] currentEnemy = enemyIterator.next();
            enemyAtt.add(currentEnemy);
            if((currentEnemy[1]/control.getBoard().getPlayer().getPlayerHeight())%2 == 0){
              		if(currentEnemy[2] == -1){
                        enemyLabels.add(new JLabel(new ImageIcon(control.getBoard().getTheOneAndOnlyHuman().getLeftImageArray()[tempPicNum])));
                    }
                    else{
                        enemyLabels.add(new JLabel(new ImageIcon(control.getBoard().getTheOneAndOnlyHuman().getRightImageArray()[tempPicNum])));
                    }
            }
            else{
                if(currentEnemy[2] == -1){
                    enemyLabels.add(new JLabel(new ImageIcon(control.getBoard().getTheOneAndOnlyBird().getLeftImageArray()[tempPicNum])));
                }
                else{
                    enemyLabels.add(new JLabel(new ImageIcon(control.getBoard().getTheOneAndOnlyBird().getRightImageArray()[tempPicNum])));
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
        if(!isTutorial){
            control.getMouseClick(e);
            playerLabel.setLocation(playerX, playerY);
        }
        else if(tutorialNum == 0 && (e.getX() < tutorialCrab.getX())){
            tutorialCrab.setLocation(tutorialCrab.getX() - 50, tutorialCrab.getY());
            tutorialText.setText("Click right of the crab to move to the right.");
            tutorialNum++;
            repaint();
        }
        else if(tutorialNum == 1 && (e.getX() > tutorialCrab.getX()+tutorialCrab.getIcon().getIconWidth())){
            tutorialCrab.setLocation(tutorialCrab.getX() + 50, tutorialCrab.getY());
            tutorialText.setText("Click above the crab to move forwards.");
            tutorialNum++;
            repaint();
        }
        else if(tutorialNum == 2 && (e.getY() < tutorialCrab.getY()-tutorialCrab.getIcon().getIconWidth()/2)){
            tutorialCrab.setLocation(tutorialCrab.getX(), tutorialCrab.getY()-50);
            tutorialText.setText("Eat the clam to get a bonus!!");
            if(tutorialCrab.getY() <= 350){
                tutorialBonus.setVisible(false);
                tutorialShark.setVisible(true);
                tutorialText.setText("Now see if you can eat that dogfish!");
            }
            if(tutorialCrab.getY()  == 250){
                tutorialCrab.setVisible(false);
                tutorialText.setText("Oh no! You have been eaten. Click anywhere to continue to the game.");
                tutorialNum++;
            }
            repaint();
        }
        else if(tutorialNum == 3){
            isTutorial = false;
            createDiffScreen();
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
    public void mousePressed(MouseEvent e) {
        
    }
    
    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }
    
    public void throwQuestion(Message currentMessage) {
        String[] buttons = new String[3];
        int r1 = (int)(Math.random()*3);
        int r2 = (int)(Math.random()*2);
        if(r1 == 0){
            buttons[0] = currentMessage.getRightAnswer();
            if(r2 == 0){
                buttons[1] = currentMessage.getWrongAnswer1();
                buttons[2] = currentMessage.getWrongAnswer2();
            }
            else{
                buttons[1] = currentMessage.getWrongAnswer2();
                buttons[2] = currentMessage.getWrongAnswer1();
            }
        }
        
        else if(r1 == 1){
            buttons[1] = currentMessage.getRightAnswer();
            if(r2 == 0){
                buttons[0] = currentMessage.getWrongAnswer1();
                buttons[2] = currentMessage.getWrongAnswer2();
            }
            else{
                buttons[0] = currentMessage.getWrongAnswer2();
                buttons[2] = currentMessage.getWrongAnswer1();
            }
        }
        else{
            buttons[2] = currentMessage.getRightAnswer();
            if(r2 == 0){
                buttons[1] = currentMessage.getWrongAnswer1();
                buttons[0] = currentMessage.getWrongAnswer2();
            }
            else{
                buttons[1] = currentMessage.getWrongAnswer2();
                buttons[0] = currentMessage.getWrongAnswer1();
            }
        }
        
        //{currentMessage.getRightAnswer(), currentMessage.getWrongAnswer1(), currentMessage.getWrongAnswer2()};
        System.out.println(currentMessage.getQuestion());
        for (int i = 0; i < buttons.length; i++) {
            System.out.println(buttons[i]);
        }
        int rc = JOptionPane.showOptionDialog(null,currentMessage.getQuestion(),"Quiz Question", JOptionPane.WARNING_MESSAGE, 0, null, buttons, null);
        if (rc == r1) {
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
                score = 0;
                createDiffScreen();
            }
        }
                                    );
        playAgain.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameOverScreen.add(playAgain);
        gameOverScreen.add(leaderboard);
        getContentPane().removeAll();
        getContentPane().add(gameOverScreen);
        //diffScreen.setOpaque(true);
        setVisible(true);
        repaint();
    }
    
    public void startWinWindow(){
        score += difficulty*control.getBoard().getRemainingTime()/10;
        JPanel winScreen = new JPanel();
        JPanel dummy = new JPanel(new BorderLayout(5,5));
        dummy.setBorder(BorderFactory.createEmptyBorder(0, getWidth()/4, 0, getWidth()/4));
        JPanel dummy2 = new JPanel(new FlowLayout());
        JPanel dummy3 = new JPanel(new BorderLayout(5,5));
        dummy.setOpaque(false);
        dummy2.setOpaque(false);
        dummy3.setOpaque(false);
        winScreen.setBackground(Color.black);
        winScreen.setLayout(new BorderLayout(0, 5));
        dummy3.setBorder(BorderFactory.createEmptyBorder(getHeight()/4, 0, 0, 0));
        JLabel winText = new JLabel("Congrats!!! You won!",SwingConstants.CENTER);
        winText.setFont(new Font(winText.getName(),Font.PLAIN, 72));
        winText.setAlignmentX(Component.CENTER_ALIGNMENT);
        dummy.add(winText, BorderLayout.NORTH);
        playAgain = new JButton("Play again?");
        playAgain.setBackground(new Color(159,253,255));
        playAgain.setOpaque(true);
        playAgain.setBorderPainted(false);
        playAgain.addActionListener(
                                    
                                    new ActionListener(){
            public void actionPerformed(ActionEvent e){
                clearStaticEnemies();
                score = 0;
                createDiffScreen();
            }
        }
                                    );
        playAgain.setAlignmentX(Component.CENTER_ALIGNMENT);
        playAgain.setSize(new Dimension(200,200));
        dummy2.add(playAgain);
        dummy.add(leaderboard, BorderLayout.CENTER);
        dummy3.add(dummy, BorderLayout.NORTH);
        dummy3.add(dummy2, BorderLayout.CENTER);
        try {
    		Image image = ImageIO.read(new File("images/Ocean Background.png"));
   		image = image.getScaledInstance(screenWidth, screenHeight, Image.SCALE_SMOOTH);
   		JLabel background = new JLabel(new ImageIcon(image));
   		background.setLayout(new BorderLayout());
   		background.add(dummy3);
   		background.setOpaque(false);
   		winScreen.add(background);
   		
	} catch (IOException e) {
   		e.printStackTrace();
	}
        
        getContentPane().removeAll();
        getContentPane().add(winScreen);
        //diffScreen.setOpaque(true);
        setVisible(true);
        if(score >= Integer.parseInt(textMatrix[9][1])){
            String tempName = "";
            String tempScore = "";
            String name = "";
            try{
                name = JOptionPane.showInputDialog("New high score! Please enter your name.");
            }catch(HeadlessException e){
                e.printStackTrace();
            }
            
            for(int i = 0; i < 10; i++){
                if(score >= Integer.parseInt(textMatrix[i][1])){
                    tempName = textMatrix[i][0];
                    tempScore = textMatrix[i][1];
                    textMatrix[i][0] = name;
                    textMatrix[i][1] = Integer.toString(score);
                    name = tempName;
                    score = Integer.parseInt(tempScore);
                }
            }
            
            String[] titles = {"Name","Score"};
            leaderboard = new JTable(textMatrix, titles);
            //leaderboard.setSize(new Dimension(360,400));
            try{
                writeLeaderboard(textMatrix);
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }
            
        }
        repaint();
    }
    
    public void clearStaticEnemies() {
        // SET NOTRASH AND NOSHARK TO TRUE AGAIN BEFORE DELETING EVERYTHING
        if(control.getBoard().getIsOcean()) {
            control.getBoard().getTheOneAndOnlyShark().setNoSharks(true);
            control.getBoard().getTheOneAndOnlyTrash().setNoTrash(true);
            control.getBoard().getTheOneAndOnlyShark().clearEnemyAtt();
            control.getBoard().getBonus().setNoBonus(true);
        }
        else {
            control.getBoard().getTheOneAndOnlyHuman().setNoHumans(true);
            control.getBoard().getTheOneAndOnlyBird().setNoBird(true);
            control.getBoard().getTheOneAndOnlyHuman().clearEnemyAtt();
            control.getBoard().getBonus().setNoBonus(true);
        }
    }
    
    private void runTutorial(){
        isTutorial = true;
        tutorialNum = 0;
        tutorialScreen = new JPanel();
        tutorialScreen.setLayout(null);
        tutorialCrab = new JLabel(new ImageIcon("images/brown rectangle.png"));
        tutorialCrab.setLocation(400,450);
        tutorialScreen.add(tutorialCrab);
        tutorialCrab.setBounds(new Rectangle(new Point(350, 450), new Dimension(tutorialCrab.getIcon().getIconWidth(), tutorialCrab.getIcon().getIconHeight())));
        tutorialBonus = new JLabel(new ImageIcon("images/clam.png"));
        tutorialBonus.setLocation(400,350);
        tutorialScreen.add(tutorialBonus);
        tutorialBonus.setBounds(new Rectangle(new Point(350, 350), new Dimension(tutorialBonus.getIcon().getIconWidth(), tutorialBonus.getIcon().getIconHeight())));
        tutorialShark = new JLabel(new ImageIcon("images/singleShark.png"));
        tutorialShark.setLocation(380,250);
        tutorialScreen.add(tutorialShark);
        tutorialShark.setVisible(false);
        tutorialShark.setBounds(new Rectangle(new Point(330, 250), new Dimension(tutorialShark.getIcon().getIconWidth(), tutorialShark.getIcon().getIconHeight())));
        tutorialText = new JLabel("Click left of the crab to move to the left", JLabel.CENTER);
        tutorialText.setFont(new Font(tutorialText.getName(),Font.PLAIN, 20));
        tutorialText.setAlignmentX(Component.CENTER_ALIGNMENT);
        tutorialText.setLocation(350,100);
        tutorialScreen.add(tutorialText);
        tutorialText.setBounds(0, 100, 800, 100);
        tutorialScreen.addMouseListener(this);
        tutorialScreen.setBackground(Color.blue);
        getContentPane().removeAll();
        getContentPane().add(tutorialScreen);
        //diffScreen.setOpaque(true);
        setVisible(true);
        repaint();
    }
    
    private void readLeaderboard() {
        String[] titles = {"Name","Score"};
        String leaderboardFile = "leaderboard.csv";
        BufferedReader br = null;
        String line = "";
        textMatrix = new String[10][2];
        int i = 0;
        try {
            br = new BufferedReader(new FileReader(leaderboardFile));
            while((line = br.readLine()) != null) {
                String[] values = line.split(",");
                textMatrix[i][0] = values[0];
                textMatrix[i][1] = values[1];
                i++;
            }
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        System.out.println(Arrays.deepToString(textMatrix));
        leaderboard = new JTable(textMatrix, titles);
        //leaderboard.setSize(new Dimension(360,400));
    }
    
    private void writeLeaderboard(String[][] input) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new File("leaderboard.csv"));
        String output = "";
        for (int i = 0; i < 10; i++) {
            output = output + input[i][0] + "," + input[i][1] + "\n";
        }
        pw.write(output);
        pw.close();
    }
    
}
