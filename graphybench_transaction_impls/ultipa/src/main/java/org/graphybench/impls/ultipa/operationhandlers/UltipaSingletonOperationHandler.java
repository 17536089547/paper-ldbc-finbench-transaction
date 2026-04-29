package org.graphybench.impls.ultipa.operationhandlers;

import org.graphybench.driver.driver.DbException;
import org.graphybench.driver.driver.Operation;
import org.graphybench.driver.driver.ResultReporter;
import org.graphybench.driver.driver.workloads.transaction.GraphyBenchNoResult;
import org.graphybench.impls.common.operationhandlers.UpdateOperationHandler;
import org.graphybench.impls.ultipa.UltipaDbConnectionState;

import java.util.Map;

public abstract class UltipaSingletonOperationHandler <TOperation extends Operation<GraphyBenchNoResult>>
        implements UpdateOperationHandler<TOperation, UltipaDbConnectionState> {

    @Override
    public abstract void executeOperation(TOperation operation,
                                 UltipaDbConnectionState state,
                                 ResultReporter resultReporter) throws DbException;

    public abstract Map<String, Object> getParameters(UltipaDbConnectionState state, TOperation operation );
}
