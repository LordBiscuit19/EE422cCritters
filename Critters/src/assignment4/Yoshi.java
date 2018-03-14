package assignment4;

import java.util.List;

import assignment4.Critter.TestCritter;

public class Yoshi extends TestCritter{

	@Override
	public String toString() { return "Y";}
	
	private int dir;
	
	public boolean fight(String nope) {
		return true;
	}
	
	@Override 
	 //will move only diagonally 
	public void doTimeStep() {
		//less than 100 energy Yoshi gets 4 times his energy 
		if(getEnergy() < 100) {
			setEnergy(4*getEnergy());
		}
		
		if (getEnergy() > 100 && getEnergy() < 400 ) {
			Yoshi pup1 = new Yoshi();
			Yoshi pup2 = new Yoshi();
			//will place pup to its current left 
			reproduce(pup1, 4);
			// will place pup to its current right
			reproduce(pup2, 0);
		}
		if (getEnergy() >=400) {
			
			Yoshi pup3 = new Yoshi();
			Yoshi pup4 = new Yoshi();
			//will place pup north
			reproduce (pup3, 2);
			//will place pup south
			reproduce (pup4, 6);

		}
		
		
		//Diagonal movement direction is random
		dir = (2 * (getRandomInt(Params.number_of_directions))+1)% Params.number_of_directions;
		walk(dir);
		
	}
	public static void runStats(List<Critter> yoshiList) {
		System.out.println("There are currently: "+ yoshiList.size() + " Yoshis' represented by 'Y'");
	}

}