import java.awt.Color;

import javax.swing.JOptionPane;

//PAUL CAMERON SMITH
//COP3252 - JAVA 
//HOMEWORK X - SETTLERS OF CATAN
//PLAYER PLAYER SUPERCLASS

public class Player {
	
	private Board board;
	private int playerID;
	private Color color;
	private int victoryPoints;
	private int wood, wool, wheat, ore, brick;
		//resources
	private String[] hand;
		//devo cards, longest road, and largest army when applicable
	private int knightsUsed;
	private Plot[] plots = new Plot[0];
	
	Player(int id, Color c, Board b){
		this.board = b;
		this.playerID = id;
		this.color = c;
		this.victoryPoints = 0;
		this.wood = 0;
		this.wool = 0;
		this.wheat = 0;
		this.ore = 0;
		this.brick = 0;
		this.hand = new String[0];
		this.knightsUsed = 0;
	}
	public int getPlayerID(){
		return this.playerID;}
	public Color getColor(){
		return this.color;}
	public int getVP(){
		return this.victoryPoints;}
	public String[] getHand(){
		return this.hand;}
	public int amtWood(){
		return this.wood;}
	public int amtWool(){
		return this.wool;}
	public int amtWheat(){
		return this.wheat;}
	public int amtOre(){
		return this.ore;}
	public int amtBrick(){
		return this.brick;}
	public int amtResources(){
		return(this.amtOre() + this.amtWheat() + 
				this.amtWool() + this.amtWood() + this.amtBrick());
		//counts up the total amount of resources
	}
	
