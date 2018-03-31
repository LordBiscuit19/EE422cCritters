package assignment4;

import javafx.application.*;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class View{
	
	Stage primaryStage;
	Stage controlStage;
	GridPane gridPane;
	FlowPane controlPane;
	Scene scene;
	Scene controlScene;
	static int num_rows = 20;
	static int num_cols = 20;
	static int size = 600 / num_rows;
	
	public View(Stage stage) {
		primaryStage = stage;
		controlStage = new Stage();
		controlPane = new FlowPane();
		gridPane = new GridPane();
		scene = new Scene(gridPane, 600, 600);
		controlScene = new Scene(controlPane, 300, 300);
		primaryStage.setTitle("critters");
		primaryStage.setScene(scene);
		controlStage.setTitle("controls");
		controlStage.setScene(controlScene);
		paintGridLines(gridPane);
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
		primaryStage.show();
		controlStage.show();
	}
	
	
}
