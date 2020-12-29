import org.junit.Test;
import sample.Board;
import sample.Difficulty;
import sample.MainGame;
import sample.Solver;

import static org.junit.Assert.assertTrue;

public class SolverTest {

        Board board;
        Solver solver;

    @Test
        public void start() {
            board = new Board(9, 9);
            board.initEmptyCell();
            board.getCell(4, 5).setMine();
            board.getCell(1, 8).setMine();
            board.getCell(2, 5).setMine();
            board.getCell(7, 6).setMine();
            board.getCell(8, 8).setMine();
            board.getCell(1, 1).setMine();
            board.getCell(4, 2).setMine();
            board.getCell(3, 7).setMine();
            board.getCell(8, 1).setMine();
            board.getCell(6, 5).setMine();

            //solver = new Solver(board);

            solver.start();

            //assertTrue(board.isWin());
        }

}
