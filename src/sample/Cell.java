package sample;

public class Cell {

    private final int x;
    private final int y;
    private CellValue id;
    private int mineCount;
    private boolean isSelected = false;
    private boolean isFlagged = false;


    public Cell(int x, int y){
        this.id = CellValue.EMPTY;
        this.x = x;
        this.y = y;
    }


    public void setMine(){
        id = CellValue.MINE;
    }

    public CellValue getID(){
        return id;
    }

    public void select(){
        isSelected = true;

        if (isFlagged){
            flag();
        }

    }

    public void flag(){
        isFlagged = !isFlagged;

    }

    public boolean isSelected(){
        return isSelected;
    }

    public boolean isFlagged(){
        return isFlagged;
    }

    public String toString(){
        switch(id){
            case MINE: return (String.format("mine, %d", mineCount));
            default: return (String.format("empty, %d", mineCount));
        }
    }

    public void setMineCount(int mineCount){
        this.mineCount = mineCount;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getMineCount(){
        return mineCount;
    }

}
