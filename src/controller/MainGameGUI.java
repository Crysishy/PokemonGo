package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
//import java.time.LocalDate;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import model.Game;
import model.Map;
import view.GraphicView;
import view.TextAreaView;

public class MainGameGUI extends JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2910611530965073582L;
	private JPanel gameViewPanel;
	private GraphicView graphicView;
	private TextAreaView textAreaView;
	private Game game;
	private String choice ;
	private int saveMa;
	// private Map gameMap;

	public static void main(String[] args) {
		MainGameGUI gameGUI = new MainGameGUI();
		gameGUI.setVisible(true);
	}

	public MainGameGUI() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setTitle("Catch the Pokemon :)");

		initializeGame();
		setupMenus();
		setUpPanels();
		this.addWindowListener(new SaveDataListener());
	}

	private void initializeGame() {

		if (!loadData()){
			mapPreparing();
		}
		else{
			
			game.setMapStyle(choice);
			game.prepareImages();
		}

		// gameMap = game.getGameMap();
	}
	
	private void mapPreparing(){
		String[] strings = {"Forest", "Desert", "Snow"};
		choice = (String) JOptionPane.showInputDialog(null, "Please choose from following map styles:",
				"Map Style", JOptionPane.PLAIN_MESSAGE, null, strings, strings[0]);
		System.out.println(choice);
		game = Game.getInstance();
		game.setMapStyle(choice);
		game.prepareImages();
			
	}

	private void setupMenus() {
		JMenuItem option = new JMenu("Options");
		JMenuItem newGame = new JMenuItem("New Game");
		option.add(newGame);
		JMenuItem exitGame = new JMenuItem("Quit");
		option.add(exitGame);
		JMenuItem forceQuit = new JMenuItem("Force Quit");
		option.add(forceQuit);

		JMenuItem view = new JMenu("Views");
		JMenuItem textView = new JMenuItem("Text Area View");
		view.add(textView);
		JMenuItem graphicView = new JMenuItem("Graphic View");
		view.add(graphicView);

		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		menuBar.add(option);
		menuBar.add(view);

		MenuItemListener menuListener = new MenuItemListener();
		newGame.addActionListener(menuListener);
		exitGame.addActionListener(menuListener);
		forceQuit.addActionListener(menuListener);
		textView.addActionListener(menuListener);
		graphicView.addActionListener(menuListener);
	}

	private void setUpPanels() {
		textAreaView = new TextAreaView(game);
		game.addObserver(textAreaView);
		graphicView = new GraphicView(game);
		game.addObserver(graphicView);

		setViewTo(graphicView);
		
			
	}

	private class MenuItemListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String text = ((JMenuItem) e.getSource()).getText();

			if (text.equals("New Game")){
				mapPreparing();
				game.startNewGame();
			}

			if (text.equals("Quit")){
				int ans = JOptionPane.showConfirmDialog(null, "Save data?", "Save", JOptionPane.YES_NO_OPTION);
				
				if (ans == JOptionPane.YES_OPTION) {
					saveData();
				}
				System.exit(0);
			}
			
			if (text.equals("Force Quit"))
				System.exit(1);

			if (text.equals("Text Area View"))
				setViewTo(textAreaView);

			if (text.equals("Graphic View"))
				setViewTo(graphicView);
		}
	}

	private void setViewTo(JPanel newView) {
		if (gameViewPanel != null)
			remove(gameViewPanel);
		gameViewPanel = newView;
		add(gameViewPanel);
		gameViewPanel.repaint();
		validate();
	}

	public void saveData() {
		try {
			FileOutputStream bytesToDisk = new FileOutputStream("gameData");
			ObjectOutputStream outFile = new ObjectOutputStream(bytesToDisk);
			outFile.writeObject(game);
			outFile.writeObject(choice);
			outFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean loadData() {
		saveMa = JOptionPane.showConfirmDialog(null, "Start with previous saved data?\nNo means all new objects",
				"Load", JOptionPane.YES_NO_CANCEL_OPTION);
		if (saveMa == JOptionPane.NO_OPTION)
			return false;
		try {
			FileInputStream rawBytes = new FileInputStream("gameData");
			ObjectInputStream inFile = new ObjectInputStream(rawBytes);

			game = (Game) inFile.readObject();
			Game.onlyGame = game;
			choice = (String) inFile.readObject();
			game.setMapStyle(choice);
			game.prepareImages();
			
			inFile.close();
		} catch (Exception e) {
			System.out.println("No loaded data!");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/*
	 * The implementation of a WindowListener pop up a window to ask if the user
	 * want to save the data or not
	 */
	private class SaveDataListener implements WindowListener {

		@Override
		public void windowOpened(WindowEvent e) {
		}

		@Override
		public void windowClosing(WindowEvent e) {
			int ans = JOptionPane.showConfirmDialog(null, "Save data?", "Save", JOptionPane.YES_NO_OPTION);
			if (ans == JOptionPane.YES_OPTION) {
				saveData();
			}
		}

		@Override
		public void windowClosed(WindowEvent e) {
		}

		@Override
		public void windowIconified(WindowEvent e) {
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
		}

		@Override
		public void windowActivated(WindowEvent e) {
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
		}

	}
}
