package assignment4;

import assignment4.Critter.TestCritter;

public class SuperAlgae extends TestCritter {

	public String toString() { return "A"; }
	
	public boolean fight(String not_used) { 
		run(getRandomInt(Params.number_of_directions));
		return true; 
	}
	
	public void doTimeStep() {
		setEnergy(getEnergy() + 10*Params.photosynthesis_energy_amount);
		
		//if the SuperAlgae's energy is above 150 it will reproduce 
		if (getEnergy() > 150) {
			SuperAlgae child = new SuperAlgae();
			reproduce(child, getRandomInt(Params.number_of_directions));
		}
	}
}
