package com.karlnosworthy.poijoi.io.writer.xls;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import com.karlnosworthy.poijoi.io.writer.Writer.WriteType;
import com.karlnosworthy.poijoi.model.ColumnDefinition;
import com.karlnosworthy.poijoi.model.PoijoiMetaData;
import com.karlnosworthy.poijoi.model.TableDefinition;

public abstract class AbstractXLSWriter<T> {

	abstract void write(T output, Workbook workbook) throws Exception;
	
	public final void write(T output, PoijoiMetaData metaData, WriteType writeType)
			throws Exception {

		HSSFWorkbook wb = new HSSFWorkbook();

		Map<String, TableDefinition> tableDefinitions = metaData
				.getTableDefinitions();
		for (String tableName : tableDefinitions.keySet()) {
			HSSFSheet sheet = wb.createSheet(tableName);
			TableDefinition table = tableDefinitions.get(tableName);
			Map<String, ColumnDefinition> columnDefinitions = table
					.getColumnDefinitions();

			// do headers
			if (writeType != WriteType.DATA_ONLY) {
				HSSFRow headerRow = sheet.createRow(0);
				for (ColumnDefinition cd : columnDefinitions.values()) {
					HSSFCell cell = headerRow.createCell(cd.getColumnIndex());
					cell.setCellValue(cd.getColumnName());
				}
			}

			// write out the data into the sheet
			if (writeType != WriteType.SCHEMA_ONLY) {
				List<HashMap<String, Object>> tableData = metaData
						.getTableData(tableName);
				for (int rowIndex = 0; rowIndex < tableData.size(); rowIndex++) {
					int insertIndex = rowIndex;
					if (writeType != WriteType.DATA_ONLY) {
						insertIndex++; // increase the insert index if header
										// included
					}
					HSSFRow row = sheet.createRow(insertIndex);
					HashMap<String, Object> columnData = tableData
							.get(rowIndex);
					for (String columnName : columnData.keySet()) {
						ColumnDefinition columnDefinition = table
								.getColumnDefinition(columnName);
						HSSFCell cell = row.createCell(columnDefinition
								.getColumnIndex());
						switch (columnDefinition.getColumnType()) {
						case DATE:
							cell.setCellValue((Date) columnData.get(columnName));
							break;
						default:
							cell.setCellValue(columnData.get(columnName)
									.toString());
						}
					}
				}
			}

		}
		write(output, wb);
	}

}