package com.enonic.gjupgrade;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

import com.google.common.base.Preconditions;

public final class UpgradeHandler
{
    private final Path root;

    private final UpgradeTaskLocator upgradeTaskLocator;

    public static final String XP_VERSION = System.getProperty( "xp-version" );

    private final Logger LOG = Logger.getLogger( UpgradeHandler.class.getName() );

    private UpgradeHandler( Builder builder )
    {
        this.root = builder.root;
        this.upgradeTaskLocator = new UpgradeTaskLocator();
    }

    public static Builder create()
    {
        return new Builder();
    }

    public void execute()
    {
        verifyRoot();

        LOG.info( "Starting upgrade..." );

        RepoNodesHandler.create().
            sourceRoot( root ).
            upgradeModels( this.upgradeTaskLocator.getUpgradeModels() ).
            build().
            execute();
    }

    private void verifyRoot()
    {
        if ( !Files.exists( root ) )
        {
            throw new UpgradeException( "Upgrade root does not exist" );
        }

        if ( !Files.isDirectory( root ) )
        {
            throw new UpgradeException( "Upgrade root is not directory" );
        }
    }

    public static final class Builder
    {
        private Path root;

        private Builder()
        {
        }

        public Builder sourceRoot( Path root )
        {
            this.root = root;
            return this;
        }

        private void validate()
        {
            Preconditions.checkNotNull( this.root );
        }

        public UpgradeHandler build()
        {
            this.validate();
            return new UpgradeHandler( this );
        }
    }
}
