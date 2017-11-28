/**
* A subclass of Message that holds the message displayed when a Bonus is hit
* @author Erin Hitchner
*/

public class BonusMessage extends Message{

	private String bonusMessage;

	/**
	Creates a BonusMessage object and assigned its message
	*/
	public BonusMessage(){
		// Sets message that will be displayed
		bonusMessage = "You ate a clam! Enemies will be slower!";
		// will probably need some method call to actually change the speed of enemies
	}
	public String toString(){
		return bonusMessage;
	}
	public String getBonusMessage(){
		return bonusMessage;
	}
}
