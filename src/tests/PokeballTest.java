package tests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import model.Common;
import model.Pokeball;
import model.Pokemon;

public class PokeballTest {

	@Test
	public void test(){
		Common Eevee = new Common("Eevee", "ImageSet/P_eevee.png", 100);
		assertEquals(60,Eevee.getChanceToBeCaptured());
		Pokeball ball = new Pokeball();
		ball.use(Eevee);
		
		Eevee.isCaptured();
		
	}
}
