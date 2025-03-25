package connect4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    // Sjekker at dropPiece faktisk plasserer en brikke nederst i kolonnen
    @Test
    public void testDropPiecePlacesPieceCorrectly() {
        Board board = new Board();
        int row = board.dropPiece(0, 'X');
        assertEquals(5, row);                      // Nederste rad i en tom kolonne er 5
        assertEquals('X', board.getPiece(5, 0));   // Skal ha X der
    }

    // Prøver å legge flere brikker enn det er plass til – forventer feil
    @Test
    public void testDropPieceInFullColumnReturnsMinusOne() {
        Board board = new Board();
        for (int i = 0; i < 6; i++) board.dropPiece(0, 'X'); // Fyller kolonnen
        int result = board.dropPiece(0, 'X');                // Ett for mye
        assertEquals(-1, result);                            // Forventer feil
    }

    // Tester horisontal seier
    @Test
    public void testHorizontalWin() {
        Board board = new Board();
        for (int col = 0; col < 4; col++) board.dropPiece(col, 'X'); // 4 på rad
        assertTrue(board.checkWin('X'));                             // Forventer seier
    }

    // Tester vertikal seier
    @Test
    public void testVerticalWin() {
        Board board = new Board();
        for (int i = 0; i < 4; i++) board.dropPiece(0, 'O');          // 4 nedover
        assertTrue(board.checkWin('O'));                              // Forventer seier
    }

    // Tester at hele brettet er fullt
    @Test
    public void testBoardIsFull() {
        Board board = new Board();
        // Fyller hele brettet med annenhver brikke
        for (int col = 0; col < 7; col++) {
            for (int row = 0; row < 6; row++) {
                board.dropPiece(col, (row % 2 == 0) ? 'X' : 'O');
            }
        }
        assertTrue(board.isBoardFull());                             // Skal være fullt
    }
}
