package sudoku.problemdomain;

import java.io.IOException;

public interface IStorage {
    void updateGameData(SudokuGame game) throw IOException;
    SudokuGame getGameData() throws IOException;
}
