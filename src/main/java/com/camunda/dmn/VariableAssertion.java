package com.camunda.dmn;

import org.assertj.core.api.Assertions;
import org.camunda.bpm.engine.variable.value.TypedValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

public class VariableAssertion {
  private final DmnResultAssertion dmnAssertion;
  private final TypedValue testeeValue;

  public DmnResultAssertion and() {
    return dmnAssertion;
  }

  public VariableAssertion isEqualTo(Object resultName) {
    assert resultName != null;
    Assertions.assertThat(testeeValue.getValue()).isEqualTo(resultName);

    return this;
  }

  public VariableAssertion isNotNull() {
    Assertions.assertThat(testeeValue).isNotNull();

    return this;
  }

  public VariableAssertion isNull() {
    Assertions.assertThat(testeeValue).isNull();
    return this;
  }
}
