//PAUL CAMERON SMITH
//COP3252 - JAVA
//HOMEWORK X - SETTLERS OF CATA
//HARBOR OBJECT CLASS

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class Harbor {
	
	private String port;
	private Color color;
	private int[] x = new int[4];	// x values
	private int[] y = new int[4];	// v values
	private Polygon shape;
	
	Harbor(String s){
		this.setPort(s);
		//sets the port value and the color dependent on the port
		if (s.compareTo("2 Lumber:1") == 0)
			this.color = (new Color(51, 102, 0));
		else if (s.compareTo("2 Ore:1") == 0)
			this.color = Color.GRAY;
		else if (s.compareTo("2 Brick:1") == 0)
			this.color = (new Color(150, 40, 0));
		else if (s.compareTo("2 Wheat:1") == 0)
			this.color = (new Color(255, 255, 60));
		else if (s.compareTo("2 Wool:1") == 0)
			this.color = (new Color(180, 255, 105));
		else
			this.color = Color.WHITE;
		}
	public String getPort(){
		return this.port;}
	public int getX(int index){
		return this.x[index];}
	public int getY(int index){
		return this.y[index];}
	public int[] getAllX(){
		return this.x;}
	public int[] getAllY(){
		return this.y;}
	private void setPort(String s){
		this.port = new String (s);}
	public void setX(int index, int value){
		this.x[index] = value;}
	public void setY(int index, int value){
		this.y[index] = value;}
	
	public void createHarbor(int h, int[] x, int[] y, int s, int hh, Graphics2D g2d){
		if (h == 0){
			this.setX(0, x[7] + (s/12));
			this.setX(1, x[7] + (s/6));
			this.setX(2, x[8] - (s/6));
			this.setX(3, x[8] - (s/12));
			//////////////////////////////////////////////////
			this.setY(0, y[1] - (hh/8));
			this.setY(1, y[1] - (hh/3));
			this.setY(2, y[1] - (hh/3));
			this.setY(3, y[1] - (hh/8));
		}
		else if (h == 1){
			this.setX(0, x[10] + (s/9));
			this.setX(1, x[10] + (s/3));
			this.setX(2, x[11] + (s/6));
			this.setX(3, x[11] + (s/18));
			//////////////////////////////////////////////////
			this.setY(0, y[2]);
			this.setY(1, y[2]);
			this.setY(2, y[3] - (hh/3));
			this.setY(3, y[3] - (hh/8));
		}
		else if (h == 2){
			this.setX(0, x[11] + (s/18));
			this.setX(1, x[11] + (s/6));
			this.setX(2, x[10] + (s/3));
			this.setX(3, x[10] + (s/9));
			//////////////////////////////////////////////////
			this.setY(0, y[5] + (hh/8));
			this.setY(1, y[5] + (hh/3));
			this.setY(2, y[6]);
			this.setY(3, y[6]);
		}
		else if (h == 3){
			this.setX(0, x[9] + (s/18));
			this.setX(1, x[9] + (s/6));
			this.setX(2, x[8] + (s/3));
			this.setX(3, x[8] + (s/9));
			//////////////////////////////////////////////////
			this.setY(0, y[8] + (hh/8));
			this.setY(1, y[8] + (hh/3));
			this.setY(2, y[9]);
			this.setY(3, y[9]);
		}
		else if (h == 4){
			this.setX(0, x[5] + (s/12));
			this.setX(1, x[5] + (s/6));
			this.setX(2, x[6] - (s/6));
			this.setX(3, x[6] - (s/12));
			//////////////////////////////////////////////////
			this.setY(0, y[10] + (hh/8));
			this.setY(1, y[10] + (hh/3));
			this.setY(2, y[10] + (hh/3));
			this.setY(3, y[10] + (hh/8));
		}
		else if (h == 5){
			this.setX(0, x[2] - (s/18));
			this.setX(1, x[2] - (s/6));
			this.setX(2, x[3] - (s/3));
			this.setX(3, x[3] - (s/9));
			//////////////////////////////////////////////////
			this.setY(0, y[8] + (hh/8));
			this.setY(1, y[8] + (hh/3));
			this.setY(2, y[9]);
			this.setY(3, y[9]);
		}
		else if (h == 6){
			this.setX(0, x[0] - (s/18));
			this.setX(1, x[0] - (s/6));
			this.setX(2, x[1] - (s/3));
			this.setX(3, x[1] - (s/9));
			//////////////////////////////////////////////////
			this.setY(0, y[5] + (hh/8));
			this.setY(1, y[5] + (hh/3));
			this.setY(2, y[6]);
			this.setY(3, y[6]);
		}
		else if (h == 7){
			this.setX(0, x[0] - (s/18));
			this.setX(1, x[0] - (s/6));
			this.setX(2, x[1] - (s/3));
			this.setX(3, x[1] - (s/9));
			//////////////////////////////////////////////////
			this.setY(0, y[3] - (hh/8));
			this.setY(1, y[3] - (hh/3));
			this.setY(2, y[2]);
			this.setY(3, y[2]);
		}
		else if (h == 8){
			this.setX(0, x[3] + (s/12));
			this.setX(1, x[3] + (s/6));
			this.setX(2, x[4] - (s/6));
			this.setX(3, x[4] - (s/12));
			//////////////////////////////////////////////////
			this.setY(0, y[1] - (hh/8));
			this.setY(1, y[1] - (hh/3));
			this.setY(2, y[1] - (hh/3));
			this.setY(3, y[1] - (hh/8));
		}
	}
	public void drawHarbor(Graphics2D g2d){
		g2d.setColor(this.color);
		g2d.fillPolygon(this.getAllX(), this.getAllY(), 4);
		g2d.setStroke(new BasicStroke(2));
		g2d.setColor(Color.BLACK);
		this.shape = new Polygon(this.getAllX(), this.getAllY(), 4);
		g2d.drawPolygon(this.shape);
	}
	public boolean isInside(int x, int y){
		if (this.shape.contains(x, y))
			return true;
		else
			return false;
	}
}	