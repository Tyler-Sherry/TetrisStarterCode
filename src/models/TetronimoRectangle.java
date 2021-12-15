package models;

import wheelsunh.users.Rectangle;

public class TetronimoRectangle extends Rectangle
{
    public static final int SIZE= 20;
    public int topEdge = 0;
    public int bottomEdge = 0;
    public int leftEdge = 0;
    public int rightEdge = 0;


    public TetronimoRectangle()
    {
        this.setSize(SIZE, SIZE);
        this.topEdge = getYLocation();
        this.bottomEdge = getYLocation() + SIZE;
        this.leftEdge = getXLocation();
        this.rightEdge = getYLocation() + SIZE;
    }

    public int getTopEdge()
    {
        topEdge = getYLocation();
        return topEdge;
    }

    public int getBottomEdge()
    {
        bottomEdge = getYLocation() + SIZE;
        return bottomEdge;
    }

    public int getLeftEdge()
    {
        leftEdge = getXLocation();
        return leftEdge;
    }

    public int getRightEdge()
    {
        rightEdge = getXLocation() + SIZE;
        return rightEdge;
    }
}
