package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.ButtonController;
import model.Bait;
import model.Game;
import model.Pokeball;
import model.Rock;
import songplayer.EndOfSongEvent;
import songplayer.EndOfSongListener;
import songplayer.SongPlayer;

public class GraphicView extends JPanel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5215210438417095973L;
	private Game game;
	private ButtonController buttonPanel;
	private ImagePanel imagePanel;
	private BattlePanel battlePanel;

	public static BufferedImage background;
	public static BufferedImage pokemonImage;
	public static BufferedImage STrainer;
	private SongPlayer battleMusic;
	private SongPlayer mapMusic;

	public GraphicView(Game currentGame) {
		this.game = currentGame;
		this.setPreferredSize(null);
		this.setLayout(null);

		buttonPanel = new ButtonController();
		this.add(buttonPanel);

		setUpLayout();
		loadImages();

		battleMusic = new SongPlayer(new SongWaiter(), "SoundSet/battle.mp3");
		mapMusic = new SongPlayer(new SongWaiter(), "SoundSet/Map.mp3"); 
		mapMusic.play();
	}

	private void loadImages() {
		try {
			if (game.getMapStyle().equals("Forest"))
				background = ImageIO.read(new File("ImageSet/battle_background.png"));
			else if (game.getMapStyle().equals("Desert"))
				background = ImageIO.read(new File("ImageSet/d_battle_background.png"));
			else if (game.getMapStyle().equals("Snow"))
				background = ImageIO.read(new File("ImageSet/s_battle_background.png"));

			pokemonImage = ImageIO.read(new File(game.getBattlingPokemon().getFileName()));
			STrainer = ImageIO.read(new File("ImageSet/trainer.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void setUpLayout() {
		imagePanel = new ImagePanel(game);
		this.add(imagePanel);
		imagePanel.repaint();
		this.validate();
	}

	private void updateImagePanel() {
		if (imagePanel != null)
			this.remove(imagePanel);
		else
			this.remove(battlePanel);
		if (game.isEncounteredPokemon()) {
			buttonPanel.resetMoveButtons(false);
			buttonPanel.setupActListeners();
			buttonPanel.setInfo("\n  In Battle" + "\n    Captured: " + game.getBattlingPokemon().isCaptured()
					+ "\n    % of Capture: " + game.getBattlingPokemon().getChanceToBeCaptured() + "\n    % of Run: "
					+ game.getBattlingPokemon().getChanceToRun() + "\n    Rocks: " + game.getTrainer().getRock().size()
					+ "\n    Baits: " + game.getTrainer().getBait().size() + "\n    Pokeballs: "
					+ game.getTrainer().getBall().size());
			battlePanel = new BattlePanel(game);
			if (mapMusic.isPlaying())
				mapMusic.stopPlaying();
			if (!battleMusic.isPlaying())
				battleMusic.play();
			this.add(battlePanel);
			battlePanel.repaint();
		} else {
			buttonPanel.resetActionButtons(false);
			buttonPanel.setupMoveListeners();
			buttonPanel.setInfo("\n  General Info" + "\n    Steps: " + game.getTrainer().getSteps() + "\n    Rocks: "
					+ game.getTrainer().getRock().size() + "\n    Baits: " + game.getTrainer().getBait().size() + "\n    Pokeballs: "
					+ game.getTrainer().getBall().size());
			if (battleMusic.isPlaying())
				battleMusic.stopPlaying();
			imagePanel = new ImagePanel(game);
			if (!mapMusic.isPlaying())
				mapMusic.play();
			this.add(imagePanel);
			imagePanel.repaint(); 
		}
		this.validate();
	}

	@Override
	public void update(Observable o, Object arg) {
		buttonPanel.resetButtons(true);
		updateImagePanel();
		if (game.isOver()) {
			buttonPanel.resetButtons(false);
			if (!game.alreadyOver) {
				if (game.isWon())
					JOptionPane.showMessageDialog(null,
							"Congratulations!\nYou have captured all pokemons!\nWell done!");
				else if (game.isExhausted())
					JOptionPane.showMessageDialog(null, "Game over!\nYou are exhausted...");
				else if (game.isOutOfPokeball())
					JOptionPane.showMessageDialog(null, "Game over!\nYou have ran out of pokeball...");

				buttonPanel.resetButtons(false);
				game.alreadyOver = true;
				return;
			}
		}
	}

	public static void getCurrentPokemonImage() {
		try {
			pokemonImage = ImageIO.read(new File(Game.getInstance().getBattlingPokemon().getFileName()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static class SongWaiter implements EndOfSongListener {

		public void songFinishedPlaying(EndOfSongEvent eosEvent) {
			System.out.println("Finished " + eosEvent.fileName() + ", " + eosEvent.finishedDate() + ", "
					+ eosEvent.finishedTime());
		}
	}

}