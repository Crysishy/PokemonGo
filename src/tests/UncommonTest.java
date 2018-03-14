package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Point;

import org.junit.Test;

import model.Bait;
import model.Common;
import model.Rock;
import model.Uncommon;

public class UncommonTest {

	@Test
	public void test(){
		Uncommon Raichu = new Uncommon("Eevee", "ImageSet/P_eevee.png", 100);
		Bait bait = new Bait();
		Rock rock = new Rock();
		rock.nextPosition();
		Rock.tic ++;
		rock.nextPosition();
		Rock.tic ++;
		rock.nextPosition();
		Rock.tic ++;
		rock.nextPosition();
		Rock.tic ++;
		rock.nextPosition();
		Rock.tic ++;
		rock.nextPosition();
		Rock.tic ++;
		rock.nextPosition();
		Rock.tic ++;
		rock.nextPosition();
		Rock.tic ++;
		rock.nextPosition();
		Rock.tic ++;
		rock.nextPosition();
		Rock.tic ++;
		rock.nextPosition();
		Rock.tic ++;
		rock.nextPosition();
		assertEquals(60, Raichu.getChanceToRun());
		assertEquals(40,Raichu.getChanceToBeCaptured());
		while(Raichu.getChanceToRun()>0){
			bait.use(Raichu);
		}
		assertEquals(0, Raichu.getChanceToRun());
		
		Uncommon rainer = new Uncommon("Eevee", "ImageSet/P_eevee.png", 100);
		assertEquals(60, rainer.getChanceToRun());
		assertEquals(40,rainer.getChanceToBeCaptured());
		while(rainer.getChanceToRun()<100){
			rock.use(rainer);
		}
		assertEquals(100, rainer.getChanceToRun());
		
		
		rainer.resetPokemon();
		assertEquals(60, rainer.getChanceToRun());
		assertEquals(40,rainer.getChanceToBeCaptured());
	}
}
