package com.enonic.gjupgrade;

import java.nio.file.Paths;

import org.junit.Test;

import static org.junit.Assert.*;

public class PathHelperTest
{
    @Test
    public void subtractPath()
        throws Exception
    {
        assertEquals( Paths.get( "b", "c" ).toString(),
                      PathHelper.subtractPath( Paths.get( "a", "b", "c" ), Paths.get( "a" ) ).toString() );

        assertEquals( "c", PathHelper.subtractPath( Paths.get( "a", "b", "c" ), Paths.get( "a", "b" ) ).toString() );

    }

    @Test(expected = IllegalArgumentException.class)
    public void not_in_path()
        throws Exception
    {
        assertNotNull( PathHelper.subtractPath( Paths.get( "a", "b", "c" ), Paths.get( "b" ) ).toString() );
    }
}
