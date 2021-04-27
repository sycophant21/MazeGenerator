package com.maze;

import com.board.Block;
import com.board.BoardHandler;
import com.window.Panel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MazeTraverser implements KeyListener {
    private final BoardHandler boardHandler;
    private final Block lastBlock;
    private final Panel panel;
    private Block currentBlock;
    private int currentRow;
    private int currentColumn;

    public MazeTraverser(BoardHandler boardHandler, Block lastBlock, Block currentBlock, Panel panel) {
        this.boardHandler = boardHandler;
        this.lastBlock = lastBlock;
        this.currentBlock = currentBlock;
        this.panel = panel;
        this.currentRow = this.currentBlock.getRow();
        this.currentColumn = this.currentBlock.getColumn();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (currentBlock != lastBlock) {
            boolean hasMoved = false;
            if (e.getKeyChar() == 'w') {
                hasMoved = travelUp();
            } else if (e.getKeyChar() == 's') {
                hasMoved = travelDown();
            } else if (e.getKeyChar() == 'a') {
                hasMoved = travelLeft();
            } else if (e.getKeyChar() == 'd') {
                hasMoved = travelRight();
            }
            if (hasMoved) {
                panel.setxCoordinate(currentBlock.getColumn());
                panel.setyCoordinate(currentBlock.getRow());
                panel.repaint();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private boolean travelUp() {
        boolean hasMoved = false;
        if (isUpperNeighbourExistent(currentRow)) {
            Block nextBlock = boardHandler.getBlockAt(currentColumn, currentRow - 1);
            if (currentBlock.getVisitedNeighbours().contains(nextBlock)) {
                currentBlock = nextBlock;
                currentColumn = currentBlock.getColumn();
                currentRow = currentBlock.getRow();
                hasMoved = true;
            }
        }
        return hasMoved;
    }

    private boolean travelDown() {
        boolean hasMoved = false;
        if (isBottomNeighbourExistent(currentRow, lastBlock.getRow())) {
            Block nextBlock = boardHandler.getBlockAt(currentColumn, currentRow + 1);
            if (currentBlock.getVisitedNeighbours().contains(nextBlock)) {
                currentBlock = nextBlock;
                currentColumn = currentBlock.getColumn();
                currentRow = currentBlock.getRow();
                hasMoved = true;
            }
        }
        return hasMoved;
    }

    private boolean travelLeft() {
        boolean hasMoved = false;
        if (isLeftNeighbourExistent(currentColumn)) {
            Block nextBlock = boardHandler.getBlockAt(currentColumn - 1, currentRow);
            if (currentBlock.getVisitedNeighbours().contains(nextBlock)) {
                currentBlock = nextBlock;
                currentColumn = currentBlock.getColumn();
                currentRow = currentBlock.getRow();
                hasMoved = true;
            }
        }
        return hasMoved;
    }

    private boolean travelRight() {
        boolean hasMoved = false;
        if (isRightNeighbourExistent(currentColumn, lastBlock.getColumn())) {
            Block nextBlock = boardHandler.getBlockAt(currentColumn + 1, currentRow);
            if (currentBlock.getVisitedNeighbours().contains(nextBlock)) {
                currentBlock = nextBlock;
                currentColumn = currentBlock.getColumn();
                currentRow = currentBlock.getRow();
                hasMoved = true;
            }
        }
        return hasMoved;
    }

    private boolean isLeftNeighbourExistent(int column) {
        return column > 0;
    }

    private boolean isUpperNeighbourExistent(int row) {
        return row > 0;
    }

    private boolean isRightNeighbourExistent(int column, int width) {
        return column < width;
    }

    private boolean isBottomNeighbourExistent(int row, int height) {
        return row < height;
    }
}
