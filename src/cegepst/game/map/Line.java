package cegepst.game.map;

import cegepst.engine.Buffer;

import java.awt.*;
import java.util.ArrayList;

public class Line {

    private ArrayList<Cell> cells;
    private Cell spawningCell;

    public Line(int y) {
        cells = new ArrayList<>();
        initializeCells(y);
        spawningCell = new Cell(800, y);
    }

    public void checkIfCellClicked(Point mousePosition) {
        for (Cell cell : cells) {
            if (cell.isClicked(mousePosition)) {
                System.out.println("Clicked cell!");
            }
        }
    }

    public void draw(Buffer buffer) {
        for (Cell cell : cells) {
            cell.draw(buffer);
        }
        spawningCell.draw(buffer);
    }

    public void emptyCells() {
        cells.forEach(Cell::empty);
    }

    public Cell getSpawningCell() {
        return spawningCell;
    }

    private void initializeCells(int y) {
        cells.add(new Cell(180, y));
        cells.add(new Cell(237, y));
        cells.add(new Cell(294, y));
        cells.add(new Cell(351, y));
        cells.add(new Cell(409, y));
        cells.add(new Cell(467, y));
        cells.add(new Cell(526, y));
        cells.add(new Cell(585, y));
        cells.add(new Cell(645, y));
    }
}
