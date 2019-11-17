import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import domain.*;

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
    
    
}
