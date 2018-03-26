//PAUL CAMERON SMITH
//COP3252 - JAVA
//HOMEWORK X - SETTLERS OF CATAN
//BOARD PLOT OBJECT CLASS FOR CITIES AND SETTLEMENTS

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Plot {
	
	private int value;	//PLOT VALUE
			// 0 = EMPTY; 1 = SETTLEMENT; 2 = CITY
	private int player;	//owner of plot
	private Color color;
	public Tile[] tiles;
	private int borderingTiles;
	private int x;		//x coord of plot
	private int y;		//y coord of plot
	
	Plot(int v, int p, Color c){
		this.value = v;		//default 0 (no structure)
		this.player = p;	//default 0 (no owner)
		this.color = c;
		this.tiles = new Tile[0];
		this.borderingTiles = 0;
	}
	public int getValue(){
		return this.value;}
	public int getPlayer(){
		return this.player;}
	public Color getColor(){
		return this.color;}
	public Tile getTile(int index){
		return this.tiles[index];}
	public int getAmtTiles(){
		return this.borderingTiles;}
	public int getX(){
		return this.x;}
	public int getY(){
		return this.y;}
	
	public void setValue(int v){
		this.value = v;}
	public void setPlayer(int p, Color c){
		this.player = p;
		this.color = c;}
	public void setCoords(int x_, int y_){
		this.x = x_;
		this.y = y_;}
	
	public void upgrade(int v, int p, Color c){
		this.value = v;
		this.player = p;
		this.color = c;
	}	
	public void setTiles(int p, Tile[] t){
		switch (p){
		case 0: this.tiles = new Tile[1]; 
			this.tiles[0] = (t[0]);
				break;
		case 1: this.tiles = new Tile[1]; 
			this.tiles[0] = (t[0]);
				break;
		case 2: this.tiles = new Tile[2]; 
			this.tiles[0] = (t[0]);
			this.tiles[1] = (t[1]);
				break;
		case 3: this.tiles = new Tile[1]; 
			this.tiles[0] = (t[1]);
				break;
		case 4: this.tiles = new Tile[2]; 
			this.tiles[0] = (t[1]);
			this.tiles[1] = (t[2]);
				break;
		case 5: this.tiles = new Tile[1]; 
			this.tiles[0] = (t[2]);
				break;
		case 6: this.tiles = new Tile[1]; 
			this.tiles[0] = (t[2]);
				break;
		case 7: this.tiles = new Tile[2]; 
			this.tiles[0] = (t[2]);
			this.tiles[1] = (t[3]);
				break;
		case 8: this.tiles = new Tile[1]; 
			this.tiles[0] = (t[3]);
				break;
		case 9: this.tiles = new Tile[2]; 
			this.tiles[0] = (t[3]);
			this.tiles[1] = (t[4]);
				break;
		case 10: this.tiles = new Tile[1]; 
			this.tiles[0] = (t[4]);
				break;
		case 11: this.tiles = new Tile[1]; 
			this.tiles[0] = (t[4]);
				break;
		case 12: this.tiles = new Tile[2]; 
			this.tiles[0] = (t[4]);
			this.tiles[1] = (t[5]);
				break;
		case 13: this.tiles = new Tile[1]; 
			this.tiles[0] = (t[5]);
				break;
		case 14: this.tiles = new Tile[2]; 
			this.tiles[0] = (t[5]);
			this.tiles[1] = (t[6]);
				break;
		case 15: this.tiles = new Tile[1]; 
			this.tiles[0] = (t[6]);
				break;
		case 16: this.tiles = new Tile[1]; 
			this.tiles[0] = (t[6]);
				break;
		case 17: this.tiles = new Tile[2]; 
			this.tiles[0] = (t[6]);
			this.tiles[1] = (t[7]);
				break;
		case 18: this.tiles = new Tile[1]; 
			this.tiles[0] = (t[7]);
				break;
		case 19: this.tiles = new Tile[2]; 
			this.tiles[0] = (t[7]);
			this.tiles[1] = (t[8]);
				break;
		case 20: this.tiles = new Tile[1]; 
			this.tiles[0] = (t[8]);
				break;
		case 21: this.tiles = new Tile[1]; 
			this.tiles[0] = (t[8]);
				break;
		case 22: this.tiles = new Tile[2]; 
			this.tiles[0] = (t[8]);
			this.tiles[1] = (t[9]);
				break;
		case 23: this.tiles = new Tile[1]; 
			this.tiles[0] = (t[9]);
				break;
		case 24: this.tiles = new Tile[2]; 
			this.tiles[0] = (t[9]);
			this.tiles[1] = (t[10]);
				break;
		case 25: this.tiles = new Tile[1]; 
			this.tiles[0] = (t[10]);
				break;
		case 26: this.tiles = new Tile[1]; 
			this.tiles[0] = (t[10]);
				break;
		case 27: this.tiles = new Tile[2]; 
			this.tiles[0] = (t[10]);
			this.tiles[1] = (t[11]);
				break;
		case 28: this.tiles = new Tile[1]; 
			this.tiles[0] = (t[11]);
				break;
		case 29: this.tiles = new Tile[2]; 
			this.tiles[0] = (t[11]);
			this.tiles[1] =	(t[0]);
				break;
		case 30: this.tiles = new Tile[3]; 
			this.tiles[0] = (t[1]);
			this.tiles[1] = (t[11]);
			this.tiles[2] = (t[12]);
				break;
		case 31: this.tiles = new Tile[3]; 
			this.tiles[0] = (t[0]);
			this.tiles[1] = (t[1]);
			this.tiles[2] = (t[12]); //.getRoll() + t[1].getRoll() + t[12].getRoll());
				break;
		case 32: this.tiles = new Tile[3]; 
			this.tiles[0] = (t[1]);
			this.tiles[1] = (t[12]);
			this.tiles[2] = (t[13]); //.getRoll() + t[12].getRoll() + t[13].getRoll());
				break;
		case 33: this.tiles = new Tile[3]; 
			this.tiles[0] = (t[1]);
			this.tiles[1] = (t[2]);
			this.tiles[2] = (t[13]); //.getRoll() + t[2].getRoll() + t[13].getRoll());
				break;
		case 34: this.tiles = new Tile[3]; 
			this.tiles[0] = (t[2]);
			this.tiles[1] = (t[3]);
			this.tiles[2] = (t[13]); //.getRoll() + t[3].getRoll() + t[13].getRoll());
				break;
		case 35: this.tiles = new Tile[3]; 
			this.tiles[0] = (t[3]);
			this.tiles[1] = (t[13]);
			this.tiles[2] = (t[14]); //.getRoll() + t[13].getRoll() + t[14].getRoll());
				break;
		case 36: this.tiles = new Tile[3]; 
			this.tiles[0] = (t[3]);
			this.tiles[1] = (t[4]);
			this.tiles[2] = (t[14]); //).getRoll() + t[4].getRoll() + t[14].getRoll());
				break;
		case 37: this.tiles = new Tile[3]; 
			this.tiles[0] = (t[4]);
			this.tiles[1] = (t[5]);
			this.tiles[2] = (t[14]); //.getRoll() + t[5].getRoll() + t[14].getRoll());
				break;
		case 38: this.tiles = new Tile[3]; 
			this.tiles[0] = (t[5]);
			this.tiles[1] = (t[14]);
			this.tiles[2] = (t[15]); //.getRoll() + t[14].getRoll() + t[15].getRoll());
				break;
		case 39: this.tiles = new Tile[3]; 
			this.tiles[0] = (t[5]);
			this.tiles[1] = (t[6]);
			this.tiles[2] = (t[15]);	//.getRoll() + t[6].getRoll() + t[15].getRoll());
				break;
		case 40: this.tiles = new Tile[3]; 
			this.tiles[0] = (t[6]);
			this.tiles[1] = (t[7]);
			this.tiles[2] = (t[15]); //).getRoll() + t[7].getRoll() + t[15].getRoll());
				break;
		case 41: this.tiles = new Tile[3]; 
			this.tiles[0] = (t[7]);
			this.tiles[1] = (t[15]);
			this.tiles[2] = (t[16]); //.getRoll() + t[15].getRoll() + t[16].getRoll());
				break;
		case 42: this.tiles = new Tile[3]; 
			this.tiles[0] = (t[7]);
			this.tiles[1] = (t[8]);
			this.tiles[2] = (t[16]); //.getRoll() + t[8].getRoll() + t[16].getRoll()); 
				break;
		case 43: this.tiles = new Tile[3]; 
			this.tiles[0] = (t[8]);
			this.tiles[1] = (t[9]);
			this.tiles[2] = (t[16]); //.getRoll() + t[9].getRoll() + t[16].getRoll());
				break;
		case 44: this.tiles = new Tile[3]; 
			this.tiles[0] = (t[9]);
			this.tiles[1] = (t[16]);
			this.tiles[2] = (t[17]); //.getRoll() + t[16].getRoll() + t[17].getRoll());
				break;
		case 45: this.tiles = new Tile[3]; 
			this.tiles[0] = (t[9]);
			this.tiles[1] = (t[10]);
			this.tiles[2] = (t[17]); //.getRoll() + t[10].getRoll() + t[17].getRoll());
				break;
		case 46: this.tiles = new Tile[3]; 
			this.tiles[0] = (t[10]);
			this.tiles[1] = (t[11]);
			this.tiles[2] = (t[17]); //(t[10].getRoll() + t[11].getRoll() + t[17].getRoll());
				break;
		case 47: this.tiles = new Tile[3]; 
			this.tiles[0] = (t[11]);
			this.tiles[1] = (t[12]);
			this.tiles[2] = (t[17]); //(t[11].getRoll() + t[12].getRoll() + t[17].getRoll());
				break;
		case 48: this.tiles = new Tile[3]; 
			this.tiles[0] = (t[12]);
			this.tiles[1] = (t[17]);
			this.tiles[2] = (t[18]); //(t[12].getRoll() + t[17].getRoll() + t[18].getRoll());
				break;
		case 49: this.tiles = new Tile[3]; 
			this.tiles[0] = (t[12]);
			this.tiles[1] = (t[13]);
			this.tiles[2] = (t[18]);//(t[12].getRoll() + t[13].getRoll() + t[18].getRoll());
				break;
		case 50: this.tiles = new Tile[3]; 
			this.tiles[0] = (t[13]);
			this.tiles[1] = (t[14]);
			this.tiles[2] = (t[18]); //(t[13].getRoll() + t[14].getRoll() + t[18].getRoll());
				break;
		case 51: this.tiles = new Tile[3]; 
			this.tiles[0] = (t[14]);
			this.tiles[1] = (t[15]);
			this.tiles[2] = (t[18]); //(t[14].getRoll() + t[15].getRoll() + t[18].getRoll());
				break;
		case 52: this.tiles = new Tile[3]; 
			this.tiles[0] = (t[15]);
			this.tiles[1] = (t[16]);
			this.tiles[2] = (t[18]); // (t[15].getRoll() + t[16].getRoll() + t[18].getRoll());
				break;
		case 53: this.tiles = new Tile[3]; 
			this.tiles[0] = (t[16]);
			this.tiles[1] = (t[17]);
			this.tiles[2] = (t[18]); //(t[16].getRoll() + t[17].getRoll() + t[18].getRoll());
				break;	
		}
		this.borderingTiles = this.tiles.length;
	}
	public void createPlots(int p, Tile[] t, int[] x_, int[] y_){
		// where case is the plot index, t is the tile, and params passed into
					// the getX and getY are the t's vertex x and y values
		switch (p){
		//TILE 0 PLOTS
		case 0: this.setCoords(t[0].getX(1), t[0].getY(1)); break;
		case 1:	this.setCoords(t[0].getX(2), t[0].getY(2)); break;
		case 2: this.setCoords(t[0].getX(3), t[0].getY(3)); break;
		case 31: this.setCoords(t[0].getX(4), t[0].getY(4)); break;
		case 30: this.setCoords(t[0].getX(5), t[0].getY(5)); break;
		case 29: this.setCoords(t[0].getX(0), t[0].getY(0)); break;
		//TILE 1 PLOTS
		case 3: this.setCoords(t[1].getX(2), t[1].getY(2)); break;
		case 4: this.setCoords(t[1].getX(3), t[1].getY(3)); break;
		case 33: this.setCoords(t[1].getX(4), t[1].getY(4)); break;
		case 32: this.setCoords(t[1].getX(5), t[1].getY(5)); break;
		//TILE 2 PLOTS
		case 5: this.setCoords(t[2].getX(2), t[2].getY(2)); break;
		case 6: this.setCoords(t[2].getX(3), t[2].getY(3)); break;
		case 7: this.setCoords(t[2].getX(4), t[2].getY(4)); break;
		case 34: this.setCoords(t[2].getX(5), t[2].getY(5)); break;
		//TILE 3 PLOTS
		case 8: this.setCoords(t[3].getX(3), t[3].getY(3)); break;
		case 9: this.setCoords(t[3].getX(4), t[3].getY(4)); break;
		case 36: this.setCoords(t[3].getX(5), t[3].getY(5)); break;
		case 35: this.setCoords(t[3].getX(0), t[3].getY(0)); break;
		//TILE 4 PLOTS
		case 10: this.setCoords(t[4].getX(3), t[4].getY(3)); break;
		case 11: this.setCoords(t[4].getX(4), t[4].getY(4)); break;
		case 12: this.setCoords(t[4].getX(5), t[4].getY(5)); break;
		case 37: this.setCoords(t[4].getX(0), t[4].getY(0)); break;
		//TILE 5 PLOTS
		case 13: this.setCoords(t[5].getX(4), t[5].getY(4)); break;
		case 14: this.setCoords(t[5].getX(5), t[5].getY(5)); break;
		case 39: this.setCoords(t[5].getX(0), t[5].getY(0)); break;
		case 38: this.setCoords(t[5].getX(1), t[5].getY(1)); break;
		//TILE 6 PLOTS
		case 15: this.setCoords(t[6].getX(4), t[6].getY(4)); break;
		case 16: this.setCoords(t[6].getX(5), t[6].getY(5)); break;
		case 17: this.setCoords(t[6].getX(0), t[6].getY(0)); break;
		case 40: this.setCoords(t[6].getX(1), t[6].getY(1)); break;
		//TILE 7 PLOTS
		case 18: this.setCoords(t[7].getX(5), t[7].getY(5)); break;
		case 19: this.setCoords(t[7].getX(0), t[7].getY(0)); break;
		case 42: this.setCoords(t[7].getX(1), t[7].getY(1)); break;
		case 41: this.setCoords(t[7].getX(2), t[7].getY(2)); break;
		//TILE 8 PLOTS
		case 20: this.setCoords(t[8].getX(5), t[8].getY(5)); break;
		case 21: this.setCoords(t[8].getX(0), t[8].getY(0)); break;
		case 22: this.setCoords(t[8].getX(1), t[8].getY(1)); break;
		case 43: this.setCoords(t[8].getX(2), t[8].getY(2)); break;
		//TILE 9 PLOTS
		case 23: this.setCoords(t[9].getX(0), t[9].getY(0)); break;
		case 24: this.setCoords(t[9].getX(1), t[9].getY(1)); break;
		case 45: this.setCoords(t[9].getX(2), t[9].getY(2)); break;
		case 44: this.setCoords(t[9].getX(3), t[9].getY(3)); break;
		//TILE 10 PLOTS 
		case 25: this.setCoords(t[10].getX(0), t[10].getY(0)); break;
		case 26: this.setCoords(t[10].getX(1), t[10].getY(1)); break; 
		case 27: this.setCoords(t[10].getX(2), t[10].getY(2)); break;
		case 46: this.setCoords(t[10].getX(3), t[10].getY(3)); break;
		//TILE 11 PLOTS
		case 28: this.setCoords(t[11].getX(1), t[11].getY(1)); break;
		case 47: this.setCoords(t[11].getX(4), t[11].getY(4)); break;
		//TILE 12 PLOTS
		case 49: this.setCoords(t[12].getX(4), t[12].getY(4)); break;
		case 48: this.setCoords(t[12].getX(5), t[12].getY(5)); break;
		//TILE 13 PLOTS
		case 50: this.setCoords(t[13].getX(5), t[13].getY(5)); break;
		//TILE 14 PLOTS
		case 51: this.setCoords(t[14].getX(0), t[14].getY(0)); break;
		//TILE 15 PLOTS
		case 52: this.setCoords(t[15].getX(1), t[15].getY(1)); break;
		//TILE 16 PLOTS
		case 53: this.setCoords(t[16].getX(2), t[16].getY(2)); break;
		}
	}
	public void drawPlot(int s, Graphics2D g2d){
		if (this.getValue() == 1){
			g2d.setColor(this.color);
			g2d.fillOval(this.getX()-s, this.getY()-s, s*2, s*2);
			g2d.setColor(Color.BLACK);
			g2d.setStroke(new BasicStroke(2));
			g2d.drawOval(this.getX()-s, this.getY()-s, s*2, s*2);}
		else if (this.getValue() == 2){
			g2d.setColor(this.color);
			g2d.fillRect(this.getX()-s, this.getY()-s, s*2, s*2);
			g2d.setColor(Color.BLACK);
			g2d.setStroke(new BasicStroke(2));
			g2d.drawRect(this.getX()-s, this.getY()-s, s*2, s*2);}
		//g2d.drawString(String.valueOf(this.tiles), x, y);
	}
}
