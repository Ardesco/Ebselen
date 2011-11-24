//TODO pull out anything useful and delete the rest
package com.lazerycode.ebselen.customhandlers;

import com.lazerycode.ebselen.EbselenCore;
import com.lazerycode.ebselen.handlers.XMLHandler;

import java.util.*;

public class validateExcelReport extends EbselenCore {

    private HashMap tableData = new HashMap();
    private XMLHandler fileData;
    private HashMap xlsfileData = new HashMap();
    private Boolean comparisonResult = false;

//    //**************************************************************************
//    //Constructor
//    //**************************************************************************
//    public validateExcelReport(String tablePath, String downloadPath) {
//        if (!isElementPresent(tablePath)) {
//            LOGGER.error("Unable to find results table!");
//            return;
//        }
//        if (!isElementPresent(downloadPath)) {
//            LOGGER.error("Unable to find Excel download link!");
//            return;
//        }
//        try {
//            readInTableData(tablePath);
//            readInExcelData(downloadPath);
//            compareTableAndFile();
//        } catch (Exception Ex) {
//            LOGGER.error("Error initialising object 'validateExcelReport': " + Ex);
//            return;
//        }
//    }
//
//    public validateExcelReport(String tablePath, String downloadPath, Boolean xls) {
//        if (!isElementPresent(tablePath)) {
//            LOGGER.error("Unable to find results table!");
//            return;
//        }
//        if (!isElementPresent(downloadPath)) {
//            LOGGER.error("Unable to find Excel download link!");
//            return;
//        }
//        try {
//            readInTableData(tablePath);
//            if (xls) {
//                readInXLSData(downloadPath);
//                //TODO Create a function to cmpare xls file with table.
//            } else {
//                readInExcelData(downloadPath);
//                compareTableAndFile();
//            }
//        } catch (Exception Ex) {
//            LOGGER.error("Error initialising object 'validateExcelReport': " + Ex);
//            return;
//        }
//
//    }
//
//    //**************************************************************************
//    //Getter
//    //**************************************************************************
//    /**
//     * Returns the result of the comparison performed between the table on screen adn the downloaded Excel file
//     *
//     * @author MPLC
//     *
//     * @return true if match, otherwise false
//     * @throws Exception
//     */
//    public Boolean getComparisonResult() throws Exception {
//        return comparisonResult;
//    }
//    //**************************************************************************
//    //Setter
//    //**************************************************************************
//    //**************************************************************************
//    //Functions
//    //**************************************************************************
//
//    /**
//     * This function will parse a table of results displayed on the current web page and load them into an array ready for comparison against and .xls file
//     *
//     * @author MPLC
//     *
//     * @param tableLocation The xpath location of the table of results we want to parse.
//     * @throws Exception
//     */
//    private void readInTableData(String tableLocation) throws Exception {
//        int numberOfTableColumns = getElementCount(tableLocation + "/thead/tr/th");
//        int numberOfTableRows = getElementCount(tableLocation + "/tbody/tr");
//        String columnName;
//        HashMap tableColumns = new HashMap();
//        // Make sure that tableData is clear before we start loading it
//        tableData.clear();
//        tableData.put(1, new HashMap());
//        for (int i = 1; i <= numberOfTableColumns; i++) {
//            columnName = driver.findElement(By.xpath(tableLocation + "/thead/tr/th[" + i + "]")).getText();
//            tableColumns.put(i, columnName);
//            ((HashMap) tableData.get(1)).put(i, columnName);
//        }
//        for (int i = 1; i <= numberOfTableRows; i++) {
//            tableData.put(i + 1, new HashMap());
//            Iterator columnNames = tableColumns.entrySet().iterator();
//            while (columnNames.hasNext()) {
//                Map.Entry columnData = (Map.Entry) columnNames.next();
//                ((HashMap) tableData.get(i + 1)).put(columnData.getKey(), driver.findElement(By.xpath(tableLocation + "/tbody/tr/td[" + columnData.getKey() + "]")).getText());
//            }
//        }
//    }
//
//    /**
//     * Download an excel file and read it in as XML,
//     *
//     * @author MPLC
//     *
//     * @param downloadPath the download link for the XML file (Not including the site domain)
//     * @throws Exception
//     */
//    private void readInExcelData(String downloadPath) throws Exception {
//        Calendar calendar = Calendar.getInstance();
//        fileDownloader excelDownload = new fileDownloader(settings.siteSubDomain(), settings.siteDomain());
//        String downloadLocation = driver.findElement(By.xpath(downloadPath)).getAttribute("href").replaceFirst("^/", "");
//        fileData = new XMLHandler(excelDownload.downloadFile(downloadLocation, "excelData" + settings.getEnvHash() + calendar.getTimeInMillis() + ".xls"));
////TODO still needed?        fileData.setXMLNamespace(true);
//        Object[] vErrors = {excelDownload};
//    }
//
//    /**
//     * Download an excel file and read it in as .xls
//     *
//     * @author MPLC
//     *
//     * @param downloadPath the download link for the XML file (Not including the site domain)
//     * @throws Exception
//     */
//    private void readInXLSData(String downloadPath) throws Exception {
//        Calendar calendar = Calendar.getInstance();
//        fileDownloader excelDownload = new fileDownloader(settings.siteSubDomain(), settings.siteDomain());
//        String downloadLocation = driver.findElement(By.xpath(downloadPath)).getAttribute("href").replaceFirst("^/", "");
//        String excelFile = excelDownload.downloadFile(downloadLocation, "excelData" + settings.getEnvHash() + calendar.getTimeInMillis() + ".xls");
//        //Workbook workbook = Workbook.getWorkbook(new File(excelFile));
//        //Sheet sheet = workbook.getSheet(0);
//        //int sheetColumns = sheet.getColumns();
//        //for (int i = 0; i < sheet.getRows(); i++) {
////            xlsfileData.put(i, new HashMap());
////            for (int n = 0; n < sheetColumns; n++) {
////                //Cell titleRow = sheet.getCell(n, 0);
////                //Cell currentCell = sheet.getCell(n, i);
////                ((HashMap) xlsfileData.get(i)).put(titleRow.getContents(), currentCell.getContents());
////            }
////        }
//        // workbook.close();
//        Object[] vErrors = {excelDownload};
//        //setVerificationErrors(vErrors);
//    }
//
//    /**
//     * This will compare the data in the excel file and the data displayed in the table displayed on the web page.
//     *
//     * @author MPLC
//     *
//     * @return true if data matches, otherwise false.
//     * @throws Exception
//     */
//    private void compareTableAndFile() throws Exception {
//        comparisonResult = null;
//        Iterator xmlFile = tableData.entrySet().iterator();
//        while (xmlFile.hasNext()) {
//            Map.Entry rowData = (Map.Entry) xmlFile.next();
//            Iterator columnData = ((HashMap) rowData.getValue()).entrySet().iterator();
//            while (columnData.hasNext()) {
//                Map.Entry cellData = (Map.Entry) columnData.next();
//                // For the below comparison multiple spaces are stripped out and replaced with a single space.
//                if (!cellData.getValue().toString().replaceAll("\\s+", "\\s").equals(fileData.xQuery("//ss:Table/ss:Row[" + rowData.getKey().toString() + "]/ss:Cell[" + cellData.getKey().toString() + "]/ss:Data").replaceAll("\\s+", "\\s"))) {
//                    LOGGER.error("Values do not match!!\nFile Data = '" + fileData.xQuery("//ss:Table/ss:Row[" + rowData.getKey().toString() + "]/ss:Cell[" + cellData.getKey().toString() + "]/ss:Data").replaceAll("\\s+", "\\s") + "'\nTable Data = '" + cellData.getValue().toString().replaceAll("\\s+", "\\s") + "'");
//                    comparisonResult = false;
//                }
//            }
//        }
//        if (comparisonResult == null) {
//            comparisonResult = true;
//        }
//    }
}
