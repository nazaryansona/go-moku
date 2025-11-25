import org.example.Board;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    void testPlaceMove() {
        Board b = new Board(15, 15);
        assertTrue(b.placeMove(7, 7, 'X'));
        assertFalse(b.placeMove(7, 7, 'O'));
    }

    @Test
    void testCheckWinHorizontal() {
        Board b = new Board(15, 15);
        for (int i = 0; i < 5; i++) {
            b.placeMove(0, i, 'X');
        }
        assertTrue(b.checkWin(0, 4, 'X'));
    }

    @Test
    void testCheckWinVertical() {
        Board b = new Board(15, 15);
        for (int i = 0; i < 5; i++) {
            b.placeMove(i, 0, 'X');
        }
        assertTrue(b.checkWin(4, 0, 'X'));
    }

    @Test
    void testCheckWinDiagonal() {
        Board b = new Board(15, 15);
        for (int i = 0; i < 5; i++) {
            b.placeMove(i, i, 'X');
        }
        assertTrue(b.checkWin(4, 4, 'X'));
    }

    @Test
    void testWouldWin() {
        Board b = new Board(15, 15);
        for (int i = 0; i < 4; i++) b.placeMove(0, i, 'X');
        assertTrue(b.wouldWin(0, 4, 'X'), "Placing X at (0,4) should be winning");
    }

}
