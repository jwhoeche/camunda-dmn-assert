package com.camunda.dmn;

import org.camunda.bpm.engine.history.HistoricDecisionOutputInstance;

public class DmnResultAssertionMapper {
  public static DmnResult map(HistoricDecisionOutputInstance histResult) {

    return new DmnResult(histResult.getVariableName(), histResult.getTypedValue());
  }

}
