package model;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

public class Trainer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7114003107610690007L;
	public static final transient BufferedImage theImage = Game.importImage("ImageSet/trainer.png");
	public static final transient BufferedImage STANDING_UP = theImage.getSubimage(61, 1, Trainer.IMAGE_WIDTH, Trainer.IMAGE_HEIGHT);
	public static final transient BufferedImage STANDING_DOWN = theImage.getSubimage(1, 1, Trainer.IMAGE_WIDTH, Trainer.IMAGE_HEIGHT);
	public static final transient BufferedImage STANDING_LEFT = theImage.getSubimage(31, 1, Trainer.IMAGE_WIDTH, Trainer.IMAGE_HEIGHT);
	public static final transient BufferedImage STANDING_RIGHT = theImage.getSubimage(91, 1, Trainer.IMAGE_WIDTH, Trainer.IMAGE_HEIGHT);
	
	public static transient ArrayList<BufferedImage> moveUp;
	public static transient ArrayList<BufferedImage> moveDown;
	public static transient ArrayList<BufferedImage> moveLeft;
	public static transient ArrayList<BufferedImage> moveRight;
	public static boolean moving = false;
	public static boolean canMove = true;
	public static int tic = 0;
	
	public static final int NUMBER_OF_STEPS = 500;
	public static final int X_SHIFT = 3;
	public static final int Y_SHIFT = 0;
	public static final int STRETCH_HORIZONTAL = -8;
	public static final int STRETCH_VERTICAL = -4;
	public static final int IMAGE_WIDTH = 14;
	public static final int IMAGE_HEIGHT = 21;
	
	private int steps;
	private ArrayList<Pokemon> pokemons;
	private Point position;
	private FacingTo facing;
	// private boolean isBattling;
	private Pokeball onlyBall;
	private Bait onlyBait;
	private Rock onlyRock;

	public Trainer(Point point) {
		steps = NUMBER_OF_STEPS;
		pokemons = new ArrayList<Pokemon>();
		position = point;
	    facing = FacingTo.DOWN;
	    onlyBall = new Pokeball();
	    onlyBait = new Bait();
	    onlyRock = new Rock();
	    prepareMovingImages();
	}

	private void prepareMovingImages() {
		moveUp = new ArrayList<BufferedImage>();
		moveUp.add(theImage.getSubimage(61, 31, Trainer.IMAGE_WIDTH, Trainer.IMAGE_HEIGHT));
		moveUp.add(theImage.getSubimage(61, 62, Trainer.IMAGE_WIDTH, Trainer.IMAGE_HEIGHT));
		moveUp.add(theImage.getSubimage(61, 92, Trainer.IMAGE_WIDTH, Trainer.IMAGE_HEIGHT));
		moveUp.add(theImage.getSubimage(61, 122, Trainer.IMAGE_WIDTH, Trainer.IMAGE_HEIGHT));
		moveUp.add(theImage.getSubimage(61, 151, Trainer.IMAGE_WIDTH, Trainer.IMAGE_HEIGHT));
		
		moveDown = new ArrayList<BufferedImage>();
		moveDown.add(theImage.getSubimage(1, 31, Trainer.IMAGE_WIDTH, Trainer.IMAGE_HEIGHT));
		moveDown.add(theImage.getSubimage(1, 62, Trainer.IMAGE_WIDTH, Trainer.IMAGE_HEIGHT));
		moveDown.add(theImage.getSubimage(1, 92, Trainer.IMAGE_WIDTH, Trainer.IMAGE_HEIGHT));
		moveDown.add(theImage.getSubimage(1, 122, Trainer.IMAGE_WIDTH, Trainer.IMAGE_HEIGHT));
		moveDown.add(theImage.getSubimage(1, 151, Trainer.IMAGE_WIDTH, Trainer.IMAGE_HEIGHT));
		
		moveLeft = new ArrayList<BufferedImage>();
		moveLeft.add(theImage.getSubimage(31, 31, Trainer.IMAGE_WIDTH, Trainer.IMAGE_HEIGHT));
		moveLeft.add(theImage.getSubimage(31, 62, Trainer.IMAGE_WIDTH, Trainer.IMAGE_HEIGHT));
		moveLeft.add(theImage.getSubimage(31, 92, Trainer.IMAGE_WIDTH, Trainer.IMAGE_HEIGHT));
		moveLeft.add(theImage.getSubimage(31, 122, Trainer.IMAGE_WIDTH, Trainer.IMAGE_HEIGHT));
		moveLeft.add(theImage.getSubimage(31, 151, Trainer.IMAGE_WIDTH, Trainer.IMAGE_HEIGHT));
		
		moveRight = new ArrayList<BufferedImage>();
		moveRight.add(theImage.getSubimage(91, 31, Trainer.IMAGE_WIDTH, Trainer.IMAGE_HEIGHT));
		moveRight.add(theImage.getSubimage(91, 62, Trainer.IMAGE_WIDTH, Trainer.IMAGE_HEIGHT));
		moveRight.add(theImage.getSubimage(91, 92, Trainer.IMAGE_WIDTH, Trainer.IMAGE_HEIGHT));
		moveRight.add(theImage.getSubimage(91, 122, Trainer.IMAGE_WIDTH, Trainer.IMAGE_HEIGHT));
		moveRight.add(theImage.getSubimage(91, 151, Trainer.IMAGE_WIDTH, Trainer.IMAGE_HEIGHT));
	}

	public boolean isAlive() {
		return steps > 0;
	}
	
	/** ↓↓↓ This part can only be used during battle ↓↓↓ */
	// the three using item action: only contact item object, not the pokemon object directly
	public void throwPokeball(Pokemon pokemon) {
		onlyBall.use(pokemon);
	}

	public void throwBait(Pokemon pokemon) {
		onlyBait.use(pokemon);
	}

	public void throwRock(Pokemon pokemon) {
		onlyRock.use(pokemon);
	}

	/** ↑↑↑ This part can only be used during battle ↑↑↑ */

	public int getSteps() {
		return steps;
	}
	
	public void reduceStepsByOne() {
		steps--;
	}

	public ArrayList<Pokemon> getPokemons() {
		return pokemons;
	}
	
	public int sizeOfBalls(){
		return onlyBall.size();
	}
	
	public int sizeOfBaits(){
		return onlyBait.size();
	}
	
	public int sizeOfRocks(){
		return onlyRock.size();
	}
	
	public Point getPosition() {
		return position;
	}
	
	public void setPosition(Point point){
		position = point;
	}
	
	public void moveUp(){
		position.x = position.x - 1;
		facing = FacingTo.UP;
	}
	
	public void moveDown(){
		position.x = position.x + 1;
		facing = FacingTo.DOWN;
	}
	
	public void moveLeft(){
		position.y = position.y - 1;
		facing = FacingTo.LEFT;
	}
	
	public void moveRight(){
		position.y = position.y + 1;
		facing = FacingTo.RIGHT;
	}
	
	public FacingTo getDirection(){
		return facing;
	}
	
	public Pokeball getBall(){
		return onlyBall;
	}
	public Bait getBait(){
		return onlyBait;
	}
	public Rock getRock(){
		return onlyRock;
	}
	
}
