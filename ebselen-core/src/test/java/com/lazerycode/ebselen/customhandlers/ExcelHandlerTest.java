package com.lazerycode.ebselen.customhandlers;

import org.junit.Test;

import java.io.File;
import java.net.URI;
import java.net.URL;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class ExcelHandlerTest {

    private final URL excelFile = this.getClass().getResource("/xls/TestData.xls");

    @Test
    public void selectAnExcelSheet() throws Exception {
        ExcelHandler testExcelFile = new ExcelHandler(new File(new URI(excelFile.toExternalForm())));
        testExcelFile.selectSheet("Blank Sheet");
        assertThat(testExcelFile.selectedSheetName(), is(equalTo("Blank Sheet")));
    }

    @Test
    public void getCellData() throws Exception {
        ExcelHandler testExcelFile = new ExcelHandler(new File(new URI(excelFile.toExternalForm())));
        testExcelFile.selectSheet("Data Sheet");
        assertThat(testExcelFile.getCellData(1, 1).getContents(), is(equalTo("Test Data")));
        assertThat(testExcelFile.getCellData(1, 2).getContents(), is(equalTo("1")));
    }

    @Test
    public void getRowData() throws Exception {
        ExcelHandler testExcelFile = new ExcelHandler(new File(new URI(excelFile.toExternalForm())));
        testExcelFile.selectSheet("Data Sheet");
        assertThat(testExcelFile.getRow(1).size(), is(equalTo(2)));
        assertThat(testExcelFile.getRow(1).get(2).getContents(), is(equalTo("More Test Data")));
    }

    @Test
    public void getColumnData() throws Exception {
        ExcelHandler testExcelFile = new ExcelHandler(new File(new URI(excelFile.toExternalForm())));
        testExcelFile.selectSheet("Data Sheet");
        assertThat(testExcelFile.getColumn(1).size(), is(equalTo(4)));
        assertThat(testExcelFile.getColumn(1).get(1).getContents(), is(equalTo("Test Data")));
    }

    @Test
    public void getRowDataSkipFirstColumn() throws Exception {
        ExcelHandler testExcelFile = new ExcelHandler(new File(new URI(excelFile.toExternalForm())));
        testExcelFile.selectSheet("Data Sheet");
        assertThat(testExcelFile.getRow(1, true).size(), is(equalTo(1)));
        assertThat(testExcelFile.getRow(1, true).get(2).getContents(), is(equalTo("More Test Data")));
    }

    @Test
    public void getColumnDataSkipFirstRow() throws Exception {
        ExcelHandler testExcelFile = new ExcelHandler(new File(new URI(excelFile.toExternalForm())));
        testExcelFile.selectSheet("Data Sheet");
        assertThat(testExcelFile.getColumn(1, true).size(), is(equalTo(3)));
        assertThat(testExcelFile.getColumn(1, true).get(2).getContents(), is(equalTo("1")));
    }
}
