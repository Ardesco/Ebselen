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
    public Cell[] getColumn(int columnNumber) throws Exception {
        if (this.selectedSheet.equals(null)) {
            throw new Exception("No sheet selected.  You must select a sheet before trying to get data!");
        } else if (columnNumber > this.selectedSheet.getColumns()) {
            throw new Exception("There are only " + this.selectedSheet.getColumns() + " columns in this sheet.  Unable to select column " + columnNumber + "!");
        }
        return this.selectedSheet.getColumn(columnNumber);
    }

    /**
     * Get a specific row from the Excel Worksheet
     * (The first row is row 1)
     *
     * @param rowNumber
     * @return
     * @throws Exception
     */
    public Cell[] getRow(int rowNumber) throws Exception {
        if (this.selectedSheet.equals(null)) {
            throw new Exception("No sheet selected.  You must select a sheet before trying to get data!");
        } else if (rowNumber > this.selectedSheet.getRows()) {
            throw new Exception("There are only " + this.selectedSheet.getRows() + " rows in this sheet.  Unable to select row " + rowNumber + "!");
        }
        return this.selectedSheet.getRow(rowNumber);
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
