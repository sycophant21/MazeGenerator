package com.window;

import com.board.BoardHandler;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    private final int width;
    private final int height;
    private final int offset;
    private final BoardHandler boardHandler;
    private int xCoordinate;
    private int yCoordinate;

    public Panel(int width, int height, BoardHandler boardHandler) {
        this.width = width;
        this.height = height;
        this.boardHandler = boardHandler;
        this.offset = this.boardHandler.getOffset();
    }

    @Override
    protected void paintComponent(Graphics g) {
        //super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,0, width * offset, height * offset);
        g.setColor(Color.WHITE);
        g.fillRect(xCoordinate * offset,yCoordinate * offset, offset, offset);
        g.setColor(Color.GREEN);
        g.fillRect(5,5, offset - 10, offset - 10);
        g.setColor(Color.RED);
        g.fillRect((width * offset) - offset + 5,(height * offset) - offset + 5, offset - 10, offset - 10);
        for (int i = 0 ; i < height ; i++) {
            for (int j = 0 ; j < width ; j++) {
                boardHandler.getBlockAt(j, i).show(g);
            }
        }
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
}
