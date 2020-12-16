package sample;

import java.util.List;
import java.util.Objects;

public class CellGroups {

    private List<Cell> cellsOfGroup;

    private int bombsCount;

    public CellGroups(List<Cell> cellsOfGroup, int bombsCount) {
        this.cellsOfGroup = cellsOfGroup;
        this.bombsCount = bombsCount;
    }

    public boolean equalsInCells(CellGroups group) {
        if (bombsCount != group.bombsCount) return false;
        for (int i = 0; i < cellsOfGroup.size(); i++) {
            Cell first = cellsOfGroup.get(i);
            Cell second = group.cellsOfGroup.get(i);
            if (!first.equals(second)) return false;
        }
        return true;
    }

    public boolean remove(CellGroups group) {
        for (Cell element: group.cellsOfGroup) {
            if (!cellsOfGroup.contains(element)) return false;
        }
        for (Cell element: group.cellsOfGroup) {
            cellsOfGroup.remove(element);
        }
        bombsCount -= group.getBombs();
        return true;
    }

    public List<Cell> getCells() {
        return cellsOfGroup;
    }

    public int getBombs() {
        return bombsCount;
    }

    public void setBombs(int num) {
        this.bombsCount = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CellGroups that = (CellGroups) o;
        return bombsCount == that.bombsCount &&
                Objects.equals(cellsOfGroup, that.cellsOfGroup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cellsOfGroup, bombsCount);
    }
}
