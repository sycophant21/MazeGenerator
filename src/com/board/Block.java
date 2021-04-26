package com.board;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Block {
    private final int column;
    private final int row;
    private final List<Block> neighbours;
    private final List<Block> visitedNeighbours;
    private Line2D leftLine;
    private Line2D upperLine;
    private Line2D rightLine;
    private Line2D bottomLine;
    private boolean isVisited;
    private List<Line2D> linesToShow;
    private boolean isConsidered = false;

    public Block(int column, int row, List<Block> neighbours, int offset) {
        this.column = column;
        this.row = row;
        this.neighbours = neighbours;
        this.visitedNeighbours = new ArrayList<>();
        isVisited = false;
        linesToShow = new ArrayList<>();
        initialiseLines(offset);
    }

    private void initialiseLines(int offset) {
        int x1 = this.column * offset;
        int y1 = this.row * offset;
        int x2 = (this.column + 1) * offset;
        int y2 = (this.row + 1) * offset;
        this.leftLine = new Line2D.Double(x1, y1, x1, y2);
        linesToShow.add(leftLine);
        this.upperLine = new Line2D.Double(x1, y1, x2, y1);
        linesToShow.add(upperLine);
        this.rightLine = new Line2D.Double(x2, y1, x2, y2);
        linesToShow.add(rightLine);
        this.bottomLine = new Line2D.Double(x1, y2, x2, y2);
        linesToShow.add(bottomLine);
    }

    public boolean isConsidered() {
        return isConsidered;
    }

    public void setConsidered() {
        isConsidered = true;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited() {
        isVisited = true;
    }

    public void hideLeftLine() {
        linesToShow.remove(leftLine);
        this.leftLine = new Line2D.Double();
    }

    public List<Block> getVisitedNeighbours() {
        return visitedNeighbours;
    }

    public void hideUpperLine() {
        linesToShow.remove(upperLine);
        this.upperLine = new Line2D.Double();
    }

    public void hideRightLine() {
        linesToShow.remove(rightLine);
        this.rightLine = new Line2D.Double();
    }

    public void hideBottomLine() {
        linesToShow.remove(bottomLine);
        this.bottomLine = new Line2D.Double();
    }

    public void addNeighbours(Block block) {
        neighbours.add(block);
    }

    public Block getRandomUnvisitedNeighbour() {
        Block block = null;
        if (neighbours.size() > 0) {
            int randomNumber = new Random().nextInt(neighbours.size());
            block = neighbours.get(randomNumber);
            while (block.isVisited) {
                //visitedNeighbours.add(block);
                neighbours.remove(block);
                if (neighbours.size() > 0) {
                    randomNumber = new Random().nextInt(neighbours.size());
                } else {
                    block = null;
                    break;
                }
                block = neighbours.get(randomNumber);
            }
            if (block != null) {
                visitedNeighbours.add(block);
                neighbours.remove(block);
            }
        }
        return block;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Block)) return false;
        Block block = (Block) o;
        return column == block.column && row == block.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }

    @Override
    public String toString() {
        return "Block{" +
                "column=" + column +
                ", row=" + row +
                '}';
    }

    public void show(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        for (Line2D line2D : linesToShow) {
            graphics.drawLine((int)line2D.getX1() ,(int)line2D.getY1(), (int)line2D.getX2(), (int)line2D.getY2());
        }
    }
}
