package org.graphybench.impls.common.operationhandlers;

import org.graphybench.driver.driver.DbConnectionState;
import org.graphybench.driver.driver.DbException;
import org.graphybench.driver.driver.Operation;
import org.graphybench.driver.driver.OperationHandler;
import org.graphybench.driver.driver.ResultReporter;
import org.graphybench.driver.driver.workloads.transaction.GraphyBenchNoResult;

public interface UpdateOperationHandler<
        TOperation extends Operation<GraphyBenchNoResult>,
        TDbConnectionState extends DbConnectionState>
        extends OperationHandler<TOperation, TDbConnectionState> {

    @Override
    void executeOperation(TOperation operation, TDbConnectionState dbConnectionState, ResultReporter resultReporter) throws DbException;

    String getQueryString(TDbConnectionState state, TOperation operation);

}
