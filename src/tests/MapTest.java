package tests;



import java.awt.Point;

import org.junit.Test;

import model.Game;
import model.Grid;
import model.Map;
import model.Trainer;

public class MapTest {

	@Test
	public void test(){
		Game game = Game.getInstance();
		Point point = new Point(1,1);
		Map map = game.getGameMap();
		Trainer trainer = game.getTrainer();
		
		map.updateTrainer(point);
		map.getTrainerLocation();
		
		Grid grid = map.getGrid(point);
		
		trainer.moveDown();
		grid.getTrainerMovingImages();
		grid.getTrainerStandingImage();
		
		trainer.moveUp();
		grid.getTrainerMovingImages();
		grid.getTrainerStandingImage();
		
		trainer.moveLeft();
		grid.getTrainerMovingImages();
		grid.getTrainerStandingImage();
		
		trainer.moveRight();
		grid.getTrainerMovingImages();
		grid.getTrainerStandingImage();
		
		
		grid.getElementImage();
		grid.getGrassImage();
		
		grid = map.getGrid(new Point(20,20));
		grid.getElementImage();
		
		grid = map.getGrid(new Point(30,30));
		grid.getElementImage();
		
		
		grid.getLandImage();
		
		System.out.println(map.toString());
	}
}
