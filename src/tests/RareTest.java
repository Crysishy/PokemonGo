package tests;

import static org.junit.Assert.assertEquals;

import java.awt.Point;

import org.junit.Test;

import model.Bait;
import model.Common;
import model.Rare;
import model.Rock;


public class RareTest {

	@Test
	public void test(){
		Rare Raichu = new Rare("Eevee", "ImageSet/P_eevee.png", 100);
		Bait bait = new Bait();
		Rock rock = new Rock();
		assertEquals(80, Raichu.getChanceToRun());
		assertEquals(20,Raichu.getChanceToBeCaptured());
		while(Raichu.getChanceToRun()>0){
			bait.use(Raichu);
		}
		assertEquals(0, Raichu.getChanceToRun());
		
		Rare rainer = new Rare("Eevee", "ImageSet/P_eevee.png", 100);
		assertEquals(80, rainer.getChanceToRun());
		assertEquals(20,rainer.getChanceToBeCaptured());
		while(rainer.getChanceToRun()<100){
			rock.use(rainer);
		}
		assertEquals(100, rainer.getChanceToRun());
		
		rainer.resetPokemon();
		assertEquals(80, rainer.getChanceToRun());
		assertEquals(20,rainer.getChanceToBeCaptured());
	}
}
