package org.graphybench.impls.common.operationhandlers;

import org.graphybench.driver.driver.DbConnectionState;
import org.graphybench.driver.driver.DbException;
import org.graphybench.driver.driver.Operation;
import org.graphybench.driver.driver.OperationHandler;
import org.graphybench.driver.driver.ResultReporter;

public interface SingletonOperationHandler<
        TOperationResult,
        TOperation extends Operation<TOperationResult>,
        TDbConnectionState extends DbConnectionState>
        extends OperationHandler<TOperation, TDbConnectionState> {

    @Override
    void executeOperation(TOperation operation, TDbConnectionState dbConnectionState, ResultReporter resultReporter) throws DbException;

    String getQueryString(TDbConnectionState state, TOperation operation);

}
