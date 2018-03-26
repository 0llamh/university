// PAUL CAMERON SMITH
// COP3252 - JAVA
// ASSIGNMENT 6
// SNOWMAN.JAVA
//		MAIN CLASS DISPLAYING A SNOWMAN

import java.awt.*;
import javax.swing.*;

public class Snowman {

	public static void main(String[] args){
		JFrame frame = new JFrame("Snowman");
		frame.setSize(600,400);	//initial frame size
		snowmanPaintComponent sPC = new snowmanPaintComponent();
		frame.add(sPC);	//places the panel into the frame
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}
	
}
