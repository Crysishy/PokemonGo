package model;


import java.awt.Point;
import java.io.Serializable;
import java.util.Random;

// first implemented by Benlong Huang

public abstract class Pokemon implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7040017684908978665L;
	private String name;
	protected int HP;
	protected int chanceToRun;
	protected int chanceToBeCaptured;
	protected boolean ran;
	protected boolean captured;
	private boolean dead;
	private String filename;
	private String soundFileName;

	public Pokemon(String name,String filename, int HP, int run, int capture) {
		this.filename = filename;
		chanceToRun = run;
		chanceToBeCaptured = capture;
		this.HP = HP;
		this.name = name;
		captured = false;
		ran = false;
		dead = false;
		//this.position = position;
	}

	public void capture() {
		Random random = new Random();
		int num = random.nextInt(100);
		if (num <= chanceToBeCaptured) {
			captured = true;
		} else {
			num = random.nextInt(100);
			if (num <= chanceToRun) {
				ran = true;
			}
		}
	}
	
	public void potioning(){
		HP += 10;
	}
	
	public abstract void changeLower();		// this is when bait is used
	
	public abstract void changeUpper();		// this is when rock is used
	

	public String getName(){
		return name;
	}
	
	public int getChanceToRun() {
		return chanceToRun;
	}

	public int getChanceToBeCaptured() {
		return chanceToBeCaptured;
	}

	public boolean isCaptured() {
		return captured;
	}

	public boolean isRan() {
		return ran;
	}

	public boolean isDead() {
		return dead;
	}
	
	public String getFileName(){
		return filename;
	}
	
	
	public abstract void resetPokemon();
	
//	public Point getPosition(){
//		return position;
//	}

}
