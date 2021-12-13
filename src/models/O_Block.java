package models;

import java.awt.*;

public class O_Block extends Tetronimo
{
    /**
     * Constructor for O-block tetronimo
     */
    public O_Block()
    {
        super.r1.setLocation( 0, 0 );
        super.r2.setLocation( Tetronimo.SIZE, 0 );
        super.r3.setLocation( 0, Tetronimo.SIZE );
        super.r4.setLocation( Tetronimo.SIZE, Tetronimo.SIZE );

        r1.setColor(Color.YELLOW);
        r2.setColor(Color.YELLOW);
        r3.setColor(Color.YELLOW);
        r4.setColor(Color.YELLOW);

        r1.setFrameColor(Color.BLACK);
        r2.setFrameColor(Color.BLACK);
        r3.setFrameColor(Color.BLACK);
        r4.setFrameColor(Color.BLACK);

        super.add( r1 );
        super.add( r2 );
        super.add( r3 );
        super.add( r4 );
    }
}
