package com.camunda.dmn;

import java.util.Map;
import org.camunda.bpm.engine.variable.value.TypedValue;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class DmnResultAssertion {
  private final DmnAssertion dmnAssertion;

  private final Map<String, TypedValue> variables;



  public VariableAssertion variable(String resultName) {
    TypedValue typedValue = variables.get(resultName);
    return new VariableAssertion(this, typedValue);
  }



  public DmnResultAssertion ruleByOrder(Integer ruleOrder) {
    return dmnAssertion.ruleByOrder(ruleOrder);
  }



}
