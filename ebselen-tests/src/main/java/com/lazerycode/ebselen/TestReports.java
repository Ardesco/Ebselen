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
package com.lazerycode.ebselen;

import com.lazerycode.ebselen.handlers.FileHandler;
import com.lazerycode.ebselen.handlers.XMLHandler;
import com.lazerycode.ebselen.exceptions.InvalidReportFormatException;
import com.lazerycode.ebselen.SeleniumTestAnnotations.suiteStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class TestReports {

    public enum reportFormat {

        HTML, DOCX, ODF, DB
    }

    ResourceBundle _reporting = ResourceBundle.getBundle("ebselen");
    private static final Logger logger = LoggerFactory.getLogger(TestReports.class);

    // Configuration settings
    private String outputDirectory;
    private reportFormat format;
    private final URI htmlTestTemplate = this.getClass().getResource("/templates/reportTemplate.html").toURI();
    private final URI htmlCSSFile = this.getClass().getResource("/templates/testStyle.css").toURI();

    // Data used to create report
    private String testSuiteName;
    private long suiteRunTime;
    private suiteStatus testSuiteStatus;  // Not used in current revision, for CI really.
    private String testSuiteAuthor;
    private String[] associatedStories;
    private SortedMap<Integer, TestData> testData = new TreeMap<Integer, TestData>();

    public TestReports() throws Exception {
        this.outputDirectory = _reporting.getString("outputDirectory");
        setReportFormat(_reporting.getString("reportFormat"));
    }

    public final void setSuiteStatus(String value) {
        this.testSuiteStatus = suiteStatus.valueOf(value.toUpperCase());
    }

    public final void setSuiteStatus(suiteStatus value) {
        this.testSuiteStatus = value;
    }

    public void setAssociatedStories(String[] value) {
        this.associatedStories = value;
    }

    public void setTestSuiteAuthor(String value) {
        this.testSuiteAuthor = value;
    }

    public void setTestSuiteName(String value) {
        this.testSuiteName = value;
    }

    public void setTestSuiteTime(long value) {
        this.suiteRunTime = value;
    }

    public void addTestData(int order, TestData data) {
        this.testData.put(order, data);
    }

    public final void setReportFormat(String value) throws InvalidReportFormatException {
        try {
            this.format = reportFormat.valueOf(value.toUpperCase());
        } catch (Exception ex) {
            throw new InvalidReportFormatException("Report format '" + value + "' not recognised!");
        }
    }

    public final void setReportFormat(reportFormat value) {
        this.format = value;
    }

    public final void setOutputDirectory(String value) {
        this.outputDirectory = value;
    }

    /**
     * Format time as "x min, x sec"
     *
     * @param timestamp - Timestamp to format as a String
     * @return String - Formatted as "x min, x sec"
     */
    public String formattedTime(long timestamp) {
        return String.format("%d min(s), %d sec(s)", TimeUnit.MILLISECONDS.toMinutes(timestamp), TimeUnit.MILLISECONDS.toSeconds(timestamp) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timestamp)));
    }

    /**
     * Create test reports
     *
     * @throws Exception
     */
    public boolean createReport() {
        try {
            switch (this.format) {
                case HTML: {
                    createHTMLReport();
                    break;
                }
                case DOCX: {
                    createDOCXReport();
                    break;
                }
                case ODF: {
                    createODFReport();
                    break;
                }
                case DB: {
                    createODFReport();
                    break;
                }
            }
        } catch (Exception e) {
            logger.error("Failed to create report: " + e);
            return false;
        }
        return true;
    }

    /**
     * Create an DOCX format report of the test run
     */
    private void createDOCXReport() {
        logger.info("Not implemented yet!");
    }

    /**
     * Create an ODF format report of the test run
     */
    private void createODFReport() {
        logger.info("Not implemented yet!");
    }

    /**
     * Create an HTML format report of the test run
     *
     * @throws Exception
     */
    private void createHTMLReport() throws Exception {
        String overallResult = "Pass";
        XMLHandler testResults = new XMLHandler(new File(this.htmlTestTemplate));
        // Populate the "test name" field
        testResults.addTextToElement(this.testSuiteName, "//p[@id='name']/span");
        // Populate the "Author(s)" field
        testResults.addTextToElement(this.testSuiteAuthor, "//p[@id='author']/span");
        // Populate "stories covered" field
        String stories = "";
        for (String story : this.associatedStories) {
            stories = stories.concat(story).concat(", ");
        }
        stories = stories.trim().substring(0, stories.length() - 2);
        testResults.addTextToElement(stories, "//p[@id='story']/span");
        // Populate "total suite time" field
        testResults.addTextToElement(formattedTime(this.suiteRunTime), "//p[@id='time']/span");
        // Build the test results table
        Iterator test = this.testData.entrySet().iterator();
        int rowNumber = 0;
        while (test.hasNext()) {
            Map.Entry pairs = (Map.Entry) test.next();
            testResults.addChildElement("tr", "//table[@id='testResults']/tbody");
            rowNumber++;
            TestData individualTestResults = (TestData) pairs.getValue();
            testResults.addChildElement("td", "//table[@id='testResults']/tbody/tr[" + rowNumber + "]");
            testResults.addTextToElement(individualTestResults.getTestName(), "//table[@id='testResults']/tbody/tr[" + rowNumber + "]/td[1]");
            testResults.addChildElement("td", "//table[@id='testResults']/tbody/tr[" + rowNumber + "]");
            testResults.addTextToElement(Integer.valueOf(individualTestResults.getFailures()).toString(), "//table[@id='testResults']/tbody/tr[" + rowNumber + "]/td[2]");
            String result = "";
            if (individualTestResults.getFailures() == 0) {
                result = "Pass";
            } else {
                result = "Fail";
                overallResult = "Fail";
            }
            testResults.addAttribute("class", result.toLowerCase(), "//table[@id='testResults']/tbody/tr[" + rowNumber + "]");
            testResults.addChildElement("td", "//table[@id='testResults']/tbody/tr[" + rowNumber + "]");
            testResults.addTextToElement(result, "//table[@id='testResults']/tbody/tr[" + rowNumber + "]/td[3]");
            testResults.addChildElement("td", "//table[@id='testResults']/tbody/tr[" + rowNumber + "]");
            testResults.addTextToElement(formattedTime(individualTestResults.getTimeTaken()), "//table[@id='testResults']/tbody/tr[" + rowNumber + "]/td[4]");
        }
        // Populate the "overall result" field
        testResults.addTextToElement(overallResult, "//h2[@id='overallResult']/span");
        testResults.addAttribute("class", overallResult.toLowerCase(), "//h2[@id='overallResult']/span");
        // Print HTML results page location
        logger.info("Test report available at {}", testResults.writeXMLFile(this.outputDirectory + this.testSuiteName.concat(".html")));
        FileHandler cssStyle = new FileHandler(new File(this.htmlCSSFile));
        cssStyle.copyFileTo(this.outputDirectory + cssStyle.getFileName());
    }
}
