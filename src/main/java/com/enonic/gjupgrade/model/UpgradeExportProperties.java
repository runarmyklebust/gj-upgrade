package com.enonic.gjupgrade.model;

import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.io.CharSource;

import com.enonic.gjupgrade.UpgradeException;
import com.enonic.gjupgrade.UpgradeHandler;


public class UpgradeExportProperties
    extends AbstractUpgradeModel
{
    private static final String KEY_VALUE_SEPERATOR = " = ";

    private static final String XP_VERSION_KEY = "xp.version";

    private static final String XP_VERSION_REGEXP = "[\\w\\.]+$";

    private static final Pattern EXPORT_PROPERTIES_PATTERN =
        Pattern.compile( XP_VERSION_KEY + KEY_VALUE_SEPERATOR + XP_VERSION_REGEXP, Pattern.MULTILINE );


    @Override
    public boolean supports( final Path path, final String repositoryName, final String branchName )
    {
        return false;
    }

    public String upgrade( final Path path, final CharSource source )
    {
        String upgradedContent = null;
        try
        {
            upgradedContent = source.read();
            final Matcher matcher = EXPORT_PROPERTIES_PATTERN.matcher( upgradedContent );

            if ( matcher.find() )
            {
                upgradedContent =
                    upgradedContent.replaceFirst( matcher.group( 0 ), XP_VERSION_KEY + KEY_VALUE_SEPERATOR + UpgradeHandler.XP_VERSION );
            }
        }
        catch ( Exception e )
        {
            throw new UpgradeException( "Failed to upgrade model " + this.getClass().getName() + " for path '" + path + "'", e );
        }

        return upgradedContent;
    }

    @Override
    protected String getLogMsg()
    {
        return "UpgradeExportProperties: Set export properties for upgraded version";
    }
}