package assignment4;
/* CRITTERS Main.java
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

import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.event.ActionEvent;



/*
 * Usage: java <pkgname>.Main <input file> test
 * input file is optional.  If input file is specified, the word 'test' is optional.
 * May not use 'test' argument without specifying input file.
 */
public class Main extends Application {

    static Scanner kb;	// scanner connected to keyboard input, or input file
    private static String inputFile;	// input file, used instead of keyboard input if specified
    static ByteArrayOutputStream testOutputString;	// if test specified, holds all console output
    private static String myPackage;	// package of Critter file.  Critter cannot be in default pkg.
    private static boolean DEBUG = false; // Use it or not, as you wish!
    static PrintStream old = System.out;	// if you want to restore output to console
    View view;
	static boolean animationFlag = false;
	static String statsCritter;
	static int animateSpeed;



    // Gets the package name.  The usage assumes that Critter and its subclasses are all in the same package.
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    
    public void animate(int frameNum, String critterStats) {
    	if (animationFlag) {
    	
	    	for (int i = 0; i < frameNum; i++) {
				Critter.worldTimeStep();
			}
			
			Critter.displayWorld();
			try {
				List<Critter> listOfCrits = Critter.getInstances(critterStats);
				String critter_class_name;
				critter_class_name = "assignment4." + critterStats;
				Class<?> c = Class.forName(critter_class_name);
				Method method = c.getMethod("runStats", List.class  );
				view.addText(method.invoke(c, listOfCrits).toString());
				System.out.print(method.invoke(c, listOfCrits));
				
			}
	
			catch( InvalidCritterException e2)
			{
				System.out.println("error processing: " + critterStats);
				view.addText("error processing: " + critterStats);
			}
			catch (NoSuchMethodException e2) {
				System.out.println("error processing: " + critterStats);
				view.addText("error processing: " + critterStats);
			}
			catch (ClassNotFoundException e2) {
				System.out.println("error processing: " + critterStats);
				view.addText("error processing: " + critterStats);
			}
			catch (InvocationTargetException e2) {
				System.out.println("error processing: " + critterStats);
				view.addText("error processing: " + critterStats);
			} 
			catch (IllegalAccessException e2) {
				System.out.println("error processing: " + critterStats);
				view.addText("error processing: " + critterStats);
			} 
			catch (IllegalArgumentException e2) {
				System.out.println("error processing: " + critterStats);
				view.addText("error processing: " + critterStats);
			}
    	}
    }

    
    
