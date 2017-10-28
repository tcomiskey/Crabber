import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.*;

public class Controller implements ActionListener{

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

	/*public void actionPerformed(ActionEvent e){
		//if(e.getSource() == v.getEasyButton()){
			System.out.println("It works!");
        
		//}
	}*/
}

	
