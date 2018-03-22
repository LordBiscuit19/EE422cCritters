package assignment4;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import assignment4.Critter;
import assignment4.InvalidCritterException;

import org.junit.Test;

public class CrittersJunit {

	
	
	@Test
	public void makeCritterTest() {
		try {
			Critter.makeCritter("Craig");
		}
		catch(InvalidCritterException e){
			fail("invlaid critter, test failed");
		}
	}
	
	
	
	
	
	@Test
	public void getInstancesTest() {
		try {
			Critter.makeCritter("Craig");
			Critter.makeCritter("Craig");
			Critter.makeCritter("Craig");
			Critter.makeCritter("Algae");
			List<Critter> newList = Critter.getInstances("Craig");
			
			
			assertEquals(4, newList.size());
			newList = Critter.getInstances("Algae");
			assertEquals(1, newList.size());
		}
		catch(InvalidCritterException e){
			fail("invlaid critter, test failed");
		}
	}

	
	@Test
	public void displayWorldTest() {
		try {
			Critter.makeCritter("Craig");
			Critter.makeCritter("Craig");
			Critter.makeCritter("Craig");
			Critter.makeCritter("Algae");
			List<Critter> newList = Critter.getInstances("Craig");
			for (Critter crit : newList) {
				System.out.print(crit);
			}
			System.out.println();
			
			Critter.displayWorld();
		}
		
		catch (InvalidCritterException e) {
			fail("invlaid critter, test failed");

		}
	}
	
}
