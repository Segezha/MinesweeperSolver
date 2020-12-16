package sample;

import javafx.scene.image.Image;


public class Cell {

    private final int x;
    private final int y;
    private CellValue id;
    private int mineCount;
    private boolean isSelected = false;
    private boolean isFlagged = false;

    private static int numFlag = 0;

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

        if (this.isFlagged()){
            numFlag++;
        } else {
            numFlag--;
        }

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

    public Image getUnselectedImage(){
        return unselected;
    }

    public Image getFlagImage(){
        return flag;
    }

    public Image getSelectedImage(){

        if (id.equals(CellValue.MINE)){
            return getMineImage();
        }

        switch (mineCount){

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

    public Image getMineImage(){
        return mine;
    }

    public static int getNumFlag(){
        return numFlag;
    }

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

}
