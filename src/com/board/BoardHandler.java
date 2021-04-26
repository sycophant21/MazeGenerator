package com.board;

import java.util.ArrayList;

public class BoardHandler {
    private final int offset;
    private final Board board;

    public BoardHandler(int offset, int height, int width) {
        this.offset = offset;
        this.board = initialiseBoard(height, width);
    }

    public int getOffset() {
        return offset;
    }

    private Board initialiseBoard(int height, int width) {
        Block[][] board = new Block[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = new Block(j, i, new ArrayList<>(), offset);
            }
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                addNeighbours(board, board[i][j]);
            }
        }
        return new Board(board, offset);
    }

    private void addNeighbours(Block[][] blocks, Block currentBlock) {
        int column = currentBlock.getColumn();
        int row = currentBlock.getRow();
        if (isLeftNeighbourExistent(column)) {
            currentBlock.addNeighbours(blocks[row][column - 1]);
        }
        if (isRightNeighbourExistent(column, blocks[0].length)) {
            currentBlock.addNeighbours(blocks[row][column + 1]);
        }
        if (isUpperNeighbourExistent(row)) {
            currentBlock.addNeighbours(blocks[row - 1][column]);
        }
        if (isBottomNeighbourExistent(row, blocks.length)) {
            currentBlock.addNeighbours(blocks[row + 1][column]);
        }
    }

    private boolean isLeftNeighbourExistent(int column) {
        return column >= 1;
    }
    private boolean isUpperNeighbourExistent(int row) {
        return row >= 1;
    }
    private boolean isRightNeighbourExistent(int column, int width) {
        return column < width - 1;
    }
    private boolean isBottomNeighbourExistent(int row, int height) {
        return row < height - 1;
    }

    public Block getBlockAt(int column, int row) {
        return board.getBlockAt(column, row);
    }
}
