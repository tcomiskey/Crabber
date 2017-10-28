import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.*;

public class Controller{

	Board b;
	View v;

<<<<<<< HEAD
	public Controller(View v){
		this.v = v;//don't know what this constructor looks like yet
		b = new Board(v.getDifficulty());//where does board get diff? (buttons but like actually how)
=======
	public Controller(){
		v = new View();//don't know what this constructor looks like yet
		v.setVisible(true);
        b = new Board(v.getDifficulty());//where does board get diff? (buttons but like actually how)
>>>>>>> 0b69ce4af86b37028ebe7c7a20d483a13e58bce1
		System.out.println(b);
		b.moveCharacter();
		System.out.println(b);
	}

<<<<<<< HEAD
=======
	/*public void actionPerformed(ActionEvent e){
		//if(e.getSource() == v.getEasyButton()){
			System.out.println("It works!");
        
		//}
	}*/
>>>>>>> 0b69ce4af86b37028ebe7c7a20d483a13e58bce1
}

	
