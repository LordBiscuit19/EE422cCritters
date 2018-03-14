package assignment4;
/* CRITTERS Critter.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Fall 2016
 */


import java.util.Iterator;
import java.util.List;


/* see the PDF for descriptions of the methods and fields in this class
 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private
 * no new public, protected or default-package code or data can be added to Critter
 */


public abstract class Critter {
	private static String myPackage;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();

	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}
	
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}
	
	
	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }
	
	private int energy = 0;
	protected int getEnergy() { return energy; }
	
	private int x_coord;
	private int y_coord;
	
	
	
	
	protected final void walk(int direction) {
		switch (direction) {
			case 0: x_coord = x_coord + 1;
					if(x_coord >= Params.world_width) {
						x_coord = 0;
					}
					break;
					
			case 1: x_coord++;
					y_coord--;
					if(x_coord >= Params.world_width && y_coord < 0) {
						x_coord = 0;
						y_coord = Params.world_height - 1;
					}
					if(x_coord >= Params.world_width) {
						x_coord = 0;
					}
					if(y_coord < 0) {
						y_coord = Params.world_height - 1;
					}
					break;
					
			case 2: y_coord--;
					if(y_coord < 0) {
						y_coord = Params.world_height - 1;
					}
					break;
					
			case 3:	x_coord--;
					y_coord--;
					if(x_coord < 0 && y_coord < 0) {
						x_coord = Params.world_width - 1;
						y_coord = Params.world_height - 1;
					}
					if(x_coord < 0) {
						x_coord = Params.world_width - 1;
					}
					if(y_coord < 0) {
						y_coord = Params.world_height - 1;
					}
					break;
			case 4: x_coord--;
					if(x_coord < 0) {
						x_coord = Params.world_width - 1;
					}
					break;
					
			case 5: x_coord--;
					y_coord++;
					if(x_coord < 0 && y_coord > Params.world_height-1) {
						x_coord = Params.world_width - 1;
						y_coord = 0;
					}
					if(x_coord < 0) {
						x_coord = Params.world_width - 1;
					}
					if(y_coord > Params.world_height-1) {
						y_coord = 0;
					}
					break;
					
			case 6: y_coord++;
					if(y_coord > Params.world_height-1) {
						y_coord = 0;
					}
					break;
					
			case 7: x_coord++;
					y_coord++;
					if(x_coord > Params.world_width-1 && y_coord > Params.world_height-1) {
						x_coord = 0;
						y_coord = 0;
					}
					if(x_coord > Params.world_width-1) {
						x_coord = 0;
					}
					if(y_coord > Params.world_height-1) {
						y_coord = 0;
					}
					break;
		}
		
		this.energy = this.energy - Params.walk_energy_cost;
		
	}
	
	
	
	
	protected final void run(int direction) {
		this.walk(direction);
		this.walk(direction);
		
		this.energy = this.energy - Params.run_energy_cost + (2 * Params.walk_energy_cost);
	}
	
	
	
	
	protected final void reproduce(Critter offspring, int direction) {
		if (this.energy >= Params.min_reproduce_energy) {
			offspring.energy = this.energy/2;
			this.energy = this.energy/2;
			offspring.x_coord = this.x_coord;
			offspring.y_coord = this.y_coord;
			offspring.walk(direction);
			babies.add(offspring);
		}
	}

	
	
	
	public abstract void doTimeStep();
	public abstract boolean fight(String oponent);
	
	/**
	 * create and initialize a Critter subclass.
	 * critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,
	 * an InvalidCritterException must be thrown.
	 * (Java weirdness: Exception throwing does not work properly if the parameter has lower-case instead of
	 * upper. For example, if craig is supplied instead of Craig, an error is thrown instead of
	 * an Exception.)
	 * @param critter_class_name
	 * @throws InvalidCritterException
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {
		try {
		critter_class_name = "assignment4." + critter_class_name;
		Class<?> c = Class.forName(critter_class_name);
		Critter myCritter = (Critter) c.newInstance();
		randPosition(myCritter);
		population.add(myCritter);
		myCritter.energy = Params.start_energy;
		}
		catch (Exception e){
			throw new InvalidCritterException(critter_class_name);
		}
	}
	
	/**
	 * Gets a list of critters of a specific type.
	 * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.
	 * @return List of Critters.
	 * @throws InvalidCritterException
	 */
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		List<Critter> result = new java.util.ArrayList<Critter>();
		critter_class_name = "assignment4." + critter_class_name;
		try {				
			for (Critter crit : population) {
				if (Class.forName(critter_class_name).isInstance(crit)) {
					result.add(crit);
				}
			}
		}
		catch (Exception e){
			throw new InvalidCritterException(critter_class_name);
		}

		return result;
	}
	
	/**
	 * Prints out how many Critters of each type there are on the board.
	 * @param critters List of Critters.
	 */
	public static void runStats(List<Critter> critters) {
		System.out.print("" + critters.size() + " critters as follows -- ");
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string,  1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			System.out.print(prefix + s + ":" + critter_count.get(s));
			prefix = ", ";
		}
		System.out.println();		
	}
	
	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure that the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctly update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}
		
		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}
		
		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}
		
		protected int getX_coord() {
			return super.x_coord;
		}
		
		protected int getY_coord() {
			return super.y_coord;
		}
		

		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}
		
		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population 
		 * at either the beginning OR the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}

	/**
	 * Clear the world of all critters, dead and alive
	 */
	public static void clearWorld() {
		// Complete this method.
		population.clear();
		babies.clear();
	}
	
	public static void worldTimeStep() {
		
		//do time step for all critters
		for (Critter crit : population) {
			crit.doTimeStep();
		}
		
		
		
		//fight logic
		for (Critter crit : population) {
			List<Critter> fightList = checkOverlap(crit);
			while (fightList.size() > 1) {
				Critter A = fightList.get(0);
				Critter B = fightList.get(1);
				boolean A_fightResponse = A.fight(B.toString());
				boolean B_fightResponse = B.fight(A.toString());
				if (compareLocation(A, B) && 
						(A.energy > 0) && (B.energy > 0)) {
					int A_fightRoll = 0;
					int B_fightRoll = 0;
					if (A_fightResponse) {
						A_fightRoll = getRandomInt(A.energy);
					}
					if (B_fightResponse) {
						B_fightRoll = getRandomInt(B.energy);
					}
					if (B_fightRoll > A_fightRoll) {
						B.energy = B.energy + A.energy/2;
						A.energy = 0;
						fightList.remove(A);
					}
					else {
						A.energy = A.energy + B.energy/2;
						B.energy = 0;
						fightList.remove(B);
					}
				}
				
				else {
					if(!compareLocation(A,B)) {
						fightList.remove(B);
					}
					if(A.energy <= 0) {
						fightList.remove(A);
					}
					if(B.energy <= 0) {
						fightList.remove(B);
					}
					
				}
				
				
			}
			
			
			
		}
		
		
		
		
		//update rest energy
		for (Critter crit : population) {
			crit.energy = crit.energy - Params.rest_energy_cost;
		}
		
		
		//add algae to world
		for (int i = 0; i < Params.refresh_algae_count; i++) {
			try {
				makeCritter("Algae");
			}
			catch (InvalidCritterException e) {
				System.out.println("For some reason algae didn't get refreshed");
			}
		}
		
		
		// add the babies
		for(Critter baby : babies) {
			population.add(baby);
		}
		
		babies.clear();
		
		//cull dead critters
		for(int i = 0; i < population.size();) {
		       if (population.get(i).energy <= 0) {
		    	   population.remove(i);
		       }
		       else {
		    	   i++;
		       }
		 }
	}
	
	
	
	
	
	
	
	public static void displayWorld() {
		char[][] display = new char[Params.world_height][Params.world_width];
		for (Critter crit : population) {
			
			display[crit.y_coord][crit.x_coord] = crit.toString().charAt(0);
			List<Critter> overLapped=checkOverlap(crit);
			if(overLapped.size() > 1) {
				display[crit.y_coord][crit.x_coord] = Integer.toString(overLapped.size()).charAt(0);
			}
		}
		
		
		//prints the line at the top
		System.out.print("+");
		for (int i = 0; i < Params.world_width; i ++) {
			System.out.print("-");
		}
		System.out.println("+");
		
		
		//prints the middle of the display
		for(int i = 0; i < Params.world_height; i++) {
			System.out.print("|");
			for (int j = 0; j < Params.world_width; j++) {
				System.out.print(display[i][j]);
			}
			System.out.println("|");
		}
		
		
		//prints the line at the bottom
		System.out.print("+");
		for (int i = 0; i < Params.world_width; i ++) {
			System.out.print("-");
		}
		System.out.println("+");
		
	}
	
	
	
	
	
	private static void randPosition(Critter crit) {
		int x = getRandomInt(Params.world_width);
		int y = getRandomInt(Params.world_height);
		crit.x_coord = x;
		crit.y_coord = y;
	}
	
	
	
	
	
	//makes a list of critters in the same space including crit1
	private static List<Critter> checkOverlap(Critter crit1) {
		List<Critter> overlapping = new java.util.ArrayList<Critter>();
		overlapping.add(crit1);
		for (Critter crit2 : population) {
			if (compareLocation(crit1, crit2)) {
				overlapping.add(crit2);
			}
		}
		
		return overlapping;
		
	}
	
	
	
	//compares two critters to see if the are in the same location. does not compare critters if they are the same object
	private static boolean compareLocation(Critter crit1, Critter crit2) {
		if (!(crit1 == crit2)) {
			if (crit1.x_coord == crit2.x_coord && crit1.y_coord == crit2.y_coord) {
				return true;
			}
		}
		
		return false;
		
	}
	
}
