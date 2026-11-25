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

import it.theparanker.conditions.conditions.Condition;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

public class PlaceholderAPIHook extends PlaceholderExpansion {

    private final ConditionsMain main;

    public PlaceholderAPIHook(ConditionsMain main) {
        this.main = main;
    }

    @Override
    public @NonNull String getIdentifier() {
        return "conditions";
    }

    @Override
    public @NonNull String getAuthor() {
        return "_theparanker_";
    }

    @Override
    public @NonNull String getVersion() {
        return "1.0";
    }

    @Override
    public String onPlaceholderRequest(Player player, @NonNull String params) {
        Condition condition = main.registry().getCondition(params);
        if (condition == null) return null;

        return condition.evaluate(player);
    }
}
