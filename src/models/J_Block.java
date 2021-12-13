package models;

import java.awt.*;

public class J_Block extends Tetronimo
{
    /**
     * Constructor for T-block tetronimo
     */
    public J_Block()
    {
        super.r1.setLocation( 0, 0 );
        super.r2.setLocation( 0, Tetronimo.SIZE );
        super.r3.setLocation( Tetronimo.SIZE * 1, Tetronimo.SIZE);
        super.r4.setLocation( Tetronimo.SIZE * 2, Tetronimo.SIZE );

        r1.setColor(Color.BLUE);
        r2.setColor(Color.BLUE);
        r3.setColor(Color.BLUE);
        r4.setColor(Color.BLUE);

        r1.setFrameColor(Color.BLACK);
        r2.setFrameColor(Color.BLACK);
        r3.setFrameColor(Color.BLACK);
        r4.setFrameColor(Color.BLACK);

        super.add( r1 );
        super.add( r2 );
        super.add( r3 );
        super.add( r4 );
    }

    /**
     * Method to rotate the J-block tetronimo
     */
    @Override
    public void rotate()
    {
        super.rotate();

        Point curLoc = super.getLocation();
        super.setLocation( 0, 0 );

        if( super.curRotation % 3 == 0 )
        {
            super.r1.setLocation( 0, Tetronimo.SIZE);
            super.r2.setLocation( Tetronimo.SIZE, Tetronimo.SIZE );
            super.r3.setLocation( Tetronimo.SIZE, 0 );
            super.r4.setLocation( Tetronimo.SIZE, -Tetronimo.SIZE);
        }
        else if (super.curRotation % 4 == 0)
        {
            super.r1.setLocation( Tetronimo.SIZE * 2, Tetronimo.SIZE);
            super.r2.setLocation( Tetronimo.SIZE * 2, 0 );
            super.r3.setLocation( Tetronimo.SIZE, 0 );
            super.r4.setLocation( 0, 0 );
        }
        else if (super.curRotation % 5 == 0)
        {
            super.r1.setLocation( Tetronimo.SIZE, 0);
            super.r2.setLocation( 0, 0 );
            super.r3.setLocation( 0, Tetronimo.SIZE );
            super.r4.setLocation( 0, Tetronimo.SIZE * 2 );
        }
        else
        {
            super.r1.setLocation( 0, 0 );
            super.r2.setLocation( 0, Tetronimo.SIZE );
            super.r3.setLocation( Tetronimo.SIZE * 1, Tetronimo.SIZE);
            super.r4.setLocation( Tetronimo.SIZE * 2, Tetronimo.SIZE );
        }

        super.setLocation( curLoc );
    }
}
