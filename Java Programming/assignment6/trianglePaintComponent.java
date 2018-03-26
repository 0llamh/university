// PAUL CAMERON SMITH
// COP3252 - JAVA
// ASSIGNMENT 6
// TRIANGLE PAINT COMPONENT
//		EXTENDS THE JPANEL AND OVERRIDES THE PAINTCOMPONENT METHOD

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.Random;
import javax.swing.JPanel;

public class trianglePaintComponent extends JPanel {

	private int tri_x;
	private int tri_y;
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);	//calls JPanel method
		for (int shape = 0; shape < 5; shape++){
			Random r = new Random();	// new random obj
			tri_x = (1 + r.nextInt(this.getWidth()));
			tri_y = (1 + r.nextInt(this.getHeight()));
			// sets a random value to the start x and y values
				// depending on the current width and height
			Graphics2D g2d = (Graphics2D) g;
			GeneralPath triangle = new GeneralPath();
			triangle.moveTo(tri_x, tri_y);
				// draws the triangle using the random values
			for (int vertices = 1; vertices < 3; vertices++)
				triangle.lineTo(1 + r.nextInt(this.getWidth()),
						1+ r.nextInt(this.getHeight()));
				//draws a line to a random vertex within the panel
			triangle.closePath();
			g2d.setColor(new Color(
				//creates new color with randm rgb values
					r.nextInt(256), r.nextInt(256), r.nextInt(256)));
			g2d.fill(triangle);
				//fills in the triangle with the line color
			g2d.draw(triangle);
		}
	}
}