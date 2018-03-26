//PAUL CAMERON SMITH
//COP 3252 - JAVA
//HOMEWORK X - SETTLERS OF CATAN
//BOARD CLASS

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

public class Board extends JPanel{
	
	private int[] x_coords;
	private int[] y_coords;
	private Tile[] tile = new Tile[19];
		private String[] terrain = {"dessert", "ore", "ore", "ore", "brick", 
			"brick", "brick", "wheat", "wheat", "wheat", "wheat", "wool",
			"wool", "wool", "wool", "lumber", "lumber", "lumber", "lumber"};
			//THESE ARE THE DEFAULT TERRAIN VALUES IN CATAN
		private int[] rolls = {5,2,6,3,8,10,9,12,11,4,8,10,9,4,5,6,3,11};
			//THE DEFAULT CATAN ROLL VALUES IN ALPHABETICAL ORDER
		private int tile_side;		//TILE SIDE LENGTH
		private int tile_halfheight;//TILE HALF HEIGHT
	private Harbor[] harbor;	//9 TRADING PORTS
		private String[] h_trade = {"2 Lumber:1", "2 Wool:1", "2 Wheat:1",
				"2 Brick:1", "2 Ore:1", "3:1", "3:1", "3:1", "3:1"};
	private Plot[] plot;
	private Road[] road;
	private static Random rand = new Random();
	private static Graphics2D g2d;
	private BorderLayout layout;
	public BoardStatus BoardStatus;
	private String whatToBuild;
	public boolean initialPhase;

