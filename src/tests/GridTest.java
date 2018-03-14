package tests;

import org.junit.Test;

import model.Grid;

public class GridTest {

	@Test
	public void test(){
		Grid grid = new Grid();
		grid.addGrass();
		grid.addPokemon();
		grid.addRock();
		grid.addTrainer();
		grid.addTree();
		grid.addWater();
		grid.hasGrass();
		grid.hasTrainer();
		grid.hasPokemon();
		grid.hasWater();
		grid.hasRock();
		grid.hasTree();
		grid.toString();
		grid.removeTrainer();
		
		Grid ff = new Grid();
		ff.addPokemon();
		ff.toString();
		
		Grid aa = new Grid();
		aa.addWater();
		aa.toString();
		
		Grid ss = new Grid();
		ss.addRock();
		ss.toString();
		
		Grid ww = new Grid();
		ww.addTree();
		ww.toString();
		
		Grid rr = new Grid();
		rr.addGrass();
		rr.toString();
		
		Grid qq = new Grid();
		
		qq.toString();
	}
}
