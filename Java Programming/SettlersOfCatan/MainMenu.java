//PAUL CAMERON SMITH
//COP 3252 - JAVA
//HOMEWORK X - SETTLERS OF CATAN
//CATAN MAIN MENU FRAME

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainMenu extends JFrame{
	
	
	private MainGame mainGame;
	private JPanel mainmenu;
	private BorderLayout layout;
	private JPanel mainSplash;
	private JPanel mainBottomBar;
	private JButton[] mainButtons;
	private int numPlayers;
	private ActionListener exitConfirmation;

		
	MainMenu(String s, MainGame mg){
		super(s);
		mainGame = mg;
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
				
		layout = new BorderLayout(5, 5);
		setLayout(layout);
		setSize(1150, 475);
		setVisible(true);
		mainSplash = new JPanel();
		mainSplash.setSize(900, 475);
		try {
			BufferedImage catanLogo = ImageIO.read(new URL("http://www.cameronsmith.org/cams_catan/catan-logo1.png"));
				//image hosted on personal web server
			mainSplash = new JPanel(){
				protected void paintComponent(Graphics g){
					super.paintComponent(g);
					g.drawImage(catanLogo, 0, 0, null);
				}
			};
			//mainSplash.setLayout(new BoxLayout(this, 0));
		}catch (IOException e1) {
			//Auto-generated catch block
			e1.printStackTrace();
		}
		
		numPlayers = 4;
		
		mainmenu = new JPanel();
		add(mainmenu);
		
		
		mainmenu.setLayout(new BorderLayout());
		mainmenu.add(mainSplash, BorderLayout.CENTER);
		mainBottomBar = new JPanel();	// button bar panel
		mainBottomBar.setLayout(new GridLayout(1, 5, 50, 5));	//with a grid layout
		mainmenu.add(mainBottomBar, BorderLayout.SOUTH);		//aligned to the south border side
		mainButtons = new JButton[5];	// main menu buttons
		for (int b = 0; b < mainButtons.length; b++){
			if (b == 0){
				mainButtons[b] = new JButton("Start Game");
				mainButtons[b].setToolTipText("Start a brand new game from the beginning.");
				mainButtons[b].addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						setVisible(false);
						mainGame.setupGame();
						mainGame.startGame();
					}
				});
			}
			else if (b == 1){
				mainButtons[b] = new JButton("Continue Game");
				mainButtons[b].setToolTipText("Pick up a game where you last left off");
				mainButtons[b].addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						//TODO
						JOptionPane.showMessageDialog(null, "Feature coming soon...");
					}
				});
				mainButtons[b].setEnabled(false);
			}
			else if (b == 2){
				mainButtons[b] = new JButton("Options");
				mainButtons[b].setToolTipText("Change settings for gameplay.");
				mainButtons[b].addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						//TODO OPTIONS
						JOptionPane.showMessageDialog(null, "Feature coming soon...");
					}
				});
				mainButtons[b].setEnabled(false);
			}
			else if (b == 3){
				mainButtons[b] = new JButton("Learn");
				mainButtons[b].setToolTipText("Click here for rules, explanations, and tactics.");
				mainButtons[b].addActionListener(new ActionListener(){
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
			}
			else if (b == 4){
				mainButtons[b] = new JButton("About");
				mainButtons[b].setToolTipText("Click here learn more about this project.");
				mainButtons[b].addActionListener(new ActionListener(){
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
			}
			mainBottomBar.add(mainButtons[b]);
		}
	}
	protected void startGame(){
		//super();
	}
}
