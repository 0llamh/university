//Paul Cameron Smith
//COP3252 - Java
//TicTacToe.java
// main program of TicTacToe game
public class TicTacToe {

	private static Board game;
	private static Player[] p;
	
	/** A bunch of if-else statements checking to see if the game is won*/
	public static boolean checkWinCondition(Player p, Board b){
		//checks for three tile tiles to see if they are equal AND not empty
		boolean condition = false;
		if ((b.getTile(1) == b.getTile(2)) && (b.getTile(1) == b.getTile(3))
				&& (b.getTile(1) != ' ') && (b.getTile(1) == p.getSymbol()))
			condition = true;
		else if ((b.getTile(1) == b.getTile(3)) && (b.getTile(1) == b.getTile(2))
				&& (b.getTile(1) != ' ') && (b.getTile(1) == p.getSymbol()))
			condition = true;
		else if ((b.getTile(2) == b.getTile(3)) && (b.getTile(2) == b.getTile(1))
				&& (b.getTile(2) != ' ') && (b.getTile(2) == p.getSymbol()))
			condition = true;
		else if ((b.getTile(4) == b.getTile(5)) && (b.getTile(4) == b.getTile(6))
				&& (b.getTile(4) != ' ') && (b.getTile(4) == p.getSymbol()))
			condition = true;
		else if ((b.getTile(4) == b.getTile(6)) && (b.getTile(4) == b.getTile(5))
				&& (b.getTile(4) != ' ') && (b.getTile(4) == p.getSymbol()))
			condition = true;
		else if ((b.getTile(5) == b.getTile(6)) && (b.getTile(5) == b.getTile(4))
				&& (b.getTile(5) != ' ') && (b.getTile(5) == p.getSymbol()))
			condition = true;
		else if ((b.getTile(7) == b.getTile(8)) && (b.getTile(7) == b.getTile(9))
				&& (b.getTile(7) != ' ') && (b.getTile(7) == p.getSymbol()))
			condition = true;
		else if ((b.getTile(7) == b.getTile(9)) && (b.getTile(7) == b.getTile(8))
				&& (b.getTile(7) != ' ') && (b.getTile(7) == p.getSymbol()))
			condition = true;
		else if ((b.getTile(8) == b.getTile(9)) && (b.getTile(8) == b.getTile(7))
				&& (b.getTile(8) != ' ') && (b.getTile(8) == p.getSymbol()))
			condition = true;
		else if ((b.getTile(1) == b.getTile(4)) && (b.getTile(1) == b.getTile(7))
				&& (b.getTile(1) != ' ') && (b.getTile(1) == p.getSymbol()))
			condition = true;
		else if ((b.getTile(1) == b.getTile(7)) && (b.getTile(1) == b.getTile(4))
				&& (b.getTile(1) != ' ') && (b.getTile(1) == p.getSymbol()))
			condition = true;
		else if ((b.getTile(4) == b.getTile(7)) && (b.getTile(4) == b.getTile(1))
				&& (b.getTile(4) != ' ') && (b.getTile(4) == p.getSymbol()))
			condition = true;
		else if ((b.getTile(2) == b.getTile(5)) && (b.getTile(2) == b.getTile(8))
				&& (b.getTile(2) != ' ') && (b.getTile(2) == p.getSymbol()))
			condition = true;
		else if ((b.getTile(2) == b.getTile(8)) && (b.getTile(2) == b.getTile(5))
				&& (b.getTile(2) != ' ') && (b.getTile(2) == p.getSymbol()))
			condition = true;
		else if ((b.getTile(5) == b.getTile(8)) && (b.getTile(5) == b.getTile(2))
				&& (b.getTile(5) != ' ') && (b.getTile(5) == p.getSymbol()))
			condition = true;
		else if ((b.getTile(3) == b.getTile(6)) && (b.getTile(3) == b.getTile(9))
				&& (b.getTile(3) != ' ') && (b.getTile(3) == p.getSymbol()))
			condition = true;
		else if ((b.getTile(3) == b.getTile(9)) && (b.getTile(3) == b.getTile(6))
				&& (b.getTile(3) != ' ') && (b.getTile(3) == p.getSymbol()))
			condition = true;
		else if ((b.getTile(6) == b.getTile(9)) && (b.getTile(6) == b.getTile(3))
				&& (b.getTile(6) != ' ') && (b.getTile(6) == p.getSymbol()))
			condition = true;
		else if ((b.getTile(1) == b.getTile(5)) && (b.getTile(1) == b.getTile(9))
				&& (b.getTile(1) != ' ') && (b.getTile(1) == p.getSymbol()))
			condition = true;
		else if ((b.getTile(1) == b.getTile(9)) && (b.getTile(1) == b.getTile(5))
				&& (b.getTile(1) != ' ') && (b.getTile(1) == p.getSymbol()))
			condition = true;
		else if ((b.getTile(5) == b.getTile(9)) && (b.getTile(5) == b.getTile(1))
				&& (b.getTile(5) != ' ') && (b.getTile(5) == p.getSymbol()))
			condition = true;
		else if ((b.getTile(3) == b.getTile(5)) && (b.getTile(3) == b.getTile(7))
				&& (b.getTile(3) != ' ') && (b.getTile(3) == p.getSymbol()))
			condition = true;
		else if ((b.getTile(3) == b.getTile(7)) && (b.getTile(3) == b.getTile(5))
				&& (b.getTile(3) != ' ') && (b.getTile(3) == p.getSymbol()))
			condition = true;
		else if ((b.getTile(5) == b.getTile(7)) && (b.getTile(5) == b.getTile(3))
				&& (b.getTile(5) != ' ') && (b.getTile(5) == p.getSymbol()))
			condition = true;
		return condition;
	}	
	
