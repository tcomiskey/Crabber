import org.junit.*;
import static org.junit.Assert.assertEquals;

public class Tests {

    @Test
    public void testHitMessage() {
        int boardWidth = 100;
        Bonus newBonus = new Bonus(boardWidth);
        Enemy newEnemy = new Enemy(boardWidth);
        
        
        assertEquals("You ate a worm! Enemies will be slower!", newBonus.hit().toString());
    }

}
