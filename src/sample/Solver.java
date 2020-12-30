package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Solver {
    private boolean isChanged;
    private boolean stillNeedChanges;
    private final List<CellGroups> listOfGroups = new ArrayList<>();
    private MainGame mainGame;

    public Solver(MainGame mainGame) {
        this.mainGame = mainGame;
    }

    public boolean start() {
        Cell[][] arrayOfCells = mainGame.getBoard().getCells();
        int sizeX = mainGame.getBoard().getXSize();
        int sizeY = mainGame.getBoard().getYSize();
        mainGame.selectCell(0, 0, mainGame.getBoard().getCell(0, 0), mainGame.getBoard());
        while (!mainGame.isEnd()) {
            isChanged = false;
            stillNeedChanges = false;
            for (int i = 0; i < sizeX; i++) {
                for (int j = 0; j < sizeY; j++) {
                    if (arrayOfCells[i][j].isSelected()) {
                        CellGroups group = new CellGroups(new ArrayList<>(), arrayOfCells[i][j].getMineCount());
                        List<Cell> cells = group.getCells();
                        for (int dx = -1; dx <= 1; dx++) {
                            int x = i + dx;
                            for (int dy = -1; dy <= 1; dy++) {
                                int y = j + dy;
                                if (x >= 0 && y >= 0 && x <= (sizeX - 1) && y <= (sizeY - 1) &&
                                        !arrayOfCells[x][y].isSelected())
                                    cells.add(arrayOfCells[x][y]);
                            }
                        }
                        if (!cells.isEmpty() && group.getBombs() != 0) {
                            stillNeedChanges = true;
                            listOfGroups.add(0, group);
                            for (int k = 0; k < listOfGroups.size(); k++) {
                                for (int n = k + 1; n < listOfGroups.size(); n++) {
                                    CellGroups first = listOfGroups.get(k);
                                    CellGroups second = listOfGroups.get(n);
                                    int sizeOfFirst = first.getCells().size();
                                    int sizeOfSecond = second.getCells().size();
                                    if (sizeOfFirst == sizeOfSecond && first.equalsInCells(second))
                                        listOfGroups.remove(second);
                                    else if (sizeOfFirst > sizeOfSecond) {
                                        if (first.remove(second)) k = 0;
                                    } else if (sizeOfFirst < sizeOfSecond){
                                        if (second.remove(first)) k = 0;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            while (stillNeedChanges) {
                restructure();
                if (mainGame.isEnd()) break;
            }
            if (!isChanged) {
                Random rnd = new Random();
                int x = rnd.nextInt(sizeX);
                int y = rnd.nextInt(sizeY);
                if (!mainGame.isEnd()) mainGame.selectCell(x, y, mainGame.getBoard().getCell(x, y), mainGame.getBoard());
                stillNeedChanges = true;
                while (stillNeedChanges) {
                    restructure();
                    if (mainGame.isEnd()) break;
                }
            }
        }
        return mainGame.isWin();
    }

    private void restructure() {
        stillNeedChanges = false;
        List<CellGroups> groupsToRemove = new ArrayList<>();
        for (CellGroups element : listOfGroups) {
            List<Cell> cells = element.getCells();
            int numberOfBombs = element.getBombs();
            if (numberOfBombs == 0) {
                for (Cell cell : cells) {
                    if (!mainGame.isEnd()) mainGame.selectCell(cell.getX(), cell.getY(), cell, mainGame.getBoard());
                    if (mainGame.isEnd()) break;
                }
                groupsToRemove.add(element);
                isChanged = true;
                stillNeedChanges = true;
            } else if (numberOfBombs == cells.size()) {
                for (Cell cell : cells) {
                    if (!cell.isFlagged()) {
                        mainGame.flag(cell.getX(), cell.getY(), mainGame.getBoard());
                        isChanged = true;
                        stillNeedChanges = true;
                    }
                }
            }
        }
        for (CellGroups element: groupsToRemove) listOfGroups.remove(element);
        for (CellGroups element: listOfGroups) {
            List<Cell> cellsToRemove = new ArrayList<>();
            List<Cell> cells = element.getCells();
            int numberOfBombs = element.getBombs();
            for (Cell cell: cells) {
                if (cell.isSelected()) {
                    cellsToRemove.add(cell);
                } else if (cell.isFlagged() && cells.size() > numberOfBombs) {
                    cellsToRemove.add(cell);
                    element.setBombs(numberOfBombs - 1);
                    numberOfBombs = element.getBombs();
                }
            }
            for (Cell cell: cellsToRemove) cells.remove(cell);
        }
    }
}
