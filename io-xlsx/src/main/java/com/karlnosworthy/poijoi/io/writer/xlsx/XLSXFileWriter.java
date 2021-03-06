package com.karlnosworthy.poijoi.io.writer.xlsx;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Workbook;

import com.karlnosworthy.poijoi.io.SupportsFormat;
import com.karlnosworthy.poijoi.io.writer.FileWriter;

/**
 * An XLSXFileWriter writes the XLSX file out to an {@link File}
 * 
 * @author john.bartlett
 *
 */
@SupportsFormat(type = "XLSX")
public class XLSXFileWriter extends AbstractXLSXWriter<File> implements
		FileWriter {
	
	@Override
	boolean isValidOutput(File output) {
		if (output == null || output.isDirectory()) {
			return false;
		}
		return true;
	}
	
	@Override
	boolean write(File output, Workbook workbook) throws Exception {
		FileOutputStream fos = new FileOutputStream(output);
		workbook.write(fos);
		fos.close();
		return true;
	}

}
