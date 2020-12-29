package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class MainGame extends GridPane{

    private ImageView[][] cell;
    private boolean end;
    private boolean win;
    private static final int CELL_SIZE = 20;
    private Board board;

    private static Image unselected = new Image("image/unselected.gif");
    private static Image mine = new Image("image/mine.gif");
    private static Image flag = new Image("image/flag.gif");
    private static Image zero = new Image("image/empty.gif");
    private static Image one = new Image("image/one.gif");
    private static Image two = new Image("image/two.gif");
    private static Image three = new Image("image/three.gif");
    private static Image four = new Image("image/four.gif");
    private static Image five = new Image("image/five.gif");
    private static Image six = new Image("image/six.gif");
    private static Image seven = new Image("image/seven.gif");
    private static Image eight = new Image("image/eight.gif");

    public MainGame(Board board, Difficulty difficulty){

        this.board = board;

        board.init(difficulty);

        cell = new ImageView[board.getYSize()][board.getXSize()];

        for (int i = 0; i < board.getYSize(); i++){
            for (int j = 0; j < board.getXSize(); j++){

                cell[i][j] = new ImageView(unselected);
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
            cell[y][x].setImage(flag);
        } else {
            cell[y][x].setImage(unselected);
        }

    }

    public void selectCell(int firstX, int firstY, Cell cell, Board board){

        int x = cell.getX();
        int y = cell.getY();

        this.cell[y][x].setImage(getSelectedImage(board.getCell(x, y)));
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
                    this.cell[i][j].setImage(getSelectedImage(board.getCell(j, i)));
                }

            }
        }
    }

    public Board getBoard(){
        return board;
    }


    public Image getSelectedImage(Cell cell){

        if (cell.getID().equals(CellValue.MINE)){
            return mine;
        }

        switch (cell.getMineCount()){

            case 0: return zero;
            case 1: return one;
            case 2: return two;
            case 3: return three;
            case 4: return four;
            case 5: return five;
            case 6: return six;
            case 7: return seven;
            case 8: return eight;

            default : return null;

        }
    }
}

