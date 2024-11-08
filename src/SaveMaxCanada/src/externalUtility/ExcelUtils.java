package externalUtility;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;

import configuration.Constants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;

public class ExcelUtils {
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static org.apache.poi.ss.usermodel.Cell Cell;
	private static XSSFRow Row;
	private static MissingCellPolicy xRow;
	public static int rowCount = 0;

	public static void setExcelFile(String Path) throws Exception {
		try {
			FileInputStream ExcelFile = new FileInputStream(Path);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
		} catch (Exception e) {
			System.out.println("setExcelFileException: " + e);
		}
	}

	@SuppressWarnings("deprecation")
	public static String getCellData(int RowNum, int ColNum, String SheetName) throws Exception {
		try {
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			Cell.setCellType(CellType.STRING);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("getCellDataException: " + e);
			return "";
		}
	}
	
	public static int getRowCount() {
		try {
			rowCount = ExcelWSheet.getLastRowNum();
		} catch (Exception e) {
			System.out.println("getRowCountException: " + e);
		}
		return rowCount;
	}

	public static int getRowCount(String SheetName) {
		try {
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			rowCount = ExcelWSheet.getLastRowNum();
		} catch (Exception e) {
			System.out.println("getRowCountException: " + e);
		}
		return rowCount;
	}

	@SuppressWarnings("static-access")
	public static void setCellData(String Result, int RowNum, int ColNum, String SheetName) throws Exception {
		try {
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			Row = ExcelWSheet.getRow(RowNum);
			Cell = Row.getCell(ColNum, xRow.RETURN_BLANK_AS_NULL);
			// Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
			if (Cell == null) {
				Cell = Row.createCell(ColNum);
				Cell.setCellValue(Result);
			} else {
				Cell.setCellValue(Result);
			}
			FileOutputStream fileOut = new FileOutputStream(Constants.PathTestData);
			ExcelWBook.write(fileOut);
			// fileOut.flush();
			fileOut.close();
			ExcelWBook = new XSSFWorkbook(new FileInputStream(Constants.PathTestData));
		} catch (Exception e) {
			System.out.println("setCellDataException: " + e);
		}
	}

