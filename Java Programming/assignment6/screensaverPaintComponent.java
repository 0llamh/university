// PAUL CAMERON SMITH
// COP3252 - JAVA
// ASSIGNMENT 6
// SCREENSAVER.JAVA
//		JPANEL PAINT COMPONENT OVERRIDE

import java.awt.BasicStroke;	// for changing the line thickness
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

public class screensaverPaintComponent extends JPanel implements ActionListener{

	private int delay = 1000; 	//1 sec delay (in milliseconds)
	private Timer timer;
	private Random r = new Random();
	private Graphics2D g2d;
	
	private int[] x = new int[50];	//x values of the ovals
	private int[] y = new int[50];	//y values of the ovals
	private int[] w = new int[50];	//width value of the ovals
	private int[] h = new int[50];	//heigh value of the ovals
	
	public screensaverPaintComponent(){
		timer = new Timer(delay, this);
		timer.start();	}
	public void actionPerformed(ActionEvent e) {
		repaint();	
	}	// repaints the panel with new ovals
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g2d = (Graphics2D) g;
		for (int oval = 0; oval < x.length; oval++){
			x[oval] = r.nextInt(this.getWidth());	// random x value within width
			y[oval] = r.nextInt(this.getHeight());	// random y value within height
			w[oval] = r.nextInt(this.getWidth()-x[oval]);	//keeps the entire width within the panel
			h[oval] = r.nextInt(this.getHeight()-y[oval]);	//keeps the height within the panel
			g2d.setStroke(new BasicStroke(r.nextInt(10)+1));
			g2d.setColor(new Color(	//random color
					r.nextInt(256), r.nextInt(256), r.nextInt(256)));
			g2d.drawOval(x[oval], y[oval], w[oval], h[oval]);
		}
		
	}
}
