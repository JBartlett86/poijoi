package com.karlnosworthy.poijoi.io.reader;

import java.sql.Connection;

/**
 * Reader that specifically uses a {@link Connection} for the Source
 * 
 * @author john.bartlett
 */
public interface JDBCConnectionReader extends Reader<Connection> {
	
	public abstract boolean isValidConnection(Connection connection);

}
