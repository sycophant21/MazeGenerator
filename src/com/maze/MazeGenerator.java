package com.maze;

import com.board.Block;
import com.window.Panel;

import java.util.Stack;

public class MazeGenerator {
    private Stack<Block> toBeVisitedBlocks;
    private final Panel panel;

    public MazeGenerator(Panel panel) {
        this.panel = panel;
        this.toBeVisitedBlocks = new Stack<>();
    }

    public void generateMaze(Block block) throws InterruptedException {
        block.setVisited();
        toBeVisitedBlocks.push(block);
        while (!toBeVisitedBlocks.isEmpty()) {
            Block currentBlock = toBeVisitedBlocks.pop();
            panel.setxCoordinate(currentBlock.getColumn());
            panel.setyCoordinate(currentBlock.getRow());
            Thread.sleep(10);
            panel.repaint();
            Block nextBlock = currentBlock.getRandomUnvisitedNeighbour();
            if (nextBlock != null) {
                toBeVisitedBlocks.push(currentBlock);
                removeWallsBetween(currentBlock, nextBlock);
                nextBlock.setVisited();
                toBeVisitedBlocks.push(nextBlock);
            }
        }
    }

    private void removeWallsBetween(Block currentBlock, Block nextBlock) {
        int currentColumn = currentBlock.getColumn();
        int currentRow = currentBlock.getRow();

        int nextColumn = nextBlock.getColumn();
        int nextRow = nextBlock.getRow();

        if (currentColumn == nextColumn + 1) {
            currentBlock.hideLeftLine();
            nextBlock.hideRightLine();
        }
        else if (currentColumn == nextColumn - 1) {
            nextBlock.hideLeftLine();
            currentBlock.hideRightLine();
        }
        else if (currentRow == nextRow + 1) {
            currentBlock.hideUpperLine();
            nextBlock.hideBottomLine();
        }
        else if (currentRow == nextRow - 1) {
            nextBlock.hideUpperLine();
            currentBlock.hideBottomLine();
        }
    }
}

//Choose the initial cell, mark it as visited and push it to the stack
//While the stack is not empty
    //Pop a cell from the stack and make it a current cell
    //If the current cell has any neighbours which have not been visited
        //Push the current cell to the stack
        //Choose one of the unvisited neighbours
        //Remove the wall between the current cell and the chosen cell
        //Mark the chosen cell as visited and push it to the stack
