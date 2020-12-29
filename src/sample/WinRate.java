package sample;

public class WinRate {

    public static void main(String[] args) {
        int counter = 0;
        int times = 10000;
        for (int i = 0; i < times; i++) {
            Board board = new Board(9, 9);
            //Solver solver = new Solver(board);
            //solver.start();
            //if (board.isWin()) counter++;
        }
        System.out.println("Winrate: " + String.format("%.3f" ,((double) counter / times * 100)) + "%");
    }
}
