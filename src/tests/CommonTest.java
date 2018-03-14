package tests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import model.Bait;
import model.Common;

import model.Rock;


public class CommonTest {

	@Test
	public void test(){
		Common Eevee = new Common("Eevee", "ImageSet/P_eevee.png", 100);
		assertEquals(40, Eevee.getChanceToRun());
		assertEquals(60,Eevee.getChanceToBeCaptured());
		Bait bait = new Bait();
		Rock rock = new Rock();
		bait.use(Eevee);
		assertTrue(Eevee.getChanceToRun() < 40);
		assertTrue(Eevee.getChanceToBeCaptured() < 60);
		bait.use(Eevee);
		bait.use(Eevee);
		bait.use(Eevee);
		bait.use(Eevee);
		bait.use(Eevee);
		bait.use(Eevee);
		bait.use(Eevee);
		assertEquals(0, Eevee.getChanceToRun());
		bait.use(Eevee);
		bait.use(Eevee);
		bait.use(Eevee);
		bait.use(Eevee);
		bait.use(Eevee);
		assertEquals(0,Eevee.getChanceToBeCaptured());
		
		Common rainer = new Common("Ranier","ImageSet/P_eevee.png", 100);
		assertEquals(40, rainer.getChanceToRun());
		assertEquals(60,rainer.getChanceToBeCaptured());
		while(rainer.getChanceToRun()<100){
			rock.use(rainer);
		}
		assertEquals(100, rainer.getChanceToRun());
		
		rainer.resetPokemon();
		assertEquals(40, rainer.getChanceToRun());
		assertEquals(60,rainer.getChanceToBeCaptured());
	}
}