	public static void setStyleFailed(int RowNum, int ColNum, String SheetName) throws Exception {
		try {
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			CellStyle style = ExcelWBook.createCellStyle();
			style.setFillForegroundColor(IndexedColors.RED.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			style.setBorderBottom(BorderStyle.THIN);
			style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderTop(BorderStyle.THIN);
			style.setTopBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderRight(BorderStyle.THIN);
			style.setRightBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderLeft(BorderStyle.THIN);
			style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			Cell.setCellStyle(style);
			FileOutputStream fileOut = new FileOutputStream(Constants.PathTestData);
			ExcelWBook.write(fileOut);
			// fileOut.flush();
			fileOut.close();
			ExcelWBook = new XSSFWorkbook(new FileInputStream(Constants.PathTestData));
		} catch (Exception e) {
			System.out.println("setStyleFailed: " + e);
		}
	}
	
	public static void insertRow(int startRowIndex, int endRowIndex, int numRowsToInsert, String sheetName)
			throws Exception {
		try {
			ExcelWSheet = ExcelWBook.getSheet(sheetName);
			int lastRowNum = ExcelWSheet.getLastRowNum();

			XSSFWorkbook tempWorkbook = new XSSFWorkbook();
			XSSFSheet tempSheet = tempWorkbook.createSheet(sheetName);

			// Copy the first row (column names)
			XSSFRow firstRow = ExcelWSheet.getRow(0);
			XSSFRow newFirstRow = tempSheet.createRow(0);
			copyRow(firstRow, newFirstRow);

			// Copy rows before the insertion point
			for (int i = 1; i <= startRowIndex - 1; i++) {
				XSSFRow sourceRow = ExcelWSheet.getRow(i);
				XSSFRow newRow = tempSheet.createRow(i);

				if (sourceRow != null) {
					copyRow(sourceRow, newRow);
				}
			}

			// Insert new rows
			for (int i = 0; i < numRowsToInsert; i++) {
				int rowIndex = startRowIndex + i;
				XSSFRow newRow = tempSheet.createRow(rowIndex);
				XSSFRow sourceRow = ExcelWSheet.getRow(startRowIndex);

				if (sourceRow != null) {
					copyRow(sourceRow, newRow);
				}
			}

			// Copy remaining rows after the insertion point
			for (int i = endRowIndex; i <= lastRowNum; i++) {
				XSSFRow sourceRow = ExcelWSheet.getRow(i);
				XSSFRow newRow = tempSheet.createRow(i + numRowsToInsert);

				if (sourceRow != null) {
					copyRow(sourceRow, newRow);
				}
			}

			// Write the modified workbook to a temporary file
			String tempFilePath = Constants.PathTestData + ".tmp";
			FileOutputStream fileOut = null;
			try {
				fileOut = new FileOutputStream(tempFilePath);
				tempWorkbook.write(fileOut);
			} finally {
				if (fileOut != null) {
					fileOut.close();
				}
			}

			// Replace the original file with the temporary file
			File originalFile = new File(Constants.PathTestData);
			File tempFile = new File(tempFilePath);
			if (originalFile.exists()) {
				originalFile.delete();
			}
			tempFile.renameTo(originalFile);
			ExcelWBook = new XSSFWorkbook(new FileInputStream(Constants.PathTestData));
			ExcelWSheet = ExcelWBook.getSheet(sheetName);
		} catch (Exception e) {
			System.out.println("insertRowsBetween: " + e);
		}
	}
	
	private static void copyRow(XSSFRow sourceRow, XSSFRow newRow) {
		int lastCellNum = sourceRow.getLastCellNum();

		for (int i = 0; i < lastCellNum; i++) {
			Cell sourceCell = sourceRow.getCell(i);
			Cell newCell = newRow.createCell(i);

			if (sourceCell != null) {
				switch (sourceCell.getCellType()) {
				case STRING:
					newCell.setCellValue(sourceCell.getStringCellValue());
					break;
				case NUMERIC:
					newCell.setCellValue(sourceCell.getNumericCellValue());
					break;
				case BOOLEAN:
					newCell.setCellValue(sourceCell.getBooleanCellValue());
					break;
				case FORMULA:
					newCell.setCellFormula(sourceCell.getCellFormula());
					break;
				default:
					break;
				}
			}
		}
	}
	
	public static void removeRows(int startRowIndex, int endRowIndex, String sheetName) throws Exception {
        try {
            ExcelWSheet = ExcelWBook.getSheet(sheetName);
            int lastRowNum = ExcelWSheet.getLastRowNum();

            if (startRowIndex < 0 || startRowIndex > endRowIndex || endRowIndex > lastRowNum) {
                throw new IllegalArgumentException("Invalid startRowIndex or endRowIndex");
            }

            // Shift remaining rows up to replace the removed rows
            for (int i = endRowIndex + 1; i <= lastRowNum; i++) {
                XSSFRow sourceRow = ExcelWSheet.getRow(i);
                XSSFRow targetRow = ExcelWSheet.getRow(i - (endRowIndex - startRowIndex + 1));

                if (sourceRow != null && targetRow != null) {
                    copyRow(sourceRow, targetRow);
                }
            }

            // Clear the remaining rows at the end
            for (int i = lastRowNum; i > lastRowNum - (endRowIndex - startRowIndex + 1); i--) {
                ExcelWSheet.removeRow(ExcelWSheet.getRow(i));
            }

            // Adjust the row count
            rowCount -= (endRowIndex - startRowIndex + 1);

            // Write the modified workbook
            try (FileOutputStream fileOut = new FileOutputStream(Constants.PathTestData)) {
                ExcelWBook.write(fileOut);
            }
        } catch (Exception e) {
            System.out.println("removeRows: " + e);
        }
    }
	
}