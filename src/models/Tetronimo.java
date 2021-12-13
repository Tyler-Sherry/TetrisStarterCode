package models;

import wheelsunh.users.Rectangle;
import wheelsunh.users.ShapeGroup;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Tetronimo.java:
 * An abstract class to model the base capaabilities of a tetronimo
 *
 * @author Tyler Sherry
 * @version 1.0 December 8, 2021
 *
 * @see java.awt.Color
 */
public abstract class Tetronimo extends ShapeGroup
{
    /**
     * Constant to represent the size of the tetronimo
     */
    public static final int SIZE= 20;

    public TetronimoRectangle r1;
    public TetronimoRectangle r2;
    public TetronimoRectangle r3;
    public TetronimoRectangle r4;

    protected int curRotation = 1;

    public ArrayList<TetronimoRectangle> tetronimoRectangleArrayList = new ArrayList<>();

    
    /**
     * Generates the four rectangles for the tetronino and puts them on the screen, they are at the default coordinates
     * to start
     * Also initializes the tetronimoRectangleArrayList with the rectangles in the tetronimo to handle the collision logic
     */
    public Tetronimo()
    {
        super();
        this.r1 = new TetronimoRectangle();
        this.r1.setSize( Tetronimo.SIZE, Tetronimo.SIZE );
        this.r1.setFrameColor( Color.BLACK );
        tetronimoRectangleArrayList.add(r1);

        this.r2 = new TetronimoRectangle();
        this.r2.setSize( Tetronimo.SIZE, Tetronimo.SIZE );
        this.r2.setFrameColor( Color.BLACK );
        tetronimoRectangleArrayList.add(r2);

        this.r3 = new TetronimoRectangle();
        this.r3.setSize( Tetronimo.SIZE, Tetronimo.SIZE );
        this.r3.setFrameColor( Color.BLACK );
        tetronimoRectangleArrayList.add(r3);

        this.r4 = new TetronimoRectangle();
        this.r4.setSize( Tetronimo.SIZE, Tetronimo.SIZE );
        this.r4.setFrameColor( Color.BLACK );
        tetronimoRectangleArrayList.add(r4);
    }

    /**
     * Increments the rotation of the tetronimo, other classes need to override this to provide the full functionality
     */
    public void rotate()
    {
        this.curRotation++;
    }

    /**
     * Shifts the tetronimo left one row
     */
    public void shiftLeft()
    {
        super.setLocation( super.getXLocation() - Tetronimo.SIZE, super.getYLocation() );
    }

    /**
     * Shifts the tetronimo right one row
     */
    public void shiftRight()
    {
        super.setLocation( super.getXLocation() + Tetronimo.SIZE, super.getYLocation() );
    }

    /**
     * Returns the current rotation of the tetronimo
     * @return
     */
    public int getCurrentRotation() {return this.curRotation;}

    /**
     * COLLISION LOGIC
     * Handles the tetronino not going beyond the left bounds of the tetris board
     * @return the furthest left X-location of the rectangles making up the tetronimo
     */
    public int collisions_getLeftEdgeOfTetronimo()
    {
        int furthestLeftXLocation = 240;
        int rectangleXLocation = 0;
        
        for (int i = 0; i < 4; i++)
        {
            Rectangle rectangle = tetronimoRectangleArrayList.get(i);
            rectangleXLocation = (rectangle.getXLocation());
            if (rectangleXLocation < furthestLeftXLocation)
            {
                furthestLeftXLocation = rectangleXLocation;
            }
        }
        return furthestLeftXLocation;
    }

    /**
     * COLLISION LOGIC
     * Handles the tetronino not going beyond the right bounds of the tetris board
     * @return the furthest right X-location of the rectangles making up the tetronimo
     */
    public int collision_getRightEdgeOfTetronimo()
    {
        int furthestRightXLocation = 40;
        int rectangleXLocation = 0;

        for (int i = 0; i < 4; i++)
        {
            Rectangle rectangle = tetronimoRectangleArrayList.get(i);
            rectangleXLocation = (rectangle.getXLocation());
            if (rectangleXLocation > furthestRightXLocation)
            {
                furthestRightXLocation = rectangleXLocation;
            }
        }
        return furthestRightXLocation;
    }

    /**
     * COLLISION LOGIC
     * Handles the tetronino hitting the bottom of the tetris board or the top of another tetronimo
     * @return the furthest right X-location of the rectangles making up the tetronimo
     */
    public int collision_getBottomEdgeOfTetronimo()
    {
        int furthestYValueOfLocation = 0;
        int rectangleYLocation = 0;

        for (int i = 0; i < 4; i++)
        {
            Rectangle rectangle = tetronimoRectangleArrayList.get(i);
            rectangleYLocation = (rectangle.getYLocation());
            if (furthestYValueOfLocation < rectangleYLocation)
            {
                furthestYValueOfLocation = rectangleYLocation;
            }
        }
        return furthestYValueOfLocation;
    }

    /**
     * COLLISION LOGIC
     * Returns the top edge of the tetronimo.  This method is overridden in the individual
     * tetronimo classes to account for cases where there could be more than one top
     * @return the top edge of the tetronimo
     */
    public int collision_getTopEdgeOfTetronimo()
    {
        int lowestYValueOfLocation = 500;
        int rectangleYLocation = 0;

        for (int i = 0; i < 4; i++)
        {
            Rectangle rectangle = tetronimoRectangleArrayList.get(i);
            rectangleYLocation = (rectangle.getYLocation());
            if (lowestYValueOfLocation > rectangleYLocation)
            {
                lowestYValueOfLocation = rectangleYLocation;
            }
        }
        return lowestYValueOfLocation;
    }

    /**
     * COLLISION LOGIC
     * The method breaks apart the tetronimo into individual rectangles and
     * @return the lowest edge of the tetronimo rectangles
     */
    public int collision_getLowestTetronimoRectangleEdge(ArrayList<TetronimoRectangle> tetronimoRectangleArrayList)
    {
        int lowestTestronimoRectangleEdge = 0;

        for (int i = 0; i < tetronimoRectangleArrayList.size(); i++)
        {
            TetronimoRectangle tetronimoRectangle = tetronimoRectangleArrayList.get(i);
            if (tetronimoRectangle.getBottomEdge() > lowestTestronimoRectangleEdge)
            {
                lowestTestronimoRectangleEdge = tetronimoRectangle.getBottomEdge();
            }
        }
        return lowestTestronimoRectangleEdge;
    }

    /**
     * COLLISION LOGIC
     * The method breaks apart the tetronimo into individual rectangles and
     * @return the highest edge of the tetronimo rectangles
     */
    public int collision_getHighestTetronimoRectangleEdge(ArrayList<TetronimoRectangle> tetronimoRectangleArrayList)
    {
        int highestTestronimoRectangleEdge = 500;

        for (int i = 0; i < tetronimoRectangleArrayList.size(); i++)
        {
            TetronimoRectangle tetronimoRectangle = tetronimoRectangleArrayList.get(i);
            if (tetronimoRectangle.getTopEdge() < highestTestronimoRectangleEdge)
            {
                highestTestronimoRectangleEdge = tetronimoRectangle.getBottomEdge();
            }
        }
        return highestTestronimoRectangleEdge - SIZE;
    }
}