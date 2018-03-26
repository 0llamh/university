// Paul Cameron Smith
// COP3252 - Java
// Human.java
// Human child class of Player

import java.util.Scanner;

public class Human extends Player {

	private Scanner input;
	
	Human(int ID, char c){
		super(ID, c);
		input = new Scanner(System.in);
	}
	protected void makeAmove(Board b, Player p){
		this.setMove(0);
		do{
			System.out.print("Player "+this.getID()+", please enter a move (1-9): ");
			this.setMove(input.nextInt());
		}while (((this.getMove() >= 1) && (this.getMove() <= 9)) && 
				((b.getTile(this.getMove())=='O') ||
				 (b.getTile(this.getMove())=='X')));
		// just checking for a valid move (empty and 1-9)
		b.setTile(this.getMove(), this.getSymbol());
		input.reset();
	}
}
