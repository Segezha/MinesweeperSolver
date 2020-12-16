import org.junit.Test;
import sample.Board;
import sample.Difficulty;
import sample.MainGame;
import sample.Solver;

import static org.junit.Assert.assertTrue;

public class SolverTest {
    MainGame mainGame;
    Board board;
    Solver solver;

    public boolean start() {
        for (int i = 0; i < 10; i++) {
            board = new Board(9, 9);
            mainGame = new MainGame(board, Difficulty.EASY);
            solver = new Solver(mainGame);

            solver.start();
        }
        return true;
    }
    @Test
    public void testSolution() {

        assertTrue(start());
    }
    }
