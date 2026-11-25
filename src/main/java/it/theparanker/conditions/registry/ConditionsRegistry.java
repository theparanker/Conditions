package it.theparanker.conditions.registry;

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

import it.theparanker.conditions.conditions.Condition;
import it.theparanker.conditions.parser.ConditionParser;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class ConditionsRegistry {

    private final Logger log;

    private Map<String, Condition> conditionsMap;

    public ConditionsRegistry(Logger logger) {
        this.log = logger;
    }

    public Condition getCondition(String name) {
        return conditionsMap.get(name);
    }

    public void loadFromConfig(FileConfiguration config) {
        this.conditionsMap = new HashMap<>();

        ConfigurationSection conditionsSection = config.getConfigurationSection("conditions");
        List<Condition> conditions = ConditionParser.parseConditions(conditionsSection);

        for (Condition condition : conditions) {
            conditionsMap.put(condition.getName(), condition);
        }

        log.info("Loaded " + conditions.size() + " conditions!");
    }

}
