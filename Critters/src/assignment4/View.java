package assignment4;

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

public class View{
	
	Stage primaryStage;
	Stage controlStage;
	GridPane gridPane;
	VBox controlPane;
	Scene primaryScene;
	Scene controlScene;
	
	Canvas critterCanvas;
	
	static int spriteScaler=10;
	static int num_rows = Params.world_width;
	static int num_cols = Params.world_height;
	static int size = 10;
	
	
	public View(Stage stage) {
		primaryStage = stage;
		controlStage = new Stage();
		controlPane = new VBox();
		gridPane = new GridPane();
		primaryScene = new Scene(gridPane, num_rows*spriteScaler, num_cols*spriteScaler);
		controlScene = new Scene(controlPane, 100, 200);
		primaryStage.setTitle("critters");
		primaryStage.setScene(primaryScene);
		controlStage.setTitle("controls");
		controlStage.setScene(controlScene);		
	}
	
	
	private static void paintGridLines(GridPane grid) {
		for (int r = 0; r < num_rows; r++) {
			for (int c = 0; c < num_cols; c++) {
				Shape s = new Rectangle (size,size);
				s.setFill(null);;
				s.setStroke(Color.BLACK);
				grid.add(s, c, r);
			}
		}
	}
	
	
	public void addButton(Button btn) {
		controlPane.getChildren().add(btn);
	}
	
	public void addComboBox(ComboBox box) {
		controlPane.getChildren().add(box);
	}
	
	
	public void show() {
		gridPane.getChildren().clear();
		paintGridLines(gridPane);
		primaryStage.show();
		controlStage.show();
	}
	
	public void paintCritter(Critter crit, int x, int y) {
		
		switch(crit.viewShape()) {
				
			case CIRCLE : Shape circle = new Circle(5);
			circle.setFill(crit.viewFillColor());
			circle.setStroke(crit.viewOutlineColor());
			gridPane.add(circle, x, y);
			break;
			
			case SQUARE: Shape square = new Rectangle(5,5);
			square.setFill(crit.viewFillColor());
			square.setStroke(crit.viewOutlineColor());
			gridPane.add(square, x, y);
			break;
			
			case TRIANGLE : Polygon triangle = new Polygon ();
			triangle.getPoints().addAll(new Double[]{
					0.0 , size-3.0,
					size-3.0 , size-3.0,
					(size-3.0)/2 , 0.0, 	
			});
			triangle.setFill(crit.viewFillColor());
			triangle.setStroke(crit.viewOutlineColor());
			gridPane.add(triangle, x, y);
			break;
			
			case DIAMOND : Polygon diamond = new Polygon();
			Double resize =(double) size;
			diamond.getPoints().addAll(new Double[] {	
				((resize*0.9)/2) , resize-resize,
				(resize*0.9), ((resize*0.9)/2),
				((resize*0.9)/2.0), resize*0.9,
				resize-resize , ((resize*0.9)/2.0),
				
			});
			diamond.setFill(crit.viewFillColor());
			diamond.setStroke(crit.viewOutlineColor());
			gridPane.add(diamond, x, y);
			
			case STAR : Polygon star = new Polygon();
			star.getPoints().addAll(new Double [] {
					0.0 , 0.6*size,
					0.3*size , 0.4*size,
					0.2*size , 0.0,
					0.5*size , 0.2*size,
					0.8*size , 0.0,
					0.7*size , 0.4*size,
					1.0*size , 0.6*size,
					0.6*size , 0.6*size,
					0.4*size , 0.6*size, 
					0.5*size , size*1.0,	
			});
			star.setFill(crit.viewFillColor());
			star.setStroke(crit.viewOutlineColor());
			gridPane.add(star, x, y);
			
		}
	}
	
	
}
