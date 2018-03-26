//Paul Cameron Smith
//COP3252 - Java
//Player.java
//Player base class for tic tac toe

import java.util.Random;

public class Player {

	private char symbol;
	private int ID;	
	protected int move;
	
	Player(int ID, char c){
		this.setSymbol(c);
		this.setID(ID);
	}
	/** allows for the change of symbol */
	protected void setSymbol(char c){
		this.symbol = c;
	}
	/**allows for the change in player ID */
	protected void setID(int i){
		this.ID = i;
	}
	/**allows for the change in move*/
	protected void setMove(int t){
		this.move = t;
	}
	/** returns the symbol the player is using */
	protected char getSymbol(){
		return this.symbol;
	}
	/** returns the players ID */
	protected int getID(){
		return this.ID;
	}
	/** returns the player's move*/
	protected int getMove(){
		return this.move;
	}
	/**A bunch of if statements checking for a win for the calling player obj*/
	protected int winnable(Board b){
		//checks if two tiles are the same (and not empty) and checks if
		//the third tile required to win is empty for a winning condition
		int condition = 0;
		if ((b.getTile(1) == b.getTile(2)) && (b.getTile(3) == ' ')
				&& (b.getTile(1) != ' ') && (b.getTile(1) == this.getSymbol()))
				condition =3;
		else if ((b.getTile(1) == b.getTile(3)) && (b.getTile(2) == ' ')
				&& (b.getTile(1) != ' ') && (b.getTile(1) == this.getSymbol()))
			condition = 2;
		else if ((b.getTile(2) == b.getTile(3)) && (b.getTile(1) == ' ')
				&& (b.getTile(2) != ' ') && (b.getTile(2) == this.getSymbol()))
			condition = 1;
		else if ((b.getTile(4) == b.getTile(5)) && (b.getTile(6) == ' ')
				&& (b.getTile(4) != ' ') && (b.getTile(4) == this.getSymbol()))
			condition = 6;
		else if ((b.getTile(4) == b.getTile(6)) && (b.getTile(5) == ' ')
				&& (b.getTile(4) != ' ') && (b.getTile(5) == this.getSymbol()))
			condition = 5;
		else if ((b.getTile(5) == b.getTile(6)) && (b.getTile(4) == ' ')
				&& (b.getTile(5) != ' ') && (b.getTile(5) == this.getSymbol()))
			condition = 4;
		else if ((b.getTile(7) == b.getTile(8)) && (b.getTile(9) == ' ')
				&& (b.getTile(7) != ' ') && (b.getTile(7) == this.getSymbol()))
			condition = 9;
		else if ((b.getTile(7) == b.getTile(9)) && (b.getTile(8) == ' ')
				&& (b.getTile(7) != ' ') && (b.getTile(7) == this.getSymbol()))
			condition = 8;
		else if ((b.getTile(8) == b.getTile(9)) && (b.getTile(7) == ' ')
				&& (b.getTile(8) != ' ') && (b.getTile(8) == this.getSymbol()))
			condition = 7;
		else if ((b.getTile(1) == b.getTile(4)) && (b.getTile(7) == ' ')
				&& (b.getTile(1) != ' ') && (b.getTile(1) == this.getSymbol()))
			condition = 7;
		else if ((b.getTile(1) == b.getTile(7)) && (b.getTile(4) == ' ')
				&& (b.getTile(1) != ' ') && (b.getTile(1) == this.getSymbol()))
			condition = 4;
		else if ((b.getTile(4) == b.getTile(7)) && (b.getTile(1) == ' ')
				&& (b.getTile(4) != ' ') && (b.getTile(4) == this.getSymbol()))
			condition = 1;
		else if ((b.getTile(2) == b.getTile(5)) && (b.getTile(8) == ' ')
				&& (b.getTile(2) != ' ') && (b.getTile(2) == this.getSymbol()))
			condition = 8;
		else if ((b.getTile(2) == b.getTile(8)) && (b.getTile(5) == ' ')
				&& (b.getTile(2) != ' ') && (b.getTile(2) == this.getSymbol()))
			condition = 5;
		else if ((b.getTile(5) == b.getTile(8)) && (b.getTile(2) == ' ')
				&& (b.getTile(5) != ' ') && (b.getTile(5) == this.getSymbol()))
			condition = 2;
		else if ((b.getTile(3) == b.getTile(6)) && (b.getTile(9) == ' ')
				&& (b.getTile(3) != ' ') && (b.getTile(3) == this.getSymbol()))
			condition = 9;
		else if ((b.getTile(3) == b.getTile(9)) && (b.getTile(6) == ' ')
				&& (b.getTile(3) != ' ') && (b.getTile(3) == this.getSymbol()))
			condition = 6;
		else if ((b.getTile(6) == b.getTile(9)) && (b.getTile(3) == ' ')
				&& (b.getTile(6) != ' ') && (b.getTile(6) == this.getSymbol()))
			condition = 3;
		else if ((b.getTile(1) == b.getTile(5)) && (b.getTile(9) == ' ')
				&& (b.getTile(1) != ' ') && (b.getTile(1) == this.getSymbol()))
			condition = 9;
		else if ((b.getTile(1) == b.getTile(5)) && (b.getTile(5) == ' ')
				&& (b.getTile(1) != ' ') && (b.getTile(1) == this.getSymbol()))
			condition = 5;
		else if ((b.getTile(5) == b.getTile(9)) && (b.getTile(1) == ' ')
				&& (b.getTile(5) != ' ') && (b.getTile(5) == this.getSymbol()))
			condition = 1;
		else if ((b.getTile(3) == b.getTile(5)) && (b.getTile(7) == ' ')
				&& (b.getTile(3) != ' ') && (b.getTile(3) == this.getSymbol()))
			condition = 7;
		else if ((b.getTile(3) == b.getTile(7)) && (b.getTile(5) == ' ')
				&& (b.getTile(3) != ' ') && (b.getTile(3) == this.getSymbol()))
			condition = 5;
		else if ((b.getTile(5) == b.getTile(7)) && (b.getTile(3) == ' ')
				&& (b.getTile(5) != ' ') && (b.getTile(5) == this.getSymbol()))
			condition = 3;
		return condition;
	}
	/** generates where the player will go */
	protected void makeAmove(Board b, Player p){
		this.setMove(0);		
		if (this.winnable(b) != 0)
			this.setMove(this.winnable(b));
		else if (p.winnable(b) != 0)
			this.setMove(p.winnable(b));
		else if (b.getTile(5) == ' ')
			this.setMove(5);
		else{
			Random tile = new Random();
			do{
				this.move = (tile.nextInt(9) + 1);
				// int 0-8 inclusive and adding 1 makes it 1-9 incl
			}while ((b.getTile(this.getMove())=='O') || 
					(b.getTile(this.getMove())=='X'));
			// just checks to see if it's is a valid move
			// empty and within the bounds of 1-9
		}
		b.setTile(this.getMove(), this.getSymbol());
		System.out.println("Player "+this.ID+" (computer) chooses position "+this.getMove());
	}
	
}
