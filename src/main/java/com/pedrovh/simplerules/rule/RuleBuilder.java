package com.pedrovh.simplerules.rule;

import java.util.ArrayList;
import java.util.List;

public class RuleBuilder {

    private int priority = 1;
    private String name = "NAME";
    private String description = "DESCRIPTION";
    private final List<Condition> conditions = new ArrayList<>();
    private final List<Action> actions = new ArrayList<>();


    public RuleBuilder priority(int priority) {
        this.priority = priority;
        return this;
    }

    public RuleBuilder name(String name) {
        this.name = name;
        return this;
    }

    public RuleBuilder description(String description) {
        this.description = description;
        return this;
    }

    public RuleBuilder condition(Condition condition) {
        this.conditions.add(condition);
        return this;
    }

    public RuleBuilder action(Action action) {
        this.actions.add(action);
        return this;
    }

    public Rule build() {
        return new Rule(priority, name, description, conditions, actions);
    }

}
