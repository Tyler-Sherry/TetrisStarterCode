package controllers;

import models.Tetronimo;
import java.util.ArrayList;

public class tetrisGameLogic
{
    public static int gameScore = 0;
    public boolean pauseGame = false;
    public ArrayList<Tetronimo> tetronimosOnTheBoard = new ArrayList<>();


    /**
     * This section handles the collision logic for the tetronimo spawned on the board
     * @param tetronimo
     * @return
     */
    public boolean isColliding(Tetronimo tetronimo,  ArrayList<Tetronimo> tetronimosOnTheBoard)
    {
        //Colliding on Y-axis
        if (isCollidingOnYAxis(tetronimo, tetronimosOnTheBoard))
        {
            return true;
        }



        //Break apart Tetronimo into individual blocks to see furthest right, left, top, bottom


        //Test that next tetronimo on board is >5 pixels away on Y-axis

        return false;
    }



    public void addTetronimosToArrayList(Tetronimo tetronimo)
    {
        tetronimosOnTheBoard.add(tetronimo);
    }

    //Testing function
    public void displayTetronimosInArrayList()
    {
        System.out.println("The following tetronimos are on the board");
        for (int i = 0; i < tetronimosOnTheBoard.size(); i++)
        {
            Tetronimo tetronimoPiece = tetronimosOnTheBoard.get(i);
            System.out.println(tetronimoPiece.getBounds());
        }
    }

    public void checkIfTetronimoTouchingOtherTetronimos(Tetronimo tetronimoToTest)
    {
        for (int i = 0; i < tetronimosOnTheBoard.size(); i++)
        {

        }
    }

    public void pauseGame()
    {
        pauseGame = true;
    }

    public void unPauseGame()
    {
        pauseGame = false;
    }

    public boolean isCollidingOnYAxis(Tetronimo tetronimo,  ArrayList<Tetronimo> tetronimosOnTheBoard)
    {
        for (int i = 0; i < tetronimosOnTheBoard.size(); i++)
        {
            Tetronimo tetronimoFromArrayList = tetronimosOnTheBoard.get(i);
            if (tetronimo.collision_getBottomEdgeOfTetronimo() == (tetronimoFromArrayList.collision_getTopEdgeOfTetronimo() - Tetronimo.SIZE)
            && tetronimo.collision_getRightEdgeOfTetronimo() > tetronimoFromArrayList.collisions_getLeftEdgeOfTetronimo())
            {
                return true;
            }
        }
        return false;
    }



}
