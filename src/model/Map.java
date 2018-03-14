package model;

import java.awt.Point;
import java.io.Serializable;
import java.util.Random;

public class Map implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1906111573004960281L;
	public final int rows_verti = 38;
	public final int cols_horiz = 38;
	private Point trainer;
	private Point[] water;
	private Point[] grass;
	private Point[] trees;
	private Point[] tree;
	private Point[] rocks;
	private Point[] rock;
	private Point[] pokemons;
	private Grid[][] map;

	public Map() {
		System.out.println("Generating a new map in Map...");
		map = new Grid[rows_verti][cols_horiz];

		setUpTrees();
		setUpPool();
		setUpObstacles();
		setUpGrass();

		for (int row = 0; row < rows_verti; row++) {
			for (int col = 0; col < cols_horiz; col++) {
				map[row][col] = new Grid();
				Point temp = new Point(row, col);

				for (Point point : trees)
					if (point.equals(temp))
						map[row][col].addTree();

				for (Point point : water)
					if (point.equals(temp))
						map[row][col].addWater();

				for (Point point : rocks)
					if (point.equals(temp))
						map[row][col].addRock();

				for (Point point : tree)
					if (point.equals(temp))
						map[row][col].addTree();

				for (Point point : rock)
					if (point.equals(temp))
						map[row][col].addRock();

				for (Point point : grass)
					if (point.equals(temp))
						map[row][col].addGrass();
			}
		}

		setUpPokemons();
		for (Point point : pokemons)
			map[point.x][point.y].addPokemon();

		setUpTrainer();
		map[trainer.x][trainer.y].addTrainer();

	}

	private void setUpTrees() {
		trees = new Point[544];
		int index = 0;
		for (int row = 0; row < rows_verti; row++) {
			for (int col = 0; col < cols_horiz; col++) {
				if (!(row >= 4 && row <= 33 && col >= 4 && col <= 33)) {
					trees[index] = new Point(row, col);
					index++;
				}
			}
		}
	}

	private void setUpPool() {
		water = new Point[60];
		int index = 0;
		for (int row = 10; row <= 14; row++) {
			for (int col = 13; col <= 24; col++) {
				water[index] = new Point(row, col);
				index++;
			}
		}

		index = 0;
		rocks = new Point[38];
		for (int row = 9; row <= 15; row++) {
			for (int col = 12; col <= 25; col++) {
				if (!(row > 9 && row < 15 && col > 12 && col < 25)) {
					rocks[index] = new Point(row, col);
					index++;
				}
			}
		}
	}

	private void setUpObstacles() {
		Random random = new Random();
		tree = new Point[random.nextInt(5) + 40];
		rock = new Point[random.nextInt(5) + 40];
		for (int i = 0; i < tree.length; i++)
			tree[i] = new Point(random.nextInt(30) + 4, random.nextInt(30) + 4);
		for (int i = 0; i < rock.length; i++)
			rock[i] = new Point(random.nextInt(30) + 4, random.nextInt(30) + 4);
	}

	private void setUpGrass() {
		Random random = new Random();
		grass = new Point[300];
		int index = 0;
		for (int row = 4; row <= 33; row++) {
			int col = random.nextInt(16) + 4;
			for (int i = 0; i < 10; i++) {
				grass[index] = new Point(row, col);
				col++;
				index++;
			}
		}
	}

	private void setUpPokemons() {
		Random random = new Random();
		pokemons = new Point[20];
		Point point = new Point(0, 0);
		for (int i = 0; i < pokemons.length; i++) {
			while (!isValidPositionForPokemon(point))
				point = new Point(random.nextInt(30) + 4, random.nextInt(30) + 4);
			pokemons[i] = point;
			point = new Point(0, 0);
		}
	}

	private void setUpTrainer() {
		Random random = new Random();
		trainer = new Point(0, 0);
		while (!isValidPositionForTrainer(trainer))
			trainer = new Point(random.nextInt(30) + 4, random.nextInt(30) + 4);
	}

	private boolean isValidPositionForTrainer(Point point) {
		int row = point.x;
		int col = point.y;
		if (map[row][col].hasPokemon())
			return false;
		if (map[row][col].hasWater())
			return false;
		if (map[row][col].hasTree())
			return false;
		if (map[row][col].hasRock())
			return false;
		if (map[row][col].hasGrass())
			return false;

		return true;
	}

	private boolean isValidPositionForPokemon(Point point) {
		int row = point.x;
		int col = point.y;
		if (map[row][col].hasPokemon())
			return false;
		if (map[row][col].hasWater())
			return false;
		if (map[row][col].hasTree())
			return false;
		if (map[row][col].hasRock())
			return false;
		if (!map[row][col].hasGrass())
			return false;

		return true;
	}

	public boolean isValidMove(Point position) {
		if (map[position.x][position.y].hasWater())
			return false;
		if (map[position.x][position.y].hasRock())
			return false;
		if (map[position.x][position.y].hasTree())
			return false;

		return true;
	}

	public void updateTrainer(Point position) {
		trainer = position;
		for (int row = 0; row < rows_verti; row++) {
			for (int col = 0; col < cols_horiz; col++) {
				if (row == position.x && col == position.y)
					map[row][col].addTrainer();
				else
					map[row][col].removeTrainer();
			}
		}
		shufflePokemonPosition();
	}

	public Point getTrainerLocation() {
		return trainer;
	}

	public Point getPokemonLocation(int index) {
		return pokemons[index];
	}

	public Grid getGrid(Point position) {
		return map[position.x][position.y];
	}

	@Override
	public String toString() {
		String result = "\n";
		for (int row = 0; row < rows_verti; row++) {
			result += " ";
			for (int col = 0; col < cols_horiz; col++) {
				result += map[row][col].toString() + " ";
			}
			result += "\n";
		}

		return result;
	}

	public boolean isEncounteredPokemon() {
		return map[trainer.x][trainer.y].hasPokemon();
	}

	public void shufflePokemonPosition() {
		for (Point point : pokemons)
			map[point.x][point.y].removePokemon();

		Random random = new Random();
		// pokemons = new
		// Point[Game.getInstance().getRemainingNumberOfPokemon()];
		pokemons = new Point[20];
		Point point = new Point(0, 0);
		for (int i = 0; i < pokemons.length; i++) {
			while (!isValidPositionForPokemon(point))
				point = new Point(random.nextInt(30) + 4, random.nextInt(30) + 4);
			pokemons[i] = point;
			point = new Point(0, 0);
		}

		for (Point newPoint : pokemons)
			map[newPoint.x][newPoint.y].addPokemon();
	}

	// public static void main(String[] args){
	//// Map map = new Map();
	//// System.out.println(map.toString());
	// Random random = new Random();
	// for (int i = 0; i < 20; i++)
	// System.out.println(random.nextInt(2));
	// }
}
