package com.camunda.dmn;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.assertj.core.api.Assertions;
import org.camunda.bpm.dmn.engine.impl.DmnDecisionTableImpl;
import org.camunda.bpm.dmn.engine.impl.DmnDecisionTableRuleImpl;
import org.camunda.bpm.engine.history.HistoricDecisionOutputInstance;
import org.camunda.bpm.engine.variable.value.TypedValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DmnAssertion {

  private final DmnEvalutationResult dmn;

  public final static DmnAssertion assertThat(DmnEvalutationResult dmn) {
    return new DmnAssertion(dmn);
  }

  public DmnAssertion hasResultSize(int size) {
    Assertions.assertThat(dmn.getResult()).hasSize(size);
    return this;
  }

  public DmnAssertion isDefinition(String key) {
    Assertions.assertThat(dmn.getDecisionDefinition().getKey()).isEqualTo(key);
    return this;
  }

  public DmnAssertion matchedRules(Integer... ruleInOrder) {
    List<HistoricDecisionOutputInstance> outputs = dmn.getDecisionInstance().getOutputs();
    Set<String> hitRules =
        outputs.stream().map(HistoricDecisionOutputInstance::getRuleId).collect(Collectors.toSet());

    Set<Integer> hitRuleNumber = new HashSet<>();

    DmnDecisionTableImpl decisionLogic =
        (DmnDecisionTableImpl) dmn.getDecisionDefinition().getDecisionLogic();
    List<DmnDecisionTableRuleImpl> rules = decisionLogic.getRules();
    for (DmnDecisionTableRuleImpl dmnRule : rules) {
      if (hitRules.contains(dmnRule.getId())) {
        hitRuleNumber.add(rules.indexOf(dmnRule) + 1);
      }
    }

    Assertions.assertThat(hitRuleNumber).containsExactly(ruleInOrder);
    return this;
  }


  public DmnResultAssertion ruleByOrder(Integer ruleOrder) {
    DmnDecisionTableImpl decisionLogic =
        (DmnDecisionTableImpl) dmn.getDecisionDefinition().getDecisionLogic();

    DmnDecisionTableRuleImpl rule = decisionLogic.getRules().get(ruleOrder - 1);

    Map<String, TypedValue> outputParams = dmn.getDecisionInstance().getOutputs().stream()
        .filter(o -> rule.getId().contentEquals(o.getRuleId()))
        .collect(Collectors.toMap(HistoricDecisionOutputInstance::getVariableName,
            HistoricDecisionOutputInstance::getTypedValue));

    return new DmnResultAssertion(this, outputParams);
  }

}
