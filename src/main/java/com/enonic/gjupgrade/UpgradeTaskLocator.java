package com.enonic.gjupgrade;

import java.util.List;

import com.google.common.collect.Lists;

import com.enonic.gjupgrade.model.UpgradeModel;
import com.enonic.gjupgrade.model.UpgradeModel001;


final class UpgradeTaskLocator
{
    private final List<UpgradeModel> upgradeModels;

    public UpgradeTaskLocator()
    {
        this.upgradeModels = Lists.newArrayList();
        this.upgradeModels.add( new UpgradeModel001() );
    }

    public List<UpgradeModel> getUpgradeModels()
    {
        return upgradeModels;
    }
}
