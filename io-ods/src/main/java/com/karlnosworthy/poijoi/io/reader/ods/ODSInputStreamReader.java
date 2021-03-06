package com.karlnosworthy.poijoi.io.reader.ods;

import java.io.IOException;
import java.io.InputStream;

import org.odftoolkit.simple.SpreadsheetDocument;

import com.karlnosworthy.poijoi.io.SupportsFormat;
import com.karlnosworthy.poijoi.io.reader.InputStreamReader;

/**
 * An ODSInputStreamReader interacts with the ODS file using an {@link InputStream}
 * 
 * @author john.bartlett
 *
 */
@SupportsFormat(type = "ODS")
public class ODSInputStreamReader extends AbstractODSReader<InputStream> implements
		InputStreamReader {

	@Override
	SpreadsheetDocument getDocument(InputStream input) throws Exception {
		return SpreadsheetDocument.loadDocument(input);
	}

	@Override
	boolean isValidInput(InputStream input) {
		if (input == null) {
			return false;
		} else {
			try {
				input.available();
			} catch (IOException ioException) {
				return false;
			}
		}
		return true;
	}
}
