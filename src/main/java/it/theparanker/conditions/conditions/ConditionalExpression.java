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

import lombok.RequiredArgsConstructor;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class ConditionalExpression {

    private final Operator operator;

    private final String leftSide;
    private final String rightSide;

    public boolean evaluate(Player player) {
        String parsedLeft = PlaceholderAPI.setPlaceholders(player, leftSide);
        String parsedRight = PlaceholderAPI.setPlaceholders(player, rightSide);

        return operator.isMet(parsedLeft, parsedRight);
    }

}
