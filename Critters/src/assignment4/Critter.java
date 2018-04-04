package assignment4;
/* CRITTERS Critter.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * Donald Maze-England
 * dsm2588
 * 15465
 * Jennifer Sin
 * js45246
 * 15466
 * Slip days used: <0>
 * Fall Spring 2018
 */


import java.util.Iterator;
import java.util.List;

import javafx.scene.shape.Shape;

import java.lang.reflect.Method;


/* see the PDF for descriptions of the methods and fields in this class
 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private
 * no new public, protected or default-package code or data can be added to Critter
 */


public abstract class Critter {

	public static int number_of_directions = 8;
	private static String myPackage;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();
	private Shape critterShape;
	static View view;
	
	/* NEW FOR PROJECT 5 */
	public enum CritterShape {
		CIRCLE,
		SQUARE,
		TRIANGLE,
		DIAMOND,
		STAR
	}
	
	/* the default color is white, which I hope makes critters invisible by default
	 * If you change the background color of your View component, then update the default
	 * color to be the same as you background 
	 * 
	 * critters must override at least one of the following three methods, it is not 
	 * proper for critters to remain invisible in the view
	 * 
	 * If a critter only overrides the outline color, then it will look like a non-filled 
	 * shape, at least, that's the intent. You can edit these default methods however you 
	 * need to, but please preserve that intent as you implement them. 
	 */
	public javafx.scene.paint.Color viewColor() { 
		return javafx.scene.paint.Color.WHITE; 
	}
	
	public javafx.scene.paint.Color viewOutlineColor() { return viewColor(); }
	public javafx.scene.paint.Color viewFillColor() { return viewColor(); }
	
