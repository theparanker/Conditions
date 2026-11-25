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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.regex.Pattern;

@RequiredArgsConstructor
public enum Operator {

    EQUALS("==", (left, right) -> Objects.equals(left, right)),
    NOT_EQUALS("!=", (left, right) -> !Objects.equals(left, right)),

    ENDS_WITH("-|", (left, right) ->  left.endsWith(right)),
    NOT_ENDS_WITH("!-|", (left, right) ->  !left.endsWith(right)),

    STARTS_WITH("|-", (left, right) -> left.startsWith(right)),
    NOT_STARTS_WITH("!|-", (left, right) -> !left.startsWith(right)),

    CONTAINS("<-", (left, right) -> left.contains(right)),
    NOT_CONTAINS("<-", (left, right) -> !left.contains(right)),

    LESS_THAN("<", (left, right) -> Double.parseDouble(left) < Double.parseDouble(right)),
    LESS_THAN_OR_EQUAL("<=", (left, right) -> Double.parseDouble(left) <= Double.parseDouble(right)),

    GREATER_THAN(">", (left, right) -> Double.parseDouble(left) >= Double.parseDouble(right)),
    GREATER_THAN_OR_EQUAL(">=", (left, right) -> Double.parseDouble(left) >= Double.parseDouble(right));

    public static final String REGEX;
    public static final Pattern PATTERN;

    private static final Map<String, Operator> ids = new HashMap<>();

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("(");

        Operator[] operators = values();
        for (int i = 0; i < operators.length; i++) {
            sb.append(Pattern.quote(operators[i].id));
            if (i < operators.length - 1) {
                sb.append("|");
            }
        }
        sb.append(")");

        REGEX = sb.toString();
        PATTERN = Pattern.compile("\\s*" + REGEX + "\\s*");

        for (Operator operator : values()) {
            ids.put(operator.id, operator);
        }
    }

    public static Operator fromId(String id) {
        return ids.get(id);
    }

    private final String id;

    private final BiFunction<String, String, Boolean> function;

    public boolean isMet(String left, String right) {
        return function.apply(left, right);
    }

}
