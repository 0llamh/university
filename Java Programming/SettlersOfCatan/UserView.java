//PAUL CAMERON SMITH
//COP3252 - JAVA
//HOMEWORK X -  SETTLERS OF CATAN
//USER VIEW PANEL CLASS

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class UserView extends JPanel {
	
	private static Player player;
	private Board board;
	private static JLabel header;
	private static int vp;
	private String[] names = {"Ore", "Wheat", "Wool", "Lumber", "Brick"};
	private static JLabel[] resources;
	public static JButton endTurn;
	public static boolean takingTurn;
	private static JLabel[] total;
	private static int[] each_total;
	private GridLayout grid;
	
	UserView(Board b, Player p){
		super();
		this.setSize(1000, 200);
		this.setBorder(BorderFactory.createTitledBorder("Player " + p.getPlayerID()));
		this.setVisible(true);
		grid = new GridLayout(2, 6, 17, 7);	//3x7 grid gaps of 5 & 10
		this.setLayout(grid);
		vp = 0;
		this.board = b;
		this.player = p;
		header = new JLabel("Victory Points: " + vp , JLabel.CENTER);
		header.setBorder(BorderFactory.createEtchedBorder());
		resources = new JLabel[5];
		endTurn = new JButton("End Turn");
		endTurn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				/*int option = JOptionPane.showConfirmDialog(null,
						"End your turn?"," ", JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION){*/
					takingTurn = false;	//ends the turn
					board.selectStructure(" ");	//resets what to build so you cant carry over resources
				//}
			}
		});
		endTurn.setMnemonic('E');
		total = new JLabel[5];
		each_total = new int[5];
		for (int i = 0; i < resources.length; i++){
			resources[i] = new JLabel(names[i], JLabel.CENTER);
			resources[i].setBorder(BorderFactory.createEtchedBorder());
			each_total[i] = 0;
			total[i] = new JLabel("x" + each_total[i], JLabel.CENTER);
		}
		
		add(header);
		for (int i = 0; i < resources.length; i++)
			add(resources[i]);
		add(endTurn);
		for (int i = 0; i < total.length; i++)
			add(total[i]);
		
	}
	
	public void takeTurn(){
		takingTurn = true;
		while(takingTurn == true){
			endTurn.getAction();
		}
	}
	public static Player getCurrentPlayer(){
		return player;}
	public void nextPlayer(Player p, int roll){
		/*if (board.initialPhase == true)
			endTurn.setEnabled(false);*/
		this.setBorder(BorderFactory.createTitledBorder("Player " + p.getPlayerID()));
		this.player = p;
		refresh();
	}
	public static void refresh(){
		updateResources(player);
		updateVP();
	}
	public static void updateVP(){
		vp = player.getVP();
		header.setText("Victory Points: " + vp);}
	public static void updateResources(Player p){
		each_total[0] = p.amtOre();
		each_total[1] = p.amtWheat();
		each_total[2] = p.amtWool();
		each_total[3] = p.amtWood();
		each_total[4] = p.amtBrick();
		for (int i = 0; i < 5; i ++)
			total[i].setText("x" + each_total[i]);}
}