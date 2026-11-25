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

import it.theparanker.conditions.conditions.ConditionalExpression;
import it.theparanker.conditions.conditions.Operator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class ConditionalExpressionParser {

    public static List<ConditionalExpression> parseConditionalExpressions(List<String> raws) {
        List<ConditionalExpression> expressions = new ArrayList<>();

        for (String raw : raws) {
            expressions.add(parseConditionalExpression(raw));
        }

        return expressions;
    }

    private static ConditionalExpression parseConditionalExpression(String raw) {
        Matcher matcher = Operator.PATTERN.matcher(raw);

        if (matcher.find()) {
            String rawOperator = matcher.group(1);
            Operator operator = Operator.fromId(rawOperator);

            String leftSide = raw.substring(0, matcher.start()).trim();
            String rightSide = raw.substring(matcher.end()).trim();

            return new ConditionalExpression(operator, leftSide, rightSide);
        } else {
            throw new IllegalArgumentException("Unknown operator used in condition " + raw);
        }
    }

}
