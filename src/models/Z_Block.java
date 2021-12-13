package models;

import java.awt.*;

public class Z_Block extends Tetronimo
{
    /**
     * Constructor for Z-block tetronimo
     */
    public Z_Block()
    {
        super.r1.setLocation( -Tetronimo.SIZE, 0 );
        super.r2.setLocation( 0, 0 );
        super.r3.setLocation( 0, Tetronimo.SIZE );
        super.r4.setLocation( Tetronimo.SIZE, Tetronimo.SIZE  );

        r1.setColor(Color.GRAY);
        r2.setColor(Color.GRAY);
        r3.setColor(Color.GRAY);
        r4.setColor(Color.GRAY);

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
     * Method to rotate the Z-block tetronimo
     */
    @Override
    public void rotate()
    {
        super.rotate();

        Point curLoc = super.getLocation();
        super.setLocation( 0, 0 );

        if( super.curRotation % 2 == 0 )
        {
            super.r1.setLocation( 0, Tetronimo.SIZE );
            super.r2.setLocation( 0, 0 );
            super.r3.setLocation( Tetronimo.SIZE, 0 );
            super.r4.setLocation( Tetronimo.SIZE, -Tetronimo.SIZE  );
        }
        else
        {
            super.r1.setLocation( -Tetronimo.SIZE, 0 );
            super.r2.setLocation( 0, 0 );
            super.r3.setLocation( 0, Tetronimo.SIZE );
            super.r4.setLocation( Tetronimo.SIZE, Tetronimo.SIZE  );
        }

        super.setLocation( curLoc );
    }
}
