package com.karlnosworthy.poijoi.io.reader.xls;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.karlnosworthy.poijoi.model.PoijoiMetaData;

public class XLSInputStreamReaderTest {

	private XLSInputStreamReader inputStreamReader;
	
	@Before
	public void onSetup() {
		inputStreamReader = new XLSInputStreamReader();
	}
	
	@After
	public void onTeardown() {
		inputStreamReader = null;
	}
	
	/**
	 * Check that passing in a null stream is handled safety.
	 */
	@Test
	public void testReaderWithNullInputStream() throws Exception {
		PoijoiMetaData metaData = inputStreamReader.read(null, true);
		assertTrue(metaData == null);
	}
	
	@Test
	public void testReaderWithClosedInputStream() throws Exception {
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("test1.xls");
		assertTrue(inputStream != null);
		
		inputStream.close();
		
		PoijoiMetaData metaData = inputStreamReader.read(inputStream, true);
		assertTrue(metaData == null);
	}
	
	@Test
	public void testSuccessfulReadWithoutData() throws Exception {
		InputStream inputStream = null;
		
		try {
			inputStream = getClass().getClassLoader().getResourceAsStream("test1.xls");
			assertTrue(inputStream != null);
			
			PoijoiMetaData metaData = inputStreamReader.read(inputStream, false);
			assertNotNull(metaData);
			assertTrue(metaData.isReadData() == false);
			assertEquals(1, metaData.getTableDefinitions().size());
			assertTrue(metaData.getTableData().isEmpty());
		} finally {
			inputStream.close();
		}
	}
}
