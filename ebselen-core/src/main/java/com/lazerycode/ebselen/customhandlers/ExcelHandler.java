/*
 * Copyright (c) 2010-2011 Ardesco Solutions - http://www.ardescosolutions.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lazerycode.ebselen.customhandlers;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import java.io.File;
import java.util.HashMap;

public class ExcelHandler {

    private HashMap<String, Sheet> importedSheets = new HashMap<String, Sheet>();
    private Sheet selectedSheet = null;

    public ExcelHandler(File excelWorkbook) throws Exception {
        Workbook workbook = Workbook.getWorkbook(excelWorkbook);
        String[] sheetNames = workbook.getSheetNames();
        Sheet[] sheetData = workbook.getSheets();
        if (sheetData.length != sheetNames.length) {
            throw new Exception("Cannot map sheets to sheet names");
        }
        for (int sheetNumber = 0; sheetNumber < sheetData.length; sheetNumber++) {
            this.importedSheets.put(sheetNames[sheetNumber], sheetData[sheetNumber]);
        }
    }

    public void selectSheet(String sheetName) throws Exception {
        if (this.importedSheets.containsKey(sheetName)) {
            this.selectedSheet = importedSheets.get(sheetName);
        } else {
            throw new Exception("Sheet with name '" + sheetName + "' doesn't exist!");
        }
    }

    public String selectedSheetName() throws Exception {
        return this.selectedSheet.getName();
    }

    /**
     * Get a specific column from the Excel Worksheet
     * (The first column is column 1)
     *
     * @param columnNumber
     * @return
     * @throws Exception
     */
    public HashMap<Integer, Cell> getColumn(int columnNumber) throws Exception {
        return getColumn(columnNumber, false);
    }

    /**
     * Get a specific column from the Excel Worksheet
     * You can optionally skip the top row of the column to ensure column titles are not pulled into the data set
     * (The first column is column 1)
     *
     * @param columnNumber
     * @param skipFirstRow
     * @return
     * @throws Exception
     */
    public HashMap<Integer, Cell> getColumn(int columnNumber, boolean skipFirstRow) throws Exception {
        if (this.selectedSheet.equals(null)) {
            throw new Exception("No sheet selected.  You must select a sheet before trying to get data!");
        } else if (columnNumber > this.selectedSheet.getColumns()) {
            throw new Exception("There are only " + this.selectedSheet.getColumns() + " columns in this sheet.  Unable to select column " + columnNumber + "!");
        }
        HashMap<Integer, Cell> selectedColumn = new HashMap<Integer, Cell>();
        for (Cell currentCell : this.selectedSheet.getColumn(columnNumber - 1)) {
            selectedColumn.put(selectedColumn.size() + 1, currentCell);
        }
        if (skipFirstRow) {
            selectedColumn.remove(1);
        }
        return selectedColumn;
    }

    /**
     * Get a specific row from the Excel Worksheet
     * (The first row is row 1)
     *
     * @param rowNumber
     * @return
     * @throws Exception
     */
    public HashMap<Integer, Cell> getRow(int rowNumber) throws Exception {
        return getRow(rowNumber, false);
    }

    /**
     * Get a specific row from the Excel Worksheet
     * You can optionally skip the first column of the row to ensure row titles are not pulled into the data set
     * (The first row is row 1)
     *
     * @param rowNumber
     * @param skipFirstColumn
     * @return
     * @throws Exception
     */
    public HashMap<Integer, Cell> getRow(int rowNumber, boolean skipFirstColumn) throws Exception {
        if (this.selectedSheet.equals(null)) {
            throw new Exception("No sheet selected.  You must select a sheet before trying to get data!");
        } else if (rowNumber > this.selectedSheet.getRows()) {
            throw new Exception("There are only " + this.selectedSheet.getRows() + " rows in this sheet.  Unable to select row " + rowNumber + "!");
        }
        HashMap<Integer, Cell> selectedRow = new HashMap<Integer, Cell>();
        for (Cell currentCell : this.selectedSheet.getRow(rowNumber - 1)) {
            selectedRow.put(selectedRow.size() + 1, currentCell);
        }
        if (skipFirstColumn) {
            selectedRow.remove(1);
        }
        return selectedRow;
    }

    /**
     * This will map two rows into a HashMap.
     * The key row will be converted into a string that can be used to reference the matching data in he value row.
     * You can optionally skip the first column of the row to ensure row titles are not pulled into the data set
     * (The first row is row 1)
     */
    public HashMap<String, Cell> mapTwoRows(int keyRow, int valueRow) throws Exception {
        return mapTwoRows(keyRow, valueRow, false);
    }

    /**
     * This will map two rows into a HashMap.
     * The key row will be converted into a string that can be used to reference the matching data in he value row.
     * You can optionally skip the first column of the row to ensure row titles are not pulled into the data set
     * (The first row is row 1)
     *
     * @param keyRow          The row number to be used as the HashMap key.
     * @param valueRow        The row number to be used as the HashMap value.
     * @param skipFirstColumn
     * @return
     * @throws Exception
     */
    public HashMap<String, Cell> mapTwoRows(int keyRow, int valueRow, boolean skipFirstColumn) throws Exception {
        if (this.selectedSheet.equals(null)) {
            throw new Exception("No sheet selected.  You must select a sheet before trying to get data!");
        } else if ((keyRow > this.selectedSheet.getRows()) || (valueRow > this.selectedSheet.getRows())) {
            throw new Exception("There are only " + this.selectedSheet.getRows() + " rows in this sheet.  Unable to select rows " + keyRow + " and " + valueRow + "!");
        }
        Cell[] hashMapKey = this.selectedSheet.getRow(keyRow - 1);
        Cell[] hashMapValue = this.selectedSheet.getRow(valueRow - 1);
        if (hashMapKey.length != hashMapValue.length) {
            throw new Exception("The rows supplied are different lengths, unable to map them!");
        }
        int startPoint = 0;
        if (skipFirstColumn) {
            startPoint = 1;
        }
        HashMap<String, Cell> selectedRows = new HashMap<String, Cell>();
        for (int i = startPoint; i < hashMapKey.length; i++) {
            selectedRows.put(hashMapKey[i].getContents(), hashMapValue[i]);
        }
        return selectedRows;
    }

    /**
     * This will map two columns into a HashMap.
     * The key column will be converted into a string that can be used to reference the matching data in he value column.
     * You can optionally skip the first row of the column to ensure column titles are not pulled into the data set
     * (The first row is row 1)
     */
    public HashMap<String, Cell> mapTwoColumns(int keyColumn, int valueColumn) throws Exception {
        return mapTwoColumns(keyColumn, valueColumn, false);
    }

    /**
     * This will map two columns into a HashMap.
     * The key column will be converted into a string that can be used to reference the matching data in he value column.
     * You can optionally skip the first row of the column to ensure column titles are not pulled into the data set
     * (The first row is row 1)
     *
     * @param keyColumn       The row number to be used as the HashMap key.
     * @param valueColumn     The row number to be used as the HashMap value.
     * @param skipFirstColumn
     * @return
     * @throws Exception
     */
    public HashMap<String, Cell> mapTwoColumns(int keyColumn, int valueColumn, boolean skipFirstColumn) throws Exception {
        if (this.selectedSheet.equals(null)) {
            throw new Exception("No sheet selected.  You must select a sheet before trying to get data!");
        } else if ((keyColumn > this.selectedSheet.getRows()) || (valueColumn > this.selectedSheet.getRows())) {
            throw new Exception("There are only " + this.selectedSheet.getRows() + " columnss in this sheet.  Unable to select columns " + keyColumn + " and " + valueColumn + "!");
        }
        Cell[] hashMapKey = this.selectedSheet.getRow(keyColumn - 1);
        Cell[] hashMapValue = this.selectedSheet.getRow(valueColumn - 1);
        if (hashMapKey.length != hashMapValue.length) {
            throw new Exception("The columns supplied are different lengths, unable to map them!");
        }
        int startPoint = 0;
        if (skipFirstColumn) {
            startPoint = 1;
        }
        HashMap<String, Cell> selectedColumns = new HashMap<String, Cell>();
        for (int i = startPoint; i < hashMapKey.length; i++) {
            selectedColumns.put(hashMapKey[i].getContents(), hashMapValue[i]);
        }
        return selectedColumns;
    }

    /**
     * Get a specific cell from the Excel Worksheet
     * (The top left cell is assumed to be in position 1, 1)
     *
     * @param column
     * @param row
     * @return
     * @throws Exception
     */
    public Cell getCellData(int column, int row) throws Exception {
        column--;
        row--;
        if (this.selectedSheet.equals(null)) {
            throw new Exception("No sheet selected.  You must select a sheet before trying to get data!");
        }
        return this.selectedSheet.getCell(column, row);
    }

}
