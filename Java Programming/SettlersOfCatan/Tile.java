//PAUL CAMERON SMITH
//COP3252 - JAVA
//HOMEWORK X - SETTLERS OF CATAN
//BOARD TILE OBJECT CLASS

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class Tile {

	private int roll;
	private String terrain;
	private Color color;
	private Polygon shape;
	private int x[] = new int[6];
	private int y[] = new int[6];
	private boolean robber = false;
	
	Tile(String s, int r){
		this.setTerrain(s);
		this.setRoll(r);
		//sets the roll and terrain value for each specific tile
		if (s.compareTo("dessert") == 0)
			this.color = new Color(255, 255, 153);
		else if (s.compareTo("lumber") == 0)
			this.color = new Color(51, 102, 0);
		else if (s.compareTo("ore") == 0)
			this.color = Color.GRAY;
		else if (s.compareTo("brick") == 0)
			this.color = new Color(150, 40, 0);
		else if (s.compareTo("wheat") == 0)
			this.color = new Color(255, 255, 60);
		else if (s.compareTo("wool") == 0)
			this.color = new Color(180, 255, 105);
		//and sets their color depending on the terrain
		}
	public String getTerrain(){
		return this.terrain;}	//returns the terrain value
	public int getRoll(){
		return this.roll;}	//returns the roll value
	public int getX(int index){
		return this.x[index];}
	public int getY(int index){
		return this.y[index];}
	public int[] getAllX(){
		return this.x;}
	public int[] getAllY(){
		return this.y;}
	private void setTerrain(String s){
		this.terrain = s;}	//sets the terrain value
	private void setRoll(int r){
		this.roll = r;}		//sets the roll value
	public void setX(int index, int value){
		this.x[index] = value;}
	public void setY(int index, int value){
		this.y[index] = value;}
	public void removeRobber(){
		this.robber = false;
	}
	public void placeRobber(){
		this.robber = true;
	}
	public boolean checkRobber(){
		return this.robber;}
	
	public void createTile(int t, int[] x_, int[] y_){
		//X VALUES:::
		if (t == 10 || t == 9 || t == 8){
			//leftmost tiles
			this.setX(0, x_[0]);
			this.setX(1, x_[1]);
			this.setX(2, x_[2]);
			this.setX(3, x_[3]);
			this.setX(4, x_[2]);
			this.setX(5, x_[1]);
		}
		else if ( t == 11 || t == 17 || t == 16 || t == 7){
			//horizontal center-left tiles
			this.setX(0, x_[2]);
			this.setX(1, x_[3]);
			this.setX(2, x_[4]);
			this.setX(3, x_[5]);
			this.setX(4, x_[4]);
			this.setX(5, x_[3]);
			}
		else if (t == 0 || t == 12 || t == 18 || t == 15 || t == 6){
			//horizontal center tiles
			this.setX(0, x_[4]);
			this.setX(1, x_[5]);
			this.setX(2, x_[6]);
			this.setX(3, x_[7]);
			this.setX(4, x_[6]);
			this.setX(5, x_[5]);
			}
		else if (t == 1 || t == 13 || t == 14 || t == 5){
			//horizontal center-right tiles
			this.setX(0, x_[6]);
			this.setX(1, x_[7]);
			this.setX(2, x_[8]);
			this.setX(3, x_[9]);
			this.setX(4, x_[8]);
			this.setX(5, x_[7]);
			}
		else if (t == 2 || t == 3 || t == 4){
			//rightmost tiles
			this.setX(0, x_[8]);
			this.setX(1, x_[9]);
			this.setX(2, x_[10]);
			this.setX(3, x_[11]);
			this.setX(4, x_[10]);
			this.setX(5, x_[9]);
			}
		
		//Y VALUES:::		
		if (t == 0){	
			//top-most tile
			this.setY(0, y_[1]);
			this.setY(1, y_[0]);
			this.setY(2, y_[0]);
			this.setY(3, y_[1]);
			this.setY(4, y_[2]);
			this.setY(5, y_[2]);
		}
		else if (t == 11 || t == 1){
			//2nd row tiles
			this.setY(0, y_[2]);
			this.setY(1, y_[1]);
			this.setY(2, y_[1]);
			this.setY(3, y_[2]);
			this.setY(4, y_[3]);
			this.setY(5, y_[3]);
		}
		else if (t == 10 || t == 12 || t == 2){
			//3rd row tiles
			this.setY(0, y_[3]);
			this.setY(1, y_[2]);
			this.setY(2, y_[2]);
			this.setY(3, y_[3]);
			this.setY(4, y_[4]);
			this.setY(5, y_[4]);
		}
		else if (t == 17 || t == 13){
			//4th row tiles
			this.setY(0, y_[4]);
			this.setY(1, y_[3]);
			this.setY(2, y_[3]);
			this.setY(3, y_[4]);
			this.setY(4, y_[5]);
			this.setY(5, y_[5]);
		}
		else if (t == 9 || t == 18 || t == 3){
			//5th row tiles
			this.setY(0, y_[5]);
			this.setY(1, y_[4]);
			this.setY(2, y_[4]);
			this.setY(3, y_[5]);
			this.setY(4, y_[6]);
			this.setY(5, y_[6]);
		}
		else if (t == 16 || t == 14){
			//6th row tiles
			this.setY(0, y_[6]);
			this.setY(1, y_[5]);
			this.setY(2, y_[5]);
			this.setY(3, y_[6]);
			this.setY(4, y_[7]);
			this.setY(5, y_[7]);
		}
		else if (t == 8 || t == 15 || t == 4){
			//7th row tiles
			this.setY(0, y_[7]);
			this.setY(1, y_[6]);
			this.setY(2, y_[6]);
			this.setY(3, y_[7]);
			this.setY(4, y_[8]);
			this.setY(5, y_[8]);
		}
		else if (t == 7 || t == 5){
			//8th row tiles
			this.setY(0, y_[8]);
			this.setY(1, y_[7]);
			this.setY(2, y_[7]);
			this.setY(3, y_[8]);
			this.setY(4, y_[9]);
			this.setY(5, y_[9]);
		}
		else if (t == 6){
			//bottom most tile
			this.setY(0, y_[9]);
			this.setY(1, y_[8]);
			this.setY(2, y_[8]);
			this.setY(3, y_[9]);
			this.setY(4, y_[10]);
			this.setY(5, y_[10]);
		}
	}
	public void drawTile(Graphics2D g2d){
			// stores the coordinates into each tile obj
		g2d.setColor(this.color);
		g2d.fillPolygon(this.getAllX(), this.getAllY(), 6);
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(7));
		this.shape = new Polygon(this.getAllX(), this.getAllY(), 6);
		g2d.drawPolygon(this.shape);
	}
	public void drawRolls(int t, boolean robber, int[] x_, int[] y_, int s, int hh, Graphics2D g2d){
		g2d.setColor(Color.BLACK);
		int x = 0, y = 0;
		int radius = ((2*s)/5);
		
		//X VALUES:::
		
		if (t == 10 || t == 9 || t == 8){
			//leftmost tiles
			x = x_[1] + radius;}
		else if ( t == 11 || t == 17 || t == 16 || t == 7){
			//horizontal center-left tiles
			x = x_[3] + radius;}
		else if (t == 0 || t == 12 || t == 18 || t == 15 || t == 6){
			//horizontal center tiles
			x = x_[5] + radius;}
		else if (t == 1 || t == 13 || t == 14 || t == 5){
			//horizontal center-right tiles
			x = x_[7] + radius;}
		else if (t == 2 || t == 3 || t == 4){
			//rightmost tiles
			x = x_[9] + radius;}
		
		//Y VALUES:::		
		
		if (t == 0){	
			//top-most tile
			y = y_[1] + (hh/7);}
		else if (t == 11 || t == 1){
			//2nd row tiles
			y = y_[2] + (hh/7);}
		else if (t == 10 || t == 12 || t == 2){
			//3rd row tiles
			y = y_[3] + (hh/7);}
		else if (t == 17 || t == 13){
			//4th row tiles
			y = y_[4] + (hh/7);}
		else if (t == 9 || t == 18 || t == 3){
			//5th row tiles
			y = y_[5] + (hh/7);}
		else if (t == 16 || t == 14){
			//6th row tiles
			y = y_[6] + (hh/7);}
		else if (t == 8 || t == 15 || t == 4){
			//7th row tiles
			y = y_[7] + (hh/7);}
		else if (t == 7 || t == 5){
			//8th row tiles
			y = y_[8] + (hh/7);}
		else if (t == 6){
			//bottom most tile
			y = y_[9] + (hh/7);}
		
		g2d.setFont(new Font("SansSerif", Font.BOLD, s/3));
		if (robber == true){	//places the Robber on the Robber Tile
			g2d.drawString("R", x, y);}
		else{
			if (this.getRoll() > 9)
				x -= (s/9);	//centers double digit values
			if (this.getRoll() != 0)
				g2d.drawString(String.valueOf(this.getRoll()), x, y);
					//only displays the roll value for tiles other than the desert
		}
			
	}
	public boolean isInside(int x, int y){
		if (this.shape.contains(x, y))
			return true;
		else
			return false;
	}
}