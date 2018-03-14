package model;


import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Game extends Observable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2476709283557736835L;

	public static Game onlyGame;

	private boolean gameOver;
	private boolean exhausted;
	private boolean ranOutOfPokeball;
	private boolean gameWon;
	private Trainer trainer;
	// private Common eevee;
	// private Common horsea;
	// private Common magikarp;
	// private Common meowth;
	// private Common oddish;
	// private Common vulpix;
	// private Uncommon bulbasaur;
	// private Uncommon charmander;
	// private Uncommon pikachu;
	// private Rare snorlax;
	private ArrayList<Pokemon> pokemonCollection;
	private ArrayList<Pokemon> trainerCollection;
	private String mapStyle;
	private Map map;
	public boolean alreadyOver;

	public transient BufferedImage landImage;
	public transient BufferedImage waterImage;
	public transient BufferedImage rock1Image;
	public transient BufferedImage rock2Image;
	public transient BufferedImage tree1Image;
	public transient BufferedImage tree2Image;
	public transient BufferedImage tree3Image;
	public transient BufferedImage grassImage;

	public static Game getInstance() {
		if (onlyGame == null) {
			onlyGame = new Game();
		}
		return onlyGame;
	}

	private Game() {
		initializeVariables();
		mapStyle = "Forest";
	}

	public void prepareImages() {
		if (mapStyle.equals("Forest")) {
			landImage = importImage("ImageSet/land.png");
			waterImage = importImage("ImageSet/water.png");
			rock1Image = importImage("ImageSet/rock1.png");
			rock2Image = importImage("ImageSet/rock2.png");
			tree1Image = importImage("ImageSet/tree1.png");
			tree2Image = importImage("ImageSet/tree2.png");
			tree3Image = importImage("ImageSet/tree3.png");
			grassImage = importImage("ImageSet/grass.png");
		}
		if (mapStyle.equals("Desert")) {
			landImage = importImage("ImageSet/d_land.png");
			waterImage = importImage("ImageSet/d_water.png");
			rock1Image = importImage("ImageSet/d_rock1.png");
			rock2Image = importImage("ImageSet/d_rock2.png");
			tree1Image = importImage("ImageSet/d_tree1.png");
			tree2Image = importImage("ImageSet/d_tree2.png");
			tree3Image = importImage("ImageSet/d_tree3.png");
			grassImage = importImage("ImageSet/d_grass.png");
		}
		if (mapStyle.equals("Snow")) {
			landImage = importImage("ImageSet/bg.png").getSubimage(307, 240, 42, 47);
			waterImage = importImage("ImageSet/s_water.png");
			rock1Image = importImage("ImageSet/bg.png").getSubimage(352, 689, 32, 30);
			rock2Image = importImage("ImageSet/s_rock2.png");
			tree1Image = importImage("ImageSet/s_tree1.png");
			tree2Image = importImage("ImageSet/s_tree2.png");
			tree3Image = importImage("ImageSet/s_tree3.png");
			grassImage = importImage("ImageSet/s_grass.png");
		}
	}

	public void startNewGame() {
		initializeVariables();
		setChanged();
		notifyObservers();
	}

	private void initializeVariables() {
		gameOver = false;
		exhausted = false;
		ranOutOfPokeball = false;
		gameWon = false;
		map = new Map();
		System.out.println("Generating a new map in Game...");
		trainer = new Trainer(map.getTrainerLocation());
		alreadyOver = false;
		trainer.getBall().resetNumber();
		trainer.getBait().resetNumber();
		trainer.getRock().resetNumber();

		// eevee = new Common("Eevee", "ImageSet/P_eevee.png", 100,
		// map.getPokemonLocation(0));
		// horsea = new Common("Horsea", "ImageSet/P_horsea.png", 100,
		// map.getPokemonLocation(1));
		// magikarp = new Common("Magikarp", "ImageSet/P_magikarp.png", 100,
		// map.getPokemonLocation(2));
		// meowth = new Common("Meowth", "ImageSet/P_meowth.png", 100,
		// map.getPokemonLocation(3));
		// oddish = new Common("Oddish", "ImageSet/P_oddish.png", 100,
		// map.getPokemonLocation(4));
		// vulpix = new Common("Vulpix", "ImageSet/P_vulpix.png", 100,
		// map.getPokemonLocation(5));
		// bulbasaur = new Uncommon("Bulbasaur", "ImageSet/P_bulbasaur.png",
		// 100, map.getPokemonLocation(6));
		// charmander = new Uncommon("Charmander", "ImageSet/P_charmander.png",
		// 100, map.getPokemonLocation(7));
		// pikachu = new Uncommon("Pikachu", "ImageSet/P_pikachu.png", 100,
		// map.getPokemonLocation(8));
		// snorlax = new Rare("Snorlax", "ImageSet/P_snorlax.png", 100,
		// map.getPokemonLocation(9));

		pokemonCollection = new ArrayList<Pokemon>();
		// pokemonCollection.add(new Common("Eevee", "ImageSet/P_eevee.png",
		// 100, map.getPokemonLocation(0)));
		// pokemonCollection.add(new Common("Horsea", "ImageSet/P_horsea.png",
		// 100, map.getPokemonLocation(1)));
		// pokemonCollection.add(new Common("Magikarp",
		// "ImageSet/P_magikarp.png", 100, map.getPokemonLocation(2)));
		// pokemonCollection.add(new Common("Meowth", "ImageSet/P_meowth.png",
		// 100, map.getPokemonLocation(3)));
		// pokemonCollection.add(new Common("Oddish", "ImageSet/P_oddish.png",
		// 100, map.getPokemonLocation(4)));
		// pokemonCollection.add(new Common("Vulpix", "ImageSet/P_vulpix.png",
		// 100, map.getPokemonLocation(5)));
		// pokemonCollection.add(new Uncommon("Bulbasaur",
		// "ImageSet/P_bulbasaur.png", 100, map.getPokemonLocation(6)));
		// pokemonCollection.add(new Uncommon("Charmander",
		// "ImageSet/P_charmander.png", 100, map.getPokemonLocation(7)));
		// pokemonCollection.add(new Uncommon("Pikachu",
		// "ImageSet/P_pikachu.png", 100, map.getPokemonLocation(8)));
		// pokemonCollection.add(new Rare("Snorlax", "ImageSet/P_snorlax.png",
		// 100, map.getPokemonLocation(9)));

		pokemonCollection.add(new Common("Eevee", "ImageSet/P_eevee.png", 100));
		pokemonCollection.add(new Common("Horsea", "ImageSet/P_horsea.png", 100));
		pokemonCollection.add(new Common("Magikarp", "ImageSet/P_magikarp.png",100));
		pokemonCollection.add(new Common("Meowth", "ImageSet/P_meowth.png", 100));
		pokemonCollection.add(new Common("Oddish", "ImageSet/P_oddish.png", 100));
		pokemonCollection.add(new Common("Vulpix", "ImageSet/P_vulpix.png", 100));
		pokemonCollection.add(new Uncommon("Bulbasaur", "ImageSet/P_bulbasaur.png", 100));
		pokemonCollection
				.add(new Uncommon("Charmander", "ImageSet/P_charmander.png",  100));
		pokemonCollection.add(new Uncommon("Pikachu", "ImageSet/P_pikachu.png", 100));
		pokemonCollection.add(new Rare("Snorlax", "ImageSet/P_snorlax.png",  100));
		Collections.shuffle(pokemonCollection);
		trainerCollection = new ArrayList<Pokemon>();
	}

	public void moveTrainer(String direction) {
		Point point = map.getTrainerLocation();
		

		switch (direction) {
		case "moveUp":
			if (map.isValidMove(new Point(point.x - 1, point.y))) {
				Trainer.canMove = true;
				trainer.moveUp();
				trainer.reduceStepsByOne();
				map.updateTrainer(trainer.getPosition());
			} else
				Trainer.canMove = false;
			break;
		case "moveDown":
			if (map.isValidMove(new Point(point.x + 1, point.y))) {
				Trainer.canMove = true;
				trainer.moveDown();
				trainer.reduceStepsByOne();
				map.updateTrainer(trainer.getPosition());
			} else
				Trainer.canMove = false;
			break;
		case "moveLeft":
			if (map.isValidMove(new Point(point.x, point.y - 1))) {
				Trainer.canMove = true;
				trainer.moveLeft();
				trainer.reduceStepsByOne();
				map.updateTrainer(trainer.getPosition());
			} else
				Trainer.canMove = false;
			break;
		case "moveRight":
			if (map.isValidMove(new Point(point.x, point.y + 1))) {
				Trainer.canMove = true;
				trainer.moveRight();
				trainer.reduceStepsByOne();
				map.updateTrainer(trainer.getPosition());
			} else
				Trainer.canMove = false;
			break;
		default:
			System.out.println("Error(Game): not yet implemented");
		}

		checkGameStatus();
		setChanged();
		notifyObservers();
	}

	public void actTrainer(String action) {
		switch (action) {
		case "runAway":
			randomMove();
			JOptionPane.showMessageDialog(null, "You have ran away!");
			Collections.shuffle(pokemonCollection);
			break;
		case "useBait":
			trainer.throwBait(getBattlingPokemon());
			break;
		case "useRock":
			trainer.throwRock(getBattlingPokemon());
			// the following will be deleted after finishing animation of
			// throwing a rock
			checkCapturedOrRan();
			break;
		case "usePokeball":
			trainer.throwPokeball(getBattlingPokemon());
			// was congrats, now in ButtonController.java
			break;
		default:
			System.out.println("What have you pressed!");
		}

		setChanged();
		notifyObservers();
	}

	public void checkCapturedOrRan() {
		if (getBattlingPokemon().isCaptured()) {

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
			JOptionPane.showMessageDialog(null,
					"Congratulations!\nYou have successfully captured " + getBattlingPokemon().getName());

			randomMove();
			trainerCollection.add(getBattlingPokemon());
			pokemonCollection.remove(getBattlingPokemon());
			Collections.shuffle(pokemonCollection);
		}
		if (getBattlingPokemon().isRan()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			getBattlingPokemon().resetPokemon();
			JOptionPane.showMessageDialog(null, "Oops!\n" + getBattlingPokemon().getName() + " has ran away...");
			randomMove();
			Collections.shuffle(pokemonCollection);
		}

		checkGameStatus();
	}
	
	private void checkGameStatus(){
		if (trainerCollection.size() == 10){
			gameOver = true;
			gameWon = true;
			return;
		}
		if (trainer.getSteps() == 0){
			gameOver = true;
			exhausted = true;
			return;
		}
		
		if (trainer.getBall().size() == 0){
			gameOver = true;
			ranOutOfPokeball = true;
			return;
		}
	}
	
	public boolean isOver(){
		return gameOver;
	}
	
	public boolean isWon(){
		return gameWon;
	}
	
	public boolean isExhausted(){
		return exhausted;
	}
	
	public boolean isOutOfPokeball(){
		return ranOutOfPokeball;

	}

	public void randomMove() {
		Point point = map.getTrainerLocation();

		if (map.isValidMove(new Point(point.x - 1, point.y))) {
			Trainer.canMove = true;
			trainer.moveUp();
			map.updateTrainer(trainer.getPosition());
			return;
		}

		if (map.isValidMove(new Point(point.x + 1, point.y))) {
			Trainer.canMove = true;
			trainer.moveDown();
			map.updateTrainer(trainer.getPosition());
			return;
		}

		if (map.isValidMove(new Point(point.x, point.y - 1))) {
			Trainer.canMove = true;
			trainer.moveLeft();
			map.updateTrainer(trainer.getPosition());
			return;
		}

		if (map.isValidMove(new Point(point.x, point.y + 1))) {
			Trainer.canMove = true;
			trainer.moveRight();
			map.updateTrainer(trainer.getPosition());
			return;
		}

		// Trainer.canMove = false;
	}

	public Map getGameMap() {
		return map;
	}

	public Trainer getTrainer() {
		return trainer;
	}

	public boolean isEncounteredPokemon() {
		
		return map.isEncounteredPokemon();
	}

	public Pokemon getBattlingPokemon() {
		if (pokemonCollection.size() == 0)
			return null;

		return pokemonCollection.get(0);
		// for (Pokemon aPokemon : pokemonCollection) {
		// if (aPokemon.getPosition().x == trainer.getPosition().x
		// && aPokemon.getPosition().y == trainer.getPosition().y)
		// return aPokemon;
		// }
		// return null;
	}

	public static BufferedImage importImage(String imageLocation) {
		try {
			return ImageIO.read(new File(imageLocation));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void getTrainerMoving() {
		super.setChanged();
		super.notifyObservers();
	}

	public int getRemainingNumberOfPokemon() {
		return pokemonCollection.size();
	}

	public void setMapStyle(String style) {
		mapStyle = style;
	}

	public String getMapStyle() {
		return mapStyle;
	}

}