	//these will take in the plot values (1 for settlement, 2 for city)
	// and add it to the the current players resources
	public void addWood(int v){
		this.wood += v;}
	public void addWool(int v){
		this.wool += v;}
	public void addWheat(int v){
		this.wheat += v;}
	public void addOre(int v){
		this.ore += v;}
	public void addBrick(int v){
		this.brick += v;}
	public void addVP(){
		this.victoryPoints++;}
	public void addPlot(Plot p){
		Plot[] temp = new Plot[this.plots.length+1];
		temp[0] = p;
		for (int i = 0; i < this.plots.length; i++)
			temp[i+1] = this.plots[i];
		this.plots = new Plot[temp.length];
		for (int i = 0; i < temp.length; i++)
			this.plots[i] = temp[i];
	}
	public void addResources(int roll){
		for (int p = 0; p < plots.length; p++){
			//for every owned plot
			int amt = plots[p].getAmtTiles();	//amout of tiles per plot
			for (int t = 0; t < amt; t++){
				//for every tiles of each plot
				Tile temp = plots[p].getTile(t);
				//copies each tile to a temp to gather data from
				if (roll == temp.getRoll() && temp.checkRobber() == false){
					if (temp.getTerrain().compareTo("wood") == 0)
						this.addWood(plots[p].getValue());
					else if (temp.getTerrain().compareTo("wool") == 0)
						this.addWool(plots[p].getValue());
					else if (temp.getTerrain().compareTo("wheat") == 0)
						this.addWheat(plots[p].getValue());
					else if (temp.getTerrain().compareTo("ore") == 0)
						this.addOre(plots[p].getValue());
					else if (temp.getTerrain().compareTo("brick") == 0)
						this.addBrick(plots[p].getValue());
				}	//adds resources to the player accordingly
			}
		}
	}
	public void deductResources(String s, String r, int t){
		// TODO IMPLEMENT ROBBER STEALING
		if (s.compareTo("city") == 0){
			this.ore -= 3;
			this.wheat -= 2;
			this.victoryPoints++;
		}
		else if (s.compareTo("settlement") == 0){
			this.wood--;
			this.wheat--;
			this.wool--;
			this.brick--;
			this.victoryPoints++;
		}
		else if (s.compareTo("road") == 0){
			this.wood--;
			this.brick--;
		}
		else if (s.compareTo("devocard") == 0){
			this.ore--;
			this.wheat--;
			this.wool--;
		}
		else if (s.compareTo("Monopoly") == 0){
			if (r.compareTo("Ore") == 0)
				this.ore = 0;
			else if (r.compareTo("Wheat") == 0)
				this.wheat = 0;
			else if (r.compareTo("Wool") == 0)
				this.wool = 0;
			else if (r.compareTo("Lumber") == 0)
				this.wood = 0;
			else if (r.compareTo("Brick") == 0)
				this.brick = 0;
		}
		else if (s.compareTo("steal") == 0){
			if (r.compareTo("Ore") == 0 && this.ore >= 1)
				this.ore--;
			else if (r.compareTo("Wheat") == 0 && this.wheat >= 1)
				this.wheat--;
			else if (r.compareTo("Wool") == 0 && this.wool >= 1)
				this.wool--;
			else if (r.compareTo("Lumber") == 0 && this.wood >= 1)
				this.wood--;
			else if (r.compareTo("Brick") == 0 && this.brick >= 1)
				this.brick--;				
		}
		else if (s.compareTo("robber") == 0){
			if (r.compareTo("Ore") == 0)
				this.ore = t;
			else if (r.compareTo("Wheat") == 0)
				this.wheat = t;
			else if (r.compareTo("Wool") == 0)
				this.wool = t;
			else if (r.compareTo("Lumber") == 0)
				this.wood = t;
			else if (r.compareTo("Brick") == 0)
				this.brick = t;		
		}
		board.repaint();
		UserView.refresh();
		CatanSideBar.refresh(this);
	}
	public void tradeInResources(String p, String r){
		//TODO implement Harbors
		if (p.compareTo("Ore") == 0)
			if (this.ore >= 4)
				this.ore -= 4;
			else
				board.BoardStatus.addLog("Insufficient amount of " + p);
		else if (p.compareTo("Wheat") == 0)
			if (this.wheat >= 4)
				this.wheat -= 4;
			else
				board.BoardStatus.addLog("Insufficient amount of " + p);
		else if (p.compareTo("Wool") == 0)
			if (this.wool >= 4)
				this.wool -= 4;
			else
				board.BoardStatus.addLog("Insufficient amount of " + p);
		else if (p.compareTo("Lumber") == 0)
			if (this.wood >= 4)
				this.wood -= 4;
			else
				board.BoardStatus.addLog("Insufficient amount of " + p);		
		else if (p.compareTo("Brick") == 0)
			if (this.brick >= 4)
				this.brick -= 4;
			else
				board.BoardStatus.addLog("Insufficient amount of " + p);
		if (r.compareTo("Ore") == 0)
			this.ore++;
		else if (r.compareTo("Wheat") == 0)
			this.wheat++;
		else if (r.compareTo("Wool") == 0)
			this.wool++;
		else if (r.compareTo("Lumber") == 0)
			this.wood++;
		else if (r.compareTo("Brick") == 0)
			this.brick++;
	}
	public void initialBuild(String free){
		if(free.compareTo("settlement") == 0){
			board.selectStructure("settlement_INITIAL");
		}
		else if (free.compareTo("road") == 0){
			board.selectStructure("road_INITIAL");
		}
	}
	public void buyRoad(int w, int b){
		if(w >= 1 && b >= 1){
			board.BoardStatus.currentLog("Select the location for your new road.");
			board.selectStructure("road");
		}
		else
			board.BoardStatus.currentLog("Insufficient Resources");
	}
	public void buySettlement(int wh, int wool, int wood, int brick){
		if (wh >= 1 && wool >= 1 && wood >= 1 && brick >= 1){
			board.BoardStatus.currentLog("Select the location for your new settlement.");
			board.selectStructure("settlement");
		}
		else
			board.BoardStatus.currentLog("Insufficient Resources");
	}
	public void buyCity(int o, int w){
		if(o >= 3 && w >= 2){
			board.BoardStatus.currentLog("Select which settlement to upgrade");
			board.selectStructure("city");
		}
		else
			board.BoardStatus.currentLog("Insufficient resources.");
	}
	public void buyDevoCard(int o, int wh, int w){
		if (o >= 1 && wh >= 1 && w >= 1){	
			String[] temp = new String[this.hand.length+1];
			temp[0] = MainGame.drawDevoCard();
			for (int i = 0; i < this.hand.length; i++)
				temp[i+1] = this.hand[i];
			this.hand = new String[temp.length];
			for (int i = 0; i < temp.length; i++)
				this.hand[i] = temp[i];
			board.BoardStatus.currentLog("You drew a " + this.hand[0] + " card!");
			this.deductResources("devocard", "void", 0);
			CatanSideBar.refresh(this);
		}
		else
			board.BoardStatus.currentLog("Insufficient resources.");
	}
	public void useKnightCard(){
		int cI = 0;
		for (int i = 0; i < this.hand.length; i++){
			if (this.hand[i].compareTo("Knight") == 0){
				UserView.refresh();
				cI = i;
			}
		}	
		this.knightsUsed++;
		if (this.knightsUsed == 3)
			this.victoryPoints+= 2; //largest army
		refreshHand(cI);
		CatanSideBar.refresh(this);
	}
	public void useVictoryPointCard(){
		int cI = 0;
		for (int i = 0; i < this.hand.length; i++){
			if (this.hand[i].compareTo("Victory Point") == 0){
				this.victoryPoints++;
				UserView.refresh();
				cI = i;
			}
		}
		refreshHand(cI);
		CatanSideBar.refresh(this);
	}
	public void useYearOfPlenty(){
		int cI = 0;
		for (int i = 0; i <this.hand.length; i++){
			if (this.hand[i].compareTo("Year of Plenty") == 0){
				Object[] resources = {"Ore", "Wheat", "Wool", "Lumber", "Brick"};
				//prompt for first desired resource
				Object receive1 = JOptionPane.showInputDialog(null,"Year of Plenty", 
						"What's the first resource do you desire?", JOptionPane.INFORMATION_MESSAGE,
						null, resources, resources[0]);
				//add the first desired resource
				if (receive1.toString().compareTo("Ore") == 0)
					this.ore++;
				else if (receive1.toString().compareTo("Wheat") == 0)
					this.wheat++;
				else if (receive1.toString().compareTo("Wool") == 0)
					this.wool++;
				else if (receive1.toString().compareTo("Lumber") == 0)
					this.wood++;
				else if (receive1.toString().compareTo("Brick") == 0)
					this.brick++;
				UserView.refresh();
				//prompt for the second desired resource
				Object receive2 = JOptionPane.showInputDialog(null,"Year of Plenty", 
						"Now what's the second resource desired?", JOptionPane.INFORMATION_MESSAGE,
						null, resources, resources[0]);
				//add the second resource chosen
				if (receive1.toString().compareTo("Ore") == 0)
					this.ore++;
				else if (receive1.toString().compareTo("Wheat") == 0)
					this.wheat++;
				else if (receive1.toString().compareTo("Wool") == 0)
					this.wool++;
				else if (receive1.toString().compareTo("Lumber") == 0)
					this.wood++;
				else if (receive1.toString().compareTo("Brick") == 0)
					this.brick++;
				UserView.refresh();
			}
		}
		refreshHand(cI);
		CatanSideBar.refresh(this);
	}
	public void useMonopoly(Player[] players){
		int cI = 0;
		for (int i = 0; i <this.hand.length; i++){
			if (this.hand[i].compareTo("Monopoly") == 0){
				Object[] resources = {"Ore", "Wheat", "Wool", "Lumber", "Brick"};
				Object monopoly = JOptionPane.showInputDialog(null,"Monopoly", 
						"What resource do you wish to monopolize?", JOptionPane.INFORMATION_MESSAGE,
						null, resources, resources[0]);
				for (int j = 1; i <= players.length; i++){
					if (j != this.getPlayerID()){
						//for all other players
						if (monopoly.toString().compareTo("Ore") == 0)
							this.ore+= players[j].amtOre();
						else if (monopoly.toString().compareTo("Wheat") == 0)
							this.wheat+= players[j].amtWheat();
						else if (monopoly.toString().compareTo("Wool") == 0)
							this.wool+= players[j].amtWool();
						else if (monopoly.toString().compareTo("Lumber") == 0)
							this.wood+= players[j].amtWood();
						else if (monopoly.toString().compareTo("Brick") == 0)
							this.brick+= players[j].amtBrick();
						//add the desired resource amount from all other players to your own
						players[j].deductResources("Monopoly", monopoly.toString(), 0);
						//deduct the stolen resource from everybody
					}
				}
			}
		}
		refreshHand(cI);
		CatanSideBar.refresh(this);
	}
	public void useRoadBuilding(){
		int cI = 0;
		for (int i = 0; i <this.hand.length; i++){
			if (this.hand[i].compareTo("Road Building") == 0){
				board.BoardStatus.addLog("Select where to build your first road...");
				board.selectStructure("Road Building");	//guaranted resources for 1st free road
				//then calls board.selectStructure("road") inside to build the second for free
				this.brick++;
				this.wood++;
			}
		}
		refreshHand(cI);
	}
	private void refreshHand(int cardIndex){
		for (int i = cardIndex; i < this.hand.length; i++){	
			if (i >= cardIndex && i < this.hand.length-1)
				this.hand[i] = this.hand[i+1];
			else if ( i == this.hand.length-1)
				this.hand[i] = new String();
		}
		String[] temp = new String[this.hand.length-1];
		for(int i = 0; i < temp.length; i++)
			temp[i] = this.hand[i];
		this.hand = new String[temp.length];
		for(int i = 0; i < temp.length; i++)
			this.hand[i] = temp[i];
		UserView.refresh();
		CatanSideBar.refresh(this);
	}
}
