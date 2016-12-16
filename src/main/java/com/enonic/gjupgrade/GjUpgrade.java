package com.enonic.gjupgrade;

import java.nio.file.Paths;

public class GjUpgrade
{

    public static void main( final String... args )
    {

        if ( args.length == 0 )
        {
            System.out.println( "Missing argument <upgrade root path>" );
            System.exit( 0 );
        }

        UpgradeHandler.create().
            sourceRoot( Paths.get( args[0] ) ).
            build().
            execute();
    }

}
