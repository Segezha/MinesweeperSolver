package sample;

public class WinRate {

    public static void main(String[] args) {
        int counter = 0;
        int times = 10000;
        for (int i = 0; i < times; i++) {
            Solver solver = new Solver(new MainGame(new Board(9, 9), Difficulty.EASY));
            if (solver.start()) counter++;
        }
        System.out.println("Winrate: " + String.format("%.2f" ,((double) counter / times * 100)) + "%");
    }
}
