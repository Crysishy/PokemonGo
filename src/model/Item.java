package model;

import java.io.Serializable;

//hello
public abstract class Item implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5629725879258022039L;

	private int START_NUMBER;
	
	protected int number;
	
	
	public static final int X_START = 150;
	public static final int Y_START = 250;
	public static int X = X_START;
	public static int Y = Y_START;
	public static int tic = 0;


	public Item(int number){
		this.number = number;
		START_NUMBER = number;
	}
	
	public int size(){
		return number;
	}
	
	public abstract void use(Pokemon pokemon);
	
	public void nextPosition() {
		X += 10;
		switch (tic) {
		case 0:
			Y -= 6;
			break;
		case 1:
			Y -= 5;
			break;
		case 2:
			Y -= 4;
			break;
		case 3:
			Y -= 3;
			break;
		case 4:
			Y -= 2;
			break;
		case 5:
			Y -= 1;
			break;
		case 6:
			Y -= 0;
			break;
		case 7:
			Y += 1;
			break;
		case 8:
			Y += 2;
			break;
		case 9:
			Y += 3;
			break;
		case 10:
			Y += 4;
			break;
		case 11:
			Y += 5;
			break;
		default:

			break;
		}
	}
	
	public void resetPosition() {
		X = X_START;
		Y = Y_START;
	}
	
	public void resetNumber(){
		number = START_NUMBER;
	}
}
