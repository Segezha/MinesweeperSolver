package sample;

import java.util.Random;

public class Board {

    private Cell[][] cells;
    public Integer[][] openCellsValues;
    private Random random = new Random();
    private int numMine;
    private static final int EASY_FACTOR = 8;
    private static final int MEDIUM_FACTOR = 6;
    private static final int HARD_FACTOR = 4;

    public Board(int xSize, int ySize){
        cells = new Cell[xSize][ySize];
        openCellsValues = new Integer[xSize][ySize];
    }

    public void init(Difficulty difficulty){

        initEmptyCell();
        numMine = initNumMine(difficulty);
        initMine();
        initMineCount();
    }

    private void initEmptyCell(){

        for (int i = 0; i < cells.length; i++){
            for (int j = 0; j < cells[0].length; j++){
                cells[i][j] = new Cell(j, i);
            }
        }
    }

    private int initNumMine(Difficulty difficulty){

        switch(difficulty){
            case EASY: return getBoardSize() / EASY_FACTOR;
            case MEDIUM: return getBoardSize() / MEDIUM_FACTOR;
            case HARD: return getBoardSize() / HARD_FACTOR;
            default: return 0;
        }
    }

    private void initMine(){

        for (int i = 0; i < numMine; i++){
            while(true){
                Cell randomCell = cells[random.nextInt(cells.length)][random.nextInt(cells[0].length)];
                if (!(randomCell.getID().equals(CellValue.MINE)) && (randomCell.getX() != 0) && randomCell.getY() != 0){
                    randomCell.setMine();
                    break;
                }
            }
        }
    }

    private void initMineCount(){

        for (int i = 0; i < cells.length; i++){
            for (int j = 0; j < cells[0].length; j++){
                if (cells[i][j].getID().equals(CellValue.MINE)){
                    continue;
                }
                int mineCount;
                mineCount = getMineCount(j, i);
                cells[i][j].setMineCount(mineCount);
            }
        }
    }

    public Cell getCell(int x, int y){
        return cells[y][x];
    }

    private int getMineCount(int x, int y){

        int mineCount = 0;

        for (int i = (y - 1); i <= (y + 1); i++){
            for (int j = (x - 1); j <= (x + 1); j++){
                if (i == y && j == x) continue;
                try {
                    if (cells[i][j].getID().equals(CellValue.MINE)){
                        mineCount++;
                    }
                } catch (IndexOutOfBoundsException ex){
                    continue;
                }
            }
        }
        return mineCount;
    }

    public int getBoardSize(){
        return getYSize() * this.getXSize();
    }

    public int getXSize(){
        return cells[0].length;
    }

    public int getYSize(){
        return cells.length;
    }

    public int getNumMine(){
        return numMine;
    }

    public Cell[][] getCells(){
        return cells;
    }

}
