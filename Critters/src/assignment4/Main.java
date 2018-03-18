package assignment4;
/* CRITTERS Main.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Fall 2016
 */

import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/*
 * Usage: java <pkgname>.Main <input file> test
 * input file is optional.  If input file is specified, the word 'test' is optional.
 * May not use 'test' argument without specifying input file.
 */
public class Main {

    static Scanner kb;	// scanner connected to keyboard input, or input file
    private static String inputFile;	// input file, used instead of keyboard input if specified
    static ByteArrayOutputStream testOutputString;	// if test specified, holds all console output
    private static String myPackage;	// package of Critter file.  Critter cannot be in default pkg.
    private static boolean DEBUG = false; // Use it or not, as you wish!
    static PrintStream old = System.out;	// if you want to restore output to console


    // Gets the package name.  The usage assumes that Critter and its subclasses are all in the same package.
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
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
        
        System.out.print("critters>");
        
        String input = kb.nextLine();
        
        
        
        
        while (!input.equals("quit")) {
        	String[] parsedString = input.split("\\s+");
        	
        	
        	//show command
        	if(parsedString[0].equals("show")) {
        		if (parsedString.length <= 1) {
            		Critter.displayWorld();
        		}
        		else {
        			System.out.println("invalid command: " + input);
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
		        			System.out.println("invalid command: " + input);
		        		}
		        		
	        		}
	        		
	        		else {
	        			Critter.worldTimeStep();
	        		}
        		}
        		
        		else {
        			System.out.println("invalid command: " + input);
        		}
        		
        	}
        	
        	
        	
        	//seed command
        	else if (parsedString[0].equals("seed")) {
        		if(parsedString.length <= 2) {
	        		if(stringIsInt(parsedString[1])) {
	        			Critter.setSeed(Integer.parseInt(parsedString[1]));
	        		}
	        		else {
	        			System.out.println("invalid command: " + input);
	        		}
        		}
        		else {
        			System.out.println("invalid command: " + input);
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
        			}
        			
        		}
        		
        		else {
        			System.out.println("invalid command: " + input);
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
        System.out.flush();

    }
    
    
    
    public static boolean stringIsInt(String str) {
    	for (int i = 0 ; i < str.length(); i++) {
    		if(str.charAt(i) < '0' || str.charAt(i) > '9') {
    			return false;
    		}
    	}
    	return true;
    }
    
    
}
