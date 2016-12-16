package com.enonic.gjupgrade;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import static org.junit.Assert.*;

public class UpgradePathHelperTest
{

    @Test
    public void generateUpgradeTargetPath()
        throws Exception
    {
        final Path targetPath = UpgradePathHelper.generateUpgradeTargetPath( Paths.get( "my", "test", "path" ), "myDump" );

        assertEquals( Paths.get( "my", "test", "path", "myDump_upgraded_" + UpgradeHandler.XP_VERSION ).toString(), targetPath.toString() );

    }
}