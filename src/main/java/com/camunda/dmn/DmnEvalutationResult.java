package com.camunda.dmn;

import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.engine.history.HistoricDecisionInstance;
import org.camunda.bpm.engine.impl.dmn.entity.repository.DecisionDefinitionEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Getter(value = AccessLevel.PACKAGE)
public class DmnEvalutationResult {

  private final DmnDecisionTableResult result;
  private final HistoricDecisionInstance decisionInstance;
  private final DecisionDefinitionEntity decisionDefinition;

}
