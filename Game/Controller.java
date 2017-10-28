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

	public Controller(View v){
		this.v = v;//don't know what this constructor looks like yet
		b = new Board(v.getDifficulty());//where does board get diff? (buttons but like actually how)
		System.out.println(b);
		b.moveCharacter();
		System.out.println(b);
	}

}

	
