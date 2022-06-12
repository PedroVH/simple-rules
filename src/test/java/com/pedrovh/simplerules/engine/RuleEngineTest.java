package com.pedrovh.simplerules.engine;

import com.pedrovh.simplerules.rule.Action;
import com.pedrovh.simplerules.rule.Condition;
import com.pedrovh.simplerules.rule.Rule;
import com.pedrovh.simplerules.rule.RuleBuilder;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RuleEngineTest {

    @Test
    void conditionFalseTest() {
        Rule rule = new RuleBuilder()
                .condition(Condition.FALSE)
                .action(knowledge -> fail("Condition was false, rule should not be executed."))
                .build();

        new RuleEngine().execute(new HashMap<>(), rule);
    }

    @Test
    void conditionTrueTest() {
        String RAN = "ran";
        Map<String, Object> knowledge = new HashMap<>();
        Rule rule = new RuleBuilder()
                .condition(Condition.TRUE)
                .action(kn -> kn.put(RAN, true))
                .build();

        new RuleEngine().execute(knowledge, rule);
        assertTrue(knowledge.containsKey(RAN));
    }

    @Test
    void multipleConditionsTest() {
        Rule rule = new RuleBuilder()
                .condition(Condition.TRUE)
                .condition(Condition.FALSE)
                .action(knowledge -> fail("One of the conditions was false, rule should not be executed."))
                .build();

        new RuleEngine().execute(new HashMap<>(), rule);
    }

    @Test
    void simpleRuleTest() {
        String THIRSTY = "thirsty";
        Map<String, Object> knowledgeBase = new HashMap<>() {{ put(THIRSTY, true); }};

        Condition isThirsty = knowledge -> (boolean) knowledge.get(THIRSTY);
        Action drinkWater = knowledge -> knowledge.put(THIRSTY, false);

        Rule rule = new RuleBuilder()
                .name("Drink water")
                .description("Drinks water if thirsty.")
                .condition(isThirsty)
                .action(drinkWater)
                .build();

        new RuleEngine().execute(knowledgeBase, rule);
        assertFalse((Boolean) knowledgeBase.get(THIRSTY));
    }

    @Test
    void multipleRulesTest() {
        String AGE = "AGE";
        String LEGAL_AGE = "LEGAL_AGE";
        String DRANK = "DRANK";

        Map<String, Object> knowledgeBase = new HashMap<>() {{ put(AGE, 18); }};

        Rule legalAgeRule = new RuleBuilder()
                .name("Legal age")
                .description("If age is bigger or equal to 18 then sets legal_age to true")
                .condition(knowledge -> (int)knowledge.get(AGE) >= 18)
                .action(knowledge -> knowledge.put(LEGAL_AGE, true))
                .build();

        Rule drinkAlcohol = new RuleBuilder()
                .name("Drink alcohol")
                .description("Drinks alcohol if its of legal age.")
                .condition(knowledge -> (boolean) knowledge.get(LEGAL_AGE))
                .action(knowledge -> knowledge.put(DRANK, true))
                .build();

        new RuleEngine().execute(knowledgeBase, legalAgeRule, drinkAlcohol);
        assertTrue((Boolean) knowledgeBase.get(LEGAL_AGE));
        assertTrue((Boolean) knowledgeBase.get(DRANK));
    }

    @Test
    void rulesPriorityOrderTest() {
        Map<String, Object> knowledgeBase = new HashMap<>() {{ put("fail", true); }};

        Rule makeNotFail = new RuleBuilder()
                .priority(1)
                .name("Shouldn't fail")
                .condition(Condition.TRUE)
                .action(knowledge -> knowledge.put("fail", false))
                .build();

        Rule shouldFail = new RuleBuilder()
                .priority(2)
                .name("Should fail")
                .condition(knowledge -> (boolean) knowledge.get("fail"))
                .action(knowledge -> fail("Rule should not be executed."))
                .build();

        new RuleEngine().execute(knowledgeBase, shouldFail, makeNotFail);
    }

}
