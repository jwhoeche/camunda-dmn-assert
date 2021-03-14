package com.camunda.dmn;

import org.camunda.bpm.engine.variable.value.TypedValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class DmnResult {

  private final String key;
  private final TypedValue value;

}
