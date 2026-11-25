package it.theparanker.conditions.bootstrap;

/*
Copyright _theparanker_ (c) 2026

ALL RIGHTS RESERVED.
================================================================================================
1. **Proprietary Restriction**:
   This Software is the exclusive property of the author. The use, copy, modification, 
   or distribution of this code is strictly prohibited for anyone without an explicit, 
   certified written permission granted by the original author.

2. **Prohibited Use**:
   - Unauthorized commercial or non-commercial use is strictly forbidden.
   - Any unlicensed integration into proprietary or open-source software is prohibited.

3. **No Warranty**:
   The Software is provided “as is”, without warranty of any kind, express or implied.
=================================================================================================
By viewing or using this software, you agree to the terms above.
==================================================================================================
*/

import it.theparanker.conditions.ConditionsMain;
import it.theparanker.conditions.objects.Startable;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginBootstrap extends JavaPlugin {

    private Startable provider;

    @Override
    public void onLoad() {
        this.provider = new ConditionsMain(this);
        this.provider.init();
    }

    @Override
    public void onEnable() {
        this.provider.start();
    }

    @Override
    public void onDisable() {
        this.provider.stop();
    }
}
