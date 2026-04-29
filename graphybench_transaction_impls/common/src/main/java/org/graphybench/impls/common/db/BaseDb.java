package org.graphybench.impls.common.db;

import org.graphybench.driver.driver.Db;
import org.graphybench.impls.common.BaseDbConnectionState;
import org.graphybench.impls.common.QueryStore;

import java.io.IOException;

public abstract class BaseDb<TQueryStore extends QueryStore> extends Db {

    protected BaseDbConnectionState<TQueryStore> dcs;

    @Override
    protected void onClose() throws IOException {
        dcs.close();
    }

    @Override
    protected BaseDbConnectionState getConnectionState() {
        return dcs;
    }
    
}
