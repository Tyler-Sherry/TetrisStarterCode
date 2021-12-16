package controllers;

import models.Tetronimo;
import java.util.ArrayList;

public class tetrisGameLogic
{
    public static int gameScore = 0;
    public int gameLevel = 1;
    public int gamePass = 0;
    public boolean pauseGame = false;



    public void pauseGame()
    {
        pauseGame = true;
    }

    public void unPauseGame()
    {
        pauseGame = false;
    }

}
