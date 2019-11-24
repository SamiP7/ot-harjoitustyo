import logic.Table;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class domainTableTest {
    
    Table s;
    
    public domainTableTest() {
        this.s = new Table();
        s.createAnswer();
    }
       
    @Test
    public void testIfAnswerIsViable() {
        assertTrue(s.checkIfCorrect(s.getAnswer()));
    }
    
    @Test
    public void testIfSudokuIsNotCompleted() {
        s.createSudokuFromAnswer();
        assertFalse(s.checkIfCorrect(s.sudokuTable));
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
