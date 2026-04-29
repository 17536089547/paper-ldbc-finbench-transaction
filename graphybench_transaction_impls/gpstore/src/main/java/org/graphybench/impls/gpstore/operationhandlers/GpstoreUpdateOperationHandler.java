package org.graphybench.impls.gpstore.operationhandlers;

import org.graphybench.driver.driver.DbException;
import org.graphybench.driver.driver.Operation;
import org.graphybench.driver.driver.ResultReporter;
import org.graphybench.driver.driver.workloads.transaction.GraphyBenchNoResult;
import org.graphybench.impls.common.operationhandlers.UpdateOperationHandler;
import org.graphybench.impls.gpstore.GpstoreDbConnectionState;
import org.pkumod.gpstore.driver.Session;

import java.util.Map;

public abstract class GpstoreUpdateOperationHandler <TOperation extends Operation<GraphyBenchNoResult>>
        implements UpdateOperationHandler<TOperation, GpstoreDbConnectionState> {

    @Override
    public void executeOperation(TOperation operation,
                                 GpstoreDbConnectionState state,
                                 ResultReporter resultReporter) throws DbException {
        String querySpecification = getQueryString(state, operation);
        String cypher = formatQuery(querySpecification, operation);
        System.out.println(cypher);
        try (final Session session = state.getSession()) {
            session.run(cypher);
        } catch (Exception e) {
            throw new DbException(e);
        }
        resultReporter.report(0, GraphyBenchNoResult.INSTANCE, operation);
    }

    protected String formatQuery(String querySpecification, TOperation operation) {
        Map<String, Object> parameterSubstitutions = getParameters(operation);
        for (String parameter : parameterSubstitutions.keySet()) {
            querySpecification = querySpecification.replace(
                    "$" + parameter,
                    (String) parameterSubstitutions.get(parameter)
            );
        }
        return querySpecification;
    }

    protected abstract Map<String, Object> getParameters(TOperation operation);
}
