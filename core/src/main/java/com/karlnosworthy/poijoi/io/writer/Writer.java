package com.karlnosworthy.poijoi.io.writer;

import com.karlnosworthy.poijoi.model.PoijoiMetaData;

public interface Writer<T> {
	
	public enum WriteType {
		DATA_ONLY,
		SCHEMA_ONLY,
		BOTH
	}

	void write(T output, PoijoiMetaData metaData, WriteType writeType) throws Exception;
}
