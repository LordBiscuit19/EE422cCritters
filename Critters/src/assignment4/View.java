package assignment4;

import java.util.List;

import javafx.application.*;
import javafx.beans.property.ReadOnlyDoubleProperty;
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
	Stage textStage;
	FlowPane textPane;
	Scene textScene;
	TextArea textArea;
	
	Canvas critterCanvas;
	
<<<<<<< HEAD
	//ReadOnlyDoubleProperty<Double> doubleSize = new ReadOnlyDoubleProperty<Double>();
	
	static int spriteScaler=20;
	static int num_rows = Params.world_width;
	static int num_cols = Params.world_height;
	static int size = 20;
=======
	static int spriteScaler=15;
	static int num_rows = Params.world_width;
	static int num_cols = Params.world_height;
	static int size = 15;
>>>>>>> ac45dd860dac671efc0eba540880abdd37870c8f
	
	
	public View(Stage stage) {
		primaryStage = stage;
		gridPane = new GridPane();
		primaryScene = new Scene(gridPane, num_rows*spriteScaler, num_cols*spriteScaler);
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
	
	
	private static void paintGridLines(GridPane grid) {
		for (int r = 0; r < num_rows; r++) {
			for (int c = 0; c < num_cols; c++) {
				Shape s = new Rectangle (size,size);
				s.setFill(null);
				s.setStrokeWidth(1);
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
	}
	
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
			resize=(double) size;
			diamond.getPoints().addAll(new Double[] {	
				((resize)/2) , 0.0,
				(resize), ((resize)/2),
				((resize)/2.0), resize,
				0.0 , ((resize)/2.0),
				
			});
			diamond.setFill(crit.viewFillColor());
			diamond.setStroke(crit.viewOutlineColor());
		
			gridPane.add(diamond, x, y);
			
			case STAR : Polygon star = new Polygon();
			Double resizeStar =(double) size;
			resizeStar = 40.0;
			
			star.getPoints().addAll(new Double [] {
					0.0 , 0.6*resizeStar,
					0.3*resizeStar , 0.4*resizeStar,
					0.2*resizeStar , 0.0,
					0.5*resizeStar , 0.2*resizeStar,
					0.8*resizeStar , 0.0,
					0.7*resizeStar , 0.4*resizeStar,
					1.0*resizeStar , 0.6*resizeStar,
					0.6*resizeStar , 0.6*resizeStar,
					0.4*resizeStar , 0.6*resizeStar, 
					0.5*resizeStar , resizeStar*1.0,	
			});
			star.setFill(crit.viewFillColor());
			star.setStroke(crit.viewOutlineColor());
			gridPane.add(star, x, y);
			
		}
	}
	
	public void closeControls() {
		controlStage.close();
	}
	
	public void openControls() {
		controlStage.show();
	}
	
	
	public void addText(String s) {
		textArea.appendText(s);
	}
	
}