	public Board(){
		super();
		this.setSize(600, 600);
		
		CatanMouseListener cml = new CatanMouseListener();
		addMouseListener(cml);
		addMouseMotionListener(cml);
		
		x_coords = new int[13];
		y_coords = new int[11];
		tile = new Tile[19];
		harbor = new Harbor[9];
		plot = new Plot[54];
		road = new Road[72];
		
		whatToBuild = new String();
		
		//CREATES TILES
		shuffle(terrain);
		for (int t = 0, r = 0; t < terrain.length; t++, r++){
			if (terrain[t].compareTo("dessert") == 0){
				tile[t] = new Tile("dessert", 0);
				r--;
				tile[t].placeRobber();}
			else
				tile[t] = new Tile(terrain[t], rolls[r]);
		}
		//CREATE HARBORS 
		shuffle(h_trade);
		for (int i = 0; i < h_trade.length; i++)
			harbor[i] = new Harbor(h_trade[i]);
		//CREATE PLOTS WITH NO VALUE, NO OWNER, AND NO COLOR
		for (int p = 0; p < plot.length; p++){
			plot[p] = new Plot(0, 0, Color.BLACK);
		}	
		//CREATE ROADS WITH NO VALUE, NO OWNER, AND NO COLOR
		for (int r = 0; r < road.length; r++){
			road[r] = new Road(false, 0, Color.BLACK);
		}
		
		layout = new BorderLayout();
		this.setLayout(layout);
		BoardStatus = new BoardStatus("Welcome to Settlers of Catan!");	//creats JPanel
		add(BoardStatus, BorderLayout.EAST);
		
		this.setVisible(true);
		initialPhase = true;
		
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g2d = (Graphics2D) g;
		tile_side = (this.getHeight()/10);
		tile_halfheight = (this.getHeight()/12);
		Arrays.fill(x_coords, 0);	// fills x_coord array with 0s
		Arrays.fill(y_coords, 0);	// does the same for y_coord array
		for (int i = 0; i < x_coords.length; i++){
			if (i == 0)
				x_coords[i] = tile_side;	//first value
			else if (i%2 == 0 && i > 0)	//each even number after that
				x_coords[i] = x_coords[i-1] + tile_side;	//is the polygon's side val
			else	//each odd number after is half the side value
				x_coords[i] = x_coords[i-1] + (tile_side/2);	//because of 30-60-90 triangle ratio
		}	// x-value coords for the polygons
		for (int i = 0; i < y_coords.length; i++){
			if (i == 0)
				y_coords[i] = tile_halfheight;
			else
				y_coords[i] = y_coords[i-1] + tile_halfheight;
		}	// y-value coords for the polygons
		for (int t = 0; t < tile.length; t++){
			//color(tile[t].getTerrain());
			tile[t].createTile(t, x_coords, y_coords);
			tile[t].drawTile(g2d);
			tile[t].drawRolls(t, tile[t].checkRobber(), x_coords, y_coords, tile_side, tile_halfheight, g2d);
				//draws the roll values to their appropriate tiles
		}
		for ( int h = 0; h < harbor.length; h++){
			//color(harbor[h].getPort());
			harbor[h].createHarbor(h, x_coords, y_coords, tile_side, tile_halfheight, g2d);
			harbor[h].drawHarbor(g2d);//draws the harbors
		}
		for ( int p = 0; p < plot.length; p++){
			//color(plot[p].getColor());
			plot[p].createPlots(p, tile, x_coords, y_coords);
		}
		for (int r = 0; r < road.length; r++){
			//color(road[r].getColor());
			//g2d.setColor(Color.MAGENTA);
			road[r].createRoads(r, plot);
			road[r].drawRoad(g2d);
		}
		for ( int p = 0; p < plot.length; p++){
			//color(plot[p].getColor());
			//g2d.setColor(Color.MAGENTA);
			plot[p].setTiles(p, tile);
			plot[p].createPlots(p, tile, x_coords, y_coords);
			plot[p].drawPlot(tile_side/8, g2d);
		}
	}
	/**
	 * Shuffles an array of strings, namely the terrain values of the tiles
	 * @param s
	 */
	private static void shuffle(String[] s){
		for(int i = 0; i < s.length; i++){
			int r = rand.nextInt(s.length);	// random index in the array
			String temp = s[i];
			s[i] = s[r];
			s[r] = temp;	// simple array swapping
		}
	}
	/**
	 * 
	 * Changes the color graphics based up the string value passed in;
	 * player or resource
	 * @param s
	 */
	public static void color(String s){
		if (s.compareTo("dessert") == 0)
			g2d.setColor(new Color(255, 255, 153));
		else if (s.compareTo("lumber") == 0 || s.compareTo("2 Lumber:1") == 0)
			g2d.setColor(new Color(51, 102, 0));
		else if (s.compareTo("ore") == 0 || s.compareTo("2 Ore:1") == 0)
			g2d.setColor(Color.GRAY);
		else if (s.compareTo("brick") == 0 || s.compareTo("2 Brick:1") == 0)
			g2d.setColor(new Color(150, 40, 0));
		else if (s.compareTo("wheat") == 0 || s.compareTo("2 Wheat:1") == 0)
			g2d.setColor(new Color(255, 255, 60));
		else if (s.compareTo("wool") == 0 || s.compareTo("2 Wool:1") == 0)
			g2d.setColor(new Color(180, 255, 105));
		else if (s.compareTo("red") == 0)
			g2d.setColor(Color.RED);
		else if (s.compareTo("blue") == 0)
			g2d.setColor(Color.BLUE);
		else if (s.compareTo("orange") == 0)
			g2d.setColor(Color.ORANGE);
		else if (s.compareTo("pink") == 0)
			g2d.setColor(Color.PINK);
		else if (s.compareTo("black") == 0)
			g2d.setColor(Color.BLACK);
		else
			g2d.setColor(Color.WHITE);
	}
	/** Returns a specific Road given the ID (index) */
	public Road getRoad(int index){
		return this.road[index];}
	/** Returns a specific Plot given the ID (index) */
	public Plot getPlot(int index){
		return this.plot[index];}
	private boolean validPlot(int p){
		boolean valid = false;
		if (p >= 0 && p <= 53){
			if (p == 0){ 
				if (plot[1].getValue() > 0 || plot[29].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 1){ 
				if (plot[0].getValue() > 0 || plot[2].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 2){ 
				if (plot[1].getValue() > 0 || plot[3].getValue() > 0 || plot[31].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 3){ 
				if (plot[2].getValue() > 0 || plot[3].getValue() > 0) // || plot[31].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 4){ 
				if (plot[3].getValue() > 0 || plot[5].getValue() > 0 || plot[33].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 5){ 
				if (plot[4].getValue() > 0 || plot[6].getValue() > 0) // || plot[31].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 6){
				if (plot[5].getValue() > 0 || plot[7].getValue() > 0) // || plot[31].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 7){ 
				if (plot[6].getValue() > 0 || plot[8].getValue() > 0 || plot[34].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 8){ 
				if (plot[7].getValue() > 0 || plot[9].getValue() > 0) // || plot[31].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 9){ 
				if (plot[8].getValue() > 0 || plot[10].getValue() > 0 || plot[36].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 10){ 
				if (plot[9].getValue() > 0 || plot[11].getValue() > 0) // || plot[31].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 11){ 
				if (plot[10].getValue() > 0 || plot[12].getValue() > 0) // || plot[31].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 12){ 
				if (plot[11].getValue() > 0 || plot[13].getValue() > 0 || plot[37].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 13){
				if (plot[12].getValue() > 0 || plot[14].getValue() > 0) // || plot[31].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 14){ 	
				if (plot[13].getValue() > 0 || plot[15].getValue() > 0 || plot[39].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 15){ 
				if (plot[14].getValue() > 0 || plot[16].getValue() > 0) // || plot[31].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 16){ 
				if (plot[15].getValue() > 0 || plot[17].getValue() > 0) // || plot[31].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 17){
				if (plot[16].getValue() > 0 || plot[18].getValue() > 0 || plot[40].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 18){
				if (plot[17].getValue() > 0 || plot[19].getValue() > 0) // || plot[31].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 19){
				if (plot[18].getValue() > 0 || plot[20].getValue() > 0 || plot[42].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 20){ 
				if (plot[19].getValue() > 0 || plot[21].getValue() > 0) // || plot[31].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 21){ 
				if (plot[20].getValue() > 0 || plot[22].getValue() > 0) // || plot[31].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 22){ 
				if (plot[21].getValue() > 0 || plot[23].getValue() > 0 || plot[43].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 23){ 
				if (plot[22].getValue() > 0 || plot[24].getValue() > 0) // || plot[31].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 24){ 
				if (plot[23].getValue() > 0 || plot[25].getValue() > 0 || plot[45].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 25){ 
				if (plot[24].getValue() > 0 || plot[26].getValue() > 0) // || plot[31].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 26){ 
				if (plot[25].getValue() > 0 || plot[27].getValue() > 0) // || plot[31].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 27){ 
				if (plot[26].getValue() > 0 || plot[28].getValue() > 0 || plot[46].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 28){ 
				if (plot[27].getValue() > 0 || plot[29].getValue() > 0) // || plot[31].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 29){ 
				if (plot[0].getValue() > 0 || plot[28].getValue() > 0 || plot[30].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 30){ 
				if (plot[29].getValue() > 0 || plot[31].getValue() > 0 || plot[47].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 31){ 
				if (plot[2].getValue() > 0 || plot[30].getValue() > 0 || plot[32].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 32){ 
				if (plot[31].getValue() > 0 || plot[33].getValue() > 0 || plot[49].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 33){ 
				if (plot[4].getValue() > 0 || plot[32].getValue() > 0 || plot[34].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 34){ 
				if (plot[7].getValue() > 0 || plot[33].getValue() > 0 || plot[35].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 35){ 
				if (plot[34].getValue() > 0 || plot[36].getValue() > 0 || plot[50].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 36){ 
				if (plot[9].getValue() > 0 || plot[35].getValue() > 0 || plot[37].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 37){ 
				if (plot[12].getValue() > 0 || plot[36].getValue() > 0 || plot[38].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 38){ 
				if (plot[37].getValue() > 0 || plot[39].getValue() > 0 || plot[51].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 39){ 
				if (plot[14].getValue() > 0 || plot[38].getValue() > 0 || plot[40].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 40){ 
				if (plot[17].getValue() > 0 || plot[39].getValue() > 0 || plot[41].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 41){ 
				if (plot[40].getValue() > 0 || plot[42].getValue() > 0 || plot[52].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 42){ 
				if (plot[19].getValue() > 0 || plot[41].getValue() > 0 || plot[43].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 43){ 
				if (plot[2].getValue() > 0 || plot[42].getValue() > 0 || plot[44].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 44){ 
				if (plot[43].getValue() > 0 || plot[45].getValue() > 0 || plot[53].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 45){ 
				if (plot[24].getValue() > 0 || plot[44].getValue() > 0 || plot[46].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 46){ 
				if (plot[27].getValue() > 0 || plot[45].getValue() > 0 || plot[47].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 47){ 
				if (plot[30].getValue() > 0 || plot[46].getValue() > 0 || plot[48].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 48){ 
				if (plot[47].getValue() > 0 || plot[49].getValue() > 0 || plot[53].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 49){ 
				if (plot[32].getValue() > 0 || plot[48].getValue() > 0 || plot[50].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 50){ 
				if (plot[35].getValue() > 0 || plot[49].getValue() > 0 || plot[51].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 51){ 
				if (plot[38].getValue() > 0 || plot[50].getValue() > 0 || plot[52].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 52){ 
				if (plot[41].getValue() > 0 || plot[51].getValue() > 0 || plot[53].getValue() > 0)
					return false;
				else
					return true;}
			if (p == 53){ 
				if (plot[44].getValue() > 0 || plot[48].getValue() > 0 || plot[52].getValue() > 0)
					return false;
				else
					return true;}
		}
		else
			return false;
		return valid;
	}
	public void selectStructure(String s){
		this.whatToBuild = new String(s);
	}
	
	/** This is a nested public class which displays select events
	 * from the player and game processes. This is essentially the
	 * Game's "Combat Log"*/
	public class BoardStatus extends JPanel{
		
		public BoardLog logBox;
		
		BoardStatus(String s){
			super();
			this.logBox = new BoardLog(s);
			this.setLayout(new BorderLayout());
			add(new JPanel(), BorderLayout.CENTER);
			add(logBox, BorderLayout.SOUTH);
		}
		public void addLog(String s){
			this.logBox.addLog(s);}
		public void currentLog(String s){
			this.logBox.currentLog(s);}
		public void updateLog(){
			this.logBox.updateLog();
		}
		/** The history log itself aligned to the bottom of the panel.
		 * @author Cameron
		 */
		private class BoardLog extends JPanel{

			private GridLayout layout;
			private JLabel[] log;

			BoardLog(String s){
				super();
				this.setBorder(BorderFactory.createTitledBorder(null,
						"Game Log:", TitledBorder.LEFT, TitledBorder.TOP));
				this.log = new JLabel[11];
				this.layout = new GridLayout(this.log.length, 1);
				this.setLayout(layout);
				for (int i = 0; i < this.log.length; i++)
					this.log[i] = new JLabel(" ", JLabel.RIGHT);
				this.addLog(s);
			}	
			public void addLog(String s){
				for (int i = 0; i < this.log.length-2; i++){
					this.log[i].setText(this.log[i+1].getText());
					//this.log[i].setVerticalAlignment(SwingConstants.BOTTOM);
					//this.log[i].setHorizontalAlignment(SwingConstants.RIGHT);
				}
				this.log[this.log.length-2].setText(s);
				this.updateLog();
			}
			public void currentLog(String s){
				this.log[this.log.length-1].setText(s);
			}
			private void updateLog(){
				for (int i = 0; i < this.log.length; i++){
					this.add(log[i]);
				}
			}
		}
	}
	private class CatanMouseListener implements MouseListener, MouseMotionListener{
		
		/** This will implement mouse-over info for components on the game board*/
		@Override
		public void mouseMoved(MouseEvent e) {
			for (int i = 0; i < tile.length; i++){
				if (tile[i].isInside(e.getX(), e.getY())){
					if (tile[i].checkRobber())
						BoardStatus.currentLog("This tile is occupied by the Robber :(");
					else
						BoardStatus.currentLog("This tile contains " + tile[i].getTerrain());
				}
			}
			for (int i = 0; i < harbor.length; i++){
				if (harbor[i].isInside(e.getX(), e.getY())){
					BoardStatus.currentLog("This Harbor allows the trade of " + harbor[i].getPort());
				}
			}
			for (int roads = 0; roads < road.length; roads++){
				if (road[roads].isInside(e.getX(), e.getY())){
					if(road[roads].getExists()){
						BoardStatus.currentLog("Player " + road[roads].getPlayer() + "'s road.");
					}
				}
			}
			for (int plots = 0; plots < plot.length; plots++){
				if ( e.getX() <= plot[plots].getX()+tile_side/8 && e.getX() >= plot[plots].getX()-tile_side/8 
						&& e.getY() <= plot[plots].getY()+tile_side/8 && e.getY() >= plot[plots].getY()-tile_side/8){
					if (plot[plots].getValue() == 1)
						BoardStatus.currentLog("Player " + plot[plots].getPlayer() + "'s settlement.");
					else if (plot[plots].getValue() == 2)
						BoardStatus.currentLog("Player " + plot[plots].getPlayer() + "'s city.");
				}
			}
		}
		/** This will be used to select components for building and selection*/
		@Override
		public void mouseClicked(MouseEvent e) {
			//BoardStatus.logBox.currentLog("Cursor clicked at: " + e.getX() + "," + + e.getY());
			if (whatToBuild.compareTo("city") == 0){
				for (int i = 0; i < plot.length; i++){
					if( e.getX() <= plot[i].getX()+tile_side/8 && e.getX() >= plot[i].getX()-tile_side/8 
							&& e.getY() <= plot[i].getY()+tile_side/8 && e.getY() >= plot[i].getY()-tile_side/8){
						if (plot[i].getValue() > 1)
							BoardStatus.addLog("Select a settlement.");
						else if(plot[i].getPlayer() > UserView.getCurrentPlayer().getPlayerID())
							BoardStatus.addLog("This is not your settlement.");
						else{	
							BoardStatus.addLog("Player " + UserView.getCurrentPlayer().getPlayerID()
								+ " built a city.");						
							UserView.getCurrentPlayer().deductResources(whatToBuild, "void", 0);
							plot[i].upgrade(2, UserView.getCurrentPlayer().getPlayerID(), 
									UserView.getCurrentPlayer().getColor());
							UserView.refresh();
							whatToBuild = new String();
						}
					}
				}
			}
			else if (whatToBuild.compareTo("settlement") == 0 ||
						whatToBuild.compareTo("settlement_INITIAL") == 0){
				for (int i = 0; i < plot.length; i++){
					if( e.getX() <= plot[i].getX()+tile_side/8 && e.getX() >= plot[i].getX()-tile_side/8 
							&& e.getY() <= plot[i].getY()+tile_side/8 && e.getY() >= plot[i].getY()-tile_side/8
								&& plot[i].getValue() == 0){
						//checks to see if the cursor was clicked in an empty plot space
						if (validPlot(i)){
							if (whatToBuild.compareTo("settlement") == 0){
								for (int r = 0; r < road.length; r++){	
									if ((plot[i].getX() == road[r].getX(0) && plot[i].getY() == road[r].getY(0) && road[r].getPlayer() == UserView.getCurrentPlayer().getPlayerID())
											|| plot[i].getX() == road[r].getY(1) && plot[i].getY() == road[r].getY(0) && road[r].getPlayer() == UserView.getCurrentPlayer().getPlayerID()){
										plot[i].upgrade(1, UserView.getCurrentPlayer().getPlayerID(),
												UserView.getCurrentPlayer().getColor());
										UserView.getCurrentPlayer().addPlot(plot[i]);
										BoardStatus.addLog("Player " + UserView.getCurrentPlayer().getPlayerID()
												+ " built a settlement.");	
										UserView.getCurrentPlayer().deductResources(whatToBuild, "void", 0);
										whatToBuild = new String();
									}
									else
										BoardStatus.currentLog("No connection available");							}
							}
							else if (whatToBuild.compareTo("settlement_INITIAL") == 0){
								plot[i].upgrade(1, UserView.getCurrentPlayer().getPlayerID(),
										UserView.getCurrentPlayer().getColor());
								UserView.getCurrentPlayer().addPlot(plot[i]);
								for (int t = 0; t < plot[i].getAmtTiles(); t++){
									//for every bordering tile of the new plot...
									if (plot[i].getTile(t).getTerrain().compareTo("ore") == 0)
										UserView.getCurrentPlayer().addOre(1);
									else if (plot[i].getTile(t).getTerrain().compareTo("wheat") == 0)
										UserView.getCurrentPlayer().addWheat(1);
									else if (plot[i].getTile(t).getTerrain().compareTo("wool") == 0)
										UserView.getCurrentPlayer().addWool(1);
									else if (plot[i].getTile(t).getTerrain().compareTo("lumber") == 0)
										UserView.getCurrentPlayer().addWood(1);
									else if (plot[i].getTile(t).getTerrain().compareTo("brick") == 0)
										UserView.getCurrentPlayer().addBrick(1);
									//give that resource to the player to start the game off with
								}
								UserView.getCurrentPlayer().addVP();
								whatToBuild = new String();
								UserView.endTurn.doClick();
							}
							repaint(); //refreshes the board
							break;
						}
						else
							JOptionPane.showMessageDialog(null, "Settlements cannot be built adjacent to eachother.", "Invalid Placement", JOptionPane.ERROR_MESSAGE);
					}
					//else
						//BoardStatus.currentLog("Select an empty plot.");
				}
			}
			else if (whatToBuild.compareTo("road") == 0 ||
						whatToBuild.compareTo("Road Building") == 0 ||
							whatToBuild.compareTo("road_INITIAL") == 0){
				for (int i = 0; i < road.length; i++){
					if (road[i].isInside(e.getX(), e.getY()) && road[i].getExists() == false){
						//checks to tsee if the cursor was clicked on an empty road
						for (int p = 0; p < plot.length; p++){
							//goes through all the plots
							for (int r = 0; r < road.length; r++){
								//goes through all roads
								if ((plot[p].getX() == road[i].getX(0) && plot[p].getY() == road[i].getY(0) && plot[p].getPlayer() == UserView.getCurrentPlayer().getPlayerID())
									|| (plot[p].getX() == road[i].getX(1) && plot[p].getY() == road[i].getY(1) && plot[p].getPlayer() == UserView.getCurrentPlayer().getPlayerID())
									//checks to see if the soon to be built road is connected to a plot
									|| (road[i].getX(0) == road[r].getX(0) && road[i].getY(0) == road[r].getY(0) && road[r].getPlayer() == UserView.getCurrentPlayer().getPlayerID())
									|| (road[i].getX(0) == road[r].getX(1) && road[i].getY(0) == road[r].getY(1) && road[r].getPlayer() == UserView.getCurrentPlayer().getPlayerID())
									|| (road[i].getX(1) == road[r].getX(0) && road[i].getY(1) == road[r].getY(0) && road[r].getPlayer() == UserView.getCurrentPlayer().getPlayerID())
									|| (road[i].getX(1) == road[r].getX(1) && road[i].getY(1) == road[r].getY(1) && road[r].getPlayer() == UserView.getCurrentPlayer().getPlayerID()))
									//also checks to see if the road to be built is connected to another road
								{
									if (plot[p].getPlayer() == UserView.getCurrentPlayer().getPlayerID() ||
											road[r].getPlayer() == UserView.getCurrentPlayer().getPlayerID()){
										//checks to see if the player building the road owns the connection
										road[i].buyRoad(true, UserView.getCurrentPlayer().getPlayerID(), UserView.getCurrentPlayer().getColor());
										if (whatToBuild.compareTo("road") == 0){
											UserView.getCurrentPlayer().deductResources(whatToBuild, "void", 0);
											BoardStatus.addLog("Player " + UserView.getCurrentPlayer().getPlayerID()
													+ " built a road.");
											whatToBuild = new String();
											}
										else if (whatToBuild.compareTo("Road Building") == 0){
											whatToBuild = new String("road");
											BoardStatus.addLog("...and again for your second road.");
										}
										else if (whatToBuild.compareTo("road_INITIAL") == 0){
											whatToBuild = new String();
											UserView.endTurn.doClick();		//ends turn after building a initial road
										}
										repaint(); //refreshes the board
									}
									else
										JOptionPane.showMessageDialog(null, "You do not own the road connection available.", "Invalid Placement", JOptionPane.ERROR_MESSAGE);
								}
								//else
									//JOptionPane.showMessageDialog(null, "There is no connection available here.", "Invalid Placement", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				}
			}
			else if (whatToBuild.compareTo("robber") == 0){
				for (int i = 0; i < tile.length; i++){
					if (tile[i].isInside(e.getX(), e.getY())){
						for (int j = 0; j < tile.length; j++){
							if (tile[j].checkRobber())
								tile[j].removeRobber();
						}	//removes the robber from whereever he currently is
						tile[i].placeRobber();	//places him in his new location
						JOptionPane.showMessageDialog(null, "He is now controlling Tile #" + (i+1) + " . This tile will produce no resources until he is moved!", "The robber has moved!", JOptionPane.PLAIN_MESSAGE);
						BoardStatus.addLog("The robber now controls tile #" + (i+1) + "!");
							//messages for the players
						repaint();	//repaints the board to show the new robber's location
						
						
						
						whatToBuild = new String();	//resets string value					}
					}
				}
			}
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			// INTENTIALLY EMPTY. NOTHING TO IMPLEMENT AT THIS TIME.			
		}
		@Override
		public void mouseExited(MouseEvent e) {
			// INTENTIALLY EMPTY. NOTHING TO IMPLEMENT AT THIS TIME.
		}
		@Override
		public void mousePressed(MouseEvent e) {
			// INTENTIALLY EMPTY. NOTHING TO IMPLEMENT AT THIS TIME.		
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// INTENTIALLY EMPTY. NOTHING TO IMPLEMENT AT THIS TIME.		
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			// INTENTIALLY EMPTY. NOTHING TO IMPLEMENT AT THIS TIME.
		}

	}
}