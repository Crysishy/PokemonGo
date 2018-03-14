package tests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import model.Common;
import model.FacingTo;
import model.Rare;
import model.Trainer;

public class TrainerTest {

	@Test
	public void test(){
		Point point = new Point(10,10);
		Trainer trainer = new Trainer(point);
		trainer.setPosition(point);
		assertEquals(point, trainer.getPosition());
		Rare pokemon = new Rare("Eevee", "ImageSet/P_eevee.png", 100);
		trainer.isAlive();
		trainer.throwPokeball(pokemon);
		trainer.throwBait(pokemon);
		trainer.throwRock(pokemon);
		trainer.getSteps();
		trainer.getPokemons();
		trainer.getPosition();
		trainer.sizeOfBalls();
		trainer.sizeOfBaits();
		trainer.sizeOfRocks();
		trainer.moveDown();
		assertEquals(FacingTo.DOWN, trainer.getDirection());
		trainer.moveUp();
		assertEquals(FacingTo.UP, trainer.getDirection());
		trainer.moveLeft();
		assertEquals(FacingTo.LEFT, trainer.getDirection());
		trainer.moveRight();
		assertEquals(FacingTo.RIGHT, trainer.getDirection());
	}
}
