package it.theparanker.conditions.conditions;

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

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

import java.util.List;

@AllArgsConstructor
public class Condition {

    @Getter
    private final String name;

    private final List<ConditionalExpression> conditions;

    private final Type type;

    private final String trueText;
    private final String falseText;

    public String evaluate(Player player) {
        if (conditions.size() == 1) {
            if (conditions.getFirst().evaluate(player)) {
                return PlaceholderAPI.setPlaceholders(player, trueText);
            } else {
                return PlaceholderAPI.setPlaceholders(player, falseText);
            }
        }

        for (ConditionalExpression expr : conditions) {
            boolean result = expr.evaluate(player);

            if (type == Type.AND && !result) {
                return PlaceholderAPI.setPlaceholders(player, falseText);
            }
            else if (type == Type.OR && result) {
                return PlaceholderAPI.setPlaceholders(player, trueText);
            }
        }

        if (type == Type.AND) {
            return PlaceholderAPI.setPlaceholders(player, trueText);
        } else {
            return PlaceholderAPI.setPlaceholders(player, falseText);
        }
    }

    public enum Type {
        AND,
        OR
    }

}
