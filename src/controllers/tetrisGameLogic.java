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
        //Is Colliding with other tetronimos - just doing stacking - need to do side to side
        if (isCollidingWithOtherTetronimos(tetronimo, tetronimosOnTheBoard))
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

    public void checkIfTetronimoTouchingOtherTetronimos(Tetronimo tetronimoToTest, ArrayList<Tetronimo> tetronimosOnTheBoard)
    {
        int tetronimosIntersected = 0;

        for (int i = 0; i < tetronimosOnTheBoard.size(); i++)
        {
            Tetronimo tetronimo = tetronimosOnTheBoard.get(i);
            if (tetronimoToTest.boundsIntersects(tetronimo))
            {
                System.out.println("The falling tetronimo interesected with: " + tetronimo);
                tetronimosIntersected++;
                if (tetronimosIntersected > 2)
                {
                    System.out.println("Intersected with two tetronimos!");
                }
            }
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

    public boolean isCollidingWithOtherTetronimos(Tetronimo tetronimo,  ArrayList<Tetronimo> tetronimosOnTheBoard)
    {
        //Check collision from the bottom of the falling tetronimo
        for (int i = 0; i < tetronimosOnTheBoard.size(); i++)
        {
            Tetronimo tetronimoFromArrayList = tetronimosOnTheBoard.get(i);
            if (tetronimo.collision_getLowestTetronimoRectangleEdge(tetronimo.tetronimoRectangleArrayList)
                    == tetronimoFromArrayList.collision_getHighestTetronimoRectangleEdge(tetronimoFromArrayList.tetronimoRectangleArrayList))
            {
                return true;
            }
        }

        //Check collision from the right of the falling tetronimo
        for (int i = 0; i < tetronimosOnTheBoard.size(); i++)
        {
            Tetronimo tetronimoFromArrayList = tetronimosOnTheBoard.get(i);
            if (tetronimo.collision_getLowestTetronimoRectangleEdge(tetronimo.tetronimoRectangleArrayList)
                    == tetronimoFromArrayList.collision_getHighestTetronimoRectangleEdge(tetronimoFromArrayList.tetronimoRectangleArrayList))
            {
                return true;
            }
        }




        return false;
    }



}
