package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {

    Board board;
    MainGame mainGame;
    Solver solver;

    public static void main(String[] a){
        launch(a);
    }

    public void start(Stage stage) {
            board = new Board(9, 9);
            mainGame = new MainGame(board, Difficulty.EASY);
            solver = new Solver(mainGame);

            VBox mainLayout = new VBox(10);

            mainLayout.getChildren().addAll(mainGame);

            Scene scene = new Scene(mainLayout);

            stage.setScene(scene);
            stage.show();
            solver.start();
        }
}