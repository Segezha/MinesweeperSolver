import org.junit.jupiter.api.Test;
import sample.Cell;
import sample.CellGroups;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CellGroupsTest {

    CellGroups first;
    CellGroups second;
    CellGroups third;
    ArrayList<Cell> firstCells = new ArrayList<>();
    ArrayList<Cell> secondCells = new ArrayList<>();
    ArrayList<Cell> thirdCells = new ArrayList<>();

    @Test
    void equalsInCells() {
        firstCells.add(new Cell(0, 0));
        firstCells.add(new Cell(1, 2));
        secondCells = firstCells;
        thirdCells.add(new Cell(2, 2));
        thirdCells.add(new Cell(7, 9));

        first = new CellGroups(firstCells, 2);
        second = new CellGroups(secondCells, 2);
        third = new CellGroups(thirdCells, 3);
        assertTrue(first.equalsInCells(second));
        assertTrue(second.equalsInCells(first));
        assertFalse(first.equalsInCells(third));
        assertFalse(third.equalsInCells(first));

        second.setBombs(3);
        assertFalse(first.equalsInCells(second));
        assertFalse(third.equalsInCells(second));
    }

    @Test
    void remove() {
        Cell a = new Cell(0, 0);
        Cell b = new Cell(6, 8);
        Cell c = new Cell(2, 3);
        firstCells.clear();
        secondCells.clear();
        thirdCells.clear();
        firstCells.add(new Cell(0, 0));
        firstCells.add(new Cell(6, 8));
        firstCells.add(new Cell(2, 3));
        secondCells.add(firstCells.get(0));
        secondCells.add(firstCells.get(1));
        thirdCells.add(firstCells.get(2));
        first = new CellGroups(firstCells, 2);
        second = new CellGroups(secondCells, 2);
        third = new CellGroups(thirdCells, 0);
        first.remove(second);
        assertEquals(first, third);
    }
}