package com.pedrovh.simplerules.rule;

import java.util.Map;

@FunctionalInterface
public interface Condition {

    Condition TRUE = knowledge -> true;
    Condition FALSE = knowledge -> false;

    /**
     * Condition to execute a {@link Rule}.
     * @param knowledge the map containing the knowledge to evaluate.
     * @return true if it should execute, false otherwise.
     */
    boolean evaluate(final Map<String, Object> knowledge);

}
