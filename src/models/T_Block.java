package models;

import java.awt.*;

public class T_Block extends Tetronimo
{
    /**
     * Constructor for T-block tetronimo
     */
    public T_Block()
    {
        super.r1.setLocation( 0, 0 );
        super.r2.setLocation( 0, Tetronimo.SIZE );
        super.r3.setLocation( 0, Tetronimo.SIZE * 2 );
        super.r4.setLocation( Tetronimo.SIZE, Tetronimo.SIZE );

        r1.setColor(Color.RED);
        r2.setColor(Color.CYAN);
        r3.setColor(Color.CYAN);
        r4.setColor(Color.CYAN);

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
     * Method to rotate the T-block tetronimo
     */
    @Override
    public void rotate()
    {
        super.rotate();

        Point curLoc = super.getLocation();
        super.setLocation( 0, 0 );

        if( super.curRotation % 3 == 0 )
        {
            super.r1.setLocation( -Tetronimo.SIZE, 0);
            super.r2.setLocation( 0, 0 );
            super.r3.setLocation( Tetronimo.SIZE, 0 );
            super.r4.setLocation( 0, -Tetronimo.SIZE );
        }
        else if (super.curRotation % 4 == 0)
        {
            super.r1.setLocation( 0, -Tetronimo.SIZE);
            super.r2.setLocation( 0, 0 );
            super.r3.setLocation( 0, Tetronimo.SIZE );
            super.r4.setLocation( -Tetronimo.SIZE, 0 );
        }
        else if (super.curRotation % 5 == 0)
        {
            super.r1.setLocation( -Tetronimo.SIZE, 0);
            super.r2.setLocation( 0, 0 );
            super.r3.setLocation( Tetronimo.SIZE, 0 );
            super.r4.setLocation( 0, Tetronimo.SIZE );
        }
        else
        {
            super.r1.setLocation( 0, 0 );
            super.r2.setLocation( 0, Tetronimo.SIZE );
            super.r3.setLocation( 0, Tetronimo.SIZE * 2 );
            super.r4.setLocation( Tetronimo.SIZE, Tetronimo.SIZE );
        }

        super.setLocation( curLoc );
    }

}