	public abstract CritterShape viewShape(); 
	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}
	
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	
	
	public static void passView(View viewPass) {
		view = viewPass;
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
	
	/**
	 * This is the walk function implements the movement of the critter in the world. It will take a 
	 * integer ranging from 0 to 7  i.	0 will be East, 1 will be Northeast, 2 will be North,
	 *  3 will be Northwest, 4 will be West, 5 will be Southwest, 6 will be South, and 7 will be Southeast.
	 *  This function will deduct the appropriate walk energy from the critter's energy level. The walk energy
	 *  cost is determined in the params class of constants 
	 * @param direction- will take a integer to determine the direction of the movement on a x-y plane 
	 */
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
	
	
	/**
	 * This method will implement the run movement on the critter. The run movement is two spaces 
	 * rather than the one space in the walk function.
	 * It will take an integer from 0 to 7 to determine the direction it will move by an increment of 2
	 * i.	0 will be East, 1 will be Northeast, 2 will be North, 3 will be Northwest, 4 will be West, 
	 * 5 will be Southwest, 6 will be South, and 7 will be Southeast
	 * This method will also subtract the appropriate amount of energy to for a run movement. The energy cost 
	 * will be determined by the params class of constants 
	 * @param direction takes an integer ranging from 0 to 7 to move in the corresponding direction 
	 */
	protected final void run(int direction) {
		this.walk(direction);
		this.walk(direction);
		
		this.energy = this.energy - Params.run_energy_cost + (2 * Params.walk_energy_cost);
	}
	
	
	
	/**
	 * This method is the reproduction function, it will construct a critter and set the location adjacent to the parent
	 * critter 
	 * It will also set the energy level the baby to half of the energy value of the parent
	 * The baby will be also added to the list of the babies to be accumulated during the world time step 
	 * The parent will also lose half of its energy 
	 * @param offspring - will the the critter type and new object passed in doTimeStep of each critter 
	 * @param direction - will be the direction integer ranging from 0 to 7 that will be adjacent to the parent critter 
	 *
	 */
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

	
	
	/**
	 * Each critter will invoke this method specific to that type of critter during the worldTimeStep 
	 */
	public abstract void doTimeStep();
	
	/**
	 * This method returns the fight decision when return true the critter will decide to fight, if return false 
	 * the critter is declining to fight. This occurs when there are more than one critter occupying the same space at a given
	 * time step invocation.  
	 * @param oponent - is the string representation of the critter's fight opponent 
	 * @return True to fight, false to decline fight 
	 */
	public abstract boolean fight(String oponent);
	
	/**
	 * create and initialize a Critter subclass.
	 * critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,
	 * an InvalidCritterException must be thrown.
	 * (Java weirdness: Exception throwing does not work properly if the parameter has lower-case instead of
	 * upper. For example, if craig is supplied instead of Craig, an error is thrown instead of
	 * an Exception.)
	 * @param critter_class_name, is the string representation of the type of critter
	 * @throws InvalidCritterException, if the string representation is not valid an exception will be thrown a
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {
		try {
		critter_class_name = myPackage +"." + critter_class_name;
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
	 * @return List of Critters- Is the the list of critters that are of a certain type. For example, 
	 * if Craig is passed the a list of all active Craig object types will be returned 
	 * @throws InvalidCritterException -if the string representation is not valid an exception will be thrown
	 */
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		List<Critter> result = new java.util.ArrayList<Critter>();
		critter_class_name = myPackage + "." + critter_class_name;
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
	 * This is the default runStats for all critters if a critter type has its own runStats
	 * function it will be called instead 
	 * @param critters List of Critters.- must pass a list of critters 
	 * it will output on the console the number of each critter type on the board 
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
	/**
	 * This method invokes the do time step function for every active critter on the board
	 * it also contains the fight logic. The fight logic determines the winner between two critters 
	 * Occupying the same space if they choose to fight, each critter will randomly generate a number between
	 * 0 and their energy lever. The critter with the larger number will win. In the case critter does not choose
	 * to fight, it will automatically roll a zero. 
	 * This method will also account for rest energy, baby list update, addition of algae and remove dead 
	 * critters at the end   
	 * 
	 */
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
	
	
	
	
	
	
	/**
	 * Will provide a visual representation of the critter world with each of the critters
	 * represented by their a character based on their type 
	 * example: craig will be represented as C, algae will be represented as A
	 * there is a boarder around the world. 
	 * When critters overlap the space occupied will contain an integer representing the number 
	 * of critters on the space 
	 */
	public static void displayWorld() {
		char[][] display = new char[Params.world_height][Params.world_width];
		for(int i = 0; i < Params.world_height; i++) {
			for (int j = 0; j < Params.world_width; j++) {
				display[i][j] = ' ';
			}
		}
		
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
		
		
		//*****
		//view object calls
		
		view.show();
		for(Critter crit:population) {
			view.paintCritter(crit, crit.x_coord, crit.y_coord);
		}
		
	}
	
	
	
	
	/**
	 * This method randomly sets a critters position in the world
	 * @param crit the critter to set the position of
	 */
	private static void randPosition(Critter crit) {
		int x = getRandomInt(Params.world_width);
		int y = getRandomInt(Params.world_height);
		crit.x_coord = x;
		crit.y_coord = y;
	}
	
	
	/**
	 * This method is to assist in checking spots for multiple critters 
	 * @param crit1 is the critter whose location that we are comparing the rest 
	 * of the population's location for every critter with the same location will be added to the list 
	 * the list will include the passed critter as well
	 * @return - a list of critters occupying the same spot as the passed critter.
	 */
	
	
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
	
	/**
	 * this method compares the location of two critters 
	 * @param crit1 is a critter 
	 * @param crit2 is a critter 
	 * @return true if the two critters passed into the the method
	 *  are occupying the same location and false otherwise
	 */
	
	//compares two critters to see if the are in the same location. does not compare critters if they are the same object
	private static boolean compareLocation(Critter crit1, Critter crit2) {
		if (!(crit1 == crit2)) {
			if (crit1.x_coord == crit2.x_coord && crit1.y_coord == crit2.y_coord) {
				return true;
			}
		}
		
		return false;
		
	}
	/**
	 * look method will pass a direction integer and a step represented by a boolean.
	 * If false one step is taken if true two steps are taken 
	 * @param direction
	 * @param steps
	 * @return
	 */
	protected final String look(int direction, boolean steps) {
		int tempXCoord = x_coord;
		int tempYCoord = y_coord;
		String onSiteCritters = "";
		if(!steps) {//false one step
			switch (direction) {
				case 0: tempXCoord = x_coord + 1;
						if(tempXCoord >= Params.world_width) {
							tempXCoord = 0;
						}
						break;
						
				case 1: tempXCoord++;
						tempYCoord--;
						if(tempXCoord >= Params.world_width && y_coord < 0) {
							tempXCoord = 0;
							tempYCoord = Params.world_height - 1;
						}
						if(tempXCoord >= Params.world_width) {
							tempXCoord = 0;
						}
						if(tempYCoord < 0) {
							tempYCoord = Params.world_height - 1;
						}
						break;
						
				case 2: tempYCoord--;
						if(tempYCoord < 0) {
							tempYCoord = Params.world_height - 1;
						}
						break;
						
				case 3:	tempXCoord--;
						tempYCoord--;
						if(tempXCoord < 0 && tempYCoord < 0) {
							tempXCoord = Params.world_width - 1;
							tempYCoord = Params.world_height - 1;
						}
						if(tempXCoord < 0) {
							tempXCoord = Params.world_width - 1;
						}
						if(tempYCoord < 0) {
							tempYCoord = Params.world_height - 1;
						}
						break;
				case 4: tempXCoord--;
						if(tempXCoord < 0) {
							tempXCoord = Params.world_width - 1;
						}
						break;
						
				case 5: tempXCoord--;
						tempYCoord++;
						if(tempXCoord < 0 && tempYCoord > Params.world_height-1) {
							tempXCoord = Params.world_width - 1;
							tempYCoord = 0;
						}
						if(tempXCoord < 0) {
							tempXCoord = Params.world_width - 1;
						}
						if(tempYCoord > Params.world_height-1) {
							tempYCoord = 0;
						}
						break;
						
				case 6: tempYCoord++;
						if(tempYCoord > Params.world_height-1) {
							tempYCoord = 0;
						}
						break;
						
				case 7: tempXCoord++;
						tempYCoord++;
						if(tempXCoord > Params.world_width-1 && tempYCoord > Params.world_height-1) {
							tempXCoord = 0;
							tempYCoord = 0;
						}
						if(tempXCoord > Params.world_width-1) {
							tempXCoord = 0;
						}
						if(tempYCoord > Params.world_height-1) {
							tempYCoord = 0;
						}
						break;
			}	
		} else { //true two steps
			switch (direction) {
			case 0: tempXCoord = x_coord + 2;
					if(tempXCoord >= Params.world_width) {
						tempXCoord = (tempXCoord%Params.world_width);
					}
					break;
					
			case 1: tempXCoord+=2;
					tempYCoord-=2;
					if(tempXCoord >= Params.world_width && tempYCoord < 0) {
						tempXCoord = (tempXCoord%Params.world_width);
						tempYCoord = Params.world_height + (tempXCoord%Params.world_height);// mod with neg is neg
					}
					if(tempXCoord >= Params.world_width) {
						tempXCoord = (tempXCoord%Params.world_width);
					}
					if(tempYCoord < 0) {
						tempYCoord = Params.world_height + (tempXCoord%Params.world_height);
					}
					break;
					
			case 2: tempYCoord--;
					if(tempYCoord < 0) {
						tempYCoord = Params.world_height + (tempXCoord%Params.world_height);
					}
					break;
					
			case 3:	tempXCoord--;
					tempYCoord--;
					if(tempXCoord < 0 && tempYCoord < 0) {
						tempXCoord = Params.world_width + (tempXCoord%Params.world_width);
						tempYCoord = Params.world_height + (tempXCoord%Params.world_height);
					}
					if(tempXCoord < 0) {
						tempXCoord = Params.world_width + (tempXCoord%Params.world_width);
					}
					if(tempYCoord < 0) {
						tempYCoord = Params.world_height + (tempXCoord%Params.world_height);
					}
					break;
			case 4: tempXCoord--;
					if(tempXCoord < 0) {
						tempXCoord = Params.world_width + (tempXCoord%Params.world_width);
					}
					break;
					
			case 5: tempXCoord--;
					tempYCoord++;
					if(tempXCoord < 0 && tempYCoord > Params.world_height-1) {
						tempXCoord = Params.world_width + (tempXCoord%Params.world_width);
						tempYCoord = tempYCoord%Params.world_height;
					}
					if(tempXCoord < 0) {
						tempXCoord = Params.world_width + (tempXCoord%Params.world_width);
					}
					if(tempYCoord > Params.world_height-1) {
						tempYCoord = tempYCoord%Params.world_height;
					}
					break;
					
			case 6: tempYCoord++;
					if(tempYCoord > Params.world_height-1) {
						tempYCoord = tempYCoord%Params.world_height;
					}
					break;
					
			case 7: tempXCoord++;
					tempYCoord++;
					if(tempXCoord > Params.world_width-1 && tempYCoord > Params.world_height-1) {
						tempXCoord = (tempXCoord%Params.world_width);
						tempYCoord = tempYCoord%Params.world_height;
					}
					if(tempXCoord > Params.world_width-1) {
						tempXCoord = (tempXCoord%Params.world_width);
					}
					if(tempYCoord > Params.world_height-1) {
						tempYCoord = tempYCoord%Params.world_height;
					}
					break;
				}
		
		}
		
		//checking to see if any critters are on the same spot selected 
		for (Critter crit : population) {
			if(crit.x_coord ==tempXCoord && crit.y_coord == tempYCoord) {
				onSiteCritters.concat(crit.toString());
			}
		}
		//checking for empty string
		if(onSiteCritters.equals("")) {
			onSiteCritters = "NULL";
		}
		
		return onSiteCritters;}
	
}
