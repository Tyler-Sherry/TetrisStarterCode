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

    /**
     * Constructor to take in a tetris board so the controller and the board can communciate
     *
     * @param tetrisBoard A tetris board instance
     */
    public TetrisController( TetrisBoard tetrisBoard )
    {
        this.TETRIS_BOARD = tetrisBoard;
    }

    /**
     * Randomly chooses the next tetronimo and returns it (INCOMPLETE)
     *
     * @return The next tetronimo to be played
     */
    public Tetronimo getNextTetromino()
    {
        Tetronimo tetronimo;

        //Generate random tetronimo
        tetronimo = getRandomTetronimo();


        //Display next tetronimo upcoming in window

        tetronimo.setLocation( 40 + (5 * Tetronimo.SIZE), 0 );

        return tetronimo;
    }

    /**
     * Method to determine if the tetronimo has landed (INCOMPLETE)
     *
     * @param tetronimo The tetronimo to evaluate
     * @return True if the tetronimo has landed (on the bottom of the board or another tetronimo), false if it has not
     */
    public boolean hasTetronimoLanded(Tetronimo tetronimo,  ArrayList<Tetronimo> tetronimosOnTheBoard )
    {
        //First function to check if hit bottom of board
        if (tetronimo.collision_getBottomEdgeOfTetronimo() == 460)
        {
            return true;
        }

        //Next function to check if tetronimo landed on another tetronimo




        return false;

    }

    public Tetronimo getRandomTetronimo()
    {
        Tetronimo tetronimo = null;
//        Random rand = new Random();
//        int upperBound = 7;
//        int int_random = rand.nextInt(upperBound);
//
//        switch (int_random)
//        {
//            case 0:
//                tetronimo = new I_Block();
//                break;
//            case 1:
//                tetronimo = new J_Block();
//                break;
//            case 2:
//                tetronimo = new L_Block();
//                break;
//            case 3:
//                tetronimo = new O_Block();
//                break;
//            case 4:
//                tetronimo = new S_Block();
//                break;
//            case 5:
//                tetronimo = new T_Block();
//                break;
//            case 6:
//                tetronimo = new Z_Block();
//                break;
//        }

        tetronimo = new T_Block();

        return tetronimo;
    }
}
