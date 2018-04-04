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
	Stage textStage;
	FlowPane textPane;
	Scene textScene;
	TextArea textArea;
	
	Canvas critterCanvas;
	
	static int spriteScaler=15;
	static int num_rows = Params.world_width;
	static int num_cols = Params.world_height;
	static int size = 15;
	
	
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
					0.0 , 0.0,
					0.0 , size-1.0,
					(size-1.0)/2 , size-1.0, 	
			});
			triangle.setFill(crit.viewFillColor());
			triangle.setStroke(crit.viewOutlineColor());
			gridPane.add(triangle, x, y);
			break;
			
			case DIAMOND : Polygon diamond = new Polygon();
			diamond.getPoints().addAll(new Double[] {
				0.0 , (size-1)/2.0,
				(size-1.0)/2.0 , 0.0,
				(size-1.0) , (size-1.0)/2,
				(size-1.0)/2.0 , (size-1.0),
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
