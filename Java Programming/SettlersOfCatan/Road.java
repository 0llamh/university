//PAUL CAMERON SMITH
//COP3252 - JAVA
//HOMEWORK X - SETTLERS OF CATAN
//ROAD OBJECT CLASS FOR PAYERS

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.BasicStroke;
import java.awt.Color;

public class Road {

	private boolean exists;
	private int player; //owner of road
	private Color color;
	private Line2D line;
	private int[] x = new int[2];	//x values for the road
	private int[] y = new int[2]; 	//y values
	
	Road(boolean b, int p, Color c){
		this.exists = b;
		this.player = p;
		this.color = c;
	}
	
	public boolean getExists(){
		return this.exists;}
	public int getPlayer(){
		return this.player;}
	public Color getColor(){
		return this.color;}
	public int getX(int index){
		if (index == 0 || index == 1)
			return this.x[index];
		else
			return 0;
	}
	public int getY(int index){
		if (index == 0 || index == 1)
			return this.y[index];
		else
			return 0;
	}
	
	public void buyRoad(boolean b, int p, Color c){
		this.exists = b;
		this.player = p;
		this.color = c;}
	
	
	public void createRoads(int r, Plot[] p){
		switch (r){
		case 0: this.setCoords(p[0].getX(), p[0].getY(), p[1].getX(), p[1].getY());
			break;
		case 1: this.setCoords(p[1].getX(), p[1].getY(), p[2].getX(), p[2].getY());
			break;
		case 2: this.setCoords(p[2].getX(), p[2].getY(), p[3].getX(), p[3].getY());
			break;
		case 3: this.setCoords(p[3].getX(), p[3].getY(), p[4].getX(), p[4].getY());
			break;
		case 4: this.setCoords(p[4].getX(), p[4].getY(), p[5].getX(), p[5].getY());
			break;
		case 5: this.setCoords(p[5].getX(), p[5].getY(), p[6].getX(), p[6].getY());
			break;
		case 6: this.setCoords(p[6].getX(), p[6].getY(), p[7].getX(), p[7].getY());
			break;
		case 7: this.setCoords(p[7].getX(), p[7].getY(), p[8].getX(), p[8].getY());
			break;
		case 8: this.setCoords(p[8].getX(), p[8].getY(), p[9].getX(), p[9].getY());
			break;
		case 9: this.setCoords(p[9].getX(), p[9].getY(), p[10].getX(), p[10].getY());
			break;
		case 10: this.setCoords(p[10].getX(), p[10].getY(), p[11].getX(), p[11].getY());
			break;
		case 11: this.setCoords(p[11].getX(), p[11].getY(), p[12].getX(), p[12].getY());
			break;
		case 12: this.setCoords(p[12].getX(), p[12].getY(), p[13].getX(), p[13].getY());
			break;
		case 13: this.setCoords(p[13].getX(), p[13].getY(), p[14].getX(), p[14].getY());
			break;
		case 14: this.setCoords(p[14].getX(), p[14].getY(), p[15].getX(), p[15].getY());
			break;
		case 15: this.setCoords(p[15].getX(), p[15].getY(), p[16].getX(), p[16].getY());
			break;
		case 16: this.setCoords(p[16].getX(), p[16].getY(), p[17].getX(), p[17].getY());
			break;
		case 17: this.setCoords(p[17].getX(), p[17].getY(), p[18].getX(), p[18].getY());
			break;
		case 18: this.setCoords(p[18].getX(), p[18].getY(), p[19].getX(), p[19].getY());
			break;
		case 19: this.setCoords(p[19].getX(), p[19].getY(), p[20].getX(), p[20].getY());
		 	break;
		case 20: this.setCoords(p[20].getX(), p[20].getY(), p[21].getX(), p[21].getY());
			break;
		case 21: this.setCoords(p[21].getX(), p[21].getY(), p[22].getX(), p[22].getY());
			break;
		case 22: this.setCoords(p[22].getX(), p[22].getY(), p[23].getX(), p[23].getY());
			break;
		case 23: this.setCoords(p[23].getX(), p[23].getY(), p[24].getX(), p[24].getY());
			break;
		case 24: this.setCoords(p[24].getX(), p[24].getY(), p[25].getX(), p[25].getY());
			break;
		case 25: this.setCoords(p[25].getX(), p[25].getY(), p[26].getX(), p[26].getY());
			break;
		case 26: this.setCoords(p[26].getX(), p[26].getY(), p[27].getX(), p[27].getY());
			break;
		case 27: this.setCoords(p[27].getX(), p[27].getY(), p[28].getX(), p[28].getY());
			break;
		case 28: this.setCoords(p[28].getX(), p[28].getY(), p[29].getX(), p[29].getY());
			break;
		case 29: this.setCoords(p[29].getX(), p[29].getY(), p[0].getX(), p[0].getY());
			break;
		case 30: this.setCoords(p[29].getX(), p[29].getY(), p[30].getX(), p[30].getY());
			break;
		case 31: this.setCoords(p[30].getX(), p[30].getY(), p[31].getX(), p[31].getY());
			break;
		case 32: this.setCoords(p[31].getX(), p[31].getY(), p[2].getX(), p[2].getY());
			break;
		case 33: this.setCoords(p[31].getX(), p[31].getY(), p[32].getX(), p[32].getY());
			break;
		case 34: this.setCoords(p[32].getX(), p[32].getY(), p[33].getX(), p[33].getY());
			break;
		case 35: this.setCoords(p[33].getX(), p[33].getY(), p[4].getX(), p[4].getY());
			break;
		case 36: this.setCoords(p[33].getX(), p[33].getY(), p[34].getX(), p[34].getY());
			break;
		case 37: this.setCoords(p[34].getX(), p[34].getY(), p[7].getX(), p[7].getY());
			break;
		case 38: this.setCoords(p[34].getX(), p[34].getY(), p[35].getX(), p[35].getY());
			break;
		case 39: this.setCoords(p[35].getX(), p[35].getY(), p[36].getX(), p[36].getY());
			break;
		case 40: this.setCoords(p[36].getX(), p[36].getY(), p[9].getX(), p[9].getY());
			break;
		case 41: this.setCoords(p[36].getX(), p[36].getY(), p[37].getX(), p[37].getY());
			break;
		case 42: this.setCoords(p[37].getX(), p[37].getY(), p[12].getX(), p[12].getY());
			break;
		case 43: this.setCoords(p[37].getX(), p[37].getY(), p[38].getX(), p[38].getY());
			break;
		case 44: this.setCoords(p[38].getX(), p[38].getY(), p[39].getX(), p[39].getY());
			break;
		case 45: this.setCoords(p[39].getX(), p[39].getY(), p[14].getX(), p[14].getY());
			break;
		case 46: this.setCoords(p[39].getX(), p[39].getY(), p[40].getX(), p[40].getY());
			break;
		case 47: this.setCoords(p[40].getX(), p[40].getY(), p[17].getX(), p[17].getY());
			break;
		case 48: this.setCoords(p[40].getX(), p[40].getY(), p[41].getX(), p[41].getY());
			break;
		case 49: this.setCoords(p[41].getX(), p[41].getY(), p[42].getX(), p[42].getY());
			break;
		case 50: this.setCoords(p[42].getX(), p[42].getY(), p[19].getX(), p[19].getY());
			break;
		case 51: this.setCoords(p[42].getX(), p[42].getY(), p[43].getX(), p[43].getY());
			break;
		case 52: this.setCoords(p[43].getX(), p[43].getY(), p[22].getX(), p[22].getY());
			break;
		case 53: this.setCoords(p[43].getX(), p[43].getY(), p[44].getX(), p[44].getY());
			break;
		case 54: this.setCoords(p[44].getX(), p[44].getY(), p[45].getX(), p[45].getY());
			break;
		case 55: this.setCoords(p[45].getX(), p[45].getY(), p[24].getX(), p[24].getY());
			break;
		case 56: this.setCoords(p[45].getX(), p[45].getY(), p[46].getX(), p[46].getY());
			break;
		case 57: this.setCoords(p[46].getX(), p[46].getY(), p[27].getX(), p[27].getY());
			break;
		case 58: this.setCoords(p[46].getX(), p[46].getY(), p[47].getX(), p[47].getY());
			break;
		case 59: this.setCoords(p[47].getX(), p[47].getY(), p[30].getX(), p[30].getY());
			break;
		case 60: this.setCoords(p[47].getX(), p[47].getY(), p[48].getX(), p[48].getY());
			break;
		case 61: this.setCoords(p[48].getX(), p[48].getY(), p[49].getX(), p[49].getY());
			break;
		case 62: this.setCoords(p[49].getX(), p[49].getY(), p[32].getX(), p[32].getY());
			break;
		case 63: this.setCoords(p[49].getX(), p[49].getY(), p[50].getX(), p[50].getY());
			break;
		case 64: this.setCoords(p[50].getX(), p[50].getY(), p[35].getX(), p[35].getY());
			break;
		case 65: this.setCoords(p[50].getX(), p[50].getY(), p[51].getX(), p[51].getY());
			break;
		case 66: this.setCoords(p[51].getX(), p[51].getY(), p[38].getX(), p[38].getY());
			break;
		case 67: this.setCoords(p[51].getX(), p[51].getY(), p[52].getX(), p[52].getY());
			break;
		case 68: this.setCoords(p[52].getX(), p[52].getY(), p[41].getX(), p[41].getY());
			break;
		case 69: this.setCoords(p[52].getX(), p[52].getY(), p[53].getX(), p[53].getY());
			break;
		case 70: this.setCoords(p[53].getX(), p[53].getY(), p[44].getX(), p[44].getY());
			break;
		case 71: this.setCoords(p[53].getX(), p[53].getY(), p[48].getX(), p[48].getY());
			break;
		}
	}
	
