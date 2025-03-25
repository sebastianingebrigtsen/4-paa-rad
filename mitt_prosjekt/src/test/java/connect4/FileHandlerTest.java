package connect4;

import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

public class FileHandlerTest {

    // Tester at vi kan lagre og hente et brett på riktig måte
    @Test
    public void testSaveAndLoadGame() {
        Board board = new Board();
        board.dropPiece(0, 'X'); // Legg til to brikker
        board.dropPiece(1, 'O');
        char currentPlayer = 'X';

        String filename = "test_spill.txt";

        // Lagrer spillet til fil
        FileHandler.saveToFile(board, currentPlayer, filename);

        // Leser tilbake
        FileHandler.LoadedGame loaded = FileHandler.loadFromFile(filename);
        assertNotNull(loaded);                                // Vi skal få tilbake et gyldig objekt
        assertEquals('X', loaded.currentPlayer);              // Riktig spiller
        assertEquals('X', loaded.board.getPiece(5, 0));       // Brikkene skal være på riktig plass
        assertEquals('O', loaded.board.getPiece(5, 1));

        new File(filename).delete(); // Sletter testfilen etterpå
    }
}
