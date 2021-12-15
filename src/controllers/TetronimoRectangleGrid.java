package controllers;

import models.TetronimoRectangle;

import java.awt.*;

public class TetronimoRectangleGrid
{
    public static final int SIZE= 20;
    /**
     * Constant to represent the width of the board
     */
    public static final int WIDTH = 10;

    /**
     * Constant to represent the height of the board
     */
    public static final int HEIGHT = 24;

    public TetronimoRectangle[][] TetrisBoardArrayOfTetronimoRectangles = new TetronimoRectangle[WIDTH][HEIGHT];
    public TetronimoRectangle defaultTetronimoRectange;

    public TetronimoRectangleGrid()
    {
        this.defaultTetronimoRectange = new TetronimoRectangle();
        this.defaultTetronimoRectange.setSize(SIZE, SIZE);
        this.defaultTetronimoRectange.setFrameColor(Color.BLACK);
        this.defaultTetronimoRectange.setColor(Color.GREEN);

        for (int i = 0; i < HEIGHT; i++)
        {
            for(int j = 0; j < WIDTH; j++)
            {
                this.TetrisBoardArrayOfTetronimoRectangles[i][j] = defaultTetronimoRectange;
            }
        }
    }

}
