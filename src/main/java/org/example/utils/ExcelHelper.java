package org.example.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelHelper {
    protected static final Logger logger = LogManager.getLogger();

    // Create a workbook
    public Workbook getWorkBook(String fileName) {
        logger.info("Create a workbook from file name: " + fileName);

        Workbook workbook = null;
        try (FileInputStream fis = new FileInputStream(fileName)) {
            if (fileName.toLowerCase().endsWith("xlsx")) {
                workbook = new XSSFWorkbook(fis);
            } else if (fileName.toLowerCase().endsWith("xls")) {
                workbook = new HSSFWorkbook(fis);
            } else {
                logger.error(new IllegalArgumentException("The specified file is not Excel file"));
                throw new IllegalArgumentException("The specified file is not Excel file");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return workbook;
    }

    // Close a workbook
    public void closeWorkbook(Workbook workbook) {
        logger.info("Close a workbook `{}`", workbook);

        try {
            workbook.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    // Verify cell value on column index and row index
    public String getCellValueOnColumn_RowIndex(String fileName, String sheetName, int columnIndex, int rowIndex) {
        Workbook workbook = getWorkBook(fileName);
        Sheet sheet = workbook.getSheet(sheetName);
        var cellValue = sheet.getRow(rowIndex).getCell(columnIndex).getStringCellValue().trim();

        logger.info("Cell on column index `{}` and row index `{}` has value `{}`", columnIndex, rowIndex, cellValue);
        closeWorkbook(workbook);
        return cellValue;
    }

    // Get row index when have column index and cell value
    public int getRowIndex(String fileName, String sheetName, int columnIndex, String cellValue) {
        int rowIndex = -1;

        Workbook workbook = getWorkBook(fileName);
        Sheet sheet = workbook.getSheet(sheetName);
        int numberOfRows = sheet.getPhysicalNumberOfRows();
        for (int i = 0; i < numberOfRows; i++) {
            var cellValueFromColumnAndRow = sheet.getRow(i).getCell(columnIndex).getStringCellValue().trim();
            if (cellValue.equalsIgnoreCase(cellValueFromColumnAndRow)) {
                rowIndex = i;
                break;
            }
        }

        logger.info("Row has index is `{}` with cell value `{}` and column index `{}` ", rowIndex, cellValue,
                columnIndex);
        closeWorkbook(workbook);
        return rowIndex;
    }

    // Get row index when have column name and cell value
    public int getRowIndex(String fileName, String sheetName, String columnName, String cellValue, int startRowIndex) {
        int rowIndex = -1;

        // Convert column name to column index
        int columnIndex = getColumnIndex(fileName, sheetName, columnName, startRowIndex);
        if (columnIndex == -1) {
            logger.error("Column name `{}` is not exists in file name `{}`", columnName, fileName);
            return rowIndex;
        }

        // Re-call getRowIndex function
        rowIndex = getRowIndex(fileName, sheetName, columnIndex, cellValue);

        logger.info("Row has index is `{}` with cell value `{}` and column name `{}` ", rowIndex, cellValue,
                columnName);
        return rowIndex;
    }

    // Get column index when have column name
    public int getColumnIndex(String fileName, String sheetName, String columnName, int startRowIndex) {
        int columnIndex = -1;

        Workbook workbook = getWorkBook(fileName);
        Sheet sheet = workbook.getSheet(sheetName);
        int noOfColumns = sheet.getRow(startRowIndex).getLastCellNum();

        for (int i = 0; i < noOfColumns; i++) {
            String columnValue = sheet.getRow(startRowIndex).getCell(i).getStringCellValue().trim();
            if (columnValue.equalsIgnoreCase(columnName)) {
                columnIndex = i;
                break;
            }
        }

        logger.info("Column index of column name `{}` is `{}`", columnName, columnIndex);
        closeWorkbook(workbook);
        return columnIndex;
    }

    // Get list of column header in excel file
    public List<String> getListOfColumnHeader(String fileName, String sheetName) {
        logger.info("Get list of column header on sheet name `{}` on file name `{}`", sheetName, fileName);

        Workbook workbook = getWorkBook(fileName);
        Sheet sheet = workbook.getSheet(sheetName);
        Row headerRow = sheet.getRow(0);

        List<String> listOfColumnHeader = new ArrayList<>();
        if (headerRow == null) {
            listOfColumnHeader = Collections.emptyList();
        } else {
            listOfColumnHeader = StreamSupport.stream(headerRow.spliterator(), false)
                    .filter(cell -> cell.getCellType() != CellType.BLANK)
                    .map(Cell::getStringCellValue)
                    .filter(cellValue -> cellValue != null && !cellValue.trim().isEmpty())
                    .map(String::trim)
                    .collect(Collectors.toList());
        }

        closeWorkbook(workbook);
        return listOfColumnHeader;
    }

    // Check sheet name is exists in excel file
    public Boolean checkSheetNameExists(String fileName, String sheetName) {
        boolean isSheetNameExists = false;

        Workbook workbook = getWorkBook(fileName);
        int numberOfSheets = workbook.getNumberOfSheets();

        for (int i = 0; i < numberOfSheets; i++) {
            if (workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {
                isSheetNameExists = true;
                break;
            }
        }

        logger.info("Sheet name `{}` is exists in file name `{}`", sheetName, fileName);
        closeWorkbook(workbook);
        return isSheetNameExists;
    }

    // Get number of row in excel file
    public int countNumberOfRows(String fileName, String sheetName) {
        int rowsCount = 0;

        Workbook workbook = getWorkBook(fileName);
        Sheet sheet = workbook.getSheet(sheetName);
        rowsCount = sheet.getPhysicalNumberOfRows();

        logger.info("Number of row in excel file is `{}`", rowsCount);
        closeWorkbook(workbook);
        return rowsCount;
    }

    // Check a column header is exists in excel file
    public Boolean checkColumnHeaderExists(String fileName, String sheetName, String expectedColumnHeader) {
        boolean isColumnHeaderExists = false;

        Workbook workbook = getWorkBook(fileName);

        List<String> actualColumnHeader = getListOfColumnHeader(fileName, sheetName);
        isColumnHeaderExists = actualColumnHeader.stream().anyMatch(expectedColumnHeader::equalsIgnoreCase);

        logger.info("Column header `{}` is exists = `{}` in file name `{}`", expectedColumnHeader, isColumnHeaderExists,
                fileName);
        closeWorkbook(workbook);
        return isColumnHeaderExists;
    }
}
