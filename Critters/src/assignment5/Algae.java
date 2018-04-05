package assignment5;

import java.util.List;

import assignment5.Critter.CritterShape;
import assignment5.Critter.TestCritter;

public class Algae extends TestCritter {

	public String toString() { return "@"; }
	
	public boolean fight(String not_used) { return false; }
	
	public void doTimeStep() {
		setEnergy(getEnergy() + Params.photosynthesis_energy_amount);
	}
	@Override
	public CritterShape viewShape() {
		
		return CritterShape.CIRCLE;
	}
	@Override
	public javafx.scene.paint.Color viewFillColor(){
		return javafx.scene.paint.Color.GREEN;
	}
	
	@Override
	public javafx.scene.paint.Color viewOutlineColor(){
		return javafx.scene.paint.Color.GREEN;
	}
	
	public static String runStats(List<Critter> algaeList) {
		return "There are currently: " + algaeList.size() + " Algaes' represented by green circles \n";
		
	}
}
