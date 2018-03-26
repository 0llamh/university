// Paul Cameron Smith
// COP3252 - Java
// Advanced. java
// Advanced Child class of Player

import java.util.Random;

public class Advanced extends Player {

	Advanced(int ID, char c){
		super(ID, c);
	}
	protected int winnable(Board b){
		return super.winnable(b);
	}
	protected void makeAmove(Board b, Player p){
		int[] corner_tiles = {1, 3, 7, 9};
		//overpowered corner tiles win games
		Random tile = new Random();
		super.setMove(0);		
		if (super.winnable(b) != 0)
			super.setMove(super.winnable(b));
		else if (p.winnable(b) != 0)
			super.setMove(p.winnable(b));
		else if (b.getTile(5) == ' ')
			super.setMove(5);
		else{		
			if ((b.getTile(1) == ' ') || (b.getTile(3) == ' ') ||
					(b.getTile(7) == ' ') || (b.getTile(9) == ' ')){
				do{
					super.setMove(corner_tiles[tile.nextInt(4)]);
					// int 0-8 inclusive and adding 1 makes it 1-9 incl
				}while ((b.getTile(super.getMove())=='O') || 
					(b.getTile(super.getMove())=='X'));
				// just checks to see if it's is a valid move
				// empty and within the bounds of 1-9
			}
			else{
				do{
					super.setMove((tile.nextInt(9) + 1));
					// int 0-8 inclusive and adding 1 makes it 1-9 incl
				}while ((b.getTile(super.getMove())=='O') || 
						(b.getTile(super.getMove())=='X'));
			}
		b.setTile(super.getMove(), super.getSymbol());
		System.out.println("Player "+super.getID()+" (computer) chooses position "+super.getMove());
		}
	}
}
