package sample;

import javafx.scene.layout.GridPane;

public class MainGame extends GridPane{

    private boolean end;
    private boolean win;
    private Board board;
    private int numFlag;

    public MainGame(Board board, Difficulty difficulty){

        this.board = board;
        this.numFlag = 0;

        board.init(difficulty);
    }

    public void flag(int x, int y, Board board){

        board.getCell(x, y).flag();

        if (board.getCell(x, y).isFlagged()){
            numFlag ++;
        } else {
            numFlag --;
        }

    }

    public void selectCell(int firstX, int firstY, Cell cell, Board board){

        int x = cell.getX();
        int y = cell.getY();

        board.getCell(x, y).select();

        if (board.getCell(x,y).getID().equals(CellValue.MINE) && x == firstX && y == firstY){

            end = true;
            System.out.println("lose");

        }
        if (numFlag == board.getNumMine()) {
            end = true;
            win = true;
            System.out.println("win");

        }
        else if (board.getCell(x,y).getMineCount() == 0){
            selectSurroundingCell(firstX, firstY, x, y, board);

        }
    }

    private void selectSurroundingCell(int firstX, int firstY, int x, int y, Board board){

        for (int i = (y - 1); i <= (y + 1); i++){
            for (int j = (x - 1); j <= (x + 1); j++){
                try {
                    if (board.getCell(j, i).isSelected()){
                        continue;
                    }
                    if (i == y && j == x){
                        continue;
                    }
                    selectCell(firstX, firstY, board.getCell(j, i), board);
                } catch (IndexOutOfBoundsException ex){
                    continue;
                }
            }
        }

    }

    public boolean isEnd(){
        return end;
    }

    public boolean isWin(){
        return win;
    }

    public Board getBoard() {
        return board;
    }
}

