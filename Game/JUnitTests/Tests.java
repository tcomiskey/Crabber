/*import static org.junit.Assert.*;

import org.junit.Test;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.junit.Test;


import org.junit.After;
import org.junit.Before;
public class Tests {
	
	//Maura
	@Test
	public void bonusMakeBonusTest() {
		Bonus b = Bonus.makeBonus(800, 200);
		assertEquals(200, b.getY());
		assertTrue(b.getX() < 800);
	}
	
//	//Have to make bonusLabel package private
//	@Test
//	public void viewUpdateBonusLocationTest(){
//		View v = new View();
//		v.setBonusX(250);
//		v.setBonusY(300);
//		assertEquals(250, v.bonusLabel.getX());
//		assertEquals(300, v.bonusLabel.getY());
//	}
//	
//	//not sure if this will work
//	@Test
//	public void viewMakeBonusLabelTest(){
//		View v = new View();
//		try{
//			v.makeBonusLabel(ImageIO.read(new File("images/clam.png")));
//		}catch (IOException e){
//			e.printStackTrace();
//		}
//		
//		try{
//			JLabel label = new JLabel(new ImageIcon(ImageIO.read(new File("images/clam.png"))));
//			assertEquals(label.getIcon(), v.bonusLabel.getIcon());
//		}catch (IOException e){
//			e.printStackTrace();
//		}
//		
//	}
//	
	//Have to make timerLabel package private
//	@Test
//	public void viewMakeTimerLabelTest(){
//		View v = new View();
//		v.makeTimerLabel("0:25");
//		assertEquals("0:25", v.timerLabel.getText());
//		assertEquals(Color.WHITE, v.timerLabel.getBackground());
//		assertEquals(new Dimension(100,50), v.timerLabel.getSize());
//	}
//	
//	@Test
//	public void viewUpdateTimerLabelTest(){
//		View v = new View();
//		v.makeTimerLabel("0:25");
//		v.updateTimerLabel("0:20");
//		assertEquals("0:20", v.timerLabel.getText());
//	}
	
//	@Test
//	public void boardGenerateBonusTest(){
//		Board b = new Board(1,true);
//		b.generateBonus();
//		assertTrue(b.getBonus().getY() < b.getPlayer().getY());
//		assertTrue(b.getBonus().getX() < b.getBoardWidth());
//	}
//	
//	@Test
//	public void boardRemoveBonusTest(){
//		Board b = new Board(1,true);
//		b.generateBonus();
//		b.removeBonus();
//		assertEquals(b.getBonus().getWidth()*-1, b.getBonus().getX());
//		assertEquals(b.getBonus().getHeight()*-1, b.getBonus().getY());
//	}
//	
//	@Test
//	public void boardBonusCollisionCheckTest(){
//		Board b = new Board(1,true);
//		b.generateBonus();
//		assertEquals(false, b.bonusCollisionCheck());
//		b.getPlayer().setX(b.getBonus().getX());
//		b.getPlayer().setY(b.getBonus().getY());
//		assertEquals(true, b.bonusCollisionCheck());
//	}
//	
//	@Test
//	public void boardTimeLeftTest(){
//		Board b = new Board(1,true);
//		assertEquals(true, b.timeLeft());
//		//Should wait over 30 sec and test when false (but not right now)
//	}
//	
//	//Have to make startTime package private
//	@Test
//	public void boardResetStartTimeTest(){
//		Board b = new Board(1,true);
//		b.resetStartTime(5000);
//		assertEquals(35000, b.startTime);
//	}
//	
	@Test
	public void boardUpdateRemainingTimeTest(){
		Board b = new Board(1,true);
		try{
			Thread.sleep(5000);			//Pauses for 5 seconds
		}catch(InterruptedException e){}
		b.updateRemainingTime();
		assertTrue(b.getRemainingTime() < 30000);
		
	}
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	//Erin
	@Test
    public void theOneAndOnlyTest() {
        Board b = new Board(1, true);
        Board b2 = new Board(1, false);
        //First test that when isOcean is true, Shark and Trash are made but not Human and Bird
        assertNotEquals(null, b.getTheOneAndOnlyShark());
        assertNotEquals(null, b.getTheOneAndOnlyTrash());
        assertEquals(null, b.getTheOneAndOnlyHuman());
        assertEquals(null, b.getTheOneAndOnlyBird());
        //Then test that when isOcean is false, Shark and Trash are not made but Human and Bird are
        assertEquals(null, b2.getTheOneAndOnlyShark());
        assertEquals(null, b2.getTheOneAndOnlyTrash());
        assertNotEquals(null, b2.getTheOneAndOnlyHuman());
        assertNotEquals(null, b2.getTheOneAndOnlyBird());
    }
    
    @Test
    public void resetPlayerTest() {
        Board b = new Board(1, true);
        Player boardPlayer = b.getPlayer();
        boardPlayer.setY(600);
        b.resetPlayer();
        //Test that after reset, playerX changes to closest safe row
        assertEquals(650, boardPlayer.getY());
    }
    
    @Test
    public void playerAtFinishTest() {
        Board b = new Board(1, true);
        Player boardPlayer = b.getPlayer();
        boardPlayer.setY(0);
        //test that playerAtFinish returns correct boolean
        assertEquals(true, b.playerAtFinish());
        
        boardPlayer.setY(600);
        assertEquals(false, b.playerAtFinish());
    }
    
    @Test
    public void bufferedImageTest() {
        Board b = new Board(1, true);
        BufferedImage bfi = null;
        try {
            bfi = ImageIO.read(new File("images/shark.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(bfi.getData(), b.getTheOneAndOnlyShark().getRightImage().getData());
        //check that the shark image doesn't match the trash image ???
        assertNotEquals(bfi.getData(), b.getTheOneAndOnlyTrash().getRightImage().getData());
    }
    
    @Test
    public void hitTest() {
        Board b = new Board(1, true);
        assertNotEquals(null, b.getTheOneAndOnlyShark().hit());
    }
    
    @Test
    public void moveTest() {
        Board b = new Board(1, true);
        int temp = b.getTheOneAndOnlyShark().getX();
        b.getTheOneAndOnlyShark().move();
        assertNotEquals(temp, b.getTheOneAndOnlyShark().getX());
    }
    
    @Test
    public void sharkFactoryTest() {
        Board b = new Board(1, true);
        assertEquals(null, Shark.sharkFactory(b.getBoardWidth(), 10, 10, 1));
    }
    //John
//    @Test
//	public void readLeaderboardTest() {
//		
//		View v = new View(5);
//		String[][] dummy = {{"John","7"},{"Erin","8"}};
//		v.textMatrix = dummy;
//		v.readLeaderboard();
//		assertNotEquals(dummy,v.textMatrix);
//		
//	}
//	
//	@Test
//	public void writeLeaderboardTest() {
//		View v = new View(5);
//		String answer = "John,7\nErin,8\nJohn,7\nErin,8\nJohn,7\nErin,8\nJohn,7\nErin,8\nJohn,7\nErin,8\n";
//		String[][] dummy = {{"John","7"},{"Erin","8"},{"John","7"},{"Erin","8"},{"John","7"},{"Erin","8"},{"John","7"},{"Erin","8"},{"John","7"},{"Erin","8"}};
//		v.textMatrix = dummy;
//		try {
//			assertEquals(answer,v.writeLeaderboard(v.textMatrix));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		assertEquals(dummy,v.textMatrix);
//	}
//	
//	@Test
//	public void runTutorialTest(){
//		View v = new View(5);
//		assertNull(v.tutorialScreen);
//		v.runTutorial();
//		assertEquals(true,v.isTutorial);
//		assertNotNull(v.tutorialScreen);
//	}
//	
//	@Test
//	public void createDiffScreenTest(){
//		View v = new View(5);
//		assertNull(v.diffScreen);
//		v.createDiffScreen();
//		assertNotNull(v.diffScreen);
//	}
//	
//	@Test
//	public void makePlayerLabelTest(){
//		View v = new View(5);
//		assertNull(v.playerLabel);
//		BufferedImage image = null;
//		try {
//            image = ImageIO.read(new File("images/brown rectangle.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//		v.makePlayerLabel(image);
//		assertNotNull(v.playerLabel);
//	}
//	
//	@Test
//	public void makeBonusLabelTest(){
//		View v = new View(5);
//		assertNull(v.bonusLabel);
//		BufferedImage image = null;
//		try {
//            image = ImageIO.read(new File("images/brown rectangle.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//		v.makePlayerLabel(image);
//		assertNotNull(v.bonusLabel);
//	}
//	
	//	@Test 
	//	public void startWinWindowTest(){
	//		View v = new View(5);
	//		v.control = new Controller(v);
	//		assertNull(v.winScreen);
	//		v.startWinWindow();
	//		assertNotNull(v.winScreen);
	//		v.control.timer.stop();
	//	}
	
	//Kaeini
//	private Player horseshoecrab;
//	
//	@Before
//	public void setUp() {
//		horseshoecrab = new Player(800,700);
//	}
//	
//	@After
//	public void after() {
//		horseshoecrab = null;
//	}
//	@Test
//	public void moveLeftRightTest() {
//		int startLocation = horseshoecrab.getX();
//		horseshoecrab.moveRight();
//		horseshoecrab.moveLeft();
//		assertEquals(startLocation, horseshoecrab.getX());
//	}
//	
//	@Test
//	public void moveForwardTest() {
//		int startLocation = horseshoecrab.getY();
//		horseshoecrab.moveForward();
//		assertEquals(startLocation - horseshoecrab.getPlayerHeight(), horseshoecrab.getY());
//	}
 * 
 */
}


