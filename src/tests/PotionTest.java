package tests;

import java.awt.Point;

import org.junit.Test;

import model.Common;
import model.Pokemon;
import model.Potion;

public class PotionTest {

	@Test
	public void test(){
		Common Raichu = new Common("Eevee", "ImageSet/P_eevee.png", 100);
		Potion.getInstance().use(Raichu);
		
		
	}
}
