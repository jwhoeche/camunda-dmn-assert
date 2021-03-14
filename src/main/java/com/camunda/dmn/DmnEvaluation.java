package com.camunda.dmn;

import java.util.List;
import java.util.Map;
import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.dmn.DecisionEvaluationBuilder;
import org.camunda.bpm.engine.history.HistoricDecisionInstance;
import org.camunda.bpm.engine.impl.dmn.entity.repository.DecisionDefinitionEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class DmnEvaluation {

  private final ProcessEngine processEngine;
  private final DecisionEvaluationBuilder evaluateDecisionByKey;

  public static DmnEvaluation createDmnEvaluation(ProcessEngine processEngine, String key) {
    return new DmnEvaluation(processEngine,
        processEngine.getDecisionService().evaluateDecisionTableByKey(key));
  }

  public DmnEvaluation variables(Map<String, Object> variables) {
    evaluateDecisionByKey.variables(variables);
    return this;
  }

  public DmnEvalutationResult evaluate() {
    DmnDecisionTableResult result = evaluateDecisionByKey.evaluate();
    List<HistoricDecisionInstance> list = processEngine.getHistoryService()
        .createHistoricDecisionInstanceQuery().includeInputs().includeOutputs().list();

    DecisionDefinitionEntity decisionDefinition = (DecisionDefinitionEntity) processEngine
        .getRepositoryService()
        .getDecisionDefinition(list.get(0).getDecisionDefinitionId());


    return new DmnEvalutationResult(result, list.get(0), decisionDefinition);
  }



}