	public static void main(String[] args){
		p = new Player[2];
		if (args.length == 0){
			//no command line arguements
			p[0] = new Human(1, 'X');	//Player 1 (Human) is always 'X'
			p[1] = new Human(2, 'O');	//Player 2 (Human) is always 'O'
		}
		else if ((args.length == 1) && (args[0].compareTo("-c") == 0)){
			//one command arguments with computer flag
			p[0] = new Player(1, 'X');	//Player 1 (Computer) is 'X'
			p[1] = new Player(2, 'O');	//Player 2 (Computer) is 'O'
		}
		else if ((args.length == 2) && (args[0].compareTo("-c") == 0) && (args[1].compareTo("1") == 0)){
			p[0] = new Player(1, 'X');	//Player 1 (Computer) is 'X'
			p[1] = new Human(2, 'O');	//Player 2 (Human) is 'O'
		}
		else if ((args.length == 2) && (args[0].compareTo("-c") == 0) && (args[1].compareTo("2") == 0)){
			p[0] = new Human(1, 'X');	//Player 1 (Player) is 'X'
			p[1] = new Player(2, 'O');	//Player 2 (Computer) is 'O'
		}
		//ADD OPTIONS HERE FOR ADVANCED SETTING
		else if ((args.length == 3) && (args[0].compareTo("-c") == 0) &&
				(args[1].compareTo("1") == 0) && (args[2].compareTo("-a") == 0)){
			p[0] = new Advanced(1, 'X');	// advanced computer player 1
			p[1] = new Human(2, 'O');		// human player 2
		}
		else if ((args.length == 3) && (args[0].compareTo("-c") == 0) && 
				(args[1].compareTo("2") == 0) && (args[2].compareTo("-a") == 0)){
			p[0] = new Human(1, 'X');		// human player 1
			p[1] = new Advanced(2, 'O');	// advanced human player 2
		}
		else if ((args.length == 1) && (args[0].compareTo("-help") == 0)){
			TicTacToe.help();
			System.exit(0);
		}
		else{
			//for invalid command line usage
			/*for ( int i = 0; i < args.length; i++)
				System.out.println(args[i]);*/
			System.out.println("Usage: \tjava TicTacToe [-c [1|2] [-a]]");
			System.out.println(" ");
			System.out.println("Enter: \tjava TicTacToe -help");
			System.out.println("for detailed options");
			System.exit(1);
		}
		
		game = new Board();
		
		/*do*/
		for ( int turn = 0; turn < 9; turn++){
			game.drawBoard(); 		//draws the board
			p[0].makeAmove(game, p[1]); 	//player 1 goes first
					//taking in the board and his opponent as parameters
					//to check for winning conditions if they are default computers
			if (TicTacToe.checkWinCondition(p[0], game)){
				System.out.println("Player "+p[0].getID()+ " has won!");
				game.drawBoard();
				break;	//ends the loop
				//checks to see if player 1 won
			}
			if (turn == 4){
				// ends in a draw
				System.out.println("GAME OVER! (Draw)");
				game.drawBoard();
				break;
			}
			else{
				game.drawBoard();
				p[1].makeAmove(game, p[0]); 	//player 2 goes next
					//taking in the board and his opponent as parameters
				if (TicTacToe.checkWinCondition(p[1], game)){
					System.out.println("Player "+p[1].getID()+ " has won!");
					game.drawBoard();
					break;	//ends the loop if player
				}
			}
		}/*while ((TicTacToe.checkWinCondition(p[0], game) == false) && 
				(TicTacToe.checkWinCondition(p[1], game) == false));*/
		
		System.out.println("Goodbye!");
		
		
	}
	
	/** prints out a full list of command line arguments for play */
	public static void help(){
		System.out.println("Welcome to TicTacToe!!");
		System.out.println(" ");
		System.out.println("Here are the following options for play:");
		System.out.println("\tjava TicTacToe -c 	\t\t// two computer players");
		System.out.println("\tjava TicTacToe -c 1	\t\t// computer is player 1, human player 2");
		System.out.println("\tjava TicTacToe -c 2	\t\t// human is player 1, comuputer player 2");
		System.out.println("\tjava TicTacToe -a		\t\t// two advaned computer players");
		System.out.println("\tjava TicTacToe -c 1 -a\t\t// advanced computer is player 1, human player 2");
		System.out.println("\tjava TicTacToe -a 2 -a\t\t// human is player 1, advanced computer player 2");
	}
	
}
