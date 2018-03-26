// PAUL CAMERON SMITH
// COP3252 - JAVA
// ASSIGNMENT 6
// TRIANGLE.JAVA
//		DRAWS RANDOM TRIANGLES 

import java.awt.*;
import javax.swing.*;

public class Triangles {

	public static void main(String[] args){
		JFrame frame = new JFrame("Triangles");	// creates the frame with title
		frame.setSize(500, 500);	//initial frame size
		trianglePaintComponent tPC = new trianglePaintComponent();
		frame.add(tPC);	//places the panel into the frame
		frame.setVisible(true);	// shows the frame
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}
}