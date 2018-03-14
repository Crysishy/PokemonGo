package model;

import java.awt.Image;
import java.awt.image.BufferedImage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;


public class Grid implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7611910706567926225L;
	private boolean trainer;
	private boolean pokemon;
	private boolean grass;
	private boolean water;
	private int rock;
	private int tree;
	private Random random;

	public Grid() {
		trainer = false;
		pokemon = false;
		grass = false;
		water = false;
		rock = 0;
		tree = 0;
		random = new Random();

	}

	public void addTrainer() {
		trainer = true;
	}

	public void removeTrainer() {
		trainer = false;
	}

	public void addPokemon() {
		pokemon = true;
	}
	
	public void removePokemon(){
		pokemon = false;
	}

	public void addGrass() {
		grass = true;
	}

	public void addWater() {
		water = true;
	}

	public void addRock() {
		rock = random.nextInt(2) + 1;
	}

	public void addTree() {
		tree = random.nextInt(3) + 1;
	}

	public boolean hasTrainer() {
		return trainer;
	}

	public boolean hasPokemon() {
		return pokemon;
	}

	public boolean hasGrass() {
		return grass;
	}

	public boolean hasWater() {
		return water;
	}

	public boolean hasRock() {
		return rock != 0;
	}

	public boolean hasTree() {
		return tree != 0;
	}

	@Override
	public String toString() {
		if (trainer)
			return "龘";
		else if (pokemon)
			return "〇";
		else if (water)
			return "氺";
		else if (rock != 0)
			return "石";
		else if (tree != 0)
			return "木";
		else if (grass)
			return "艹";
		else
			return "一";
	}

	public Image getLandImage() {
		return Game.getInstance().landImage;
	}
	
	public Image getGrassImage() {
		return Game.getInstance().grassImage;
	}

	public BufferedImage getTrainerStandingImage() {
		if (Game.getInstance().getTrainer().getDirection().equals(FacingTo.DOWN))
			return Trainer.STANDING_DOWN;
		else if(Game.getInstance().getTrainer().getDirection().equals(FacingTo.UP))
			return Trainer.STANDING_UP;
		else if(Game.getInstance().getTrainer().getDirection().equals(FacingTo.LEFT))
			return Trainer.STANDING_LEFT;
		else
			return Trainer.STANDING_RIGHT;
	}
	
	public ArrayList<BufferedImage> getTrainerMovingImages(){
		if (Game.getInstance().getTrainer().getDirection().equals(FacingTo.DOWN))
			return Trainer.moveDown;
		else if(Game.getInstance().getTrainer().getDirection().equals(FacingTo.UP))
			return Trainer.moveUp;
		else if(Game.getInstance().getTrainer().getDirection().equals(FacingTo.LEFT))
			return Trainer.moveLeft;
		else
			return Trainer.moveRight;
	}

	public Image getElementImage() {
		if (water)
			return Game.getInstance().waterImage;
		if (rock != 0) {
			if (rock == 1)
				return Game.getInstance().rock1Image;
			else
				return Game.getInstance().rock2Image;
		}
		if (tree != 0) {
			if (tree == 1)
				return Game.getInstance().tree1Image;
			else if (tree == 2)
				return Game.getInstance().tree2Image;
			else
				return Game.getInstance().tree3Image;
		}
		if (grass)
			return Game.getInstance().grassImage;

		return Game.getInstance().landImage;
	}

	// private Image importImage(String imageLocation) {

	// if image == null, create a new new one, it's not null, use the old one
	// (Singleton/Flyweight desgin patterns)// make game faster

	// animate walking AND (maybe) throwing pokeball/rock/etc..
	// music
	// get rid of bugs
	// WOW factor: Snow/desert/forest maps
	// can walk to different map(s)
	// can see list of pokemon and other items
	

	/*
	 * try { return ImageIO.read(new File(imageLocation)); } catch (IOException
	 * e) { e.printStackTrace(); } return null; }
	 * 
	 */

}