    public void start(Stage stage) {
    	view = new View(stage);
    	Timeline timeline = new Timeline(new KeyFrame(
    	        Duration.millis(1000),
    	        ae -> animate(animateSpeed, statsCritter)));
    	timeline.setCycleCount(Animation.INDEFINITE);
    	timeline.play();
    	
    	
    	
    	Button showBtn = new Button("show");
    	showBtn.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent e) {
    			
    			Critter.displayWorld();
    		}
    	});
    	
    	
    	Button seedBtn = new Button("seed");
    	seedBtn.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent e) {
    			Stage seedStage = new Stage();
    			FlowPane seedPane = new FlowPane();
    			Scene seedScene = new Scene(seedPane, 50,200);
    			TextField seedTextField = new TextField("seed:");
    			seedPane.getChildren().add(seedTextField);
    			
    			seedStage.setScene(seedScene);
    			seedStage.show();
    			
    			seedTextField.setOnAction(new EventHandler<ActionEvent>() {
    				@Override
    				public void handle(ActionEvent e) {
    					int seedNum = Integer.parseInt( seedTextField.getText() );
    					System.out.println(seedNum);
    	    			Critter.setSeed(seedNum);
    	    			seedStage.close();
    				}
    			});
    			
    		}
    	});
    	
    	
    	Button quitBtn = new Button("quit");
    	quitBtn.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent e) {
    			System.exit(0);
    		}
    	});
    	
    	
    	
    	
    	Button statsBtn = new Button("stats");
    	statsBtn.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle (ActionEvent e) {
    			Stage statsStage = new Stage ();
				FlowPane statsPane = new FlowPane();
				Scene statsScene = new Scene (statsPane, 50, 200);
				Button showStatsBtn = new Button("show stats");
				TextField statsText = new TextField("Type of Critter: ");
	
				statsPane.getChildren().add(statsText);
				statsPane.getChildren().add(showStatsBtn);
				statsStage.setScene(statsScene);
				statsStage.show();
				
				showStatsBtn.setOnAction(new EventHandler <ActionEvent>(){
					@Override
					public void handle (ActionEvent e) {
						try{
		        			
		    				List<Critter> listOfCrits = Critter.getInstances(statsText.getText());
		    				String critter_class_name;
		    				
		    				critter_class_name = "assignment4." + statsText.getText();
		    				Class<?> c = Class.forName(critter_class_name);
		    				Method method = c.getMethod("runStats", List.class);
		    				view.addText(method.invoke(c, listOfCrits).toString());
		    				System.out.print(method.invoke(c, listOfCrits));
		    				statsStage.close();
		    			
		        		}
		        		catch( InvalidCritterException e2)
		        		{
		        			System.out.println("error processing: " + statsText.getText());
		        			view.addText("error processing: " + statsText.getText());
		    				statsStage.close();
		        		}
		        		catch (NoSuchMethodException e2) {
		        			System.out.println("error processing: " + statsText.getText());
		        			view.addText("error processing: " + statsText.getText());
		    				statsStage.close();
		        		}
		        		catch (ClassNotFoundException e2) {
		        			System.out.println("error processing: " + statsText.getText());
		        			view.addText("error processing: " + statsText.getText());
		    				statsStage.close();
		        		}
		        		catch (InvocationTargetException e2) {
		        			System.out.println("error processing: " + statsText.getText());
		        			view.addText("error processing: " + statsText.getText());
		    				statsStage.close();
		        		} 
		        		catch (IllegalAccessException e2) {
							System.out.println("error processing: " + statsText.getText());
							view.addText("error processing: " + statsText.getText());
		    				statsStage.close();
						} 
		        		catch (IllegalArgumentException e2) {
							System.out.println("error processing: " + statsText.getText());
							view.addText("error processing: " + statsText.getText());
		    				statsStage.close();
						}
					}
				});
    		}	
    	});
    	
    	
    	
    	
    	Button makeBtn = new Button("make");
    	makeBtn.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle (ActionEvent e) {			
				Stage makeStage = new Stage ();
				FlowPane makePane = new FlowPane();
				Scene makeScene = new Scene (makePane, 50, 200);
				Button spawnBtn = new Button("spawn");
				TextField makeCritterField = new TextField("Type of Critter: ");
				TextField makeTextField  = new TextField("Number of Critters: ");
	
				makePane.getChildren().add(makeTextField);
				makePane.getChildren().add(makeCritterField);
				makePane.getChildren().add(spawnBtn);
				makeStage.setScene(makeScene);
				makeStage.show();
				
				spawnBtn.setOnAction(new EventHandler <ActionEvent>(){
					@Override
					public void handle (ActionEvent e) {
						try {
							int makeNum = Integer.parseInt( makeTextField.getText());
							System.out.println (makeNum);
							
	        					for (int i = 0; i < makeNum; i++) {
	            					Critter.makeCritter(makeCritterField.getText());
	        					}
							makeStage.close();
						}
						catch (InvalidCritterException e2) {
							System.out.println("error processing: " + makeCritterField.getText());
							view.addText("error processing: " + makeCritterField.getText());
							makeStage.close();
						}
					}
					
				});
    		}
    	});


    	Button stepBtn = new Button("step");
    	stepBtn.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent e) {
    			Stage stepStage = new Stage();
    			FlowPane stepPane = new FlowPane();
    			Scene stepScene = new Scene(stepPane, 50,200);
    			TextField stepTextField = new TextField("number of steps:");
    			stepPane.getChildren().add(stepTextField);
    			
    			stepStage.setScene(stepScene);
    			stepStage.show();
    			
    			stepTextField.setOnAction(new EventHandler<ActionEvent>() {
    				@Override
    				public void handle(ActionEvent e) {
    					int stepNum = Integer.parseInt( stepTextField.getText() );
    					System.out.println(stepNum);
    					for (int i = 0; i < stepNum; i++) {
    						Critter.worldTimeStep();
    					}
    	    			stepStage.close();
    				}
    			});
    			
    		}
    	});
    	
    	
    	
    	Button animateBtn = new Button("animate");
    	animateBtn.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle (ActionEvent e) {	
				Stage animateStage = new Stage ();
				FlowPane animatePane = new FlowPane();
				Scene animateScene = new Scene (animatePane, 200, 200);
				Button startBtn = new Button("start");
				Button stopBtn = new Button("stop");
				TextField animateTextField  = new TextField("Number of steps per frame: ");
				TextField critterStats = new TextField("show stats of this critter:");
	
				animatePane.getChildren().add(animateTextField);
				animatePane.getChildren().add(critterStats);
				animatePane.getChildren().add(startBtn);
				animatePane.getChildren().add(stopBtn);
				animateStage.setScene(animateScene);
				animateStage.show();
				
				startBtn.setOnAction(new EventHandler <ActionEvent>(){
					@Override
					public void handle (ActionEvent e) {
						animationFlag = true;
						view.closeControls();
						
						animateSpeed = Integer.parseInt( animateTextField.getText());
						statsCritter = critterStats.getText();
								
					}
					
				});
				
				stopBtn.setOnAction(new EventHandler <ActionEvent>(){
					@Override
					public void handle (ActionEvent e) {
						animationFlag = false;
						animateStage.close();
						view.openControls();
					}
					
				});
				
    		}
    	});

    	
    	
    	
    	view.addButton(showBtn);
    	view.addButton(quitBtn);
    	view.addButton(seedBtn);
    	view.addButton(statsBtn);
    	view.addButton(makeBtn);
    	view.addButton(stepBtn);
    	view.addButton(animateBtn);
    	Critter.passView(view);
    	Critter.displayWorld();
    	view.show();
    }
    
    
    
    /**
     * Main method.
     * @param args args can be empty.  If not empty, provide two parameters -- the first is a file name, 
     * and the second is test (for test output, where all output to be directed to a String), or nothing.
     */
    public static void main(String[] args) { 
        if (args.length != 0) {
            try {
                inputFile = args[0];
                kb = new Scanner(new File(inputFile));			
            } catch (FileNotFoundException e) {
                System.out.println("USAGE: java Main OR java Main <input file> <test output>");
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("USAGE: java Main OR java Main <input file>  <test output>");
            }
            if (args.length >= 2) {
                if (args[1].equals("test")) { // if the word "test" is the second argument to java
                    // Create a stream to hold the output
                    testOutputString = new ByteArrayOutputStream();
                    PrintStream ps = new PrintStream(testOutputString);
                    // Save the old System.out.
                    old = System.out;
                    // Tell Java to use the special stream; all console output will be redirected here from now
                    System.setOut(ps);
                }
            }
        } else { // if no arguments to main
            kb = new Scanner(System.in); // use keyboard and console
        }

        /* Do not alter the code above for your submission. */
        /* Write your code below. */
        
    	Application.launch(args);
    	
    	
    	/*
    	System.out.print("critters>");
        
        String input = kb.nextLine();
        
        
        //main while loops that runs until the quit command is entered
        while (!input.equals("quit")) {
        	String[] parsedString = input.split("\\s+");
        	
        	
        	//show command
        	if(parsedString[0].equals("show")) {
        		if (parsedString.length <= 1) {
            		Critter.displayWorld();
            		//view.show();
        		}
        		else {
        			System.out.println("error processing: " + input);
        		}
        	}
        	
        	//step command
        	else if(parsedString[0].equals("step")) {
        		if (parsedString.length <= 2) {
	        		if (parsedString.length > 1) {
		        		if(stringIsInt(parsedString[1])) {
		        			for (int i = 0; i < Integer.parseInt(parsedString[1]); i++) {
		        				Critter.worldTimeStep();
		        			}
		        		}
		        		
		        		else {
		        			System.out.println("error processing: " + input);
		        		}
		        		
	        		}
	        		
	        		else {
	        			Critter.worldTimeStep();
	        		}
        		}
        		
        		else {
        			System.out.println("error processing: " + input);
        		}
        		
        	}
        	
        	
        	
        	//seed command
        	else if (parsedString[0].equals("seed")) {
        		if(parsedString.length <= 2) {
	        		if(stringIsInt(parsedString[1])) {
	        			Critter.setSeed(Integer.parseInt(parsedString[1]));
	        		}
	        		else {
	        			System.out.println("error processing: " + input);
	        		}
        		}
        		else {
        			System.out.println("error processing: " + input);
        		}
        	}
        	
        	
        	
        	
        	//make command
        	else if(parsedString[0].equals("make")) {
        		//make sure command has the right number of arguments
        		if(parsedString.length <= 3 && parsedString.length > 1) {
        			
        			//if there is no number argument
        			if (parsedString.length <= 2) {
        				try {
        					Critter.makeCritter(parsedString[1]);
        				}
        				catch (InvalidCritterException e) {
                			System.out.println("error processing: " + input);
        				}
        			}
        			
        			//if there is a number argument
        			else {
        				if (stringIsInt(parsedString[2])) {
        					int numCrittersToMake = Integer.parseInt(parsedString[2]);
        					try {
	        					for (int i = 0; i < numCrittersToMake; i++) {
	            					Critter.makeCritter(parsedString[1]);
	        					}
        					}
        					catch (InvalidCritterException e) {
                    			System.out.println("error processing: " + input);
            				}
        				}
        				else {
                			System.out.println("error processing: " + input);
                		}
        			}
        			
        		}
        		
        		else {
        			System.out.println("error processing: " + input);
        		}
        	}
        	
        	
        	
        	//stats command
        	else if(parsedString[0].equals("stats") && parsedString.length == 2) {
        		try{
        			
    				List<Critter> listOfCrits = Critter.getInstances(parsedString[1]);
    				String critter_class_name;
    				
    				critter_class_name = "assignment4." + parsedString[1];
    				Class<?> c = Class.forName(critter_class_name);
    				Method method = c.getMethod("runStats", List.class  );
    				method.invoke(c, listOfCrits);
    				
    			
        		}
        		catch( InvalidCritterException e)
        		{
        			System.out.println("error processing: " + input);
        		}
        		catch (NoSuchMethodException e) {
        			System.out.println("error processing: " + input);
        		}
        		catch (ClassNotFoundException e) {
        			System.out.println("error processing: " + input);
        		}
        		catch (InvocationTargetException e) {
        			System.out.println("error processing: " + input);
        		} 
        		catch (IllegalAccessException e) {
					System.out.println("error processing: " + input);
				} 
        		catch (IllegalArgumentException e) {
					System.out.println("error processing: " + input);
				}
       
        		
        	}
        	
        	//if the command entered is not in the library of commands
        	else {
        		System.out.println("invalid command: " + input);
        	
        	}
        	
        	
            System.out.print("critters>");
            input = kb.nextLine();
        }
        
        
        
        
        
        
        // System.out.println("GLHF");
        
        /* Write your code above */
        //System.out.flush();

        
    }
    
    
    /**
     * helper function
     * @param str if the string is an int 
     * @return true if the string is an in ranging from 0 to 9 
     */
    public static boolean stringIsInt(String str) {
    	for (int i = 0 ; i < str.length(); i++) {
    		if(str.charAt(i) < '0' || str.charAt(i) > '9') {
    			return false;
    		}
    	}
    	return true;
    }
    
    
}
