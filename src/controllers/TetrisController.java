package controllers;

import models.*;
import views.TetrisBoard;

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
    public ArrayList<Tetronimo> tetronimosForTheLevel = new ArrayList<>();
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

    //TEST - NEED TO REMOVE
    public void test_DisplayTetronimosIntheArrayList(ArrayList<Tetronimo> tetronimosForTheLevel)
    {
        System.out.println("The following tetronimos are still in the array list");
        for (int i = 0; i < tetronimosForTheLevel.size(); i++)
        {
            System.out.print(tetronimosForTheLevel + ", ");
        }
    }

    /**
     * Method to load all the tetronimos that will be spawning for the level
     * @param tetronimosForTheLevel
     */
    public void loadTetronimosArrayList(ArrayList<Tetronimo> tetronimosForTheLevel)
    {
        //Load the tetronimos into the array list
        for(int i = 0; i < 10; i++)
        {
            Tetronimo tetronimo = getRandomTetronimo(getRandomNumberForTetronimo());
            tetronimosForTheLevel.add(tetronimo);
            tetronimosForTheLevel.get(i).setLocation(400, 150);
        }

        //Now space out tetronimos in the array list
        for (int i = 1; i < tetronimosForTheLevel.size(); i++)
        {
            int Y_locationOfTetronimoInFront = tetronimosForTheLevel.get(i - 1).getYLocation();
            tetronimosForTheLevel.get(i).setLocation(400, Y_locationOfTetronimoInFront + 100);
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
        Tetronimo tetronimo = tetronimosForTheLevel.get(0);

        tetronimo.setLocation( 40 + (5 * Tetronimo.SIZE), 0 );
        return tetronimo;

    }

    /**
     * Method to remove the tetronimos from the level array list of tetronimos once
     * the tetronimo has landed
     * @param tetronimosForTheLevel
     */
    public void removeTetronimosFromArrayList(ArrayList<Tetronimo> tetronimosForTheLevel)
    {
        tetronimosForTheLevel.remove(0);
        for (int i = 0; i < tetronimosForTheLevel.size(); i++)
        {
            tetronimosForTheLevel.get(i).setLocation(tetronimosForTheLevel.get(i).getXLocation(), tetronimosForTheLevel.get(i).getYLocation() - 100);
        }
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

            //Check to see if grid row is full
            if (isGridRowFull())
            {
                System.out.println("The grid row is full - now to call a function to update row with color");
            }

            return true;
        }

        //Next function to check if tetronimo landed on another tetronimo
        if(isTetronimoRectangleBelow(tetronimo))
        {
            addTetronimoRectanglesLandedToGrid(tetronimo);

            //Check to see if grid row is full
            if (isGridRowFull())
            {
                System.out.println("The grid row is full - now to call a function to update row with color");
            }

            return true;
        }



        return false;

    }

    public boolean isGridRowFull()
    {
        int tetronimosInRow = 0;

        //This pass goes row by row
        for (int i = 0; i < 24; i++)
        {
            //This pass check each column in the row
            for (int j = 0; j < 10; j++)
            {
                if (landedGridOfTetronimoRectangles[i][j] != null)
                {
                    System.out.println("There is a tetronimo rectangle at: [" + i + "] " + "[" + j + "]");
                    tetronimosInRow++;
                    System.out.println("There are: " + tetronimosInRow + " in row " + i);
                    if (tetronimosInRow == 9)
                    {
                        System.out.println("Row " + i + " is full!");
                        return true;
                    }
                }
            }
            tetronimosInRow = 0;
        }
        return false;
    }









    public void changeFilledRowColor(TetronimoRectangle tetronimoRectangle)
    {
        int rowPosition = tetronimoRectangle.getCenterY() / 20;
        //Loop through row and change the color
        for (int k = 0; k < 10; k++)
        {
            landedGridOfTetronimoRectangles[rowPosition][k].setColor(tetronimoRectangle.getColor());
        }
    }


    /**
     * Method used to track the tetronimo moving down the grid in relation to the other
     * tetronimos that have landed on the grid
     * @param tetronimoMoving
     */
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
