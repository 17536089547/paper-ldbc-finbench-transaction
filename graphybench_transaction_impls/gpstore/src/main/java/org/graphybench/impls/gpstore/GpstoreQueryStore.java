package org.graphybench.impls.gpstore;

import org.graphybench.driver.driver.DbException;
import org.graphybench.impls.common.QueryStore;

public class GpstoreQueryStore extends QueryStore {

    public GpstoreQueryStore(String path) throws DbException {
        super(path, ".cypher");
    }


}
