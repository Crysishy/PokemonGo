package tests;





import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import model.Game;


public class GameTests {
	
	@Test
	public void test(){
		Game game = Game.getInstance();
		game.prepareImages();
		game.startNewGame();
		Point point=game.getTrainer().getPosition();
		game.moveTrainer("moveDown");
		point.x = point.x+1;
		assertEquals(point,game.getTrainer().getPosition());
		game.moveTrainer("moveUp");
		point.x = point.x-1;
		assertEquals(point,game.getTrainer().getPosition());
		game.moveTrainer("moveLeft");
		point.y = point.y-1;
		assertEquals(point,game.getTrainer().getPosition());
		game.moveTrainer("moveRight");
		point.y = point.y+1;
		assertEquals(point,game.getTrainer().getPosition());
		game.getGameMap();
		
		game.moveTrainer("moveDown");
		game.moveTrainer("moveUp");
		game.moveTrainer("moveLeft");
		game.moveTrainer("moveRight");
		game.moveTrainer("moveDown");
		game.moveTrainer("moveUp");
		game.moveTrainer("moveLeft");
		game.moveTrainer("moveRight");
		
		game.actTrainer("runAway");
		game.actTrainer("useBait");
		game.actTrainer("useRock");
		game.actTrainer("usePokeball");
		game.actTrainer("~");
		
		game.isEncounteredPokemon();
		game.isOutOfPokeball();
		game.isOver();
		game.isWon();
		game.isExhausted();
		
		game.randomMove();game.randomMove();game.randomMove();game.randomMove();
		
		game.getTrainerMoving();
		game.getRemainingNumberOfPokemon();
		
		
	}

}