	private void setCoords(int x1, int y1, int x2, int y2){
		this.x[0] = x1;
		this.x[1] = x2;
		this.y[0] = y1;
		this.y[1] = y2;
	}
	
	public void drawRoad(Graphics2D g2d){
		if (exists){
			g2d.setStroke(new BasicStroke(3));
			g2d.setColor(this.color);
			g2d.draw(new Line2D.Double((double) this.x[0], (double) this.y[0],
								(double) this.x[1], (double) this.y[1]));
		}
	}
	public boolean isInside(int x, int y){
		//the following code is a little wonky
		//because Line2D is an abstract class, I cannot create an object,
		//so I have to duplicate the create of it
		//I chose to use the intersect() function since contains() 
		//only works for shapes with an area, and lines don't have area
		//and used a buffer area of 3.0 to allow the use to have terrible
		//mouse accuracy when selecting a line
		if(new Line2D.Double((double) this.x[0], (double) this.y[0],
				(double) this.x[1], (double) this.y[1]).intersects(
						(double)x, (double)y, 2.0, 2.0))
			return true;
		else
			return false;
	}
	public boolean isConnected(Player p, int[] x, int[] y){
		//checks to see if the soon to be built road will be
		//touching/connected to the road build by the same player
		if (this.player == p.getPlayerID()){
			if ( this.x[0] == x[1] && this.y[0] == y[1])
				return true;
			else if (this.x[1] == x[0] && this.y[1] == y[0])
				return true;
			else
				return false;
		}
		else return false;
	}
	
	
	
}
