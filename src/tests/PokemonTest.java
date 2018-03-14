package tests;

import java.awt.Point;

import org.junit.Test;

import model.Common;
import model.Pokemon;

public class PokemonTest {

	@Test
	public void test(){
		Pokemon dd = new Common("Eevee", "ImageSet/P_eevee.png", 100);
		dd.capture();
		dd.getName();
		dd.getChanceToBeCaptured();
		dd.getChanceToRun();
		dd.isCaptured();
		dd.isDead();
		dd.isRan();
		dd.changeLower();
		dd.changeUpper();
		
		Pokemon ff = new Common("Eevee", "ImageSet/P_eevee.png", 100);
		ff.capture();
		
		
	}
}
