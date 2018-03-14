package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.Bait;
import model.Game;
import model.Pokeball;
import model.Pokemon;
import model.Rock;
import model.Trainer;
import songplayer.EndOfSongEvent;
import songplayer.EndOfSongListener;
import songplayer.SongPlayer;

public class BattlePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3495796408473520150L;
	private Game game;
	private Pokemon battlingPokemon;
	private BufferedImage background;
	private BufferedImage pokemonImage;
	private BufferedImage STrainer;

	public BattlePanel(Game currGame) {
		this.game = currGame;
		battlingPokemon = game.getBattlingPokemon();
		this.setSize(950, 950);
		this.setLocation(250, 0);
		background = GraphicView.background;
		GraphicView.getCurrentPokemonImage();
		pokemonImage = GraphicView.pokemonImage;
		STrainer = GraphicView.STrainer;
	}


	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.cyan);

		BufferedImage standing = STrainer.getSubimage(180, 180, 48, 52);

		g2.drawImage(background, 0, 0, 840, 500, null);
		// Position: x=550, y=170

		
		if (!game.getBattlingPokemon().isCaptured() || Pokeball.animating)
			g2.drawImage(pokemonImage, 600, 220, 100, 100, null);
		else if(game.getBattlingPokemon().isCaptured() && !Pokeball.animating){
			g2.drawImage(Pokeball.balls.get(0), 600, 250, 50, 50, null);
		}

		g2.drawImage(standing, 0, 300, 200, 200, null);

		if (Pokeball.animating) {
			if (Pokeball.tic >= Pokeball.balls.size()) {
				// We're done with the battle scene
				Pokeball.animating = false;
				Pokeball.tic = 0;
				game.getTrainer().getBall().resetPosition();
			} else {
				g2.drawImage(Pokeball.balls.get(Pokeball.tic), Pokeball.X, Pokeball.Y, 50, 50, null);
				game.getTrainer().getBall().nextPosition();
			}
		}
		if (Rock.animating) {
			if (Rock.tic >= Rock.rocks.size()) {
				// We're done with the battle scene
				Rock.animating = false;
				Rock.tic = 0;
				game.getTrainer().getRock().resetPosition();
			} else {
				g2.drawImage(Rock.rocks.get(Rock.tic), Rock.X, Rock.Y, 50, 50, null);
				game.getTrainer().getRock().nextPosition();
			}
		}
		if (Bait.animating) {
			if (Bait.tic >= Bait.baits.size()) {
				// We're done with the battle scene
				Bait.animating = false;
				Bait.tic = 0;
				game.getTrainer().getBait().resetPosition();
			} else {
				g2.drawImage(Bait.baits.get(Bait.tic), Bait.X, Bait.Y, 50, 50, null);
				game.getTrainer().getBait().nextPosition();
			}
		}
	}
	
	
	
	
}
