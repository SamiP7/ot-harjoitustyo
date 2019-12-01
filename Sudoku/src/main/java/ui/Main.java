package ui;

import logic.*;
import java.util.*;

public class Main {
    
    static private boolean done = false;
    static private boolean correctAnswer = false;
    static Table t = new Table();
    static Scanner l = new Scanner(System.in);
    static Interface s = new Interface();
    
    public static void main(String[] args) {
        
        
        s.launch(Interface.class);
        
    }
    
    /*private static void start() {
        String x = "";
        t.createAnswer();
        t.createSudokuFromAnswer();
        System.out.println("In this version you can play sudoku and a couple of features have been added.");
        System.out.println("Should run somewhat smoothly but the code is a bit of a mess right now.");
        
        while (true) {
            if (correctAnswer) {
                System.out.println("Congratulations, you solved the puzzle :)");
                break;
            }
            
            if (!done) {
                System.out.println("For commands press 'c'");
                x = l.nextLine();
            }
            
            if (x.equals("x") || done) {
                l.close();
                System.out.println("Generated answer: ");
                System.out.println("");
                for (int i = 0; i < 9; i++) {
                    System.out.println(Arrays.toString(t.getAnswer()[i]));
                }
                System.out.println("");
                System.out.println("Hope you had fun :)");
                break;
            } else if (x.equals("c")) {
                System.out.println("Press 'x' to close the program");
                System.out.println("To play press 'p'");
            } else if (x.equals("p")) {
                play();
            } else {
                System.out.println("Not a valid command");
            }
            
        }
    }
    
    private static void play() {
        System.out.println("Press 'x' if you want to quit");
        System.out.println("Press 'c' to clear the table");
        System.out.println("Press 'p' to cancel last move");
        System.out.println("To add a number to the table, just write them in a single line");
        System.out.println("For example '123' means add number 3 to line 2 column 1");
                
        while (true) {
            System.out.println("");
            for (int i = 0; i < 9; i++) {
                System.out.println(Arrays.toString(t.getTable()[i]));
            }
            System.out.println("Yet to be added: " + t.yetToBeAdded());
            String s = l.nextLine();
            if (s.equals("x")) {
                done = true;
                break;
            } else if (s.equals("c")) {
                t.clearTable();
                continue;
            } else if (s.equals("p")) {
                t.cancelLastMove();
                continue;
            } else if (s.length() != 3) {
                System.out.println("Not a valid command");
                continue;
            }
            boolean areValid = true;
            String[] add = s.split("");
            for (int i = 0; i < 3; i++) {
                if (add[i].hashCode() > 57 || add[i].hashCode() < 49) {
                    System.out.println("Not a valid command");
                    areValid = false;
                    break;
                }
            }
            if (!areValid) {
                continue;
            }
            t.addNumber(Integer.valueOf(add[0]), Integer.valueOf(add[1]), Integer.valueOf(add[2]));
            if (t.isPuzzleDone()) {
                if (t.checkIfCorrect(t.sudokuTable)) {
                    correctAnswer = true;
                    break;
                } else {
                    System.out.println("Your solution isn't correct");
                }
            }
        }
    }*/
}
