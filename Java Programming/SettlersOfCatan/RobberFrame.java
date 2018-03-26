//PAUL CAMERON SMITH
// COP3252 - JAVA
// HOMEWORK X - SETTLERS OF CATAN
// ROBBER FRAME CLASS

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class RobberFrame extends JFrame {
	
	private Player player;
	private int playerResources;	//total amount of resources before losing half
	private String[] resources = {"Ore", "Wheat", "Wool", "Lumber", "Brick"};
	private JPanel headerPanel;
	private JLabel[] headers;
	private JPanel robberPanel;
	private JButton[] resourceButtons;
	private JLabel[] lostResources;
	private int[] resourceValues;
	private int selectedResources;	//total amount of resources saved
	private JButton confirm;
	
	RobberFrame(Player p){
		super("Player " + p.getPlayerID() + ", The Robber is loose!");
		setSize(340, 400);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
			//prevents user from just closing the window
		player = p;		//player who it affects
		playerResources = player.amtResources();	
				//amount of resources that playe has
		setLayout(new BorderLayout());
				//border layout for two Panels
		headerPanel = new JPanel();
		headerPanel.setLayout(new GridLayout(4, 1));
		headers = new JLabel[3];
		for (int i = 0; i < headers.length; i++){
			if (i == 0)
				headers[i] = new JLabel("The robber has stolen your excess resources.",
						JLabel.CENTER);
			else if (i == 1)
				headers[i] = new JLabel("However, you are able to salvage half of your cache.",
						JLabel.CENTER);
			else if (i == 2)
				headers[i] = new JLabel("What are you willing to discard? Choose wisely...",
						JLabel.CENTER);
			headerPanel.add(headers[i]);
		}
		add(headerPanel, BorderLayout.NORTH);
		
		robberPanel = new JPanel();
		robberPanel.setLayout( new GridLayout(6, 2, 40, 20));		
		resourceButtons = new JButton[resources.length];
		for (int i = 0; i < resources.length; i++){
			resourceButtons[i] = new JButton(resources[i]);
			if (i == 0){ //ore
				resourceButtons[i].addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						decrementOre();
						refresh();
					}
				});
			}
			else if (i == 1){ //wheat
				resourceButtons[i].addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						decrementWheat();
						refresh();
					}
				});
			}
			else if (i == 2){ //wool
				resourceButtons[i].addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						decrementWool();
						refresh();
					}
				});
			}
			else if (i == 3){ //lumber
				resourceButtons[i].addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						decrementLumber();
						refresh();
					}
				});
			}
			else if (i == 4){ //brick
				resourceButtons[i].addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						decrementBrick();
						refresh();
					}
				});
			}
		}
		
		lostResources = new JLabel[resources.length];
		for (int i = 0; i < resources.length; i++){
			lostResources[i] = new JLabel(" ", JLabel.CENTER);
		}	//initializes labels
		resourceValues = new int[resources.length];
		for (int i = 0; i < resources.length; i++){
			if (i == 0){
				resourceValues[i] = player.amtOre();
			}
			else if (i == 1){
				resourceValues[i] = player.amtWheat();
			}
			else if (i == 2){
				resourceValues[i] = player.amtWool();
			}
			else if (i == 3){
				resourceValues[i] = player.amtWood();
			}
			else if (i == 4){
				resourceValues[i] = player.amtBrick();
			}
		}	//initializes values for labels
		
		selectedResources = 0;
		confirm = new JButton("Confirm");
		confirm.setToolTipText("Select half what resources to keep n order to continue");
		confirm.setEnabled(false);	
		confirm.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent t){
				for (int i = 0; i < resources.length; i++){
					if (i == 0){
						player.deductResources("robber", "Ore", resourceValues[i]);
					}
					else if (i == 1){
						player.deductResources("robber", "Wheat", resourceValues[i]);
					}
					else if (i == 2){
						player.deductResources("robber", "Wool", resourceValues[i]);
					}
					else if (i == 3){
						player.deductResources("robber", "Lumber", resourceValues[i]);
					}
					else if (i == 4){
						player.deductResources("robber", "Brick", resourceValues[i]);
					}
				}
				setVisible(false);	//hides the frame
				dispose();	//closes the frame
			}
		});
		//initializes confirm button
		
		robberPanel.add(new JLabel("DISCARD:", JLabel.CENTER));
		robberPanel.add(confirm);
		
		for (int i = 0; i < resources.length*2; i++){
			if (i%2 == 0)
				robberPanel.add(resourceButtons[i/2]);
			else if ( i%2 == 1)
				robberPanel.add(lostResources[i/2]);
		}
		
		refresh();	//updates the labels with new values and checks confirm button
		add(robberPanel, BorderLayout.CENTER);
				//adds the main panel to the frame
		setVisible(true);
	}

	private void decrementOre(){
		if (resourceValues[0] > 0)
			resourceValues[0]--;
	}
	private void decrementWheat(){
		if (resourceValues[1] > 0)
			resourceValues[1]--;
	}
	private void decrementWool(){
		if (resourceValues[2] > 0)
			resourceValues[2]--;
	}
	private void decrementLumber(){
		if (resourceValues[3] > 0)
			resourceValues[3]--;
	}
	private void decrementBrick(){
		if (resourceValues[4] > 0)
			resourceValues[4]--;
	}
	
	private void refresh(){
		//refreshes labels with new values
		selectedResources = 0;
		for (int i = 0; i < resources.length; i++){
			if (i == 0){
				if (player.amtOre() <= 0)
					resourceButtons[i].setEnabled(false);
			}
			else if (i == 1){
				if (player.amtWheat() <= 0)
					resourceButtons[i].setEnabled(false);
			}
			else if (i == 2){
				if (player.amtWool() <= 0)
					resourceButtons[i].setEnabled(false);
			}
			else if (i == 3){
				if (player.amtWood() <= 0)
					resourceButtons[i].setEnabled(false);
			}
			else if (i == 4){
				if (player.amtBrick() <= 0)
					resourceButtons[i].setEnabled(false);
			}
			
			
			lostResources[i].setText(String.valueOf(resourceValues[i]));
			selectedResources += resourceValues[i];
		}
		if (selectedResources <= ((playerResources+1)/2)){
			confirm.setEnabled(true);
			for (int i = 0; i < resources.length; i++)
				resourceButtons[i].setEnabled(false);
		}
	}
	
}
