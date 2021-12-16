package controllers;

import models.*;
import views.TetrisBoard;

import javax.swing.text.Utilities;
import java.util.ArrayList;
import java.util.Random;

/**
 * TetrisController.java:
 * Class to hold all of the game logic for tetris
 *
 * @author Tyler Sherry
 * @version 1.0 December 8, 2021
 */
public class TetrisController
{
    private final TetrisBoard TETRIS_BOARD;
    public ArrayList<Tetronimo> tetronimosDroppingArrayList = new ArrayList<>();
    public ArrayList<Tetronimo> tetronimosDisplayedArrayList = new ArrayList<>();
    public ArrayList<TetronimoRectangle> tetronimoRectangleArrayList = new ArrayList<>();
    public TetronimoRectangle[][] landedGridOfTetronimoRectangles = new TetronimoRectangle[24][10];
    public TetronimoRectangle[][] movingGridOfTetronimoRectangles = new TetronimoRectangle[24][10];

    /**
     * Constructor to take in a tetris board so the controller and the board can communciate
     * @param tetrisBoard A tetris board instance
     */
    public TetrisController( TetrisBoard tetrisBoard )
    {
        this.TETRIS_BOARD = tetrisBoard;
    }



    public void createTetronimoDisplayList(ArrayList<Tetronimo> tetronimosDisplayedArrayList)
    {
        Tetronimo tetronimoToDisplay1 = getRandomTetronimo(getRandomNumberForTetronimo());
        Tetronimo tetronimoToDisplay2 = getRandomTetronimo(getRandomNumberForTetronimo());

        tetronimosDisplayedArrayList.add(tetronimoToDisplay1);
        tetronimosDisplayedArrayList.add(tetronimoToDisplay2);

        tetronimosDisplayedArrayList.get(0).setLocation(400, 150);
        tetronimosDisplayedArrayList.get(1).setLocation(500, 150);
    }

    public void loadTetronimosArrayList(ArrayList<Tetronimo> tetronimosForTheLevel)
    {
        for(int i = 0; i < 20; i++)
        {
            Tetronimo tetronimo = getRandomTetronimo(getRandomNumberForTetronimo());
            tetronimosForTheLevel.add(tetronimo);
            tetronimosForTheLevel.get(i).setLocation(400, 150);
        }
    }

    /**
     * Method to get random number
     * @return random number that was generated
     */
    public int getRandomNumberForTetronimo()
    {
        Random rand = new Random();
        int upperBound = 7;
        int int_random = rand.nextInt(upperBound);
        return int_random;
    }

    /**
     * Method to create a random tetronimo based on the
     * @param int_random number that is passed in
     * @return a random number is returned
     */
    public Tetronimo getRandomTetronimo(int int_random)
    {
        Tetronimo tetronimo = null;
        switch (int_random)
        {
            case 0:
                tetronimo = new I_Block();
                break;
            case 1:
                tetronimo = new J_Block();
                break;
            case 2:
                tetronimo = new L_Block();
                break;
            case 3:
                tetronimo = new O_Block();
                break;
            case 4:
                tetronimo = new S_Block();
                break;
            case 5:
                tetronimo = new T_Block();
                break;
            case 6:
                tetronimo = new Z_Block();
                break;
        }
        return tetronimo;
    }


    /**
     * Randomly chooses the next tetronimo and returns it
     * @return The next tetronimo to be played
     */
    public Tetronimo getNextTetrominoFromArrayList(ArrayList<Tetronimo> tetronimosForTheLevel)
    {
        removeTetronimosFromArrayList(tetronimosForTheLevel);
        Tetronimo tetronimo = tetronimosForTheLevel.get(0);

        tetronimo.setLocation( 40 + (5 * Tetronimo.SIZE), 0 );

        return tetronimo;
    }

    public void removeTetronimosFromArrayList(ArrayList<Tetronimo> tetronimosForTheLevel)
    {
        int newXLoc = tetronimosForTheLevel.get(0).getXLocation();
        int newYLoc = tetronimosForTheLevel.get(0).getYLocation();
        tetronimosForTheLevel.get(1).setLocation(newXLoc, newYLoc);
        tetronimosForTheLevel.remove(0);
    }




    public Tetronimo test_GetNextTetronimoForBoard(ArrayList<Tetronimo> tetronimosBeingDisplayed)
    {
        Tetronimo tetronimoToPutOnBoard = tetronimosBeingDisplayed.get(0);
        tetronimoToPutOnBoard.setLocation( 40 + (5 * Tetronimo.SIZE), 0 );
        return tetronimoToPutOnBoard;
    }

    public void displayNextTetronimo(ArrayList<Tetronimo> tetronimosBeingDisplayed, Tetronimo tetronimoToDisplay)
    {
        this.tetronimosDroppingArrayList.add(tetronimoToDisplay);
        tetronimosBeingDisplayed.get(0).setLocation(400, 150);
    }





    /**
     * Method to determine if the tetronimo has landed (INCOMPLETE)
     *
     * @param tetronimo The tetronimo to evaluate
     * @return True if the tetronimo has landed (on the bottom of the board or another tetronimo), false if it has not
     */
    public boolean hasTetronimoLanded(Tetronimo tetronimo)
    {
        //First function to check if hit bottom of board
        if (tetronimo.collision_getBottomEdgeOfTetronimo() == 460)
        {
            addTetronimoRectanglesLandedToGrid(tetronimo);
            displayTetronimoRectanglesOnTheBoardThatHaveLanded();
            return true;
        }

        //Next function to check if tetronimo landed on another tetronimo
        if(isTetronimoRectangleBelow(tetronimo))
        {
            addTetronimoRectanglesLandedToGrid(tetronimo);
            return true;
        }



        return false;

    }

