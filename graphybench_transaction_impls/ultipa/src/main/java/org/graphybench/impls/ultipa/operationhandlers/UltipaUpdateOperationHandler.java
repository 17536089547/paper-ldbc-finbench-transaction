package org.graphybench.impls.ultipa.operationhandlers;

import com.ultipa.sdk.connect.Connection;
import org.graphybench.driver.driver.DbException;
import org.graphybench.driver.driver.Operation;
import org.graphybench.driver.driver.ResultReporter;
import org.graphybench.driver.driver.workloads.transaction.GraphyBenchNoResult;
import org.graphybench.impls.common.operationhandlers.UpdateOperationHandler;
import org.graphybench.impls.ultipa.UltipaDbConnectionState;
import org.graphybench.impls.ultipa.converter.UltipaConverter;

import java.util.Map;

public abstract class UltipaUpdateOperationHandler <TOperation extends Operation<GraphyBenchNoResult>>
        implements UpdateOperationHandler<TOperation,UltipaDbConnectionState> {

    @Override
    public void executeOperation(TOperation operation,
                                 UltipaDbConnectionState state,
                                 ResultReporter resultReporter) throws DbException {
        Connection conn = state.getConn();
        Map<String, Object> map = getParameters(state,operation);
        String query = getQueryString(state,operation);

        String[] querys = query.split(";");
        for (int i = 0; i < querys.length; i++) {
            String uql = UltipaConverter.replaceVariables(querys[i],map);
            System.out.println(uql);
            conn.uql(uql);
        }
        resultReporter.report(0, GraphyBenchNoResult.INSTANCE, operation);
    };


    public abstract Map<String, Object> getParameters(UltipaDbConnectionState state, TOperation operation );

}
