package model;

import java.io.Serializable;
import java.util.Random;

public class Rare extends Pokemon implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2581117735340218940L;
	private static final int RUN = 80;
	private static final int CAPTURE = 20;

	public Rare(String name,String filename, int HP) {
		super(name, filename, HP, RUN, CAPTURE);
	}
 

	@Override
	public void changeLower() {
		Random random = new Random();
		int num = 5 + random.nextInt(3);
		super.chanceToRun -= num;
		if (chanceToRun < 0)
			chanceToRun = 0;
		num = 10 + random.nextInt(10);
		chanceToBeCaptured -= num;
		if (chanceToBeCaptured < 0)
			chanceToBeCaptured = 0;
	}

	@Override
	public void changeUpper() {
		Random random = new Random();
		int num = 5 + random.nextInt(3);
		super.chanceToRun += num;
		if (chanceToRun > 100){
			chanceToRun = 100;
			super.ran = true;
		}
		num = 10 + random.nextInt(10);
		chanceToBeCaptured += num;

		if (chanceToBeCaptured > 100)
			chanceToBeCaptured = 100;
		super.HP -= 5;
	}


	@Override
	public void resetPokemon() {
		super.captured = false;
		super.ran = false;
		super.chanceToBeCaptured = CAPTURE;
		super.chanceToRun = RUN;
		
	}

}
