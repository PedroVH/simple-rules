package com.pedrovh.simplerules.engine;

import com.pedrovh.simplerules.rule.Rule;

import java.util.*;

public class RuleEngine {

    public void execute(Map<String, Object> knowledge, Collection<Rule> rules) {
        Objects.requireNonNull(rules);

        rules.stream()
                .sorted(Comparator.comparingInt(Rule::getPriority))
                .forEachOrdered(rule -> {
            if(rule.getConditions().stream().allMatch(condition -> condition.evaluate(knowledge)))
                rule.getActions().forEach(action -> action.execute(knowledge));
        });
    }

    public void execute(Map<String, Object> knowledge, Rule... rules) {
        execute(knowledge, Arrays.stream(rules).toList());
    }

}
