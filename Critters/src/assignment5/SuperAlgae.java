package assignment5;

/* CRITTERS SuperAlgae.java
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

import assignment5.Critter.TestCritter;

public class SuperAlgae extends TestCritter {

	public String toString() { return "A"; }
	
	/**
	 * Super Algae will never fight, it will always try to run away in a random direction
	 */
	public boolean fight(String not_used) { 
		run(getRandomInt(number_of_directions));
		return true; 
	}
	
	/**
	 * doTimeStep for SuperAlgae. Super Algae will photosynthesize at 10 times the rate of Algae and will always run from fights
	 * It will reproduce when it has more than 150 energy
	 */
	public void doTimeStep() {
		setEnergy(getEnergy() + 10*Params.photosynthesis_energy_amount);
		
		//if the SuperAlgae's energy is above 150 it will reproduce 
		if (getEnergy() > 150) {
			SuperAlgae child = new SuperAlgae();
			reproduce(child, getRandomInt(number_of_directions));
		}
	}
	
	public static String runStats(List<Critter> superAlgaes) {
		return "There are currently " + superAlgaes.size() + " superAlgaes represented as red circles \n";
		
	}

	@Override
	public CritterShape viewShape() {
		
		return CritterShape.CIRCLE;
	}
	@Override
	public javafx.scene.paint.Color viewFillColor(){
		return javafx.scene.paint.Color.RED;
	}
	
	@Override
	public javafx.scene.paint.Color viewOutlineColor(){
		return javafx.scene.paint.Color.RED;
	}
	
}
