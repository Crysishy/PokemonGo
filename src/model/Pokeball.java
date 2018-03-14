package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Pokeball extends Item implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2634381295615546644L;

	public Pokeball() {
		super(30);
		balls = new ArrayList<BufferedImage>();
		try {
			allBalls = ImageIO.read(new File("ImageSet/balls.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		balls.add(allBalls.getSubimage(5, 5, 54, 54));
		balls.add(allBalls.getSubimage(69, 5, 54, 54));
		balls.add(allBalls.getSubimage(133, 5, 54, 54));
		balls.add(allBalls.getSubimage(197, 5, 54, 54));
		balls.add(allBalls.getSubimage(261, 5, 54, 54));
		balls.add(allBalls.getSubimage(325, 5, 54, 54));
		balls.add(allBalls.getSubimage(389, 5, 54, 54));
		balls.add(allBalls.getSubimage(453, 5, 54, 54));
		balls.add(allBalls.getSubimage(517, 5, 54, 54));
		balls.add(allBalls.getSubimage(581, 5, 54, 54));
		balls.add(allBalls.getSubimage(645, 5, 54, 54));
		balls.add(allBalls.getSubimage(709, 5, 54, 54));
	}

	private transient BufferedImage allBalls;
	public static ArrayList<BufferedImage> balls;
	public static boolean animating = false;


	@Override
	public void use(Pokemon pokemon) {
		pokemon.capture();
		super.number--;
	}

}
