package com.algo;

import com.board.Block;

public class Comparator implements java.util.Comparator<Block> {
    private final Block lastBlock;

    public Comparator(Block lastBlock) {
        this.lastBlock = lastBlock;
    }

    @Override
    public int compare(Block o1, Block o2) {
        return (int)Math.ceil(calculateDistanceFromEnd(o2) - calculateDistanceFromEnd(o1));
    }

    private double calculateDistanceFromEnd(Block block) {
        double x2 = Math.pow(block.getColumn() - lastBlock.getColumn(), 2);
        double y2 = Math.pow(block.getRow() - lastBlock.getRow(), 2);
        return Math.sqrt(x2 + y2);
    }
}
