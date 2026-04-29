package org.graphybench.impls.ultipa;

import org.graphybench.driver.driver.DbException;
import org.graphybench.impls.common.QueryStore;

public class UltipaQueryStore extends QueryStore {

    public UltipaQueryStore(String path)  throws DbException {
        super(path, ".uql");
    }


}
