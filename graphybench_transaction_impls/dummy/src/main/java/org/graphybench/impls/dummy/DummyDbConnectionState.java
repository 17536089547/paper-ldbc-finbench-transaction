package org.graphybench.impls.dummy;

import org.graphybench.driver.driver.DbConnectionState;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;

public class DummyDbConnectionState extends DbConnectionState {

	public DummyDbConnectionState() {
	}

	@Override
	public void close() throws IOException {
	}

}
