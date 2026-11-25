package it.theparanker.conditions.parser;

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
import it.theparanker.conditions.conditions.ConditionalExpression;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

public class ConditionParser {

    public static List<Condition> parseConditions(ConfigurationSection conditionsSection) {
        List<Condition> conditions = new ArrayList<>();
        if (conditionsSection == null) return conditions;

        for (String key : conditionsSection.getKeys(false)) {
            ConfigurationSection conditionSection = conditionsSection.getConfigurationSection(key);

            if (conditionSection != null) {
                conditions.add(parseCondition(key, conditionSection));
            }
        }

        return conditions;
    }

    private static Condition parseCondition(String name, ConfigurationSection conditionSection) {
        List<ConditionalExpression> expressions = ConditionalExpressionParser.parseConditionalExpressions(conditionSection.getStringList("conditions"));

        Condition.Type type = Condition.Type.valueOf(conditionSection.getString("type", "AND").toUpperCase());

        String trueText = conditionSection.getString("true");
        String falseText = conditionSection.getString("false");

        return new Condition(name, expressions, type, trueText, falseText);
    }

}
