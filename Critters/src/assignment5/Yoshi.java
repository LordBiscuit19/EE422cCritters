package assignment5;

/* CRITTERS Yoshi.java
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

public class Yoshi extends TestCritter{

	@Override
	public String toString() { return "Y";}
	
	private int dir;
	
	public boolean fight(String nope) {
		return true;
	}
	/**
	 * Yoshi critter is a cheater, Yoshi will reproduce when his energy lever is greater than 100.
	 * If his energy level is at 100 he will make two babies. 
	 * If his energy level exceeds 400 he will make 4 babies.
	 * At the beginning of the turn if Yoshi's health level is less than 50 he will get two times his 
	 * current energy
	 * Yoshi will only move in a diagonal direction
	 */
	@Override  
	public void doTimeStep() {
		//less than 50 energy Yoshi gets 2 times his energy 
		if(getEnergy() < 50) {
			setEnergy(2*getEnergy());
		}
	//will only reproduce if energy is greater than 100 and will either produce 2 pups or 4 pups	
		if (getEnergy() >= 100 && getEnergy() < 400 ) {
			Yoshi pup1 = new Yoshi();
			Yoshi pup2 = new Yoshi();
			
			reproduce(pup1,getRandomInt(number_of_directions ));
			reproduce(pup2, getRandomInt(number_of_directions));
		}
		if (getEnergy() >=400) {
			
			Yoshi pup3 = new Yoshi();
			Yoshi pup4 = new Yoshi();
		
			reproduce (pup3, getRandomInt(number_of_directions));
			reproduce (pup4, getRandomInt(number_of_directions));

		}
		
		
		//Diagonal movement direction is random
		dir = (2 * (getRandomInt(number_of_directions))+1)% number_of_directions;
		walk(dir);
		
	}
	/**
	 * Will output  the current number of active Yoshi's on the board 
	 * @param yoshiList- a list of all active instances of Yoshi 
	 */
	public static String runStats(List<Critter> yoshiList) {
		return "There are currently: "+ yoshiList.size() + " Yoshis' represented by blue circles \n";
	}
	@Override
	public CritterShape viewShape() {
		return CritterShape.CIRCLE;
	}
	@Override
	public javafx.scene.paint.Color viewFillColor(){
		return javafx.scene.paint.Color.BLUE;
	}
	
	@Override
	public javafx.scene.paint.Color viewOutlineColor(){
		return javafx.scene.paint.Color.BLUE;
	}

}