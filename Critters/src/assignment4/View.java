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
	Scene scene;
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
		scene = new Scene(gridPane, num_rows*spriteScaler, num_cols*spriteScaler);
		controlScene = new Scene(controlPane, 100, 200);
		primaryStage.setTitle("critters");
		primaryStage.setScene(scene);
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
			
			
		}
	}
	
	
}
