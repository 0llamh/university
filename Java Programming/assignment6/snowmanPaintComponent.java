// PAUL CAMERON SMITH
// COP3252 - JAVA
// ASSIGNMENT 6
// SNOWMAN PAINT COMPONENT
//		EXTENDS JPANEL TO CREATE A SNOWMAN

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.Random;
import javax.swing.*;

public class snowmanPaintComponent extends JPanel {

	private Random r = new Random();
	private int c_x[] = new int[5];
	private int c_y[] = new int[5];
	private int h = this.getHeight();
	private int w = this.getWidth();
	private int diameter = 0;
	
	//arm values
	private int x1 = (w/2) - (w/8);
	private int x2 = (w/2) - ((2*w)/64);
	private int x3 = (w/2) + ((2*w)/64);;
	private int x4 = (w/2) + (w/8);
	private int y1 = (h/4);
	private int y2 = (11*h)/32;
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
			//circle center values
		for (int circle = 0; circle < 5; circle++){
			if (circle == 0){	//head circle
				c_y[circle] = (h/8); 
				diameter = (h/8);
				c_x[circle] = (w/2)-(diameter/2);}	//horizontally centered
			else if (circle == 1 || circle == 2){// eye circles
				c_y[circle] = (5*h)/32;
				diameter = (h/80);
				if (circle == 1)
					c_x[circle] = (w/2)-(h/32);
				else if (circle == 2)
					c_x[circle] = (w/2)+((h/32)-diameter);}
			else if (circle == 3){	//middle circle
				c_y[circle] = (h/4);
				diameter = (h/4);
				c_x[circle] = (w/2)-(diameter/2);}	//horizontally centered
			else if (circle == 4){ 	//bottom circle
				c_y[circle] = (h/2);
				diameter = (3*h)/8;
				c_x[circle] = (w/2)-(diameter/2);}	//horizontally centered				
			Graphics2D g2d = (Graphics2D) g;
			if (circle == 1 || circle ==2){// for the eye circles only
				g2d.setColor(Color.BLACK);
				g2d.fillOval(c_x[circle], c_y[circle], diameter, diameter);}
			else{	// everything else
				g2d.setColor(new Color(
						//creates new color with randm rgb values
							r.nextInt(256), r.nextInt(256), r.nextInt(256)));
				g2d.drawOval(c_x[circle], c_y[circle], diameter, diameter);}	
		}	//does the body circles of the snowman
		Graphics2D g2d2 = (Graphics2D) g;
		g2d2.setColor(Color.BLACK);	//black arms
		g2d2.drawLine(x1, y1, x2, y2);
		g2d2.drawLine(x3, y2, x4, y1);
	}
}
