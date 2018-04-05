package assignment5;

import java.util.List;

import javafx.application.*;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * The view class stores the javafx objects needed to display the world. It has several functions to update these object outlined in the further comments
 * @author Don and Jen
 */
public class View{
	
	Stage primaryStage;	//the stage for the grid
	Stage controlStage;	//the stage for the controls
	GridPane gridPane;	//the pane for the grid
	VBox controlPane;	//the pane for the controls
	Scene primaryScene;	//the scene for the grid
	Scene controlScene;	//the scene for the controls
	Stage textStage;	//the stage for the output text
	FlowPane textPane;	//the pane for the output text
	Scene textScene;	//the scene for the output text
	TextArea textArea;	//the textArea for the output text
	
	
	static int num_rows = Params.world_width;
	static int num_cols = Params.world_height;
	static int size = 750/(num_rows);	//the size scaler of the world
	
	
	/**
	 * constructor for the view object
	 * @param stage is set to the primary stage local object used for the grid
	 */
	public View(Stage stage) {
		primaryStage = stage;
		gridPane = new GridPane();
		//primaryScene = new Scene(gridPane, 500, 500);
		primaryScene = new Scene(gridPane, num_rows*size, num_cols*size);
		primaryStage.setTitle("critters");
		primaryStage.setScene(primaryScene);
		
		controlStage = new Stage();
		controlPane = new VBox();
		controlScene = new Scene(controlPane, 200, 200);
		controlStage.setTitle("controls");
		controlStage.setScene(controlScene);		
		controlStage.show();
		
		textStage = new Stage();
		textPane = new FlowPane();
		textScene = new Scene(textPane, 400, 200);
		textArea = new TextArea();
		textPane.getChildren().add(textArea);
		textStage.setTitle("Program Output:");
		textStage.setScene(textScene);
		textStage.show();
	}
	
	
	/**
	 * Paints a grid onto the gridPane passed in the grid parameter. The size of the grid is dependent on Size
	 * @param grid	the GridPane to draw the grid on
	 */
	private static void paintGridLines(GridPane grid) {
		for (int r = 0; r < num_rows; r++) {
			for (int c = 0; c < num_cols; c++) {
				Shape s = new Rectangle (size,size);
				s.setFill(null);
				s.setStroke(Color.BLACK);
				grid.add(s, c, r);
			}
		}
	}
	
	
	/**
	 * adds a button to the control pane
	 * @param btn the button to add to the pane
	 */
	public void addButton(Button btn) {
		controlPane.getChildren().add(btn);
	}
	
	
	/**
	 * resets the primary stage and repaints the grid
	 */
	public void show() {
		gridPane.getChildren().clear();
		paintGridLines(gridPane);
		primaryStage.show();
	}
	
	
	/**
	 * paints the critter passed to the function at the desired x and y locations
	 * @param crit	the critter to paint
	 * @param x	the desired x location
	 * @param y	the desired y location
	 */
	public void paintCritter(Critter crit, int x, int y) {
		
		switch(crit.viewShape()) {
				
			case CIRCLE : Shape circle = new Circle(size/2);
			circle.setFill(crit.viewFillColor());
			circle.setStroke(crit.viewOutlineColor());
			gridPane.add(circle, x, y);
			break;
			
			case SQUARE: Shape square = new Rectangle(size/2,size/2);
			square.setFill(crit.viewFillColor());
			square.setStroke(crit.viewOutlineColor());
			gridPane.add(square, x, y);
			break;
			
			case TRIANGLE : Polygon triangle = new Polygon ();
			triangle.getPoints().addAll(new Double[]{
					0.0 , size-0.0,
					size-0.0 , size-0.0,
					size/2.0 , 0.0, 	
			});
			triangle.setFill(crit.viewFillColor());
			triangle.setStroke(crit.viewOutlineColor());
			triangle.setStrokeWidth(0);
			gridPane.add(triangle, x, y);
			break;
			
			case DIAMOND : Polygon diamond = new Polygon();
			Double resize =(double) size;
			resize = resize*1.0;
			diamond.getPoints().addAll(new Double[] {	
				(resize/2) , 0.2,
				resize, (resize/2),
				(resize/2), resize,
				0.2 , (resize/2),
				
			});
			diamond.setFill(crit.viewFillColor());
			diamond.setStroke(crit.viewOutlineColor());
			diamond.setStrokeWidth(0);
			gridPane.add(diamond, x, y);
			break; 
			
			case STAR : Polygon star = new Polygon();
			Double reSize = (double)size;
			reSize = reSize*1.0;
			star.getPoints().addAll(new Double [] {
					0.2 , 0.6*reSize,
					0.3*reSize , 0.4*reSize,
					0.2*reSize , 0.5,
					0.5*reSize , 0.2*reSize,
					0.8*reSize , 0.5,
					0.7*reSize , 0.4*reSize,
					reSize , 0.6*reSize,
					0.6*reSize , 0.6*reSize,
					0.5*reSize , reSize,
					0.4*reSize , 0.6*reSize, 
						
			});
			star.setFill(crit.viewFillColor());
			star.setStroke(crit.viewOutlineColor());
			star.setStrokeWidth(0);
			gridPane.add(star, x, y);
			
		}
	}
	
	/**
	 * Closes the control stage so users cannot change anything during animation
	 */
	public void closeControls() {
		controlStage.close();
	}
	
	/**
	 * re-opens the control stage after animation
	 */
	public void openControls() {
		controlStage.show();
	}
	
	/**
	 * adds the string s to the output text box
	 * @param s the string to display
	 */
	public void addText(String s) {
		textArea.appendText(s);
	}
	
}