    public void addTetronimoRectanglesMovingToGrid(Tetronimo tetronimoMoving)
    {
        for (int i = 0; i < tetronimoMoving.tetronimoRectangleArrayList.size(); i++)
        {
            TetronimoRectangle tetronimoRectangle = tetronimoMoving.tetronimoRectangleArrayList.get(i);
            int colPosition = (tetronimoRectangle.getCenterX() - 40) / 20;
            int rowPosition = tetronimoRectangle.getCenterY() / 20;
            this.movingGridOfTetronimoRectangles[rowPosition][colPosition] = tetronimoRectangle;
        }
    }

    /**
     * Method to display the tetronimo rectangles currently moving down the board.
     */
    public void displayTetronimoRectanglesOnTheBoardThatAreMoving()
    {
        for (int i = 0; i < 24; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                if (this.movingGridOfTetronimoRectangles[i][j] != null)
                {
                    System.out.println("There is a tetronimo rectangle at: [" + i + "] " + "[" + j + "]");
                }
            }
        }
    }

    /**
     * Method to track the tetronimo rectangles moving down the board and to detect
     * if any of the tetronimo rectangles collide with tetronimo rectangles already on
     * the board
     * @param tetronimoMoving
     * @return
     */
    public boolean isTetronimoRectangleBelow(Tetronimo tetronimoMoving)
    {
        //Get the tetronimo rectangles making up the tetronimo
        for (int i = 0; i < tetronimoMoving.tetronimoRectangleArrayList.size(); i++)
        {
            TetronimoRectangle tetronimoRectangle = tetronimoMoving.tetronimoRectangleArrayList.get(i);
            int colPosition = (tetronimoRectangle.getCenterX() - 40) / 20;
            int rowPosition = tetronimoRectangle.getCenterY() / 20;

            //Testing
            //System.out.println("The tetronimo rectangles are at [" + rowPosition + "] [" + colPosition + "]");
            //System.out.println("Next row down is [" + (rowPosition + 1) + "] [" + colPosition + "]");

            //Now check if there are any tetronimo rectangles currently on the board that have landed in the next row down
            if (landedGridOfTetronimoRectangles[(rowPosition + 1)][colPosition] != null)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to track the tetronimo rectangles moving down the board and to detect
     * if any of the tetronimo rectangles collide with tetronimo rectangles already on
     * the board to the left of the falling tetronimo
     * @param tetronimoMoving
     * @return
     */
    public boolean isTetronimoRectangleToTheLeft(Tetronimo tetronimoMoving)
    {
        //Get the tetronimo rectangles making up the tetronimo
        for (int i = 0; i < tetronimoMoving.tetronimoRectangleArrayList.size(); i++)
        {
            TetronimoRectangle tetronimoRectangle = tetronimoMoving.tetronimoRectangleArrayList.get(i);
            int colPosition = (tetronimoRectangle.getCenterX() - 40) / 20;
            int rowPosition = tetronimoRectangle.getCenterY() / 20;

            //Now check if there are any tetronimo rectangles currently on the board that have landed in the next row down
            if (landedGridOfTetronimoRectangles[rowPosition][(colPosition - 1)] != null)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to track the tetronimo rectangles moving down the board and to detect
     * if any of the tetronimo rectangles collide with tetronimo rectangles already on
     * the board to the right of the falling tetronimo
     * @param tetronimoMoving
     * @return
     */
    public boolean isTetronimoRectangleToTheRight(Tetronimo tetronimoMoving)
    {
        //Get the tetronimo rectangles making up the tetronimo
        for (int i = 0; i < tetronimoMoving.tetronimoRectangleArrayList.size(); i++)
        {
            TetronimoRectangle tetronimoRectangle = tetronimoMoving.tetronimoRectangleArrayList.get(i);
            int colPosition = (tetronimoRectangle.getCenterX() - 40) / 20;
            int rowPosition = tetronimoRectangle.getCenterY() / 20;

            //Now check if there are any tetronimo rectangles currently on the board that have landed in the next row down
            if (landedGridOfTetronimoRectangles[rowPosition][(colPosition + 1)] != null)
            {
                return true;
            }
        }
        return false;
    }


    /**
     * Method to keep track of the tetronimo rectangles that have landed on the board
     * @param tetronimoLanded
     */
    public void addTetronimoRectanglesLandedToGrid(Tetronimo tetronimoLanded)
    {
        for (int i = 0; i < tetronimoLanded.tetronimoRectangleArrayList.size(); i++)
        {
        TetronimoRectangle tetronimoRectangle = tetronimoLanded.tetronimoRectangleArrayList.get(i);
        int colPosition = (tetronimoRectangle.getCenterX() - 40) / 20;
        int rowPosition = tetronimoRectangle.getCenterY() / 20;
        this.landedGridOfTetronimoRectangles[rowPosition][colPosition] = tetronimoRectangle;
        }

        //Call function to remove landed tetronimo rectangles from moving grid
        //Basically cycle through moving grid and make to all null pointers
    }

    public void displayTetronimoRectanglesOnTheBoardThatHaveLanded()
    {
        for (int i = 0; i < 24; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                if (this.landedGridOfTetronimoRectangles[i][j] != null)
                {
                    System.out.println("There is a tetronimo rectangle at: [" + i + "] " + "[" + j + "]");
                }
            }
        }
    }



}
