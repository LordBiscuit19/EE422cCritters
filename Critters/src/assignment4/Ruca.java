package assignment4;

/* CRITTERS Ruca.java
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
import java.lang.reflect.Method;

public class Ruca extends TestCritter {

	@Override

	public String toString() {return "R";}
	
	private int dir;
	/**
	 * fight determination 
	 */
	public boolean fight(String not_used) {
		
		return true;
	}
	/**
	 *  Find out the current highest energy level in the population 
	 * then set Ruca to that energy *2 leaving out all the other Rucas
	 * this is to give it a statistical advantage to roll a higher 
	 * number than opponents. Expecting all the Rucas to have the same Energy
	 * except new babies
	 */
	@Override
	public void doTimeStep() {
		
		List <Critter> checkList = getPopulation();
		int highestEnergy=0;
		
		for(Critter crit : checkList) {
			if(crit.getEnergy() > highestEnergy && !(crit instanceof Ruca )) {
				highestEnergy=crit.getEnergy();
			}
		}
		setEnergy(highestEnergy*2);
		
		/*Chance for reproduction, roll an even number then reproduce
		 * if odd do nothing 
		 */
		
		if (getRandomInt(Params.start_energy)%2 == 0) {
			Ruca kitten = new Ruca();
			reproduce (kitten, getRandomInt(number_of_directions));
		}
		
		dir = getRandomInt(number_of_directions);
		walk(dir);
		
		
	}
	
	
	/**
	 * Counts how many rucas are on the on board 
	 * @param rucaList- a list of all instance of Rucas 
	 */
	public static String runStats(List<Critter> rucaList) {
		return "There are currently: " + rucaList.size() + " Rucas' represented by brown diamond \n";
		
	}
	
	@Override
	public CritterShape viewShape() {
		
		return CritterShape.DIAMOND;
	}
	@Override
	public javafx.scene.paint.Color viewFillColor(){
		return javafx.scene.paint.Color.CHOCOLATE;
	}
	
	@Override
	public javafx.scene.paint.Color viewOutlineColor(){
		return javafx.scene.paint.Color.CHOCOLATE;
	}
}
