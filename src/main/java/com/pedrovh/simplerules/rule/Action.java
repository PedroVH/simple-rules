package com.pedrovh.simplerules.rule;

import java.util.Map;

@FunctionalInterface
public interface Action {

    /**
     * An action for a {@link Rule} to execute.
     * @param knowledge a map with useful knowledge to execute the rule.
     */
    void execute(final Map<String, Object> knowledge);

}
