package com.enonic.gjupgrade.model;

import com.google.common.base.Charsets;
import com.google.common.io.CharSource;
import com.google.common.io.Resources;

import com.enonic.gjupgrade.xml.DomHelper;

import static org.junit.Assert.*;

public abstract class AbstractUpgradeModelTest
{
    protected void assertResult( final String upgraded, final String fileName )
        throws Exception
    {
        assertEquals( DomHelper.serialize( DomHelper.parse( getSource( fileName ).openStream() ) ),
                      DomHelper.serialize( DomHelper.parse( upgraded ) ) );
    }

    protected CharSource getSource( final String name )
    {
        return Resources.asCharSource( this.getClass().getResource( name ), Charsets.UTF_8 );
    }
}
