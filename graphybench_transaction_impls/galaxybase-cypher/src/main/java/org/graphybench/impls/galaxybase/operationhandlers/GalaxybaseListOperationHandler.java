package org.graphybench.impls.galaxybase.operationhandlers;


import com.graphdbapi.driver.Graph;
import com.graphdbapi.driver.v1.Record;
import com.graphdbapi.driver.v1.StatementResult;
import com.graphdbapi.driver.v1.Value;
import java.util.Map;
import org.graphybench.impls.galaxybase.GalaxybaseDbConnectionState;
import java.util.ArrayList;
import java.util.List;
import org.graphybench.driver.driver.DbException;
import org.graphybench.driver.driver.Operation;
import org.graphybench.driver.driver.ResultReporter;
import org.graphybench.impls.common.operationhandlers.ListOperationHandler;

public abstract class GalaxybaseListOperationHandler<TOperationResult, TOperation extends Operation<List<TOperationResult>>>
    implements ListOperationHandler<TOperationResult, TOperation, GalaxybaseDbConnectionState> {

    @Override
    public void executeOperation(TOperation operation,
                                 GalaxybaseDbConnectionState state,
                                 ResultReporter resultReporter) throws DbException {
        Graph graph = state.getGraph();
        List<TOperationResult> results = new ArrayList<>();
        int resultCount = 0;

        String query = getQuery(state, operation);
        Map<String, Value> params = getParams(state, operation);
        if (query.contains("$truncationOrder")) {
            query = query.replace("$truncationOrder", params.get("truncationOrder").asString());
            query = query.replace("$truncationLimit", String.valueOf(params.get("truncationLimit").asInt()));
        }

        try {
            StatementResult statementResult = graph.executeCypher(query, params);
            while (statementResult.hasNext()) {
                Record record = statementResult.next();
                resultCount++;
                TOperationResult tuple;
                tuple = convertSingleResult(record);
                results.add(tuple);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        resultReporter.report(resultCount, results, operation);
    }
    protected abstract TOperationResult convertSingleResult(Record record);

    protected abstract String getQuery(GalaxybaseDbConnectionState state, TOperation operation);

    protected abstract Map<String, Value> getParams(GalaxybaseDbConnectionState state, TOperation operation);

}
