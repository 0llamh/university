//PAUL CAMERON SMITH
//COP3252 - JAVA
//HOMEWORK X - SETTLERS OF CATAN
//SIDE PANEL FOR FRAME

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class CatanSideBar extends JPanel{
	private MainGame mainGame;
	private Board board;
	private Player[] total_players;
	private static Player current_player;
//	private int ore, wheat, wool, wood, brick;
	private static JButton[] buy;
	private String[] names = {"Development Card", "City", "Settlement", "Road", "Trade in resources"};
	private static JButton[] use;
	private String[] cards = {"Knight", "Victory Point", "Year of Plenty", "Monopoly", "Road Building"};
	private static LeaderBoard lb;
	private static BuyButtons bb;
	private static UseButtons ub;
	private GridBagLayout layout;
	private GridBagConstraints c;
	
	CatanSideBar(MainGame mg, Board b, Player[] players, Player p){
		super();
		mainGame = mg;
		this.board = b;
		this.layout = new GridBagLayout();
		this.c = new GridBagConstraints();
		this.setLayout(layout);
		this.setSize(200, 800);
		this.setVisible(true);
		total_players = new Player[players.length];
		this.current_player = p;
/*		this.ore = current_player.amtOre();
		this.wheat = current_player.amtWheat();
		this.wool = current_player.amtWool();
		this.wood = current_player.amtWood();
		this.brick = current_player.amtBrick();*/
		for (int i = 0; i < total_players.length; i++)
			total_players[i] = players[i];
		buy = new JButton[names.length];
		for (int i = 0; i < buy.length; i++){
			buy[i] = new JButton(names[i]);
			if (i == 0){
				buy[0].setToolTipText("Requires 1x Ore, 1x Wheat, and 1x Wool");
				buy[0].addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						current_player.buyDevoCard(current_player.amtOre(),
								current_player.amtWheat(), current_player.amtWool());
					}
				});
			}
			else if (i == 1){
				buy[1].setToolTipText("Requires 3x Ore and 2x Wheat");
				buy[1].addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						current_player.buyCity(current_player.amtOre(), current_player.amtWheat());
					}
				});
			}
			else if (i == 2){
				buy[2].setToolTipText("Requires 1x Wheat, 1x Wool, 1x Lumber, and 1x Brick");
				buy[2].addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						current_player.buySettlement(current_player.amtWheat(), current_player.amtWool(),
								current_player.amtWood(), current_player.amtBrick());
					}
				});
			}
			else if (i == 3){
				buy[3].setToolTipText("Requires 1x Lumber and 1x Brick");
				buy[3].addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						current_player.buyRoad(current_player.amtWood(), current_player.amtBrick());
					}
				});
			}
			else if (i == 4){
				buy[4].setToolTipText("Trade in resources for one of your choosing.");
				buy[4].addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						Object[] resources = {"Ore", "Wheat", "Wool", "Lumber", "Brick"};
						Object tradeIn = JOptionPane.showInputDialog(null,"Trade in?", 
								"What resource do you wish to trade in?", JOptionPane.INFORMATION_MESSAGE,
								null, resources, resources[0]);
						Object receive = JOptionPane.showInputDialog(null,"Receive?", 
								"What resource do you wish to receive?", JOptionPane.INFORMATION_MESSAGE,
								null, resources, resources[0]);
						if (tradeIn.toString().compareTo(receive.toString()) != 0)
							current_player.tradeInResources(tradeIn.toString(), receive.toString());
						else
							JOptionPane.showMessageDialog(null, "Select a different resource to receive.");
						UserView.refresh();
					}
				});
			}
		}
		use = new JButton[cards.length];
		for (int i = 0; i < cards.length; i++){
			use[i] = new JButton(cards[i]);
			if (i == 0){
				use[i].setToolTipText("Voluntarily move the robber to negate any tile of your choice during your turn."
						+ " Any player (including you) with more than 7 resources will have to discard half rounding down."
						+ " He/She can choose which to discard. Click to consume.");
				use[i].addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						mainGame.robber(current_player);
						current_player.useKnightCard();
					}
				});
			}
			else if (i == 1){
				use[i].setToolTipText("Congratulations! You have earned a free Victory Point. Click to consume.");
				use[i].addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						current_player.useVictoryPointCard();
					}
				});
			}
			else if (i == 2){
				use[i].setToolTipText("You are rewarded 2 free resources of your choosing! Click to consume.");
				use[i].addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						current_player.useYearOfPlenty();
					}
				});
			}
			else if (i == 3){
				use[i].setToolTipText("You can gain a monopoly on any resource of your choosing. "
						+ "All other players are required to give this resource to you. Click to Consume.");
				use[i].addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						current_player.useMonopoly(total_players);
					}
				});
			}
			else if (i == 4){
				use[i].setToolTipText("You can build two roads for free. Click to consume.");
				use[i].addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						current_player.useRoadBuilding();
					}
				});
			}
		}
		lb = new LeaderBoard(players);
		lb.setAlignmentY(TOP_ALIGNMENT);
		this.c.gridwidth = 0;
		this.c.gridheight = 2;
		this.c.weighty = 2;
		this.add(lb, c);
		this.c.gridheight = 4;
		this.c.weighty = 1;
		this.c.fill = c.HORIZONTAL;
		ub = new UseButtons(this.current_player, use);
		ub.setAlignmentY(BOTTOM_ALIGNMENT);
		this.add(ub, c);
		bb = new BuyButtons(this.current_player, buy);
		bb.setAlignmentY(BOTTOM_ALIGNMENT);
		this.add(bb, c);		
	}
	
	public static void refresh(Player p){
		//runs whenever a turn starts
		current_player = p;	//changes current player
			//updates resources
		lb.refresh();	//refreshes the leaderboard
		bb.refresh(current_player);	//refreshes their available buy buttons
		ub.refresh(current_player);	//refreshes their available use buttons
	}
	
	private class LeaderBoard extends JPanel{
		
		private Player[] players;
		private int[] resources;
		private JLabel[] stats;
		private JPanel[] playerFrame;
		
		LeaderBoard(Player[] p){
			this.setLayout(new GridLayout(4, 1, 20, 10));
			this.players = new Player[p.length];
			this.resources = new int[p.length];
			this.stats = new JLabel[p.length*2];
			this.playerFrame = new JPanel[this.players.length];
			for (int i = 0; i < this.players.length; i++){
				this.players[i] = p[i];
				this.resources[i] = this.players[i].amtOre();
				this.resources[i] += this.players[i].amtWheat();
				this.resources[i] += this.players[i].amtWool();
				this.resources[i] += this.players[i].amtWood();
				this.resources[i] += this.players[i].amtBrick();
				this.playerFrame[i] = new JPanel();
				this.playerFrame[i].setLayout(new GridLayout(2, 1, 5, 5));
				this.playerFrame[i].setBorder(BorderFactory.createTitledBorder(null,
						"Player " + this.players[i].getPlayerID(), TitledBorder.RIGHT,
						TitledBorder.TOP, getFont(), this.players[i].getColor()));
						//.createTitledBorder(null,
						//"Player " + this.players[i].getPlayerID(), TitledBorder.RIGHT, TitledBorder.TOP, this.players[i].getColor()));
				
			}
			for (int i = 0; i < this.stats.length; i++){
				if (i%2 == 0)
					stats[i] = new JLabel("Victory Points:        " + this.players[i/2].getVP());
				else
					stats[i] = new JLabel("Total Resources:   " + this.resources[i/2]);
			}
			for (int i = 0; i < this.stats.length; i++){
				if (i == 0 || i == 1)
					playerFrame[0].add(stats[i]);
				else if (i == 2 || i == 3)
					playerFrame[1].add(stats[i]);
				else if (i == 4 || i == 5)
					playerFrame[2].add(stats[i]);
				else if (i == 6 || i == 7)
					playerFrame[3].add(stats[i]);
				add(this.add(playerFrame[i/2]));
			}	
			refresh();
		}
		public void refresh(){
			for (int i = 0; i < this.players.length; i++){
				this.resources[i] = this.players[i].amtOre();
				this.resources[i] += this.players[i].amtWheat();
				this.resources[i] += this.players[i].amtWool();
				this.resources[i] += this.players[i].amtWood();
				this.resources[i] += this.players[i].amtBrick();
			}	//refreshes the amount of resources for each player
			for (int i = 0; i < this.stats.length; i++){
				if (i%2 == 0)
					stats[i].setText("Victory Points:        " + this.players[i/2].getVP());
				else
					stats[i].setText("Total Resources:   " + this.resources[i/2]);
			}	//refreshes the values/labels for each player
		}
	}
	
	private class BuyButtons extends JPanel{
		
		private Player player;
		private JButton[] buttons;
		private GridLayout layout;
		
		BuyButtons(Player p, JButton[] b){
			this.player = p;
			this.buttons = new JButton[b.length];
			for (int i = 0; i < b.length; i++)
				this.buttons[i] = b[i];
			this.layout = new GridLayout(5, 1, 5, 5);
			this.setLayout(layout);
			this.setBorder(BorderFactory.createTitledBorder(null, "Buy...",
					TitledBorder.LEFT, TitledBorder.TOP));
			refresh(this.player);
		}
		public void refresh(Player p){
			for (int i = 0; i < buttons.length; i++){
				this.add(buttons[i]);	//adds the button component
				if (i == 0){
					if (p.amtOre() >= 1 && p.amtWheat() >= 1 && p.amtWool() >= 1)
						buttons[i].setEnabled(true);
					else
						buttons[i].setEnabled(false);
				}
				else if (i == 1){
					if (p.amtOre() >= 3 && p.amtWheat() >= 2)
						buttons[i].setEnabled(true);
					else
						buttons[i].setEnabled(false);
				}
				else if (i == 2){
					if (p.amtWheat() >= 1 && p.amtWool() >= 1 && p.amtWood() >= 1 && p.amtBrick() >= 1)
						buttons[i].setEnabled(true);
					else
						buttons[i].setEnabled(false);
				}
				else if (i == 3){
					if (p.amtWood() >= 1 && p.amtBrick() >= 1)
						buttons[i].setEnabled(true);
					else
						buttons[i].setEnabled(false);
				}
			}	//and only enables it if the current user has the sufficient resources
		}
	}
	
	private class UseButtons extends JPanel{
		
		private Player player;
		private JButton[] buttons;
		private JLabel emptyHand;
		private GridLayout layout;
		
		UseButtons(Player p, JButton[] b){
			this.player = p;
			this.buttons = new JButton[b.length];
			for (int i = 0; i < b.length; i++)
				this.buttons[i] = b[i];
			this.emptyHand = new JLabel("Your hand is empty.", JLabel.CENTER);
			this.layout = new GridLayout(0, 1, 5, 5);
			this.setLayout(layout);
			this.setBorder(BorderFactory.createTitledBorder(null, "Use...",
					TitledBorder.LEFT, TitledBorder.TOP));
			refresh(this.player);
		}
		public void refresh(Player p){
			String[] temp = new String[p.getHand().length];
			for (int i = 0; i < temp.length; i++)	//deep copy hand
				temp[i] = p.getHand()[i];	//this looks weird...
			add(emptyHand);
			for (int i = 0; i < buttons.length; i++)
				remove(buttons[i]);	//removes all buttons at the start of refresh
			for (int i = 0; i < p.getHand().length; i++){
			//and adds it to the panel if they have the applicable card in their hand
				if (temp[i].compareTo("Knight") == 0){
					add(buttons[0]);
					remove(emptyHand);}
				else if (temp[i].compareTo("Victory Point") == 0){
					add(buttons[1]);
					remove(emptyHand);}
				else if (temp[i].compareTo("Year of Plenty") == 0){
					add(buttons[2]);
					remove(emptyHand);}
				else if (temp[i].compareTo("Monopoly") == 0){
					add(buttons[3]);
					remove(emptyHand);}
				else if (temp[i].compareTo("Road Building") == 0){
					add(buttons[4]);
					remove(emptyHand);}
				//add(buttons[i]);
			}
			
		}
	}
}
