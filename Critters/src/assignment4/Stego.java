package assignment4;

import java.util.List;

import assignment4.Critter.TestCritter;

public class Stego extends TestCritter {

	@Override
	public String toString() { return "S"; }
	
	private int dir = 0;
	
	
	public boolean fight(String not_used) { return true; }

	@Override
	//the Stego will look for algea near it and move toward them
	public void doTimeStep() {
		
		//if the Stego has more than 50 energy it will reproduce
		if(getEnergy() > 200) {
			Stego child = new Stego();
			reproduce(child, getRandomInt(Params.number_of_directions));
		}
		
		
		//movement algorithm for Stego
		dir = getRandomInt(Params.number_of_directions);
		try {
			
			
			List<Critter> closeAlgae = getInstances("Algae");
			//we find all Algae within a radius of 4 of the Stego
			for (int i = 0; i < closeAlgae.size(); i++) {
				if (compareDistance(this, closeAlgae.get(i)) > 5) {
					closeAlgae.remove(i);
					i--;
				}
			}
			
			
			//if there are any algae within a radius of 4 we move toward the closest one
			if (!closeAlgae.isEmpty()) {
				
				//find the closest algae
				Algae closestAlgae = (Algae) closeAlgae.get(0);
				for (int i = 1; i < closeAlgae.size(); i++) {
					if (compareDistance(this, closestAlgae) > compareDistance(this, closeAlgae.get(i))) {
						closestAlgae = (Algae) closeAlgae.get(i);
					}
				}
				
				
				//decide what direction to  move based on the closet Algae
				walk(moveTowardAlgae(this, closestAlgae));
				
				
				
			}
			
			
			//if there are no algae close by just walk randomly
			else {
				walk(dir);
			}
			
			
		}
		catch (InvalidCritterException e) {
			System.out.println("Stego looked for Algae and getInstances did not work");
		}
	}

	
	public static void runStats(List<Critter> stegos) {
		System.out.println("" + stegos.size() + " critters as follows -- S:" + stegos.size());
		System.out.println("test");
		
	}
	
	
	//calculates the distance between a Stego Critter and an Algae Critter
	private int compareDistance(Stego stego, Critter crit) {
		//the getX_coord functions do not work on critters, so we must type cast the critters to Algae
		Algae algae = (Algae) crit;
		return ( (int) Math.sqrt((Math.pow(stego.getX_coord() - algae.getX_coord(), 2) + Math.pow(stego.getY_coord() - algae.getY_coord(), 2))) );
	}
	
	
	private int moveTowardAlgae(Stego stego, Algae algae) {
		//if the x positions are different move toward the Algae in the x direction
		if (stego.getX_coord() != algae.getX_coord()) {
			if (stego.getX_coord() > algae.getX_coord()) {
				return 4;
			}
			else {
				return 0;
			}
		}
		
		//if the y positions are different move toward the Algae in the y direction
		else if (stego.getY_coord() != algae.getY_coord()) {
			if (stego.getY_coord() > algae.getY_coord()) {
				return 2;
			}
			else {
				return 6;
			}
		}
		
		//if the coordinants are the same just return a random direction
		else {
			return (dir);
		}
		
	}
	
	
	
	
	
	
}

