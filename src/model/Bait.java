package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Bait extends Item implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8772725933206203767L;

	public Bait(){ 
		super(20);
		baits = new ArrayList<BufferedImage>();
		try {
			allBaits = ImageIO.read(new File("ImageSet/throwing_bait.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		baits.add(allBaits);
		baits.add(allBaits);
		baits.add(allBaits);
		baits.add(allBaits);
		baits.add(allBaits);
		baits.add(allBaits);
		baits.add(allBaits);
		baits.add(allBaits);
		baits.add(allBaits);
		baits.add(allBaits);
		baits.add(allBaits);
		baits.add(allBaits);
	}

	public static boolean animating = false;
	private transient BufferedImage allBaits;
	public static transient ArrayList<BufferedImage> baits;

	@Override
	public void use(Pokemon pokemon) {
		pokemon.changeLower();
		super.number--;
	}
}
