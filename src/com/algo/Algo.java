package com.algo;

import com.board.Block;
import com.window.Panel;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Algo {
    private final Stack<Block> blockStack;
    private final Panel panel;

    public Algo(Panel panel) {
        this.panel = panel;
        this.blockStack = new Stack<>();
    }

    public void findPath(Block firstBlock, Block lastBlock) throws InterruptedException {
        blockStack.push(firstBlock);
        while (!blockStack.isEmpty()) {
            Block currentBlock = blockStack.pop();
            currentBlock.setConsidered();
            panel.setxCoordinate(currentBlock.getColumn());
            panel.setyCoordinate(currentBlock.getRow());
            Thread.sleep(50);
            panel.repaint();
            if (currentBlock != lastBlock) {
                List<Block> visitedBlocks = currentBlock.getVisitedNeighbours();
                if (visitedBlocks.size() > 0) {
                    blockStack.push(currentBlock);
                    visitedBlocks.sort(new Comparator(lastBlock));
                    List<Block> blocksToRemove = new ArrayList<>();
                    for (Block block : visitedBlocks) {
                        if (!block.isConsidered()) {
                            blockStack.push(block);
                        }
                        else {
                            blocksToRemove.add(block);
                        }
                    }
                    for (Block block : blocksToRemove) {
                        visitedBlocks.remove(block);
                    }
                }
            }
            else {
                break;
            }
        }
    }
}
