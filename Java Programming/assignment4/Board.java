//Paul Cameron Smith
//COP3252 - Java
//Board.java
//Board class for Tic Tac toe

public class Board {

	private char[][] tile;
	
	Board(){
		tile = new char[3][3];
		for (int i = 1; i < 10; i++)
			setTile(i, ' ');
	}
	
	/** Print the game board is it currently is */
	public void drawBoard(){
		
		System.out.printf("\n%11s %12s %s","Game Board:", " ", "Positions:\n");
		System.out.println(" ");
		System.out.printf(" "+getTile(1)+" | "+getTile(2)+" | "+getTile(3)+
				"%15s 1 | 2 | 3 \n", " ");
		System.out.printf("----------- %12s -----------\n", " ");
		System.out.printf(" "+getTile(4)+" | "+getTile(5)+" | "+getTile(6)+
				"%15s 4 | 5 | 6 \n", " ");
		System.out.printf("----------- %12s -----------\n", " "); 
		System.out.printf(" "+getTile(7)+" | "+getTile(8)+" | "+getTile(9)+
				"%15s 7 | 8 | 9 \n", " ");
		System.out.println(" ");

	}
	/** Wipes the board clean for a new game after */
	public void clearBoard(){
		for (int i = 1; i < 10; i++)
			setTile(i, ' ');
	}
	/** Takes in a tile (1-9)  and a char (X or O) and stores it in the tile array*/
	public void setTile(int t, char c){
		
		switch (t) {
			case 1: this.tile[0][0] = c;
				break;
			case 2: this.tile[0][1] = c;
				break;
			case 3: this.tile[0][2] = c;
				break;
			case 4: this.tile[1][0] = c;
				break;
			case 5: this.tile[1][1] = c;
				break;
			case 6: this.tile[1][2] = c;
				break;
			case 7: this.tile[2][0] = c;
				break;
			case 8: this.tile[2][1] = c;
				break;
			case 9: this.tile[2][2] = c;
				break;
		}
	}
	/** returns a tile's contents given its address */
	public char getTile(int address){
		char r = ' ';
		switch (address) {
			case 1: r = this.tile[0][0];
				break;
			case 2: r = this.tile[0][1];
				break;
			case 3: r = this.tile[0][2];
				break;
			case 4: r = this.tile[1][0];
				break;
			case 5: r = this.tile[1][1];
				break;
			case 6: r = this.tile[1][2];
				break;
			case 7: r = this.tile[2][0];
				break;
			case 8: r = this.tile[2][1];
				break;
			case 9: r = this.tile[2][2];
				break;
		}
		return r;
	}
}
