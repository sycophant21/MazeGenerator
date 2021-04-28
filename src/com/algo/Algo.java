package com.algo;

import com.board.Block;
import com.window.Panel;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Algo {
    private final Stack<Block> blockStack;
    private final Panel panel;
    private final Path path;

    public Algo(Panel panel, Path path) {
        this.panel = panel;
        this.path = path;
        this.blockStack = new Stack<>();
    }

    public void findPath(Block firstBlock, Block lastBlock) throws InterruptedException {
        blockStack.push(firstBlock);
        while (!blockStack.isEmpty()) {
            Block currentBlock = blockStack.pop();
/*            if (path.getIthCoordinate(0) != null) {
                path.retractLastCoordinate();
            }*/
            currentBlock.setConsidered();
            panel.setxCoordinate(currentBlock.getColumn());
            panel.setyCoordinate(currentBlock.getRow());
            Thread.sleep(200);
            panel.repaint();
            if (currentBlock != lastBlock) {
                List<Block> visitedBlocks = currentBlock.getVisitedNeighbours();
                if (visitedBlocks.size() > 0) {
                    blockStack.push(currentBlock);
                    path.insertCoordinates(new Coordinates(currentBlock.getColumn(), currentBlock.getRow()));
                    path.considerLastCoordinate();
                    visitedBlocks.sort(new Comparator(lastBlock));
                    List<Block> blocksToRemove = new ArrayList<>();
                    for (Block block : visitedBlocks) {
                        if (!block.isConsidered()) {
                            blockStack.push(block);
                            path.insertCoordinates(new Coordinates(block.getColumn(), block.getRow()));
                        }
                        else {
                            blocksToRemove.add(block);
                        }
                    }
                    for (Block block : blocksToRemove) {
                        visitedBlocks.remove(block);
                    }
                }
                else {
                    path.retractLastCoordinate();
                }
            }
            else {
                break;
            }
        }
    }
}
