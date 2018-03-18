package assignment4;

import java.util.List;
import assignment4.Critter.TestCritter;
import java.lang.reflect.Method;

public class Ruca extends TestCritter {

	@Override

	public String toString() {return "R";}
	
	private int dir;
	
	public boolean fight(String not_used) {
		
		return true;
	}
	
	@Override
	public void doTimeStep() {
		/* Find out the current highest energy level in the population 
		 * then set Ruca to that energy *2 leaving out all the other Rucas
		 * this is to give it a statistical advantage to roll a higher 
		 * number than opponents. Expecting all the Rucas to have the same Energy
		 * except new babies
		 */
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
			reproduce (kitten, getRandomInt(Params.number_of_directions));
		}
		
		dir = getRandomInt(Params.number_of_directions);
		walk(dir);
		
		
	}
	
	public static void runStats(List<Critter> rucaList) {
		System.out.println("There are currently: " + rucaList.size() + "Rucas' represented by 'R'");
		
	}
}