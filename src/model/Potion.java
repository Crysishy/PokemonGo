package model;

public class Potion extends Item{
	
	private Potion(){
		super(0);
	}
	
	private static Potion thePotion;
	
	public static Potion getInstance(){
		if(thePotion == null)
			thePotion = new Potion();
		return thePotion;
	}

	@Override
	public void use(Pokemon pokemon) {
		pokemon.potioning();
		super.number--;
	}

}
