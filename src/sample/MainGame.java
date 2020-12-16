package sample;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class MainGame extends GridPane{

    private ImageView[][] cell;
    private boolean end;
    private boolean win;
    public static final int CELL_SIZE = 20;
    private Board board;

    public MainGame(Board board, Difficulty difficulty){

        this.board = board;

        board.init(difficulty);

        cell = new ImageView[board.getYSize()][board.getXSize()];

        for (int i = 0; i < board.getYSize(); i++){
            for (int j = 0; j < board.getXSize(); j++){

                cell[i][j] = new ImageView(board.getCell(j, i).getUnselectedImage());
                cell[i][j].setFitHeight(CELL_SIZE);
                cell[i][j].setFitWidth(CELL_SIZE);
                GridPane.setRowIndex(cell[i][j], i + 1);
                GridPane.setColumnIndex(cell[i][j], j + 1);
                this.getChildren().add(cell[i][j]);

            }
        }
    }

    public void flag(int x, int y, Board board){

        board.getCell(x, y).flag();

        if (board.getCell(x, y).isFlagged()){
            cell[y][x].setImage(board.getCell(x, y).getFlagImage());
        } else {
            cell[y][x].setImage(board.getCell(x, y).getUnselectedImage());
        }

    }

    public void selectCell(int firstX, int firstY, Cell cell, Board board){

        int x = cell.getX();
        int y = cell.getY();

        this.cell[y][x].setImage(board.getCell(x, y).getSelectedImage());
        board.getCell(x, y).select();

        if (board.getCell(x,y).getID().equals(CellValue.MINE) && x == firstX && y == firstY){

            displayAll(board);
            end = true;
            System.out.println("lose");

        }
        if (Cell.getNumFlag() == board.getNumMine()) {
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


    private void displayAll(Board board){

        for (int i = 0; i < board.getYSize(); i++){
            for (int j = 0; j < board.getXSize(); j++){

                if (!(board.getCell(j, i).isSelected())){
                    this.cell[i][j].setImage(board.getCell(j, i).getSelectedImage());
                }

            }
        }
    }

    public Board getBoard(){
        return board;
    }

}

