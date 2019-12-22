package logictests;

import java.util.HashMap;
import sudokuapp.logic.Table;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TableTest {
    
    Table s;
    
    
    @Before
    public void setUp() {
        this.s = new Table();
        s.createAnswer();
    }
       
    
    @Test
    public void testIfSudokuIsNotCompleted() {
        s.createSudokuFromAnswer();
        assertFalse(s.checkIfCorrect(s.sudokuTable));
    }
    
    @Test
    public void testIfSudokuIsCompleted() {
        s.createSudokuFromAnswer();
        assertFalse(s.isPuzzleDone());
    }
    
    @Test
    public void testNumberLeft() {
        s.createSudokuFromAnswer();
        HashMap<Integer, Integer> left = new HashMap<>();
        for (int i = 1; i <= 9; i++) {
            left.put(i, 9);
        }
        left.put(0, 10000);
        for (int i = 0; i < 9; i++) {
            for (int o = 0; o < 9; o++) {
                if (s.sudokuTable[i][o] != 0) {
                    left.put(s.sudokuTable[i][o], left.get(s.sudokuTable[i][o]) - 1);
                }
            }
        }
        assertEquals(left, s.yetToBeAdded());
    }
    
    @Test
    public void testIfNumberCanBeAdded() {
        s.createSudokuFromAnswer();
        int i = s.sudokuTable[5][4];
        if (i == 0) {
            s.addNumber(6, 5, 4);
            assertEquals(4,s.sudokuTable[5][4]);
        } else {
            int o = 0;
            if (i == 9) {
                o = i - 1;
            } else {
                o = i + 1;
            }
            s.addNumber(6, 5, o);
            assertNotEquals(o,s.sudokuTable[5][4]);
        }
    }
    
    @Test
    public void testIfPuzzleIsDone() {
        s.createSudokuFromAnswer();
        assertFalse(s.isPuzzleDone());
    }
    
    @Test
    public void testClearTable() {
        s.createSudokuFromAnswer();
        int[][] x = s.sudokuTable;
        s.clearTable();
        Assert.assertArrayEquals(s.sudokuTable,x);
    }
    
    @Test
    public void testCancelLastMove() {
        s.createSudokuFromAnswer();
        int y = 0;
        int x = 0;
        for (int i = 0; i < 9; i++) {
            for (int o = 0; o < 9; o++) {
                if (s.sudokuTable[i][o] == 0) {
                    y = i;
                    x = o;
                    break;
                }
                    
            }
        }
        s.addNumber(y+1, x+1, 5);
        s.addNumber(y+1, x+1, 7);
        s.cancelLastMove();
        assertEquals(5,s.sudokuTable[y][x]);
    }
    
    
}
