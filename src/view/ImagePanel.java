/*
 * Author: Yang Hong
 * Class: ImagePanel.java
 * Description: This class extends JPanel. It is the core component of the class GraphicView.
 * 				It overrides paintComponent(Graphic g) method therefore is able to draw
 * 				images upon the JPanel. It receives Game message from GraphicView class which
 * 				implements Observer, thus the game info is synchronized with the Game class.
 */

package view;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.FacingTo;
import model.Game;
import model.Map;
import model.Trainer;
import model.Grid;

public class ImagePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5022980405496098704L;
	public static final int PIXEL_SIZE = 20;
	private Game game;
	private Map gameMap;
	private Point position;
	private Grid grid;

	public ImagePanel(Game currentGame) {
		this.game = currentGame;
		this.gameMap = game.getGameMap();
		this.setSize(950, 950);
		// this.setLayout(new BorderLayout());

		this.setLocation(250, 0);
		// this.setBackground(Color.BLACK);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (int row_height = 0; row_height < 38; row_height++) {
			for (int col_width = 0; col_width < 38; col_width++) {

				position = new Point(row_height, col_width);
				grid = gameMap.getGrid(position);
				
				g2.drawImage(grid.getLandImage(), col_width * PIXEL_SIZE, row_height * PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE, this);
				
				if (grid.hasGrass())
					g2.drawImage(grid.getGrassImage(), col_width * PIXEL_SIZE, row_height * PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE, this);
				
				g2.drawImage(grid.getElementImage(), col_width * PIXEL_SIZE, row_height * PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE, this);
				
			}
		}
		
		// draw the trainer in the map after all map elements have been drawn
		int x = game.getTrainer().getPosition().y;
		int y = game.getTrainer().getPosition().x;
		grid = gameMap.getGrid(game.getTrainer().getPosition());
		if (Trainer.moving) {
			ArrayList<BufferedImage> movingImages = grid.getTrainerMovingImages();
			if (Trainer.tic >= movingImages.size()) {
				// We're done with the battle scene
				g2.drawImage(grid.getTrainerStandingImage(), x * PIXEL_SIZE + Trainer.X_SHIFT,
						y * PIXEL_SIZE + Trainer.Y_SHIFT, PIXEL_SIZE + Trainer.STRETCH_HORIZONTAL,
						PIXEL_SIZE + Trainer.STRETCH_VERTICAL, null);
				Trainer.moving = false;
				Trainer.tic = 0;
			} else {
				
				if (game.getTrainer().getDirection().equals(FacingTo.RIGHT))
					g2.drawImage(movingImages.get(Trainer.tic),
							x * PIXEL_SIZE + Trainer.X_SHIFT - (4 - Trainer.tic) * 4,
							y * PIXEL_SIZE + Trainer.Y_SHIFT,
							PIXEL_SIZE + Trainer.STRETCH_HORIZONTAL, PIXEL_SIZE + Trainer.STRETCH_VERTICAL,
							null);
				if (game.getTrainer().getDirection().equals(FacingTo.LEFT))
					g2.drawImage(movingImages.get(Trainer.tic),
							x * PIXEL_SIZE + Trainer.X_SHIFT + (4 - Trainer.tic) * 4,
							y * PIXEL_SIZE + Trainer.Y_SHIFT,
							PIXEL_SIZE + Trainer.STRETCH_HORIZONTAL, PIXEL_SIZE + Trainer.STRETCH_VERTICAL,
							null);
				if (game.getTrainer().getDirection().equals(FacingTo.UP))
					g2.drawImage(movingImages.get(Trainer.tic),
							x * PIXEL_SIZE + Trainer.X_SHIFT,
							y * PIXEL_SIZE + Trainer.Y_SHIFT + (4 - Trainer.tic) * 4,
							PIXEL_SIZE + Trainer.STRETCH_HORIZONTAL, PIXEL_SIZE + Trainer.STRETCH_VERTICAL,
							null);
				if (game.getTrainer().getDirection().equals(FacingTo.DOWN))
					g2.drawImage(movingImages.get(Trainer.tic),
							x * PIXEL_SIZE + Trainer.X_SHIFT,
							y * PIXEL_SIZE + Trainer.Y_SHIFT  - (4 - Trainer.tic) * 4,
							PIXEL_SIZE + Trainer.STRETCH_HORIZONTAL, PIXEL_SIZE + Trainer.STRETCH_VERTICAL,
							null);
			}
		} else {
			g2.drawImage(grid.getTrainerStandingImage(), x * PIXEL_SIZE + Trainer.X_SHIFT,
					y * PIXEL_SIZE + Trainer.Y_SHIFT, PIXEL_SIZE + Trainer.STRETCH_HORIZONTAL,
					PIXEL_SIZE + Trainer.STRETCH_VERTICAL, null);
		}
	}
}