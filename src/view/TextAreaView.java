/*
 * Author: Yang Hong
 * Class: TextAreaView.java
 * Description: This class extends JPanel and implements Observer. It is a text view
 * 				option for the game. It has two main components: button controlling panel
 * 				and JTextArea view window. It receive messages regarding the game whenever
 * 				the game state has changed and it immediately update the graphic window.
 */

package view;

import java.awt.Color;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.ButtonController;
import model.Game;

public class TextAreaView extends JPanel implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1586156566880363114L;
	private JTextArea gameStateArea;
	//private JTextArea roomInfoArea;
	private Game game;
	private ButtonController buttonPanel;
	
	public TextAreaView(Game currGame) {
		this.game = currGame;
		this.setPreferredSize(null);
		//this.setLayout(new FlowLayout());
		//this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		buttonPanel = new ButtonController();
		this.add(buttonPanel);
		setUpLayout();
	}

	private void setUpLayout() {
		gameStateArea = new JTextArea();
		//gameStateArea.setSize(500, 400);
		//gameStateArea.setLocation(250, 0);
		gameStateArea.setBackground(Color.WHITE);
		gameStateArea.setEditable(false);
		gameStateArea.setFont(new Font("Arial", Font.BOLD, 14));
		gameStateArea.setText(game.getGameMap().toString());
		this.add(gameStateArea);
		
//		roomInfoArea = new JTextArea();
//		roomInfoArea.setSize(500, 100);
//		roomInfoArea.setLocation(250, 400);
//		roomInfoArea.setBackground(Color.BLACK);
//		roomInfoArea.setEditable(false);
//		roomInfoArea.setFont(new Font("Courier", Font.BOLD, 18));
//		roomInfoArea.setForeground(Color.WHITE);
//		//roomInfoArea.setText(game.getGameMap().roomInfo());
//		this.add(roomInfoArea);
		
		/**
		 * THis is for when loading data, it should check if trainer has left steps or not
		 * */
		if (game.getTrainer().getSteps() == 0){
			buttonPanel.resetButtons(false);
		}
	}
	
	private void updateGameStatusArea() {
		gameStateArea.setText(game.getGameMap().toString());
		//roomInfoArea.setText(game.getGameMap().roomInfo());
	}

	@Override
	public void update(Observable o, Object arg) {
		updateGameStatusArea();
		buttonPanel.resetButtons(true);
//		if (game.getTrainer().getSteps() == 0){
//			JOptionPane.showMessageDialog(null, "Game Over\nYou are exhausted");
//			buttonPanel.resetButtons(false);
//		}
	}
	
	

}
