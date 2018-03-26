//PAUL CAMERON SMITH
//COP 3252 - JAVA
//HOMEWORK X - SETTLERS OF CATAN
//CATAN MAIN CLASS

import javax.swing.*;
import java.awt.SplashScreen;

public class Catan {
	public static void main(String[] args){		
		MainGame catanGame = new MainGame(4);
		//MainMenu catanMenu = new MainMenu("Settlers of Catan", catanGame);	
			//just doesn't want to seem to work with MainGame
		catanGame.setupGame();	//sets up the frame, panels, and data
		catanGame.startGame();
		catanGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		catanGame.setVisible(true);
	}
}
