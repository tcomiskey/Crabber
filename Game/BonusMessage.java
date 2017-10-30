public class BonusMessage extends Message{

	private String bonusMessage;
	
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
