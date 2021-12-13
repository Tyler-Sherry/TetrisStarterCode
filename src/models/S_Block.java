package models;

import java.awt.*;

public class S_Block extends Tetronimo
{
    /**
     * Constructor for S-block tetronimo
     */
    public S_Block()
    {
        super.r1.setLocation( 0, Tetronimo.SIZE );
        super.r2.setLocation( Tetronimo.SIZE, Tetronimo.SIZE );
        super.r3.setLocation( Tetronimo.SIZE, 0 );
        super.r4.setLocation( Tetronimo.SIZE * 2, 0  );

        r1.setColor(Color.GREEN);
        r2.setColor(Color.GREEN);
        r3.setColor(Color.GREEN);
        r4.setColor(Color.GREEN);

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
     * Method to rotate the S-block tetronimo
     */
    @Override
    public void rotate()
    {
        super.rotate();

        Point curLoc = super.getLocation();
        super.setLocation( 0, 0 );

        if( super.curRotation % 2 == 0 )
        {
            super.r1.setLocation( Tetronimo.SIZE, Tetronimo.SIZE);
            super.r2.setLocation( Tetronimo.SIZE, 0 );
            super.r3.setLocation( 0, 0 );
            super.r4.setLocation( 0, -Tetronimo.SIZE );
        }
        else
        {
            super.r1.setLocation( 0, Tetronimo.SIZE );
            super.r2.setLocation( Tetronimo.SIZE, Tetronimo.SIZE );
            super.r3.setLocation( Tetronimo.SIZE, 0 );
            super.r4.setLocation( Tetronimo.SIZE * 2, 0  );
        }

        super.setLocation( curLoc );
    }
}
