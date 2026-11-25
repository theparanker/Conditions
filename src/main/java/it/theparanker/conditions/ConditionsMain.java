package it.theparanker.conditions;

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

import it.theparanker.conditions.commands.ConditionCommand;
import it.theparanker.conditions.objects.Startable;
import it.theparanker.conditions.registry.ConditionsRegistry;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

@Getter @Accessors(fluent = true)
public class ConditionsMain implements Startable {

    private final JavaPlugin javaPlugin;
    private final Logger log;

    private ConditionsRegistry registry;

    public ConditionsMain(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;
        this.log = javaPlugin.getLogger();
    }

    @Override
    public void init() {
        javaPlugin.saveDefaultConfig();

        this.registry = new ConditionsRegistry(log);
        this.registry.loadFromConfig(javaPlugin.getConfig());
    }

    @Override
    public void start() {
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) {
            log.severe("PlaceholderAPI not found, disabling the plugin!");
            Bukkit.getPluginManager().disablePlugin(javaPlugin);
            return;
        }

        new PlaceholderAPIHook(this).register();

        ConditionCommand conditionCmd = new ConditionCommand(this);
        javaPlugin.getCommand("condition").setExecutor(conditionCmd);
        javaPlugin.getCommand("condition").setTabCompleter(conditionCmd);
    }

    public void reload() {
        javaPlugin.reloadConfig();
        this.registry.loadFromConfig(javaPlugin.getConfig());
        log.info("Plugin reloaded!");
    }
}
