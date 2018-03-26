//PAUL CAMERON SMITH
//COP 3252 - JAVA
//HOMEWORK X - SETTLERS OF CATAN
//CATAN MAIN GAME FRAME

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainGame extends JFrame{
	private JMenu file;
	private JMenuItem learn;
	private JMenuItem about;
	private JMenuItem loadGame;
	private JMenuItem saveGame;
	private JMenuItem exit;
	private JMenuBar bar;
	
	private BorderLayout layout;
	private Random rand = new Random();
	private UserView userview;
	private CatanSideBar sidebar;
	private Board board;
	private RobberFrame robberframe;
	private int numPlayers;
	private Player[] players;
	private Color[] playerColors;
	private static final String[] devoDeck = new String[25];
	private ActionListener exitConfirmation;
	
	MainGame(int numPlayers){
		super("Settlers of Catan");
		layout = new BorderLayout(5, 5);
		setLayout(layout);
		setVisible(false);
		setSize(1150, 800);		
		this.numPlayers = numPlayers;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		exitConfirmation = new ActionListener(){
			//terminates the program
			public void actionPerformed (ActionEvent event){
				//implements confirmation dialogue
				int option = JOptionPane.showConfirmDialog(null,
				"Are you sure? All unsaved progress will be lost.",
				"Exit current game?", JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION)
						System.exit(0);
			}
		};
		
		//JMENU FRAME IMPLEMENTATION!!!
		//file menu item
		file = new JMenu("File");	//file menu tab
		file.setMnemonic('F');	//adds Alt-F hotkey
		
		bar = new JMenuBar();	//creates the menu bar
		setJMenuBar(bar);	//adds the menu bar to the program
		
		//how to play
		learn = new JMenuItem("How to play");
		learn.setMnemonic('H');	//Alt-H hotkey
		file.add(learn);	//adds file menu item
		learn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//we can just open up the default browser to display game rules rather than copying them
				if(Desktop.isDesktopSupported())
				{	//if the program is running in an environment with a desktop
					try {
						Desktop.getDesktop().browse(new URI("http://www.catan.com/service/game-rules"));
						//opens default browser connecting to official game rules
					}
					catch (IOException e1) {
						// catch input/output exception
						e1.printStackTrace();
					} 
					catch (URISyntaxException e1) {
						// catches invalid URI exception
						e1.printStackTrace();
					}
				}
			}
		});
		//bar.add(learn); 	//adds howtoplay to bar
		
		// about sub-menu for file
		about = new JMenuItem("Read Me!");	// links to the about page
		about.setMnemonic('R');	// adds Alt-A hotkey
		file.add(about);	//adds about to file menu
		about.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//TODO WRITE ABOUT ME PAGE ABOUT 
				//opens up default browser to personally hosted webpage
				if(Desktop.isDesktopSupported())
				{	//if the program is running in an environment with a desktop
					try {
						Desktop.getDesktop().browse(new URI("http://www.cameronsmith.org/cams_catan/readme.html"));
						//opens default browser connecting to readme page
					}
					catch (IOException e1) {
						// catch input/output exception
						e1.printStackTrace();
					} 
					catch (URISyntaxException e1) {
						// catches invalid URI exception
						e1.printStackTrace();
					}
				}
			}
		});
		//bar.add(about); //adds about to bar
		
		//load game
		loadGame = new JMenuItem("Load Game");
		loadGame.setMnemonic('L');
		file.add(loadGame);
		loadGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//TODO
				JOptionPane.showMessageDialog(null, "Feature coming soon...");
			}
		});
		//bar.add(loadGame);	//adds loadgame to bar
		
		//save game
		saveGame = new JMenuItem("Save Game");
		saveGame.setMnemonic('S');
		file.add(saveGame);
		saveGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//TODO
				JOptionPane.showMessageDialog(null, "Feature coming soon...");
			}
		});
		//bar.add(saveGame);	//adds savegame to bar
		
		//exit game
		exit = new JMenuItem("Exit");	//exit button
		exit.setMnemonic('X');//Alt-X hotkey
		file.add(exit);	//adds exit to file menu
		exit.addActionListener(exitConfirmation);
		//bar.add(exit);	//adds exit menu to bar
	
		bar.add(file);	//adds file menu to menu bar
	}
	public void setupGame(){		
		
		board = new Board();
		add(board, BorderLayout.CENTER);

		players = new Player[numPlayers];
		for (int i = 0; i < players.length; i++){
			if (i == 0)
				players[i] = new Player(i+1, Color.RED, board);
			else if (i == 1)
				players[i] = new Player(i+1, Color.BLUE, board);
			else if (i == 2)
				players[i] = new Player(i+1, Color.ORANGE, board);
			else if (i == 3)
				players[i] = new Player(i+1, Color.PINK, board);}
	
		userview = new UserView(board, players[0]);
		add(userview, BorderLayout.SOUTH);
		sidebar = new CatanSideBar(this, board, players, players[0]);
		add(sidebar, BorderLayout.EAST);
		
		for (int i = 0; i < devoDeck.length; i++){
			if (i < 14)
				devoDeck[i] = new String("Knight");
			else if (i >= 14 && i < 19)
				devoDeck[i] = new String("Victory Point");
			else if (i >= 19 && i < 21)
				devoDeck[i] = new String("Year of Plenty");
			else if (i >= 21 && i < 23)
				devoDeck[i] = new String("Monopoly");
			else if (i >= 23)
				devoDeck[i] = new String("Road Building");
		}	//creates the 25 devocards
		for(int i = 0; i < devoDeck.length; i++){
			int r = rand.nextInt(devoDeck.length);	// random index in the array
			String temp = devoDeck[i];
			devoDeck[i] = devoDeck[r];
			devoDeck[r] = temp;	// simple array swapping
		}	// shuffles the devo deck
		
	}
	public void startGame(){
		setVisible(true);

		boolean gameWon = false;
		int winner = 0;
		board.initialPhase = true;
		for (int turn = 0; turn < players.length; turn++){
			//first pre-game build
			userview.nextPlayer(players[turn], 0);
			board.BoardStatus.addLog("Player " + players[turn].getPlayerID() + ", place your first settlement.");
			players[turn].initialBuild("settlement");
			userview.takeTurn();
			CatanSideBar.refresh(players[turn]);
			board.BoardStatus.addLog("Player " + players[turn].getPlayerID() + ", place your first road.");
			players[turn].initialBuild("road");
			userview.takeTurn();
			CatanSideBar.refresh(players[turn]);
		}
		for (int turn = (players.length-1); turn >=0; turn--){
			//second pre-game build
			userview.nextPlayer(players[turn], 0);
			board.BoardStatus.addLog("Player " + players[turn].getPlayerID() + ", place your second settlement.");
			players[turn].initialBuild("settlement");
			userview.takeTurn();
			CatanSideBar.refresh(players[turn]);
			board.BoardStatus.addLog("Player " + players[turn].getPlayerID() + ", place your second road.");
			players[turn].initialBuild("road");
			userview.takeTurn();
			CatanSideBar.refresh(players[turn]);
		}
		board.initialPhase = false;	//ends the inital phase for the board
		do{
			for (int i = 0; i < players.length; i++){
				int roll = 0;
				roll = (rand.nextInt(6)+ 1) + (rand.nextInt(6) + 1);
				userview.nextPlayer(players[i], roll);
				board.BoardStatus.addLog("Player " + this.players[i].getPlayerID() + " rolled a " + roll);
				if (roll != 7)
					for (int j = 0; j < players.length; j++)
						players[j].addResources(roll);
				else{
					JOptionPane.showMessageDialog(null,"The robber is on the loose!", "Uh no!", JOptionPane.WARNING_MESSAGE);
					robber(players[i]);	//sends the current player to move the robber
				}
				UserView.refresh();
				CatanSideBar.refresh(players[i]);
				userview.takeTurn();
				if (players[i].getVP() >= 10){
					winner = players[i].getPlayerID();
					gameWon = true;}
			}
		}while(gameWon == false);
		board.BoardStatus.addLog("Player " + winner + " has won. Thank you for playing!");		
	}
	public static String drawDevoCard(){
		String card = new String(devoDeck[0]);
		String temp[] = new String[devoDeck.length-1];
		for (int i = 0; i < temp.length; i++)
			temp[i] = devoDeck[i+1];
		for (int i = 0; i < temp.length; i++)
			devoDeck[i] = temp[i];
		return card;
	}
	public  void robber(Player p){
		//TODO ROBBER STEALING MECHANIC
		for (int i = 0; i < players.length; i++){
			if (players[i].amtResources() > 7)
				robberframe = new RobberFrame(players[i]);
		}
		JOptionPane.showMessageDialog(null, "Because you rolled a 7, you get to move the robber to any tile of your choice.\n"
				+ "Negating any resource gained from it.\n",
//TODO			+ "You also get to steal a resource from a player bordering that tile",
				"Place the Robber", JOptionPane.PLAIN_MESSAGE);
		board.selectStructure("robber");
	}

	protected void processWindowEvent(WindowEvent e){
		// detects when the window frame is closing, and 
		if (e.getID() == WindowEvent.WINDOW_CLOSING){
			int exit = JOptionPane.showConfirmDialog(null,
					"Are you sure? All unsaved progress will be lost.");
			if (exit == JOptionPane.YES_OPTION)
				System.exit(0);
		}
		else
			super.processWindowEvent(e);
	}
}
