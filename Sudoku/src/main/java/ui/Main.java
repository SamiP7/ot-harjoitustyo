package ui;

import java.util.*;
import domain.*;

public class Main {
    public static void main(String[] args) {
        Table t = new Table();
        Scanner l = new Scanner(System.in);
        t.createAnswer();
        t.createSudokuFromAnswer();
        System.out.println("Pretty much a prealpha version so close to nothing is included.");
        System.out.println("Currently you can only view the answer and see the table and close the program.");
        System.out.println("It's also quite buggy at the time so here's a  little word of warning.");
        
        while (true) {
            System.out.println("For commands press c");
            String x = l.nextLine();
            if (x.equals("x")) {
                l.close();
                System.out.println("Hope you had fun :)");
                break;
            }
            else if (x.equals("c")) {
                System.out.println("Press x to close the program");
                System.out.println("To see the answer press a");
                System.out.println("Press s to see the table");
            }
            
            else if (x.equals("a")) {
                for (int i = 0; i < 9; i++) {
                    System.out.println(Arrays.toString(t.getAnswer()[i]));
                }
            }
            
            else if (x.equals("s")) {
                for (int i = 0; i < 9; i++) {
                    System.out.println(Arrays.toString(t.sudokuTable[i]));
                }
            }
            else {
                System.out.println("Not a valid command");
            }
            
        }
    }
}
