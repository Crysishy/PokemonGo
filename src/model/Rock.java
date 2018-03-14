package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Rock extends Item implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7588418149265127612L;

	public Rock() {
		super(20);
		rocks = new ArrayList<BufferedImage>();
		try {
			allRocks = ImageIO.read(new File("ImageSet/throwing_rock.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rocks.add(allRocks);
		rocks.add(allRocks);
		rocks.add(allRocks);
		rocks.add(allRocks);
		rocks.add(allRocks);
		rocks.add(allRocks);
		rocks.add(allRocks);
		rocks.add(allRocks);
		rocks.add(allRocks);
		rocks.add(allRocks);
		rocks.add(allRocks);
		rocks.add(allRocks);
	}
	
	public static boolean animating = false;
	private transient BufferedImage allRocks;
	public static transient ArrayList<BufferedImage> rocks;

	@Override
	public void use(Pokemon pokemon) {
		pokemon.changeUpper();
		super.number--;
	}


}
