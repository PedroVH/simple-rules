package com.pedrovh.simplerules.rule;

import java.util.*;

public class Rule {

    private final int priority;
    private final String name;
    private final String description;
    private final List<Condition> conditions;
    private final List<Action> actions;

    protected Rule(int priority, String name, String description, List<Condition> conditions, List<Action> actions) {
        this.priority = priority;
        this.name = name;
        this.description = description;
        this.conditions = conditions;
        this.actions = actions;
    }

    public int getPriority() {
        return priority;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Action> getActions() {
        return actions;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rule rule = (Rule) o;
        return priority == rule.priority && name.equals(rule.name) && description.equals(rule.description) && actions.equals(rule.actions) && conditions.equals(rule.conditions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(priority, name, description, actions, conditions);
    }
    
}
