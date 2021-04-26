package com.board;

public class Board {
    private final Block[][] board;
    private final int width;
    private final int height;
    private final int offset;

    public Board(Block[][] board, int offset) {
        this.board = board;
        width = board[0].length;
        height = board.length;
        this.offset = offset;
    }

    public Block getBlockAt(int column, int row) {
        return board[row][column];
    }
}
