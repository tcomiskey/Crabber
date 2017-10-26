public class BonusMessage extends Message{
	private String bonusMessage;
	public BonusMessage(){
		bonusMessage = "You ate a worm! Enemies will be slower!";
	}
	public String toString(){
		return bonusMessage;
	}
	public String getBonusMessage(){
		return bonusMessage;
	}
}
