public class Controller{

	Board b;
	View v;

	public Controller(){
		v = new View();//don't know what this constructor looks like yet
        v.setVisible(true);
		b = new Board(v.getDifficulty());//where does board get diff? (buttons but like actually how)
        System.out.println(b);
        b.moveCharacter();
        System.out.println(b);
	}

}

	
