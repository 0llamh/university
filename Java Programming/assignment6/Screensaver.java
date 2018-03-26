// PAUL CAMERON SMITH
// COP3252 - JAVA
// ASSIGNMENT 6
// SCREENSAVER.JAVA
//		MAIN CLASS DISPLAYING SCREENSAVER

import java.awt.*;
import javax.swing.*;

public class Screensaver {
	
	public static void main(String[] args){
		JFrame frame = new JFrame("Screensaver");
		frame.setSize(800, 600); //800x600 starting window
		screensaverPaintComponent sPC = new screensaverPaintComponent();
		frame.add(sPC);	//places screensaver into frame
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}
}
