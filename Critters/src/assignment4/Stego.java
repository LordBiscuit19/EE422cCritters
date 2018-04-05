package assignment4;

/* CRITTERS Stego.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * Donald Maze-England
 * dsm2588
 * 15465
 * Jennifer Sin
 * js45246
 * 15466
 * Slip days used: <0>
 * Spring 2018
 */

import java.util.List;

import assignment4.Critter.CritterShape;
import assignment4.Critter.TestCritter;

public class Stego extends TestCritter {

	@Override
	public String toString() { return "S"; }
	
	private int dir = 0;
	
	
	public boolean fight(String not_used) { return true; }

	@Override
	/**
	 * the Stego will look for algea near it and move toward them
	 * It will reproduce if it gets more than 200 energy
	 */
	public void doTimeStep() {
		
		//if the Stego has more than 200 energy it will reproduce
		if(getEnergy() > 200) {
			Stego child = new Stego();
			reproduce(child, getRandomInt(number_of_directions));
		}
		
		
		//movement algorithm for Stego
		dir = getRandomInt(number_of_directions);
			
		//first look for algae 2 steps away and if one is found set the movement direction to the corresponding direction
		for (int i = 0; i < 8; i++) {
			if(this.look(i,true).equals("@")) {
				dir = i;
			}
		}
		//then look for algae 1 step away and if one is found set the movement direction to the corresponding direction
		//this way the stego will prioritize the closer algae
		for (int i = 0; i < 8; i++) {
			if(this.look(i,false).equals("@")) {
				dir = i;
			}
		}
			
		walk(dir);
			
			
	}

	
	public static String runStats(List<Critter> stegos) {
		return "There are currently " + stegos.size() + " stegos represented as purple purple triangles \n";
		
	}
	
	
	
	
	@Override
	public CritterShape viewShape() {
		
		return CritterShape.TRIANGLE;
	}
	@Override
	public javafx.scene.paint.Color viewFillColor(){
		return javafx.scene.paint.Color.PURPLE;
	}
	
	@Override
	public javafx.scene.paint.Color viewOutlineColor(){
		return javafx.scene.paint.Color.PURPLE;
	}
	
	
	
	
	
}

