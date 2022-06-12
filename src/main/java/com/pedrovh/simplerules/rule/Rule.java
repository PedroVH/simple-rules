package com.pedrovh.simplerules.rule;

import java.util.*;

public class Rule {

    private int priority = 1;
    private String name = "NAME";
    private String description = "DESCRIPTION";
    private List<Condition> conditions = new ArrayList<>();
    private List<Action> actions = new ArrayList<>();

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
